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
 * 财务对账，调用财务系统接口
 * @author jeep
 *
 */
public class CheckMoneyAction extends Action{
	private static Log logger = LogFactory.getLog(CheckMoneyAction.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = "";
		String orderID = request.getParameter("orderID");
		String amount = request.getParameter("amount");
		
		//---------  url拼接   ------
		String rawUrl = MyConfig.rawUrl;
		StringBuilder sb = new StringBuilder(rawUrl);
		sb.append("/account");
	 	sb.append("/reconciliation.json").append("?");
	 	sb.append("orderId").append("=").append(orderID);
	 	sb.append("&");
	 	sb.append("amount").append("=").append(amount);
	 	String url = sb.toString();
	 	logger.debug("URL:"+url); 
		
	 	//------------------------------
	 	str = NetWorkUtil.getHttp(url);
	 	logger.debug("RESULT: 	"+str);
	 	
	 	JSONObject json = JSONObject.fromObject(str);
	 	JSONObject stateJson = (JSONObject) json.get("stateVO");
	 	String code = stateJson.getString("code");
	 	
	 	logger.debug("xx " + code); 

	 	if("1".equals(code)){
	 		code = "1";  // 对账成功
	 	}else{
	 		code = "-1";  // 失败
	 	}
		
		return code;
	}

}
