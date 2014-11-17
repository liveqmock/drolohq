package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import wph.iframework.Action;

/**   06-18 添加用户注册 时间 
 * 用户注册  
 * @author jeep
 *
 *  14/07/05  注册第一步 ： 提交手机号、验证码 
 *
 */
public class UserRegisterAction extends Action{
	private static Log logger = LogFactory.getLog(UserRegisterAction.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		String name = request.getParameter("name");
//		name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		
		String userYzm = request.getParameter("yzm");
//		String myYzm = UserGetYzmAction.getYzm();
		String myYzm = UserYzmRegAliAction.getYzm();
		
		String phone = request.getParameter("phone");
		
		
		logger.debug("系统生成验证码: "+myYzm+"  用户 提交的验证码 : "+userYzm+" 注册电话 ： "+phone);
		
//		String city = request.getParameter("city");
//		city = new String(city.getBytes("ISO-8859-1"), "UTF-8");
		
//		String sex = request.getParameter("sex");
		String str="";
		
		if(!userYzm.equals(myYzm)){
			str = "-1";    // 验证码 不对
		}else{
			
			str = "1";
			/* try {
				db=new SqlServer();
				 UserDao userDao = new UserDao(db);
				 str = userDao.registerFirst(phone);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				db.close();
			}*/
		}
		
		return str; 
	}
		
}
