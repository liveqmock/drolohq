package ouc.drolo.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.AddressDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 *  根据 用户 userId 查询 地址 
 * @author jeep
 *
 */
public class UserFindAddressAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "0";
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			Database db =  new SqlServer();
			db.setAutoCommit(false);
			AddressDao aDao = new AddressDao(db);
			result = aDao.findAddress(uid);
			db.commit();
			db.close();
			System.out.println("用户查询地址结果  ： "+result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
