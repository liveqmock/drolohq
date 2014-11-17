package ouc.drolo.action.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.StaffDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**   v0925
 * 物流人员  查询 送衣 订单
 * 	   在  _order 表中 订单 状态 为  10
 * @author jeep
 *
 */
public class StaffSendOrderAction extends Action {
	private static Log logger = LogFactory.getLog(StaffSendOrderAction.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String staffId = request.getParameter("staffId");
		
		String orderInfo = "" ;
		try {
			Database db =  new SqlServer();
			StaffDao sd = new StaffDao(db); 
			orderInfo = sd.findSendOrder(staffId); 
			db.close();
			
			logger.debug("STAFF FIND SEND ORDER： "+orderInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orderInfo;
	}

}
