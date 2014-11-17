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
 * aliyun 修改密码时获取验证码
 * @author jeep
 *
 */

public class YzmPswAliAction extends Action{
	private static Log logger = LogFactory.getLog(YzmPswAliAction.class);
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String phone = request.getParameter("phone");
		String pswYzm = request.getParameter("pswYzm");

		String content = "尊敬的用户，您本次修改密码的短信验证码为 :"+pswYzm;
	
		String pstContent = SendMessage.createSubmitXml(phone, content);
		logger.debug("[CHANGE PASSWORD]" + pstContent); 
		
		String str = Http.post(pstContent);	
		
		logger.debug("[CHANGE　PASSWORD] users'phone :"+phone+"  the system's yzm :"+pswYzm+"  dx result:"+str);

		return str; 
	}

}
