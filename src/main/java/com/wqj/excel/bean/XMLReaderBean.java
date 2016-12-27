package com.wqj.excel.bean;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import javax.servlet.ServletConfig;

/**
 * 
 * @author LE CHEN
 *
 */
public class XMLReaderBean {

	public static final String DEFAULT_CONFIG_LOCATION_PREFIX = "/WEB-INF/";

	public static final String DEFAULT_CONFIG_LOCATION_CLASSPATH = "classpath:";
	
	public static final String separator = "/";
	
	public static final String DOUBLE_SEPARATOR = "\\\\";
	


	private static  String configLocation;

	private static ServletConfig config;

	@SuppressWarnings("static-access")
	public XMLReaderBean(String configLocation,ServletConfig config){
		this.configLocation = configLocation;
		this.config = config;
	}

	@SuppressWarnings("static-access")
	public InputStream createInputStream(){
		configLocation = convert(configLocation);
		InputStream input = null;
		try {
			if(configLocation.trim().startsWith(DEFAULT_CONFIG_LOCATION_PREFIX)){
				configLocation = this.config.getServletContext().getRealPath(configLocation);
				
				input = new FileInputStream(configLocation);

			}else if(configLocation.trim().startsWith(DEFAULT_CONFIG_LOCATION_CLASSPATH)){
				input = this.getClass().getResourceAsStream(separator+cleanPath(configLocation));
			}else{
				input = new FileInputStream(configLocation);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		checkIputStreamException(input);
		try {
			input =checkForUtf8BOMAndDiscardIfAny(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return input;

	}


	/**
	 * @return the configLocation
	 */
	public String getConfigLocation() {
		return configLocation;
	}

	/**
	 * @param configLocation the configLocation to set
	 */
	@SuppressWarnings("static-access")
	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	/**
	 * @return the config
	 */
	public static ServletConfig getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public static void setConfig(ServletConfig config) {
		XMLReaderBean.config = config;
	}


	private String cleanPath(String path){
		int prefix = path.indexOf(":");
		if(prefix>= 0){
			if(path.indexOf(separator)== (prefix+1) ){
				prefix += 1; 
			}
			return path.substring(prefix+1);
		}
		return path.substring(prefix+1);
	}


	
	private void checkIputStreamException(InputStream input){
		
		if(input ==null)
			try {
				throw new IOException(" not finds  [ "+configLocation+" ] ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	private String convert(String path){
		return path.replaceAll(DOUBLE_SEPARATOR, separator);
	}
	
	
	private  InputStream checkForUtf8BOMAndDiscardIfAny(InputStream inputStream) throws IOException {  
	    PushbackInputStream pushbackInputStream = new PushbackInputStream(new BufferedInputStream(inputStream), 3);  
	    byte[] bom = new byte[3];  
	    if (pushbackInputStream.read(bom) != -1) {  
	        if (!(bom[0] == (byte) 0xEF && bom[1] == (byte) 0xBB && bom[2] == (byte) 0xBF)) {  
	            pushbackInputStream.unread(bom);  
	        }  
	    }  
	    return pushbackInputStream;   
	}
	
	public static void main(String[] args) {
		System.out.println(new XMLReaderBean(null,null).convert("file:\\F:\\space\\Joker-Test\\target/classes/doc.xml"));
	}




}
