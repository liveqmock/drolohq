package ouc.drolo.action.change;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.util.MyConfig;
import ouc.drolo.util.NetWorkUtil;
import wph.iframework.Action;

public class DepositAction extends Action{
	private static Log logger = LogFactory.getLog(DepositAction.class); 
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = "";
//		String uid = request.getParameter("uid");
		String cardID = request.getParameter("cardID");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		//---------  url拼接   ------
		String rawUrl = MyConfig.rawUrl;
		StringBuilder sb = new StringBuilder(rawUrl);
		sb.append("/card");
	 	sb.append("/use.json").append("?");
	 	sb.append("code").append("=").append(cardID);
	 	sb.append("&");
	 	sb.append("password").append("=").append(password);
	 	sb.append("&");
	 	sb.append("mdn").append("=").append(phone);
	 	String url = sb.toString();
	 	logger.debug("URL:"+url); 
		
	 	//------------------------------
	 	str = NetWorkUtil.getHttp(url);
	 	logger.debug("RESULT: 	"+str);
	 	
	 /*	JSONObject json = JSONObject.fromObject(str);
	 	JSONObject stateJson = (JSONObject) json.get("stateVO");
	 	String code = stateJson.getString("code");*/
		return str;
	}

}
