package com.wqj.image.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

import org.joda.time.LocalDate;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wqj.common.context.SystemPropertyInit;
import com.wqj.common.util.ValidateUtils;

import jersey.repackaged.com.google.common.collect.Maps;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	public final static Integer WIDTH = 300;  
	public final static Integer HEIGHT = 300;
	
	// 文件大小
	public static final String FILE_SIZE ="originalFileSize";
	// 文件名
	public static final String FILE_NAME = "fileName";
	// 文件后缀
	public static final String FILE_EXT = "fileExt";
	
	// 文件上传标识
	public static final String UPLOAD_PATH = "upload/";
	// 文件的相对路径
	public static final String RELATIVE_PATH = "relativePath";
	// 文件的绝对路径
	public static final String WHOLE_PATH = "wholePath";
	// 斜杠
	public static final String SEPARATOR = "/";
	// 点
	public static final String POINT = ".";
	
	
	/**
	 * 上传原图
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> original(CommonsMultipartFile file) throws Exception{
		Map<String, String> fileMap = getFileInfo(file);
		if(ValidateUtils.isEmpty(fileMap)){
			return null;
		}
		
		// 文件输入流
		InputStream is = file.getInputStream();
		byte[] data = new byte[Integer.parseInt(fileMap.get(FILE_SIZE))];
		is.read(data);  // 读入文件

		OutputStream os = getOutputStream(fileMap.get(WHOLE_PATH));
		os.write(data);  // 写出文件

		// 关闭流
		is.close();
		os.close();
		return fileMap;
	}
	
	/**
	 * 缩放
	 * @param file
	 * @param width
	 * @param height
	 * @param isRatio  true按照指定的宽或高比例进行缩放，   false安装指定的大小进行缩放
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> compress(CommonsMultipartFile file,int width,int height,boolean isRatio) throws Exception {
		Map<String, String> fileMap = getFileInfo(file);
		if(ValidateUtils.isEmpty(fileMap) || width<=0 || height<=0){
			return null;
		}
		// keepAspectRatio(false)表示安装指定大小进行缩放  ，默认是按照比例缩放的
		Thumbnails.of(file.getInputStream()).size(width, height).keepAspectRatio(isRatio).toFile(fileMap.get(WHOLE_PATH));
		return fileMap;
	}
	
	/**
	 *  在中心点裁剪
	 */
	public static Map<String, String> tailor(CommonsMultipartFile file,int width,int height) throws Exception {
		Map<String, String> fileMap = getFileInfo(file);
		// sourceRegion(Positions.CENTER, width, height) 在原图片的中心width*height区域进行裁剪
		Thumbnails.of(file.getInputStream()).size(width, height).keepAspectRatio(false).sourceRegion(Positions.CENTER, width, height).toFile(fileMap.get(WHOLE_PATH));
		return fileMap;
	}

	/**
	 * 旋转   
	 * @param file
	 * @param angle 正数：顺时针 负数：逆时针
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> rotate(CommonsMultipartFile file,double angle) throws Exception {
		Map<String, String> fileMap = getFileInfo(file);
		if(ValidateUtils.isEmpty(fileMap)){
			return null;
		}
		Thumbnails.of(file.getInputStream()).rotate(angle).toFile(fileMap.get(WHOLE_PATH));
		return fileMap;
	}
	
	/**
	 * 先裁剪后压缩
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> cutCompress(CommonsMultipartFile file,int width,int height) throws Exception {
		Map<String, String> fileMap = getFileInfo(file);
		if(ValidateUtils.isEmpty(fileMap) || width<=0 || height<=0){
			return null;
		}
		BufferedImage image = Thumbnails.of(file.getInputStream()).scale(1.0f).asBufferedImage();  
		
		Builder<BufferedImage> builder = null;  
		  
		int imageWidth = image.getWidth();  
		int imageHeitht = image.getHeight();  
		if ((float)height / width != (float)imageWidth / imageHeitht) {  
		    if (imageWidth > imageHeitht) {  
		        image = Thumbnails.of(file.getInputStream()).height(height).asBufferedImage();  
		    } else {  
		        image = Thumbnails.of(file.getInputStream()).width(width).asBufferedImage();  
		    }  
		    builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height).size(width, height);  
		} else {  
		    builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height).size(width, height);  
		}  
		
		builder.toFile(fileMap.get(WHOLE_PATH)); 
		return fileMap;
	}
	
	//水印
	//watermark(位置，水印图，透明度)  
	/*Thumbnails.of("D:\\fen.jpg")   
	        .size(1280, 1024)  
	        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("D:\\logo.png")), 0.5f)   
	        .outputQuality(0.8f)   
	        .toFile("D:\\fen_watermark.jpg");  */
	
	
	//===============================================   helper  ======================================================================

	/**
	 * 获取上传文件的相关信息
	 * @param file
	 * @return
	 */
	public static Map<String, String> getFileInfo(CommonsMultipartFile file){
		if(ValidateUtils.isEmpty(file)){
			return null;
		}
		Map<String, String> map = null;
		Long fileSize = file.getSize();  // 文件大小
		if(fileSize > 0){
			map = Maps.newHashMap();  //保存文件的相关信息
			map.put(FILE_SIZE, fileSize.toString());   //文件大小
			
			String originalFilename = file.getOriginalFilename();
			map.put(FILE_NAME, originalFilename);  // 文件名
			
			String fileExt = new StringBuffer(POINT).append(StringUtils.getFilenameExtension(originalFilename)).toString();
			map.put(FILE_EXT, fileExt);  //原始文件后缀
			
			String relativePath = new StringBuffer(getWholeFileName()).append(fileExt).toString();   //文件的相对路径
			map.put(RELATIVE_PATH, relativePath);  //文件的相对路径
			
			String serverPath = SystemPropertyInit.getInstance().getProperty("file.path");   //文件根路径
			String wholePath = new StringBuffer(serverPath).append(relativePath).toString();        // 文件的完整路径
			
			
			map.put(WHOLE_PATH, wholePath);  //文件的相对路径
		}
		return map;
	}
	
	public static String getWholeFileName() {
		StringBuffer sb = new StringBuffer(UPLOAD_PATH);
		sb.append(todayToString()).append(SEPARATOR)
				.append(UUID.randomUUID());
		return sb.toString();
	}
	
	public static String todayToString() {
		return new LocalDate().toString("yyMMdd");
	}
	
	public static OutputStream getOutputStream(String path) throws Exception{
		File newFile = new File(path);
		if (!newFile.exists()) {
			makeDir(newFile.getParentFile());
			newFile.createNewFile();
		} 
		OutputStream os = new FileOutputStream(newFile);
		return os;
	}
	
	/**
	 * Create package
	 * @param dir
	 */
	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}
	
	
}
