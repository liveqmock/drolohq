package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.UserExtraDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**  v0927
 *14/07/07  用户查询当前订单 (状态不是 37 的)
 * @author jeep
 *
 */
public class UserCurrentOrderAction extends Action{
	private static Log logger = LogFactory.getLog(UserCurrentOrderAction.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String res = "[]";
		int userId = Integer.parseInt(request.getParameter("userId"));
		Database db = null;
		
		try {
			db =  new SqlServer();
			UserExtraDao ueDao = new UserExtraDao(db);
			res = ueDao.findCurrentOrder(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		
		logger.debug("用户编号 :"+userId+"  用户查询当前订单结果:"+res);
		return res;  
	}
	
}
