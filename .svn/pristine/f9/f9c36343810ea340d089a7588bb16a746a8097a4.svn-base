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
 * 物流人员  查询 送衣 订单 详情
 * @author jeep
 *
 */
public class StaffSendOrderXqAction extends Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String orderId = request.getParameter("orderId");
		
		String orderXqInfo = "" ;
		try {
			Database dbDatabase =  new SqlServer();
			dbDatabase.setAutoCommit(false);
			StaffDao sDao = new StaffDao(dbDatabase);
			orderXqInfo = sDao.findSendOrderXq(orderId); 
			dbDatabase.commit();
			dbDatabase.close();
			System.out.println("订单号 ：　"+orderId+"         物流人员查询 送衣订单 详情 ：       "+orderXqInfo);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderXqInfo;
	}

}
