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
 *  根据 用户 userId 查询   联系人信息
 * @author jeep
 *
 */
public class UserFindContactAction extends Action{
	private static Log logger = LogFactory.getLog(UserFindContactAction.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "0";
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		Database db = null;
		try {
			db =  new SqlServer();
			ContactDao cDao = new ContactDao(db);
			result = cDao.findContact(uid);
			
			logger.debug("用户查询联系人： "+result);
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
