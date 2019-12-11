package com.lc.crm.utils;

public class MD5_JM {

	private static String password;
	//将md5加密的数据进行解密
	public static String getJm(String password) {
		return MD5Utils.convertMD5(MD5Utils.convertMD5(password));
	}
}
