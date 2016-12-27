package com.wqj.jqueryFileUpload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wqj.common.context.SystemPropertyInit;
import com.wqj.common.util.JSONHelper;
import com.wqj.jqueryFileUpload.entity.BaseImageEntity;

public class JQueryFileUploadUtil {
	public static final String UPLOAD_PATH = "upload/";

	//原始文件名
	public static final String ORIGINAL_FILENAME = "originalFilename";
	//原始文件后缀
	public static final String ORIGINAL_FILE_EXT = "originalFileExt";
	//原始文件大小
	public static final String ORIGINAL_FILESIZE ="originalFileSize";

	//新文件名
	public static final String NEW_FILENAME = "newFilename";

	public static final String REAL_NAME = "realName";
	public static final String OLD_MAP = "oldMap";
	public static final String NEW_MAP = "newMap";
	public static final String WIDTH = "width";
	public static final String HEIGTH = "heigth";
	public static final String UNDERLINE = "_";
	public static final String SEPARATOR = "/";
	public static final String JPG =".jpg";


	//文件拷贝
	public static List<Map<String, String>> upload(MultipartFile[] files){
		List<Map<String, String>> list = Lists.newArrayList();
		Map<String, String> map = null;
		try {
			for (MultipartFile file : files) {
				long size = file.getSize();
				if(size>0){
					map = Maps.newHashMap();

					// 原始文件名
					String originalFilename = file.getOriginalFilename();
					map.put(ORIGINAL_FILENAME, originalFilename);
					System.out.println("原始文件名: " + originalFilename);

					// 原始文件大小
					map.put(ORIGINAL_FILESIZE, String.valueOf(size));

					//原始文件后缀
					String originalFileExt = "." + StringUtils.getFilenameExtension(originalFilename);
					map.put(ORIGINAL_FILE_EXT, originalFileExt);

					System.out.println("originalFilename.equals(\".\")=======>>>>>> " + originalFilename.equals("."));
					String originalFileExtension = "";
					if (originalFilename.equals(".")) {
						originalFileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
					} else {
						originalFileExtension = originalFilename;
					}
					System.out.println("originalFileExtension:" + originalFileExtension);

					// 新文件名称
					String newFilenameBase = UUID.randomUUID().toString();
					String newFilename = newFilenameBase + originalFileExtension;
					map.put(NEW_FILENAME, newFilename);
					System.out.println("新文件名称: " + newFilename);

					// 新文件存放的位置
					String storageDirectory = SystemPropertyInit.getInstance().getProperty("file.path");

					File newFile = new File(storageDirectory + File.separator + newFilename);
					if (!newFile.exists()) {
						makeDir(newFile.getParentFile());
						newFile.createNewFile();
					} 
					System.out.println("新文件存放的位置: " + newFile.getAbsolutePath());

					// 复制文件
					file.transferTo(newFile);

					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//文件拷贝
	public static List<Map<String, String>> upload(HttpServletRequest request){
		List<Map<String, String>> list = Lists.newArrayList();
		Map<String, String> map = null;
		try {
			MultipartFile mpf;
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				Iterator<String> itr = multipartRequest.getFileNames();
				while (itr.hasNext()) {
					mpf = multipartRequest.getFile(itr.next());

					long size = mpf.getSize();
					if(size>0){
						map = Maps.newHashMap();

						// 原始文件名
						String originalFilename = mpf.getOriginalFilename();
						map.put(ORIGINAL_FILENAME, originalFilename);
						System.out.println("原始文件名: " + originalFilename);

						// 原始文件大小
						map.put(ORIGINAL_FILESIZE, String.valueOf(size));

						//原始文件后缀
						String originalFileExt = "." + StringUtils.getFilenameExtension(originalFilename);
						map.put(ORIGINAL_FILE_EXT, originalFileExt);

						System.out.println("originalFilename.equals(\".\")=======>>>>>> " + originalFilename.equals("."));
						String originalFileExtension = "";
						if (originalFilename.equals(".")) {
							originalFileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
						} else {
							originalFileExtension = originalFilename;
						}
						System.out.println("originalFileExtension:" + originalFileExtension);

						// 新文件名称
						String newFilenameBase = UUID.randomUUID().toString();
						String newFilename = newFilenameBase + originalFileExtension;
						map.put(NEW_FILENAME, newFilename);
						System.out.println("新文件名称: " + newFilename);

						// 新文件存放的位置
						String storageDirectory = SystemPropertyInit.getInstance().getProperty("file.path");

						File newFile = new File(storageDirectory + File.separator + newFilename);
						if (!newFile.exists()) {
							makeDir(newFile.getParentFile());
							newFile.createNewFile();
						} 
						System.out.println("新文件存放的位置: " + newFile.getAbsolutePath());

						// 复制文件
						mpf.transferTo(newFile);

						list.add(map);
					}else{
						System.out.println("file size le zero ...");
					}
				}
			}else{
				System.out.println("request instanceof MultipartHttpServletRequest is false... ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//删除磁盘上的图片
	public static String deleteImage(BaseImageEntity baseImageEntity) throws Exception{
		String filePath = SystemPropertyInit.getInstance().getProperty("file.path");
		// 需要删除的图片
		File imageFile = new File(filePath + baseImageEntity.getNewFilename());
		//删除操作
		imageFile.delete();

		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		Map<String, Object> success = new HashMap<String, Object>();
		success.put("success", true);
		results.add(success);

		return JSONHelper.toJson(results);
	}

	//预览图片
	public static void previewImage(HttpServletResponse response,
			BaseImageEntity baseImageEntity) throws Exception{
		String filePath = SystemPropertyInit.getInstance().getProperty("file.path");
		File imageFile = new File(filePath + baseImageEntity.getNewFilename());
		InputStream inputStream = new FileInputStream(imageFile);
		IOUtils.copy(inputStream, response.getOutputStream()); 
	}


	//加载已存在的图片
	public static String loadImage(List<?> imageList){
		Map<String, Object> files = Maps.newHashMap();
		files.put("files", imageList);
		return JSONHelper.toJson(files);
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
