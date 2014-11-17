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
 * 物流人员 查询     所有   取衣订单   记录 
 * 	按月（  订单已经付款 ，表示 物流已取衣）
 * @author jeep
 *
 */
public class StaffFindAllTakeOrderAction extends Action{
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String allTakeOrder = "" ;
		String staffId = request.getParameter("staffId");
		String month = request.getParameter("month");	//按月查询
		month = month.substring(0, 4) + "-" +month.substring(4) ; // 201406  转换成 2014-06
		
		Database db = null; 
		
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			
			StaffDao sd = new StaffDao(db);
			allTakeOrder = sd.allTakeOrders(staffId,month); 
			System.out.println("月份   ：　"+month+"　　物流人员   "+staffId+"   所有 取衣 订单 记录  ："+allTakeOrder); 
			
			db.commit();  
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTakeOrder;
	}
}
