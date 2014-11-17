package ouc.drolo.action.change;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.ChangeDao;
import ouc.drolo.dao.UserDao;
import ouc.drolo.util.MyConfig;
import ouc.drolo.util.NetWorkUtil;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**  v0926 邀请码
 * 用户注册   在财务系统中创建账户
 * @author jeep
 */
public class UserRegCaiwuAction extends Action{
	private static Log logger = LogFactory.getLog(UserRegCaiwuAction.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String invite = request.getParameter("invite"); //朋友的邀请码
		String invitePhone = null;
		
		Database db=null;
		String str="-1";
		
		 try {
			 db=new SqlServer();
			 UserDao userDao = new UserDao(db);
			 ChangeDao cDao = new ChangeDao(db);
			 String inviteCode = null;  // 本身的邀请码
			 
			 if("0".equals(invite)){ //  不填邀请码的情况
				 inviteCode = cDao.getInviteCode();
				 logger.debug("[REGCaiwu]UserName ： "+name+"  phone :"+phone+" pwd:"+password+" INVITECODE: "+inviteCode);
				 str = userDao.register(phone,password,name,inviteCode,invite);
			 }else{
				 invitePhone = cDao.getPhoneByInviteCode(invite);  // 获取朋友的电话
				 logger.debug("FRIEND'S INVITE:"+invite+"  FRIEND's invitePhone:"+invitePhone); 
				 if("1111".equals(invitePhone)){ //邀请码错误
					 str = "-2";
				 }else{
					 inviteCode = cDao.getInviteCode();
					 logger.debug("[REGCaiwu]UserName ： "+name+"  phone :"+phone+" pwd:"+password+" INVITECODE: "+inviteCode);
					 str = userDao.register(phone,password,name,inviteCode,invite);
				 }
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		
		if("-2".equals(str)){
			return "-2";  // 邀请码错误
		}
		 
		//------------- 在财务 创建用户账户          -----
		String rawUrl = MyConfig.rawUrl;
		StringBuilder sb = new StringBuilder(rawUrl);
		sb.append("/account");
	 	sb.append("/create.json").append("?");
	 	sb.append("mdn").append("=").append(phone);
	 	String url = sb.toString();
	 	logger.debug("URL:"+url); 
		
	 	//------------------------------
	 	String ret = NetWorkUtil.getHttp(url);
	 	logger.debug("RESULT: 	"+ret);
	 	
	 	JSONObject json = JSONObject.fromObject(ret);
	 	JSONObject stateJson = (JSONObject) json.get("stateVO");
	 	String code = stateJson.getString("code");
	 	
	 	logger.debug("CODE: " + code); 
	
	 	 if(!"0".equals(invite)){  // 有邀请码的情况
	 	 	// ----  调用推广充值接口    -------------------
	 	 	//http://118.193.14.243:8080/recharge/api/account/prom.json?mdn=13011888471
 			StringBuilder builder = new StringBuilder(rawUrl);
 			builder.append("/account");
 			builder.append("/prom.json").append("?");
 			
 			builder.append("mdn").append("=").append(phone); //自身获取推广金额
 		 	String tg = builder.toString();
 		 	String tg1 = NetWorkUtil.getHttp(tg);
 		 	logger.debug("RESULT: 	"+tg1);
 			
 		 	for(int i=0;i<2;i++){   //朋友2倍获取推广金额
 		 		String tg2 = rawUrl + "/account/prom.json?mdn="+invitePhone;
 			 	String tg_ret = NetWorkUtil.getHttp(tg2);
 			 	logger.debug("RESULT: 	"+tg_ret);
 		 	}
	 	 } // end if

		return str; 
	}
		
}
