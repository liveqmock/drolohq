package ouc.drolo.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.UserInfoDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 *  接收ios 异常信息
 * @author jeep
 *
 */
public class UserExceptionAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int ret = -1;
		String exception = request.getParameter("exception");
		exception = new String(exception.getBytes("ISO-8859-1"), "UTF-8");
		
		Database db = null;
		
		try {
			db = new SqlServer();
			UserInfoDao  uiDao = new UserInfoDao(db);
			
			ret = uiDao.addException(exception);
			logger.debug("exception :" +exception+"  ret: "+ret); 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		
		return  String.valueOf(ret); 
	}

}
