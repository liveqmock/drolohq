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
 *  根据 用户 userId 查询 用户 信息
 * @author jeep
 *
 */
public class UserFindNameAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "0";
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			Database db =  new SqlServer();
			
			UserDao uDao = new UserDao(db);
			result = uDao.findName(uid);
			
			db.close();
			logger.debug(uid + "  USER INFO : " + result); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
