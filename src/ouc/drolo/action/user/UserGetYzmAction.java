package ouc.drolo.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.UserDao;
import ouc.drolo.sms.Http;
import ouc.drolo.sms.SendMessage;
import ouc.drolo.util.Yzm;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 用户注册  时  获取验证码
 * @author jeep
 *return :000:发送成功； 其他发送失败
 */
public class UserGetYzmAction extends Action{
	private static Log logger = LogFactory.getLog(UserGetYzmAction.class);
	private static String yzm ;
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String phone = request.getParameter("phone");
		
		yzm = Yzm.createRandom(true, 4);
		String content = "尊敬的用户，您本次注册的短信验证码为 :"+yzm;
		
		String result = "-1";
		String isTel = "-1";
		Database db = null; 
		
		try {
			db = new SqlServer();
			
			UserDao ud = new UserDao(db);
			isTel = ud.isTel(phone);
			if(isTel.equals("-1")){  // 手机号不存在 
				
				String pstContent = SendMessage.createSubmitXml(phone, content);
//				logger.debug("[INFO]" + pstContent); 
				result = Http.post(pstContent);			
				
				logger.debug("[REG YZM]users'phone : "+phone+" the system's yzm: "+yzm+" dx result: "+result);
			}else{
				return "-1";   // 手机号存在
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		
		return result;
	}
	//-------------------

	public static String getYzm() {
		return yzm;
	}
	public static void setYzm(String yzm) {
		UserGetYzmAction.yzm = yzm;
	}

}
