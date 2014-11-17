package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.UserDao;
import ouc.drolo.util.MyConfig;
import ouc.drolo.util.NetWorkUtil;
import ouc.drolo.util.Yzm;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 用户   修改 密码   获取验证码
 * 		直接调用 阿里云的接口
 * @author jeep
 *return :00:发送成功； 其他发送失败
 */
public class UserYzmPswAliAction extends Action{
	private static Log logger = LogFactory.getLog(UserYzmPswAliAction.class);
	
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
					
					//---------  url拼接   ------
					String aliUrl = MyConfig.aliUrl;
					StringBuilder sb = new StringBuilder(aliUrl);
				 	sb.append("/aliPswYzm.u").append("?");
				 	sb.append("phone").append("=").append(phone);
				 	sb.append("&");
				 	sb.append("pswYzm").append("=").append(yzm);
				 	String url = sb.toString();
				 	logger.debug("URL:"+url); 
					
					str = NetWorkUtil.getHttp(url);
				 	logger.debug("RESULT: 	"+str);
				 }
			} catch (Exception e) {
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
		UserYzmPswAliAction.yzm = yzm; 
	}

}
