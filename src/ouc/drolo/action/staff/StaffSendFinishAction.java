package ouc.drolo.action.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import ouc.drolo.dao.OperatorDao;
import ouc.drolo.dao.StaffDao;
import ouc.drolo.domain.Order;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;
import wph.iframework.push.OperatorPush;

/**
 * 物流人员   完成 送衣 订单 
 * @author jeep
 *  成功：1 ； 失败 ：-1 
 */
public class StaffSendFinishAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = "-1";
		String staffId = request.getParameter("staffId");
		String orderId = request.getParameter("orderId");
		String sendTime = request.getParameter("sendTime");
//		String phone = "";
		
		logger.debug("送衣完成时间  ：　"+sendTime+"　　　物流人员完成的  送衣 订单号      "+orderId);
		
		Database db = null;
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			StaffDao sd = new StaffDao(db);
			flag = sd.sendFinish(staffId,orderId,sendTime);
				
			Order order=new Order();
			OperatorDao dao=new OperatorDao(db);
			int f=dao.getuserPh(orderId,order);
			
			 JSONObject json = new JSONObject();
			 json.put("ordrId", orderId);
			 json.put("tel", order.getTakePhone());
			 json.put("name", order.getUserName());
			 System.out.println("name:"+order.getUserName());
			 json.put("num", order.getAmount());
			 json.put("address", order.getAddress());
			 
			 String oid=1+"";
			 OperatorPush.songyihuifang(oid, json.toString());
			 
/*			 UserInfoDao udao = new UserInfoDao(db);
			 phone = udao.getPhone(orderId);*/
			 
			 db.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		
		// JPUSH 
/*		MyJPush myJpush = new MyJPush();
		String msgContent = "您的衣服已送回，欢迎使用！";
		myJpush.aliasSend(phone,msgContent);*/
		
		return flag;
	}
	
}
