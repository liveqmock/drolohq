package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.ContactDao;
import ouc.drolo.dao.UserDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**   06-18 添加用户注册 时间 
 * 用户注册  
 * @author jeep
 * 
 * 14/07/05 
 * 		注册第二步：提交 手机号、姓名、密码
 *
 */
@Deprecated
public class UserRegisterTAction extends Action{
	private static Log logger = LogFactory.getLog(UserRegisterTAction.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		
		logger.debug("姓名 ： "+name+"  电话 :"+phone+" 密码:"+password);
		
		Database db=null;
		String str="0";
		
		 try {
			db=new SqlServer();
			db.setAutoCommit(false);
			
			 UserDao userDao = new UserDao(db);
//			 str = userDao.register(phone,password,name);
			 
			 String uid = userDao.getUid(name, phone);
			 
			 if(!uid.equals("-1")){
				 ContactDao cDao = new ContactDao(db);  //注册成功后，将自己添加到联系人中 ,uid
				 String cstr = cDao.addContact(uid, name, phone);
				 logger.debug("注册添加自己结果:　"+cstr);
			 }
			 
/*			 ContactDao cDao = new ContactDao(db);  //注册成功后，将自己添加到联系人中 ,uid
			 String cstr = cDao.addContact("0", name, phone);
			 logger.debug("注册添加自己结果:　"+cstr);*/ 
			 
			 db.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return str; 
	}
		
}
