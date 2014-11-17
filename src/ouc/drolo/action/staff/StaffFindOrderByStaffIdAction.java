package ouc.drolo.action.staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.OrderDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 物流人员   查询  
 * 		已接收 订单 列表
 * @author jeep
 *
 */
public class StaffFindOrderByStaffIdAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderInfo = "";
		String staffId = request.getParameter("staffId");
		Database db = null;
		
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			OrderDao od = new OrderDao(db);
			orderInfo = od.findOrderByStaffId(staffId);
			System.out.println("物流人员工号 : "+staffId+"      已接收的订单信息 : " + orderInfo); 
			
			db.commit(); 
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderInfo;
	}

}
