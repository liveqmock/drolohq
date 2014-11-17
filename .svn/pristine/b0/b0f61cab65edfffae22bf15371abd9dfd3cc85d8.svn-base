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

public class UserFindClothesAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "0";
		String orderId = request.getParameter("orderId");
		try {
			
			Database dbDatabase =  new SqlServer();
			UserDao uDao = new UserDao(dbDatabase);
			result = uDao.findClothesByOrderId(orderId);
			
			dbDatabase.close();
			System.out.println(" 用户查询  订单下 衣服   "+result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
