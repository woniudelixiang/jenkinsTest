package com.wqj.jqueryFileUpload.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.wqj.common.controller.CommonController;
import com.wqj.jqueryFileUpload.entity.Image;
import com.wqj.jqueryFileUpload.entity.ImgUser;

@Controller
@RequestMapping("/jfujc")
public class JQueryFileUploadJsonController  extends CommonController{
	
	// 跳转主页面
	@RequestMapping("/main")
	public String main(HttpServletRequest request) {
		ImgUser imgUser = imgUserService.get(2L);
		request.setAttribute("imgUser", imgUser);
		return "main";
	}
	
	// 跳转到图片上传页面
	@RequestMapping("/add/{id}")
	public String jqueryFileUpload(HttpServletRequest request,@PathVariable Long id) {
		ImgUser imgUser = imgUserService.get(id);
		request.setAttribute("imgUser", imgUser);
		return "jqueryFileUploadJson";
	}
	
	// 加载已存在的图片
	@ResponseBody
	@RequestMapping(value = "/load/{fkId}", method = RequestMethod.GET)
	public String list(@PathVariable long fkId) {
		System.out.println("***********************************  加载已存在的图片  ******************************************" + fkId);
		//查询出需呀加载的图片
		List<Image> imageList = imageService.findDatas("user.userId", fkId);
		//添加附加信息
		imageList = addImagesInfo(imageList);
		return JQueryFileUploadUtil.loadImage(imageList);
	}
	
	//文件上传
	@ResponseBody
	@RequestMapping(value="/toUpload", method = RequestMethod.POST) /* @RequestBody ImgUser user,*/
	public String jqueryFileUpload(@RequestParam("files") MultipartFile[] files, String param1) {
		List<Image> imageList = Lists.newArrayList();
		System.out.println("***********************************  文件上传  ******************************************param1: " + param1);
		try {
//			System.out.println("userId: "+user.getUserId() + " ,  user: " + user);
//			ImgUser imgUser = imgUserService.get(user.getUserId());
			ImgUser imgUser = imgUserService.get(2L);
//			imgUser.setUsername(user.getUsername());
			imgUser.setUsername("1");
//			imgUser.setPassword(user.getPassword());
			imgUser.setPassword("1");
			//保存文本信息
			System.out.println("imgUser: " + imgUser);
			imgUserService.update(imgUser);
			
			//拷贝上传的文件
			List<Map<String, String>> uploadList = JQueryFileUploadUtil.upload(files);
			//将文件信息保存到数据库
			Image image = null;
			for (Map<String, String> map : uploadList) {
				image = new Image();
				image.setSize(Long.valueOf(map.get(JQueryFileUploadUtil.ORIGINAL_FILESIZE)));
				image.setName(map.get(JQueryFileUploadUtil.ORIGINAL_FILENAME));
				image.setNewFilename(map.get(JQueryFileUploadUtil.NEW_FILENAME));
//				image.setUser(user);
				image.setUser(imgUser);
				
				//弹出层展示
				image.setUrl("jfujc/thumbnail");
				//预览
				image.setThumbnailUrl("jfujc/thumbnail");
				//删除
				image.setDeleteUrl("jfujc/delete");
				image.setDeleteType("DELETE");
				
				imageService.save(image);
				imageList.add(image);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JQueryFileUploadUtil.loadImage(imageList);
	}
	
	//删除图片
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable long id) {
		System.out.println("***********************************  删除图片  ******************************************");
		Image image = imageService.get(id);
		String results = "";
		try {
			//删除数据库图片
			baseImageEntityService.delete(image);
			//删除磁盘上的文件
			results = JQueryFileUploadUtil.deleteImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	//文件预览
	@RequestMapping(value = "/thumbnail/{id}", method = RequestMethod.GET)
	public void thumbnail(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		System.out.println("***********************************  文件预览  ******************************************id : "+ id);
		try {
			Image image = imageService.get(id);
			List<Image> imageList = Lists.newArrayList();
			imageList.add(image);
			//添加附加信息
			imageList = addImagesInfo(imageList);
			JQueryFileUploadUtil.previewImage(response, image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Image> addImagesInfo(List<Image> imageList){
		for (Image image : imageList) {
			//弹出层展示
			image.setUrl("jfujc/thumbnail");
			//预览
			image.setThumbnailUrl("jfujc/thumbnail");
			//删除
			image.setDeleteUrl("jfujc/delete");
			image.setDeleteType("DELETE");
		}
		return imageList;
	}
	
	
	// 设置背景图片
	@ResponseBody
	@RequestMapping(value = "/setConver/{id}", method = RequestMethod.GET)
	public String setConver(@PathVariable Long id) {
		// 把原来是背景图片的置为不是背景图片
		List<Image> findDatas = imageService.findDatas("cover", 1);
		for (Image image : findDatas) {
			image.setCover(0);
			imageService.update(image);
		}
		
		System.out.println("********  设置背景图片  ********" + id);
		Image image = imageService.get(id);
		image.setCover(1);
		imageService.update(image);
		return "1";
	}
}
