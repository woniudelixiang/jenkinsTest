package com.wqj.image.controller;

import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.wqj.common.controller.CommonController;
import com.wqj.image.util.ImageUtil;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/image")
public class ImageController extends CommonController {

	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		return "image/list";
	}

	@RequestMapping("/upload")
	public String upload( HttpServletRequest request) throws Exception{
		MultipartFile mpf = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> itr = multipartRequest.getFileNames();
		Map<String, String> original = null;
		Map<String, String> compress = null;
		while (itr.hasNext()) {
			mpf = multipartRequest.getFile(itr.next());
			CommonsMultipartFile file = (CommonsMultipartFile) mpf;
			original = ImageUtil.original(file);
			compress = ImageUtil.compress(file, ImageUtil.WIDTH, ImageUtil.HEIGHT, false);
		}
		
		//原图的url
		String imageUrl = original.get(ImageUtil.WHOLE_PATH);
		
		//缩略图的url
		String thumImageUrl = compress.get(ImageUtil.WHOLE_PATH);
		
		request.setAttribute("imageUrl", imageUrl);
		request.setAttribute("thumImageUrl", thumImageUrl);
		return forward("/list");
	}
	
	
	
}
