package ouc.drolo.action.change;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.util.MyConfig;
import ouc.drolo.util.NetWorkUtil;
import wph.iframework.Action;

/**
 * 用户获取余额
 * @author jeep
 *
 */
public class UserGetBalanceAction extends Action{
	private static Log  logger = LogFactory.getLog(UserGetBalanceAction.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String balance = "0";
//		String uid = request.getParameter("uid");
		String phone = request.getParameter("phone");
		
		//---------  url拼接   ------
		String rawUrl = MyConfig.rawUrl;
		StringBuilder sb = new StringBuilder(rawUrl);
		sb.append("/account");
	 	sb.append("/balance.json").append("?");
	 	sb.append("mdn").append("=").append(phone);
	 	String url = sb.toString();
	 	logger.debug("URL:"+url); 
		
	 	//------------------------------
	 	String str = NetWorkUtil.getHttp(url);
	 	logger.debug("RESULT:"+str);
	 	
	 	JSONObject json = JSONObject.fromObject(str);
	 	JSONObject stateJson = (JSONObject) json.get("stateVO");
	 	int code = Integer.parseInt(stateJson.getString("code"));
	 	if(code==1){
	 		JSONObject accountVo = (JSONObject) json.get("accountVo");
	 		double amount =  (Double) accountVo.get("amount");
	 		balance = String.valueOf(amount);
	 	}
	 	
	 	logger.debug("Phone: 	"+phone+"	Balance:"+balance);
		return balance;
	}

}
