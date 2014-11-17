package ouc.drolo.util;

import java.security.NoSuchAlgorithmException;

/**
 * 试用java自带API进行MD5加密
 * @author jeep
 *
 */
public  class  EncryptionByMD5 {

	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];

			}
			s = new String(str);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
	public static void main(String[] args){
		
		String test=EncryptionByMD5.getMD5("duola123".getBytes());
		System.out.println(test);

	}
}