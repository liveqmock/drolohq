package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.UserDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 用户   修改密码  0905
 * @author jeep
 *
 */
public class UserUpdatePswAction extends Action{
	private static Log logger = LogFactory.getLog(UserUpdatePswAction.class); 
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String tel = request.getParameter("phone");
		String psw = request.getParameter("psw");
		
		logger.debug("手机号:"+tel +"    修改的密码："+psw);
		
		Database db=null;
		String str="-1";
		
		try {
			 db=new SqlServer();
			 UserDao userDao = new UserDao(db);
			 str = userDao.updatePsw(tel,psw);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		} 
		
		return str; 
	}
		
}
