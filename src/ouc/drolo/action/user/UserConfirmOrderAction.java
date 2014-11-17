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
 * 用户确认收取订单，status ->36
 * @author jeep
 *
 */
public class UserConfirmOrderAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "0";
		String orderId = request.getParameter("orderId"); 
		try {
			Database dbDatabase =  new SqlServer();
			dbDatabase.setAutoCommit(false);
			UserDao uDao = new UserDao(dbDatabase);
			result = uDao.confirmOrder(orderId);
			dbDatabase.commit();
			dbDatabase.close();
			System.out.println(" 用户   确认订单  结果      "+result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
