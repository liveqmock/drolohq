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
 * 判断 用户 手机号  是否 已经注册
 * 		param: 手机号
 * @author jeep
 *
 */
public class IsRegisterAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = "0";
		String phone = request.getParameter("phone");
		
		Database db = null;
		try {
			db = new SqlServer();
			UserDao ud = new UserDao(db);
			flag = ud.isRegister(phone);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return flag;
	}

}
