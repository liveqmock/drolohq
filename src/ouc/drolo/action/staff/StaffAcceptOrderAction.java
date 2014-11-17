package ouc.drolo.action.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.action.diaodu.Dispatcher;
import ouc.drolo.dao.OrderDao;
import ouc.drolo.dao.UserInfoDao;
import ouc.drolo.domain.Order;
import ouc.drolo.jpush.api.utils.MyJPush;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 物流人员  接单 
 * @author jeep
 */
public class StaffAcceptOrderAction extends Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = "-1" ;
		int isOrder = -1 ;
		String phone = "";
		
		Database db =null ;
		String orderId = request.getParameter("orderId");
		String staffId = request.getParameter("staffId"); 
		logger.debug("orderID=" +orderId +"  staffID="+staffId);
		
		Dispatcher dispatcher = Dispatcher.getInstance();
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			
			OrderDao od = new OrderDao(db);
			
			isOrder =od.isStaffOrder(staffId, orderId);
			if(isOrder == 1){
				Order order=dispatcher.getOrder(orderId); 
				logger.debug("------" + order.getOrderId());
				order.cancel();
				dispatcher.remove(orderId);
				//  dispatcher.getOrders().remove(staffId);
				flag = od.acceptOrder(staffId,orderId);
				
				UserInfoDao udao = new UserInfoDao(db);
				phone = udao.getPhone(orderId);
				logger.debug("用户电话： " + phone); 
				
				// JPUSH 
				MyJPush myJpush = new MyJPush();
				String msgContent = "您的订单有更新，请注意查看！";
				myJpush.aliasSend(phone,msgContent);
			}
			  
			db.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}

		return flag;
	}

}
