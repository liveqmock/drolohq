package ouc.drolo.action.staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.action.diaodu.Dispatcher;
import ouc.drolo.dao.StaffDao;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 物流人员 退出登陆
 * @author jeep
 *
 */
public class StaffLogoutAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = "0";
		String staffId = request.getParameter("staffId");

		Database db = null;
		try {
			db = new SqlServer();
			StaffDao sd = new StaffDao(db);
			flag = sd.logout(staffId);
			System.out.println("------登陆返回结果  ："+staffId);
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		  Dispatcher dispatcher = Dispatcher.getInstance();
		  String  flag1=dispatcher.onStaffLogout(staffId);
			
		  System.out.println("退出成功");
		return flag;
	}
	
}
