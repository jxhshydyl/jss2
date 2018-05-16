package com.wf.ew.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class md5Util {
	private static String cover(byte[] bytes){
		String result="";
		for(int i=0;i<bytes.length;i++){
			int tem=bytes[i]&0xff;
			String temhex=Integer.toHexString(tem);
			if(temhex.length()<2){
				result+="0"+temhex;
			}else
				result+=temhex;
		}
		return result;
	}
	public static String md5jdk(String message){
		String tem="";
		try {
			MessageDigest md5dist=MessageDigest.getInstance("MD5");
			byte[] b=md5dist.digest(message.getBytes());
			tem=cover(b);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tem;
	}
	
}
