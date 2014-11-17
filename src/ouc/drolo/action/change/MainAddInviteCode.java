package ouc.drolo.action.change;

import ouc.drolo.util.NetWorkUtil;

public class MainAddInviteCode {

	public static void main(String[] args) {
		
		String[] list = {"18678888357","18688683567","18753110699","18764008079",
						"18764025709","18765852918","18865314512","18888352881",
						"18905311579","18953055506","18953192366","18954109193","18954168917"
						};
		
		//---------         -----
		for(String phone:list){
			String rawUrl = "http://localhost:8080/drolo/addInviteCode.u?";
			StringBuilder sb = new StringBuilder(rawUrl);
		 	sb.append("phone").append("=").append(phone);
		 	String url = sb.toString();
		 	
		 	//------------------------------
		 	try {
				String ret = NetWorkUtil.getHttp(url);
				System.err.println("xxx " + ret); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		 	
		}
		

	 	
	}
}
