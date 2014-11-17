package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.UserInfoDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 用户查询订单
 * @author jeep
 */
public class UserFindOrderAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "0";
		int userId = Integer.parseInt(request.getParameter("userId"));
		try {
			Database db =  new SqlServer();
			UserInfoDao uiDao = new UserInfoDao(db);
			result = uiDao.findOrder(userId);
			
			db.close();
			System.out.println(" 用户   查询订单结果      "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;  
	}
	
}
