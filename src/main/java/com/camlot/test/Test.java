package com.camlot.test;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {


		
		/**
		 * 
		 * <p>Discription:MD5加密</p>
		 * Created on 2015年2月11日
		 * @param str
		 * @return
		 * @author:胡恒心
		 */
		public static String encipher(String str) {
	        MessageDigest messageDigest = null;  
	        try {  
	            messageDigest = MessageDigest.getInstance("MD5");  
	  
	            messageDigest.reset();  
	  
	            messageDigest.update(str.getBytes("UTF-8"));  
	        } catch (NoSuchAlgorithmException e) {
	        } catch (UnsupportedEncodingException e) {
	        }  
	  
	        byte[] byteArray = messageDigest.digest();  
	  
	        StringBuffer md5StrBuff = new StringBuffer();  
	  
	        for (int i = 0; i < byteArray.length; i++) {              
	            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
	                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
	            else  
	                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
	        }  
	  //test one
	        return md5StrBuff.toString();  
	    }  

}
