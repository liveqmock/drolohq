package ouc.drolo.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.UserDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 用户  获取地址个数
 * @author jeep
 *
 */
public class UserGetAddressNumAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = "0";
		String uid = request.getParameter("uid");
		
		Database db = null;
		try {
			db = new SqlServer();
			UserDao ud = new UserDao(db);
			flag = ud.getAddressNum(uid);
			logger.debug("Address Num :" + flag); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return flag;
	}

}
