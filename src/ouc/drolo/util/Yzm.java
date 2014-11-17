package ouc.drolo.util;

import java.util.Random;

public final class Yzm {
	
	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 *            是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890": "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {  //至少是两个长度
				bDone = false;
			}
		} while (bDone);
	
		return retStr;
	}
	
	/**
	 * 生成邀请码，唯一性
	 * @param Len ： 邀请码长度 6 位
	 * @return
	 */
    public static String getRandomString(int Len) {
        String[] baseString={"0","1","2","3","4","5","6","7","8","9",
                "a","b","c","d","e","f","g","h","i","j",
                "k","l","m","n","o","p","q","r","s","t",
                "u","v","w","x","y","z"};
        Random random = new Random();
        int length=baseString.length;
        String randomString="";
        for(int i=0;i<length;i++){
            randomString+=baseString[random.nextInt(length)];
        }
        random = new Random(System.currentTimeMillis());
        String resultStr="";
        for (int i = 0; i < Len; i++) {
            resultStr += randomString.charAt(random.nextInt(randomString.length()-1));
        }
        
        return resultStr;
    }
	
	public static void main(String[] args) {
//		String str = Yzm.createRandom(true, 4);
		for(int i=0;i<100;i++){
			String str = getRandomString(6);
			System.out.println(str);
		}
	}

}