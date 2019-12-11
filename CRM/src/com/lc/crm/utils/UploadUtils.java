package com.lc.crm.utils;

import java.util.UUID;
/**
 * 文件上传工具类
 * @author user LC
 *
 */
public class UploadUtils {

	/**
	 * 获取随机的文件名
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		
		//获取扩展名
		int index = fileName.lastIndexOf(".");
		String extension = fileName.substring(index);
		UUID.randomUUID().toString().replace("-","");
		return UUID.randomUUID().toString().replace("-","")+extension;
	}
	
	/**
	 * 获取分离的二级文件目录
	 * @return
	 */
	public static String getFilePath(String fileName) {
		int code1 = fileName.hashCode();
		int d1 = code1 & 0xf;
		int code2 = d1>>>4;
		int d2 = code2 & 0xf;
		return "/"+d1+"/"+d2;
	}
}

