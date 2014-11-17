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
 * 物流人员 查询     所有 订单 记录 
 * @author jeep
 *
 */
public class StaffFindAlOrderAction extends Action{
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String allOrder = "" ;
		String staffId = request.getParameter("staffId");
		Database db = null; 
		
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			
			StaffDao sd = new StaffDao(db);
			allOrder = sd.allOrders(staffId); 
			System.out.println("物流人员   "+staffId+"   所有订单信息  ："+allOrder); 
			
			db.commit();  
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allOrder;
	}
}
