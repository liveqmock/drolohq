package ouc.drolo.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.ContactDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/** 14/07/07
 *  删除  联系人
 * @author jeep
 *
 */
public class UserDelContactAction extends Action{
	private static Log logger = LogFactory.getLog(UserDelContactAction.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "0";
		String uid = request.getParameter("uid");
		String cTel = request.getParameter("cTel");
		
		Database db = null;
		try {
			db =  new SqlServer();
			ContactDao cDao = new ContactDao(db);
			result = cDao.delContact(uid, cTel);
			
			logger.debug("用户编号："+uid+"   删除联系人  ： "+cTel+"  结果："+result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return result;
	}
	
}
