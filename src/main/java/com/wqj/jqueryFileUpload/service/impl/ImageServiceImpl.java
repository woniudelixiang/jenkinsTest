package com.wqj.jqueryFileUpload.service.impl;

import org.springframework.stereotype.Service;
import com.wqj.common.orm.dao.HibernateDaoSupport;
import com.wqj.jqueryFileUpload.entity.Image;
import com.wqj.jqueryFileUpload.service.ImageService;

@Service
public class ImageServiceImpl extends HibernateDaoSupport<Image> implements ImageService{

}
