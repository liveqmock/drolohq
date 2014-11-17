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
 *  物流人员 查询    会员卡 对应的 余额 
 * @author jeep
 *
 */
public class StaffFindCzkBalanceAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = " ";
		String czkNum = request.getParameter("czkNum");
		String tel = request.getParameter("tel");
		try {
			Database db =  new SqlServer();
			db.setAutoCommit(false);
			CzkDao cDao = new CzkDao(db);
			result = cDao.findCzkBalance(czkNum,tel);
			db.commit();
			db.close();
			System.out.println("会员卡卡号  ：  "+czkNum+"  用户电话    "+tel+"   物流人员 根据  会员卡卡号  查询 余额  ： "+result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
