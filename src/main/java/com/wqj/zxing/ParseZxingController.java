package com.wqj.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class ParseZxingController {
	public static String contents ="https://www.baidu.com/";
	public static BarcodeFormat format = BarcodeFormat.QR_CODE;
	public static Integer width = 300;
	public static Integer height = 300;
	public static String ext= "png";
	public static Map<DecodeHintType, Object> hints = Maps.newHashMap();
	
	public static void main(String[] args) throws Exception {
		MultiFormatReader formatReader = new MultiFormatReader();
		File file = new File("D:/qrcode/image.png");
		BufferedImage image = ImageIO.read(file);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
		
		// 编码
		hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
		Result result = formatReader.decode(binaryBitmap, hints);
	    System.out.println(result.toString());
	}

}
