/*****************************************************************************
 *
 *                      FORNOW PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to ForNow
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from ForNow.
 *
 *            Copyright (c) 2014 by ForNow.  All rights reserved.
 *
 *****************************************************************************/
package com.wqj.common.util;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.wqj.common.context.SystemPropertyInit;


/**
 * @author Jiafa Lv
 * @date Apr 21, 2014 2:11:40 PM
 * @email simon-jiafa@126.com
 * 
 */
@Component
public class UploadHelper {
//	public static final String REAL_PATH = "D:/work/fornow/";
	public static final String UPLOAD_PATH = "upload/";
	public static final String FILE_NAME = "fileName";
	public static final String REAL_NAME = "realName";
	public static final String FILE_EXT = "fileExt";
	public static final String OLD_MAP = "oldMap";
	public static final String NEW_MAP = "newMap";
	public static final String WIDTH = "width";
	public static final String HEIGTH = "heigth";
	public static final String UNDERLINE = "_";
	public static final String SEPARATOR = "/";

	public static final String JPG =".jpg";
	private static final String CLASS_NAME = UploadHelper.class.getName();
	public static final String QRCODE_PATH = "qrcode/";
	
	/**
	 * 
	 * @param request
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getFileNameByUpload(
			HttpServletRequest request, String inputFileName) throws Exception {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			LoggerUtils.debug(CLASS_NAME, "Spring3 MVC upload file with Multipart form");

			MultipartFile file = multipartRequest.getFiles(inputFileName).get(0);
			long size = file.getSize();
			if(size>0){
				Map<String, String> map = new HashMap<String, String>();
				byte[] data = new byte[(int) size];
				InputStream input = file.getInputStream();
				input.read(data);

				// create file, if no app context path, will throws access denied.
				// seems like you could not create any file at tomcat/bin
				// directory!!!

				LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> " + file.getContentType());
				String fileName = getWholeFileName();
				map.put(FILE_NAME, fileName);
				LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
				String fileExt = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
				map.put(FILE_EXT, fileExt);
				LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
				String wholeFileName = SystemPropertyInit.getInstance().getProperty("file.path") + fileName + fileExt;
				LoggerUtils.debug(CLASS_NAME, "file WholeName ==> " + wholeFileName);
				File outFile = new File(wholeFileName);

				if (!outFile.exists()) {
					makeDir(outFile.getParentFile());
					outFile.createNewFile();
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				} else {
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				}
				FileOutputStream outStream = new FileOutputStream(outFile);

				outStream.write(data);
				outStream.close();
				input.close();
				
				String newFileName = getWholeFileName();
				map.put(FILE_NAME, newFileName);
				String newWholeFileName = SystemPropertyInit.getInstance().getProperty("file.path") + newFileName + fileExt;
				save640X420Image(wholeFileName, newWholeFileName);
				
				// delete oldFileName
				isSucc4DelFile(wholeFileName);
				
				return map;
			}
		}
		
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @param request
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Map<String,String>> getFileNameByUploadToCompress(
			HttpServletRequest request, String inputFileName,int isCompress,int wantWidth) throws Exception {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			LoggerUtils.debug(CLASS_NAME, "Spring3 MVC upload file with Multipart form");

			MultipartFile file = multipartRequest.getFiles(inputFileName).get(0);
			long size = file.getSize();
			
			if(size>0){
				String realName = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
				Map<String, Map<String,String>> map = new HashMap<String, Map<String,String>>();
				byte[] data = new byte[(int) size];
				InputStream input = file.getInputStream();
				input.read(data);

				LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> " + file.getContentType());
				String fileName = getWholeFileName();
				LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
				String fileExt = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
				
				LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
				String wholeFileName = SystemPropertyInit.getInstance().getProperty("file.path") + fileName + fileExt;
				LoggerUtils.debug(CLASS_NAME, "file WholeName ==> " + wholeFileName);
				File outFile = new File(wholeFileName);

				if (!outFile.exists()) {
					makeDir(outFile.getParentFile());
					outFile.createNewFile();
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				} else {
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				}
				FileOutputStream outStream = new FileOutputStream(outFile);
				outStream.write(data);
				
				Map<String,String> oldMap = new HashMap<String, String>();
				oldMap.put(FILE_EXT, fileExt);
				oldMap.put(FILE_NAME, fileName);
				oldMap.put(REAL_NAME,realName);
				InputStream inputStream = new FileInputStream(outFile);
				Image src1 = ImageIO.read(inputStream);
				int width = src1.getWidth(null);
				int height = src1.getHeight(null);
				oldMap.put(WIDTH, ""+width);
				oldMap.put(HEIGTH, ""+height);
				map.put(OLD_MAP, oldMap);
				outStream.close();
				input.close();
				inputStream.close();
				// new Image
				String newFileName = getWholeFileName();
				//Image路径名
				Map<String,String> newMap = new HashMap<String, String>();
				
				newMap = compressOrShear(new File(wholeFileName), newFileName,fileExt, isCompress, wantWidth);
				if(newMap!=null){
					newMap.put(REAL_NAME,realName);
				}
				map.put(NEW_MAP, newMap);
				return map;
			}
		}
		
		return null;
	}
	/**
	 * 
	 * @param input
	 * @param outStream
	 * @param Picum
	 */
	public static Map<String,String> compressOrShear(File file,String newFileName,String fileExt, int isCompress,int wantWidth ){
		Map<String,String> newMap = new HashMap<String, String>();
		Image src;
		try {
			InputStream inputStream = new FileInputStream(file);
			src = ImageIO.read(inputStream);
			float width = (float) (src.getWidth(null) );
			float height = (float) (src.getHeight(null));
			float scale = 1.0f;
			int widthInt = 0;
			int heightInt= 0;
			if(isCompress==3){
				newFileName = getWholeFileName();
			}
			//等比缩放
			String newWholeFileName = SystemPropertyInit.getInstance().getProperty("file.path") + newFileName + fileExt;
			File outFile = new File(newWholeFileName);
			int swantWidth = wantWidth;
			if(isCompress!=2){
				scale = getScale(width, height, wantWidth);
				if(scale==1.0f){
					return null;
				}
				FileOutputStream outStream = new FileOutputStream(outFile);
				widthInt = (int)(width*scale);
				heightInt = (int)(height*scale);
				ImageUtils.scaleImage(outStream, src, widthInt, heightInt);
			}else if(isCompress==2){
				//剪切
				if(width==wantWidth&&height==wantWidth){
					return null;
				}
				//复制剪切图片
				File cutFile = getCopyFile(file,outFile);
				int x,y=0;
				boolean isBig = true;
				//需要的长度X坐标
				x = Math.abs((int) ((width-height)/2));
				if(width>wantWidth&&height>wantWidth){
					if(width>=height){
						wantWidth =(int) height;
					}else{
						y = x;
						x = 0;
						wantWidth = (int)width;
					}
				}else{
					isBig= false;
					if(width > height){
						wantWidth = (int)height;
					}else{
						y = x;
						x = 0;
						wantWidth = (int)width;
					}
				}
				if(x==0&&y==0){
					
				}else{
					if(!JPG.equals(fileExt)){
						fileExt = JPG;
						File jpgFile = ImageUtils.imgToJPG(cutFile, new File(newWholeFileName.substring(0, newWholeFileName.lastIndexOf("."))+fileExt));
						cutFile.delete();
						cutFile = jpgFile;
					}
					ImageUtils.cutImage(cutFile, x,y,wantWidth,wantWidth);
				}
				if(isBig){
					newMap =  compressOrShear(cutFile, newFileName, fileExt, 3, swantWidth);
					cutFile.delete();
					inputStream.close();
					return newMap;
				}
			}
			newMap.put(WIDTH, ""+widthInt);
			newMap.put(HEIGTH, ""+heightInt);
			newMap.put(FILE_NAME, newFileName);
			newMap.put(FILE_EXT, fileExt);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newMap;
	}
	
	public static File getCopyFile(File file,File outFile) throws Exception{
		InputStream inputStream = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		inputStream.read(data);
		if (!outFile.exists()) {
			makeDir(outFile.getParentFile());
			outFile.createNewFile();
			LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
		} else {
			LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
		}
		FileOutputStream outStream = new FileOutputStream(outFile);
		outStream.write(data);
		inputStream.close();
		outStream.close();
		return outFile;
	}
	
	
	public static float getScale(float width,float height,float wantWidth){
		float scale =  1.0f;
		wantWidth = (float)wantWidth;
		if(width<= wantWidth&&height<=wantWidth){
			scale = 1.0f;
		}else if(width>wantWidth&&height<=wantWidth){
			scale = wantWidth/width;
		}else if(height>wantWidth&&width<= wantWidth){
			scale = wantWidth/height;
		}else{
			if(width>=height){
				scale = wantWidth/width;
			}else{
				scale = wantWidth/height;
			}
		}
		return  scale;
	}
	
	/**
	 * 
	 * @param request
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getNameByUpload(
			HttpServletRequest request, String inputFileName) throws Exception {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			LoggerUtils.debug(CLASS_NAME, "Spring3 MVC upload file with Multipart form");
			
			MultipartFile file = multipartRequest.getFiles(inputFileName)
					.get(0);
			
			long size = file.getSize();
			if(size>0){
				String realName = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
				Map<String, String> map = new HashMap<String, String>();
				byte[] data = new byte[(int) size];
				InputStream input = file.getInputStream();
				input.read(data);
				LoggerUtils.debug(CLASS_NAME, "file.getContentType() ==> " + file.getContentType());
				String fileName = getWholeFileName();
				map.put(FILE_NAME, fileName);
				LoggerUtils.debug(CLASS_NAME, "fileName ==> " + fileName);
				String fileExt = "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
				map.put(FILE_EXT, fileExt);
				LoggerUtils.debug(CLASS_NAME, "fileExt ==> " + fileExt);
				String wholeFileName = SystemPropertyInit.getInstance().getProperty("file.path") + fileName + fileExt;
				LoggerUtils.debug(CLASS_NAME, "file WholeName ==> " + wholeFileName);
				File outFile = new File(wholeFileName);
				map.put(REAL_NAME, realName);
				if (!outFile.exists()) {
					makeDir(outFile.getParentFile());
					outFile.createNewFile();
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				} else {
					LoggerUtils.debug(CLASS_NAME, "full path = " + outFile.getAbsolutePath());
				}
				FileOutputStream outStream = new FileOutputStream(outFile);
				
				outStream.write(data);
				outStream.close();
				input.close();
				return map;
			}
		}
		
		return null;
	}

	/**
	 * Save image as 640X420 size
	 * 
	 * @param realDirPath
	 * @param fileExt
	 * @throws Exception
	 */
	public static void save640X420Image(String realDirPath, String newFilePath)
			throws Exception {
		ImageUtils.saveImageAsJpg(realDirPath, newFilePath,
				ImageUtils.MEDIUM_WIDTH, ImageUtils.MEDIUM_HEIGHT);
	}
	
	/**
	 * Save image as 640X420 size
	 * 
	 * @param realDirPath
	 * @param fileExt
	 * @throws Exception
	 */
	public static void saveTopicImage(String realDirPath, String newFilePath)
			throws Exception {
		ImageUtils.saveImageAsJpg(realDirPath, newFilePath,
				ImageUtils.MEDIUM_WIDTH, ImageUtils.MEDIUM_HEIGHT);
	}
	

	/**
	 * Get the whole file name
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getWholeFileName() {
		StringBuffer sb = new StringBuffer(UPLOAD_PATH);
		sb.append(todayToString()).append(SEPARATOR)
				.append(UUID.randomUUID());

		return sb.toString();
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean isSucc4DelFile(String sPath) {
		boolean flag = false;
		LoggerUtils.debug(CLASS_NAME, sPath);
		if(isNoEmpty(sPath)){
			File file = new File(sPath);
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				LoggerUtils.debug(CLASS_NAME, "File is delete successfully!");
				file.delete();
				flag = true;
			}
		}
		
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(SEPARATOR)) {
			sPath = sPath + SEPARATOR;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = isSucc4DelFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
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
	
	public static String todayToString() {
		return new LocalDate().toString("yyMMdd");
	}
	
	public static boolean isNoEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		}

		return false;
	}
	
	
	/**
	 * 二维码生成路径
	 * 
	 * @return
	 */
	public static String getQRCodeWholeFileName() {
		StringBuffer sb = new StringBuffer(QRCODE_PATH);
		sb.append(DateUtils.todayToString()).append(SEPARATOR)
		.append(UUID.randomUUID());

		return sb.toString();
	}
}
