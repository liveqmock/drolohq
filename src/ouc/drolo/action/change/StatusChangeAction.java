package ouc.drolo.action.change;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.ChangeDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 订单状态改变
 * @author jeep
 *
 */
public class StatusChangeAction extends Action{
	private static Log logger = LogFactory.getLog(StatusChangeAction.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int ret = -1;
		Database db = null ;
		String orderID = request.getParameter("orderID");
//		String beforeStatus = request.getParameter("beforeStatus"); //改变前状态
		String nextStatus = request.getParameter("nextStatus");
		
		try {
			db = new SqlServer();
			ChangeDao cDao = new ChangeDao(db);
			ret = cDao.statusChange(orderID,nextStatus);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{ 
			db.close();
		}
		logger.debug("orderID:"+orderID+" nextStatus:"+nextStatus+" RESULT:"+ ret);
		
		return String.valueOf(ret); 
	}

}
