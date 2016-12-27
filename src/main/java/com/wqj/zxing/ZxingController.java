package com.wqj.zxing;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxingController {
	public static String contents ="https://www.baidu.com/";
	public static BarcodeFormat format = BarcodeFormat.QR_CODE;
	public static Integer width = 300;
	public static Integer height = 300;
	public static String ext= "png";
	public static Map<EncodeHintType, Object> hints = Maps.newHashMap();
	
	public static void main(String[] args) throws Exception {
		// 编码
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		// 纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		// 边距
		hints.put(EncodeHintType.MARGIN, 2);

		BitMatrix matrix = new MultiFormatWriter().encode(contents, format, width, height, hints);
		Path file = new File("D:/qrcode/image.png").toPath();
		
		MatrixToImageWriter.writeToPath(matrix, ext, file);
	}

}
