package ouc.drolo.action.staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.CzkDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 *  物流人员 查询 用户下  所有 会员卡 卡号
 * @author jeep
 *
 */
public class StaffFindCzkAction extends Action{

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
			System.out.println("物流人员 根据 用户电话 查询 用户会员卡  ： "+result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
