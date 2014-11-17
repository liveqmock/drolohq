package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import wph.iframework.Action;

/**  0905 添加用户注册 时间 
 * 用户注册  
 *     修改密码第一步 ： 提交手机号、验证码 
 *
 */
public class UserUpdatePswYZMAction extends Action{
	private static Log logger = LogFactory.getLog(UserUpdatePswYZMAction.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String userYzm = request.getParameter("yzm");
//		String myYzm = UserYzmPswAction.getYzm();
		String myYzm = UserYzmPswAliAction.getYzm();
		
		String phone = request.getParameter("phone");
		
		logger.debug("系统生成验证码: "+myYzm+"  用户 提交的验证码 : "+userYzm+" 注册电话 ： "+phone);
		
		String str="";
		
		if(!userYzm.equals(myYzm)){
			str = "-1";    // 验证码 不对
		}else{
			
			str = "1";
		}
		
		return str; 
	}
		
}
