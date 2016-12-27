package com.wqj.zyupload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ZyuploadUtil {
	//原始文件名
	public static final String ORIGINAL_FILENAME = "originalFilename";
	//原始文件后缀
	public static final String ORIGINAL_FILE_EXT = "originalFileExt";
	//原始文件大小
	public static final String ORIGINAL_FILESIZE ="originalFileSize";
	//新文件名
	public static final String NEW_FILENAME = "newFilename";
	
	protected static String dirTemp = "upload/widget/temp";  
	   
	
	//文件拷贝
	public static List<Map<String, String>> upload(HttpServletRequest request){
		List<Map<String, String>> list = Lists.newArrayList();
		Map<String, String> map = null;
		
		try {
	        
			// 临时文件目录   
			//String tempPathBase = UUID.randomUUID().toString();
	        String tempPath = request.getSession().getServletContext().getRealPath("/") + dirTemp;
	        System.out.println("tempPath: " + tempPath);    
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
	        String ymd = sdf.format(new Date());  
	      
	        tempPath += "/" + ymd + "/";  
	        System.out.println("tempPath: " + tempPath);  
	        //创建临时文件夹  
	        File dirTempFile = new File(tempPath);  
	        if (!dirTempFile.exists()) {  
	            dirTempFile.mkdirs();  
	        }  
	        
            DiskFileItemFactory  factory = new DiskFileItemFactory();  
            factory.setSizeThreshold(20 * 1024 * 1024); //设定使用内存超过5M时，将产生临时文件并存储于临时目录中。     
            factory.setRepository(new File(tempPath)); //设定存储临时文件的目录。     
            ServletFileUpload upload = new ServletFileUpload(factory);  
            upload.setHeaderEncoding("UTF-8"); 
	        		
            List<FileItem> items = upload.parseRequest(request);  
            System.out.println("items = " + items);  
            Iterator<FileItem> itr = items.iterator();  
            String s ="";
            while (itr.hasNext()){  
                FileItem item = itr.next();  
                long fileSize = item.getSize(); 
                if(fileSize>0){
                	
                	map = Maps.newHashMap();
                	// 原始文件大小
    				map.put(ORIGINAL_FILESIZE, String.valueOf(fileSize));
    				System.out.println("原始文件大小：----------->>>>> fileSize: " + fileSize);
    				
    				// 原始文件名
        			String originalFilename =  item.getName();  
        			map.put(ORIGINAL_FILENAME, originalFilename);
        			System.out.println("原始文件名: " + originalFilename);
        			
        			String originalFileExtension = "";
        			if(originalFilename!=null){
        				if (originalFilename.equals(".")) {
        					originalFileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        					s = originalFileExtension;
        				} else {
        					originalFileExtension = originalFilename;
        				}
        				System.out.println("originalFileExtension:" + originalFileExtension);
        			}
    				
                	// 新文件名称
        			String newFilenameBase = UUID.randomUUID().toString();
        			String newFilename = newFilenameBase + s;//originalFileExtension;
        			map.put(NEW_FILENAME, newFilename);
        			System.out.println("新文件名称: " + newFilename);
                    
        			// 新文件存放的位置
    				String storageDirectory = "D:/work/fornow/";
    				File newFile = new File(storageDirectory + File.separator + newFilename);
    				if (!newFile.exists()) {
    					//makeDir(newFile.getParentFile());
    					newFile.getParentFile().mkdirs();  
    					newFile.createNewFile();
    				} 
    				System.out.println("新文件存放的位置: " + newFile.getAbsolutePath());
    				
    				
    				if (!item.isFormField()) { 
    					System.out.println("处理原文件................");
    					// 复制文件
        				//mpf.transferTo(newFile);
                        OutputStream os = new FileOutputStream(newFile);  
                        InputStream is = item.getInputStream();  
                        byte buf[] = new byte[1024];//可以修改 1024 以提高读取速度  
                        int length = 0;    
                        while( (length = is.read(buf)) > 0 ){    
                            os.write(buf, 0, length);    
                        }    
                        //关闭流    
                        os.flush();  
                        os.close();    
                        is.close();    
                        
                    }else {  
                        String filedName = item.getFieldName();  
                        System.out.println("处理裁剪后的文件................");
                        System.out.println(new String(item.getString().getBytes("iso-8859-1"),"utf-8"));  
                        System.out.println("FieldName："+filedName);  
                        // 获得裁剪部分的坐标和宽高
                        System.out.println("String："+item.getString());  
                    }  
    				list.add(map);
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
	/**
	 * Create package
	 * 
	 * @param dir
	 */
	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}
	
	
	
	public static void main(String[] args) {
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
            @Override  
            public void run() {  
            	System.out.println("************执行任务了**************");
            	timer.cancel();
            }  
        }, 1000*4);  
        
        System.out.println("main 方法执行完了哦....");
	}
	
}
