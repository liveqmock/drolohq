package ouc.drolo.action.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.StaffExtraDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 *  物流人员 查询 分配给他的 未接收的订单 
 * @author jeep
 *
 */
public class StaffFindOrderAction extends Action {
	private static Log logger = LogFactory.getLog(StaffFindOrderAction.class); 
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String staffId = request.getParameter("staffId");
		Database db = null;
		String orderInfo = "" ;
		try {
			db =  new SqlServer();
			StaffExtraDao seDao = new StaffExtraDao(db);
			orderInfo =  seDao.findUnOrder(staffId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		logger.debug("STAFFID="+staffId+" STAFF　FIND　UNACCEPT　ORDER :" + orderInfo); 
		
		return orderInfo;
	}

}
