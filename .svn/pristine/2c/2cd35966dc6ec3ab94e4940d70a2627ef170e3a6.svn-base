package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.sms.Http;
import ouc.drolo.sms.SendMessage;
import ouc.drolo.util.Yzm;
import wph.iframework.Action;

/**  14/07/10
 *      14/08/05  短信通道修改
 * 用户  添加联系人  时  获取验证码
 * @author jeep
 */
public class UserLxrYzmAction extends Action{
	private static Log logger = LogFactory.getLog(UserLxrYzmAction.class);
	private static String yzm ;
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "-1";
		
		String phone = request.getParameter("phone");
		yzm = Yzm.createRandom(true, 4);
		String content = "尊敬的用户，您本次的短信验证码为 :"+yzm;
		String pstContent = SendMessage.createSubmitXml(phone, content);
		result = Http.post(pstContent);
		
		/*  webservice 方式
		String loginName = "duola";
		String password = EncryptionByMD5.getMD5("duola123".getBytes());
		WebService webService = new WebService();
		WebServiceSoap wss = webService.getWebServiceSoap();
		result = wss.send(loginName, password, "", phone, content, "1");*/
		logger.debug("xml " + pstContent);	 
		
		logger.debug("用户添加联系人电话 : "+phone+" 系统生成验证码  : "+yzm+" 结果: "+result);
		
		return result;
	}
	//-------------------

	public static String getYzm() {
		return yzm;
	}
	public static void setYzm(String yzm) {
		UserLxrYzmAction.yzm = yzm;
	}

}
