package common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
* @date 2019-11-06 13:24:18
* @author LEI
* TODO
*/

public class EncryptUtil {
	/**
	 * MD5
	 */
	public static final String ALGORITHM_MD5 = "MD5";
	/**
	 * SHA
	 */
	public static final String ALGORITHM_SHA = "SHA";
	
	public static String encrypt(String algorithm, String data) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(data.getBytes());
			return new String(md.digest());
				
		} catch(NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static boolean eq(String algorithm,String enData,String deData) {
		return enData.equals(encrypt(algorithm,deData));
	}
	
	public static void main(String[] args) {
		String s = "123456";
		String en = encrypt(ALGORITHM_MD5, s);
		System.out.println(eq(ALGORITHM_SHA, en, "hello"));
		System.out.println(en);
		int i = 145879;
		
	}

}
