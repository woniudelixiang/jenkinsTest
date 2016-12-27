package com.wqj.jqueryFileUpload.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqj.common.controller.CommonController;
import com.wqj.jqueryFileUpload.entity.Image;
import com.wqj.jqueryFileUpload.entity.ImgUser;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/jfuc")
public class JQueryFileUploadController extends CommonController {

	// 跳转到图片上传页面
	@RequestMapping("/list")
	public String jqueryFileUpload() {
		return "jqueryFileUpload";
	}
	
	// 加载已存在的图片
	@RequestMapping(value = "/load/{fkId}", method = RequestMethod.GET)
	@ResponseBody
	public String list(@PathVariable long fkId) {
		//查询出需呀加载的图片
		List<Image> imageList = imageService.findDatas("t.userId", fkId);
		return JQueryFileUploadUtil.loadImage(imageList);
	}
	
	
	// 加载已存在的图片
//	@RequestMapping(value = "/load", method = RequestMethod.GET)
//	@ResponseBody
//	public String list() {
//		System.out.println("加载已存在的图片...........");
//		List<Image> imageList = imageService.findAll();
//		Map<String, Object> files = new HashMap<String, Object>();
//		files.put("files", imageList);
//		System.out.println("Returning: {}" + JSONHelper.toJson(files));
//		return JSONHelper.toJson(files);
//	}

	
	//删除图片
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable long id) {
		Image image = imageService.get(id);
		String results = "";
		try {
			//删除数据库图片
//			deleteDatabaseImage(image);
			//删除磁盘上的文件
			results = JQueryFileUploadUtil.deleteImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	
	
	//删除图片
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//	@ResponseBody
//	public String delete(HttpServletRequest request, @PathVariable long id) {
//		System.out.println("*********  删除图片图片！  *************");
//		
//		Image image = imageService.get(id); 
//		// 删除文件
//		File imageFile = new File("D:/work/fornow/" + image.getNewFilename()); 
//		imageFile.delete(); 
//		// 删除数据库 
//		imageService.delete(id);
//		 
//		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
//		Map<String, Object> success = new HashMap<String, Object>();
//		success.put("success", true);
//		results.add(success);
//
//		return JSONHelper.toJson(results);
//	}
	
	//文件预览
	@RequestMapping(value = "/thumbnail/{id}", method = RequestMethod.GET)
	public void thumbnail(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		try {
			Image image = imageService.get(id);
			JQueryFileUploadUtil.previewImage(response, image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*//文件预览
	@RequestMapping(value = "/thumbnail/{id}", method = RequestMethod.GET)
	public void thumbnail(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>**************   thumbnail   ***************"+id);
		Image image = imageService.get(id);
		File imageFile = new File("D:/work/fornow/"+image.getNewFilename());
		try {
			InputStream is = new FileInputStream(imageFile);
			IOUtils.copy(is, response.getOutputStream());
		} catch (IOException e) {
			System.out.println("Could not show thumbnail ");
		}
	}*/

	
	//文件上传
	@SuppressWarnings("unchecked")
	@RequestMapping("/toUpload")
	@ResponseBody
	public String jqueryFileUpload(HttpServletRequest request, HttpServletResponse response,ImgUser user) {
		List<Image> imageList = null;
		try {
			System.out.println("userId: "+user.getUserId());
			//保存文本信息
			imgUserService.saveOrUpdate(user);
			//拷贝上传的文件
			List<Map<String, String>> uploadList = JQueryFileUploadUtil.upload(request);
			//将文件信息保存到数据库
//			imageList = saveUploadFileToDatabase(uploadList, Image.class, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JQueryFileUploadUtil.loadImage(imageList);
	}
	
	/*//文件上传
	@SuppressWarnings("unchecked")
	@RequestMapping("/toUpload")
	@ResponseBody
	public String jqueryFileUpload(HttpServletRequest request, HttpServletResponse response,ImgUser user) {
		Map<String, Object> files = new HashMap<String, Object>();
		try {
			//保存文本信息
			imgUserService.save(user);
			//拷贝上传的文件
			List<Map<String, String>> uploadList = JQueryFileUploadUtil.upload(request);
			//将文件信息保存到数据库
			List<Image> list = saveUploadFileToDatabase(uploadList, Image.class, user);
			files.put("files", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONHelper.toJson(files);
	}*/
		
	
	
	
	//文件上传
/*	@RequestMapping("/toUpload")
	@ResponseBody
	public String jqueryFileUpload(HttpServletRequest request, HttpServletResponse response,ImgUser user) {
		//保存文本信息
		imgUserService.save(user);
		
		List<Image> list = new LinkedList<Image>();
		List<Map<String, String>> uploadList = JQueryFileUploadUtil.upload(request);
	
		if(ValidateUtils.isNotEmpty(uploadList)){
			Image image = null;
			for (Map<String, String> map : uploadList) {
				image = new Image();
				image.setSize(Long.valueOf(map.get(JQueryFileUploadUtil.ORIGINAL_FILESIZE)));
				image.setName(map.get(JQueryFileUploadUtil.ORIGINAL_FILENAME));
				image.setNewFilename(map.get(JQueryFileUploadUtil.NEW_FILENAME));
				//弹出层展示
				image.setUrl("jfuc/thumbnail");
				//预览
				image.setThumbnailUrl("jfuc/thumbnail");
				//删除
				image.setDeleteUrl("jfuc/delete");
				image.setDeleteType("DELETE");
				imageService.save(image);
				list.add(image);
			}
		}
		
		Map<String, Object> files = new HashMap<String, Object>();
		files.put("files", list);
		return JSONHelper.toJson(files);
	}
	*/
		
		
		
		
/*	//文件上传
	@RequestMapping("/toUpload")
	@ResponseBody
	public String jqueryFileUpload(HttpServletRequest request, HttpServletResponse response,ImgUser user) {
		System.out.println("-----------------------  toUpload   ------------------------------");
		//保存文本信息
		
		System.out.println(user);
		
		MultipartFile mpf;
		List<Image> list = new LinkedList<Image>();

		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> itr = multipartRequest.getFileNames();
			while (itr.hasNext()) {
				mpf = multipartRequest.getFile(itr.next());
				String originalFileExtension = "";
				if (mpf.getOriginalFilename().equals(".")) {
					originalFileExtension = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
				} else {
					originalFileExtension = mpf.getOriginalFilename();
				}
				
				System.out.println("originalFileExtension:" + originalFileExtension);
				String newFilenameBase = UUID.randomUUID().toString();
				String newFilename = newFilenameBase + originalFileExtension;// 新文件名称
				String storageDirectory = "D:/work/fornow/";// 绝对路径
				File newFile = new File(storageDirectory + File.separator + newFilename);
				try {
					// 复制文件
					mpf.transferTo(newFile);
					
					// 大图
//					BufferedImage thumbnailBig = Scalr.resize(ImageIO.read(newFile), Scalr.Method.SPEED,Scalr.Mode.FIT_TO_WIDTH, 600, 600,Scalr.OP_ANTIALIAS);// 生产缩略图
//					String thumbnailFilenameBig = newFilenameBase + "-big.png";
//					File thumbnailFileBig = new File(storageDirectory + File.separator + thumbnailFilenameBig);
//					ImageIO.write(thumbnailBig, "png", thumbnailFileBig);

					// 小图
//					BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), Scalr.Method.SPEED,Scalr.Mode.FIT_TO_WIDTH, 100, 100,Scalr.OP_ANTIALIAS);// 生产缩略图
//					String thumbnailFilename = newFilenameBase + "-thumbnail.png";
//					File thumbnailFile = new File(storageDirectory + File.separator + thumbnailFilename);
//					ImageIO.write(thumbnail, "png", thumbnailFile);

					Image image = new Image();
					image.setSize(mpf.getSize());
					image.setName(mpf.getOriginalFilename());
//					image.setThumbnailFilename(thumbnailFilename);
					image.setNewFilename(newFilename);// 大图
					//弹出层展示
					image.setUrl("jfuc/thumbnail");
					//预览
					image.setThumbnailUrl("jfuc/thumbnail");
					//删除
					image.setDeleteUrl("jfuc/delete");
					image.setDeleteType("DELETE");
					imageService.save(image);
					
					list.add(image);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		Map<String, Object> files = new HashMap<String, Object>();
		files.put("files", list);
		return JSONHelper.toJson(files);
	}*/
}
