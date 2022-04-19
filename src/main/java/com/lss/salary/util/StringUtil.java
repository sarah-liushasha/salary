package com.lss.salary.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	//判断字符串是否为空
	public static String md5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md=MessageDigest.getInstance("MD5");
		md.update(str.getBytes("UTF-8"));
		return new BigInteger(1,md.digest()).toString(16);
	}
	
	// 判断字符串是否为空
		public static Boolean isEmpty(String param) {
			return param == null || "".equals(param);
		}

		// 下划线命名转驼峰命名
		public static String underLineToHump(String para) {
			if (isEmpty(para)) {
				return "";
			}
			StringBuilder sb = new StringBuilder();
			String[] a = para.split("_");
			for (String s : a) {
				if (!para.contains("_")) {
					sb.append(s);
					continue;
				}
				if (sb.length() == 0) {
					sb.append(s.toLowerCase());
				} else {
					sb.append(s.substring(0, 1).toUpperCase());
					sb.append(s.substring(1).toLowerCase());
				}
			}
			return sb.toString();

		}

}
