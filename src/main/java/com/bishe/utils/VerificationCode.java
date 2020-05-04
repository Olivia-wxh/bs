package com.bishe.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VerificationCode {

	/**
	 * 生成随机6为数字
	 * @return
	 */
	public static String get6Code(){
		String str = "1234567890";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<6; i++){
			Integer temp = random.nextInt(10);
			sb.append(str.charAt(temp));
		}
		return sb.toString();
	}
}
