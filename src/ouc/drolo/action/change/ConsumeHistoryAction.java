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

/**
 * 查询消费记录
 * @author jeep
 *
 */
public class ConsumeHistoryAction extends Action{
	private static Log logger = LogFactory.getLog(ConsumeHistoryAction.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		String uid = request.getParameter("uid");
		String phone = request.getParameter("phone");
		
		//---------  url拼接   ------
		String rawUrl = MyConfig.rawUrl;
		StringBuilder sb = new StringBuilder(rawUrl);
		sb.append("/account");
	 	sb.append("/record.json").append("?");
	 	sb.append("mdn").append("=").append(phone);
	 	String url = sb.toString();
	 	logger.debug("URL:"+url); 
		
	 	//------------------------------
	 	String str = NetWorkUtil.getHttp(url);
	 	logger.debug("Phone: "+phone+" RESULT:"+str);
	 	
		return str;
	}

}
