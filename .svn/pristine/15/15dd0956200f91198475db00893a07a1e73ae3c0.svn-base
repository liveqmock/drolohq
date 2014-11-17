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

/*
 * 08/05  物流人员修改订单地址
 */
public class StaffUpdateOrderAddAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = "-1";
		Database db = null;
		
		String orderId = request.getParameter("orderId");
		String address = request.getParameter("address");
		address = new String(address.getBytes("ISO-8859-1"), "UTF-8");
		
		try {
			db = new SqlServer();
			StaffDao sDao = new StaffDao(db);
			flag = sDao.updateAddress(orderId,address);
			
			logger.debug("物流修改地址： "+address+"  订单号："+orderId +"  结果:"+flag); 
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
