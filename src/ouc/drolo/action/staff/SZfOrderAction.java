package ouc.drolo.action.staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.StaffDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 物流人员    订单 作废 
 * @author jeep
 *
 */
public class SZfOrderAction extends Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = "-1" ;
		
		Database db =null ;
		String orderId = request.getParameter("orderId");
		String staffId = request.getParameter("staffId");
		try {
			db = new SqlServer();
			StaffDao sDao = new StaffDao(db);
			
			flag = sDao.zfOrder(staffId,orderId);
			System.out.println("物流人员工号: "+staffId+"    作废的订单号 : " + orderId); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return flag;
	}

}
