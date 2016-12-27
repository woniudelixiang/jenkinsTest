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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author Jiafa Lv
 * @date Apr 22, 2014 9:13:22 AM
 * @email simon-jiafa@126.com
 * 
 */
public class ImageUtils {
	public static final int MEDIUM_WIDTH = 640;
	public static final int MEDIUM_HEIGHT = 420;
	public static final String EXT_MEDIUM_SIZE = "640X420";

	public static final int SMALL_SIZE = 80;
	public static final String EXT_SMALL_SIZE = "80X80";

	public static final int LANGE_SIZE = 200;
	public static final String EXT_LANGE_SIZE = "200X200";
	public static final String JPG = "jpg";
	/**
	 * 实现图像的等比缩放和缩放后的截取
	 * 
	 * @param inFilePath
	 *            要截取文件的路径
	 * @param outFilePath
	 *            截取后输出的路径
	 * @param width
	 *            要截取宽度
	 * @param hight
	 *            要截取的高度
	 * @throws Exception
	 */

	public static void saveImageAsJpg(String inFilePath, String outFilePath,
			int width, int hight) throws Exception {
		File file = new File(inFilePath);
		InputStream in = new FileInputStream(file);
		File saveFile = new File(outFilePath);

		BufferedImage srcImage = ImageIO.read(in);

		if (width > 0 || hight > 0) {
			// 原图的大小
			int sw = srcImage.getWidth();
			int sh = srcImage.getHeight();
			// 如果原图像的大小小于要缩放的图像大小，直接将要缩放的图像复制过去
			if (sw > width && sh > hight) {
				srcImage = resize(srcImage, width, hight);
			} else {
				String fileName = saveFile.getName();
				String formatName = fileName.substring(fileName
						.lastIndexOf('.') + 1);
				ImageIO.write(srcImage, formatName, saveFile);
				in.close();
				return;
			}
		}
		// 缩放后的图像的宽和高
		int w = srcImage.getWidth();
		int h = srcImage.getHeight();
		// 如果缩放后的图像和要求的图像宽度一样，就对缩放的图像的高度进行截取
		if (w == width) {
			// 计算X轴坐标
			int x = 0;
			int y = h / 2 - hight / 2;
			saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
		}
		// 否则如果是缩放后的图像的高度和要求的图像高度一样，就对缩放后的图像的宽度进行截取
		else if (h == hight) {
			// 计算X轴坐标
			int x = w / 2 - width / 2;
			int y = 0;
			saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
		}
		
		in.close();
	}

	/**
	 * 实现缩放后的截图
	 * 
	 * @param image
	 *            缩放后的图像
	 * @param subImageBounds
	 *            要截取的子图的范围
	 * @param subImageFile
	 *            要保存的文件
	 * @throws IOException
	 */
	private static void saveSubImage(BufferedImage image,
			Rectangle subImageBounds, File subImageFile) throws IOException {
		if (subImageBounds.x < 0 || subImageBounds.y < 0
				|| subImageBounds.width - subImageBounds.x > image.getWidth()
				|| subImageBounds.height - subImageBounds.y > image.getHeight()) {
			return;
		}
		BufferedImage subImage = image.getSubimage(subImageBounds.x,
				subImageBounds.y, subImageBounds.width, subImageBounds.height);
		String fileName = subImageFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
		ImageIO.write(subImage, formatName, subImageFile);
	}

	/**
	 * 实现图像的等比缩放
	 * 
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	private static BufferedImage resize(BufferedImage source, int targetW,
			int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx < sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
					targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	public static void scaleImage(OutputStream outStream,Image src,int width,int height) {
		try {
			
			BufferedImage bufferedImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			bufferedImage.getGraphics().drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH),0, 0, null);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream);
			encoder.encode(bufferedImage);
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 public static void cutImage(File file,int x,int y,int width,int heigth) throws IOException{  
		 	String fileName= file.getName();
	     	String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
		  	Iterator<?> iterator = ImageIO.getImageReadersByFormatName(prefix);   
	        ImageReader reader = (ImageReader)iterator.next();   
	        InputStream in=new FileInputStream(file);  
	        ImageInputStream iis = ImageIO.createImageInputStream(in);   
	        reader.setInput(iis, true);   
	        ImageReadParam param = reader.getDefaultReadParam();   
	        Rectangle rect = new Rectangle(x, y, width, heigth);    
	        param.setSourceRegion(rect);   
	        BufferedImage bi = reader.read(0,param);     
	        ImageIO.write(bi, prefix, file);   
	        iis.close();
	        in.close();
	     }  
	
	 public static File imgToJPG(File file,File outfile) throws IOException{
		  //读取文件
		  BufferedImage bufferedImage;
	      bufferedImage = ImageIO.read(file);
	      // create a blank, RGB, same width and height, and a white background
	      BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
	            bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
	      newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
	      // write to jpeg file
	      ImageIO.write(newBufferedImage, JPG, outfile);
	      return outfile;
	   }
	 
	 
//	 public static void cutHalfImage(File file,String dest,int size) throws IOException{   
//		 String fileName= file.getName();
//	     String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
//         Iterator<?> iterator = ImageIO.getImageReadersByFormatName(prefix);   
//         ImageReader reader = (ImageReader)iterator.next();   
//         InputStream in=new FileInputStream(file);  
//         ImageInputStream iis = ImageIO.createImageInputStream(in);   
//         reader.setInput(iis, true);   
//         ImageReadParam param = reader.getDefaultReadParam();   
////         int imageIndex = 0;   
//         int width = size;
//         int height = size;   
//         Rectangle rect = new Rectangle(size/2, size/2, width, height);   
//         param.setSourceRegion(rect);   
//         BufferedImage bi = reader.read(0,param);     
//         ImageIO.write(bi, prefix, new File(dest));     
//     }  
	
	
//	 public static void main(String[] args) {
//		 float s =1.00f;
//		 s = 1.00f/4.00f;
//		  System.out.println(s);
//	}
}
