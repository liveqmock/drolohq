package ouc.drolo.action.staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.StaffDao;
import ouc.drolo.util.order.FileEveryDaySerialNumber;
import ouc.drolo.util.order.SerialNumber;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 物流端 生成 订单 
 * @author jeep
 *
 */
public class StaffOrderGenerateAction extends Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		SerialNumber serial = new SFileEveryDaySerialNumber(4, "SEveryDaySerialNumber.dat");
		SerialNumber serial = new FileEveryDaySerialNumber(4, "EveryDaySerialNumber.dat");
		String orderId =serial.getSerialNumber();
		
		String flag = "0";
		
		String staffId = request.getParameter("staffId");
		String takePhone = request.getParameter("takePhone"); // 收衣电话 
		String address = request.getParameter("address");
		address = new String(address.getBytes("ISO-8859-1"), "UTF-8");

		String userName = request.getParameter("name");
		userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
		
		String note = request.getParameter("note");
		note = new String(note.getBytes("ISO-8859-1"), "UTF-8");
		
		System.out.println("订单号 ： "+orderId+"      用户下单地址   :   " + address+"  用户电话 :" + takePhone); 
	
       	Database db = null;
		try {
			db = new SqlServer();
			
			StaffDao sd = new StaffDao(db);
			flag = sd.generateOrder(staffId, takePhone, address, userName, note, orderId);
			
			db.close();
		}  catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
