package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.sms.Http;
import ouc.drolo.sms.SendMessage;
import wph.iframework.Action;

/**
 *aliyun 用户注册获取验证码
 */
public class YzmRegAliAction extends Action{
	private static Log logger = LogFactory.getLog(YzmRegAliAction.class);
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String phone = request.getParameter("phone");
		String regYzm = request.getParameter("regYzm");
		
		String content = "尊敬的用户，您本次注册的短信验证码为 :"+regYzm;
		
		String pstContent = SendMessage.createSubmitXml(phone, content);
//				logger.debug("[INFO]" + pstContent); 
		String result = Http.post(pstContent);			
		
		logger.debug("[REG YZM]users'phone : "+phone+" the system's yzm: "+regYzm+" dx result: "+result);
	
		return result;
	}

}
