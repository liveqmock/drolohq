package ouc.drolo.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.CzkDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

public class UserFindCZKAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = " ";
		String tel = request.getParameter("tel");
		try {
			Database db =  new SqlServer();
			
			db.setAutoCommit(false);
			CzkDao cDao = new CzkDao(db);
			result = cDao.findCzk(tel);
			db.commit();
			db.close();
			System.out.println("用户电话 ：　"+tel+"　　用户查询会员卡  ： "+result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
