package ouc.drolo.action.kf;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.StaffDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

public class TestAction extends Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Database  db = null;
		String  flag = "" ;
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			
			StaffDao sd = new StaffDao(db);
			flag = sd.sendOrder("123");
			System.err.println("xxxxxxxxxx   " + flag);
			db.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
