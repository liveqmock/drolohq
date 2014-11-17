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
 * 用户   修改 密码   获取验证码
 * @author jeep
 *return :000:发送成功； 其他发送失败
 */
public class UserYzmPswAction extends Action{
	private static Log logger = LogFactory.getLog(UserYzmPswAction.class);
	
	private static String yzm ;
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String phone = request.getParameter("tel");
		Database db=null;
		String str="";
			try {
				 db=new SqlServer();
				 UserDao  uDao = new UserDao(db);
				 str = uDao.isTel(phone);
				 if(str.equals("1")){
					yzm = Yzm.createRandom(true, 4);
					String content = "尊敬的用户，您本次修改密码的短信验证码为 :"+yzm;
				
					String pstContent = SendMessage.createSubmitXml(phone, content);
					logger.debug("[CHANGE PASSWORD]" + pstContent); 
					
					str = Http.post(pstContent);	
					
					logger.debug("[CHANGE　PASSWORD] users'phone :"+phone+"  the system's yzm :"+yzm+"  dx result:"+str);
				 }
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(db!=null){
					db.close();
				}
			}
		return str; 
	}

	//-------------------

	public static String getYzm() {
		return yzm;
	}
	public static void setYzm(String yzm) {
		UserYzmPswAction.yzm = yzm; 
	}

}
