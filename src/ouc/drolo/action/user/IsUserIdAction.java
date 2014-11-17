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

/**  06 -18
 * 判断 用户 userId 是否 存在 
 * 		param: 用户Id
 * @author jeep
 *
 */
public class IsUserIdAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = "0";
		String userId = request.getParameter("userId");
		
		Database db = null;
		try {
			db = new SqlServer();
			UserDao ud = new UserDao(db);
			flag = ud.isUserId(userId);
			db.close();
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
