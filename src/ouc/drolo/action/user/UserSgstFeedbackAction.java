package ouc.drolo.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.UserDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

public class UserSgstFeedbackAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ret = "-1";
		
		String uid = request.getParameter("uid");
		String sgst = request.getParameter("sgst");
		sgst = new String(sgst.getBytes("ISO-8859-1"), "UTF-8"); 
		
		Database db = null ;
		
		try {
			db = new SqlServer();
			UserDao uDao = new UserDao(db);
			ret = uDao.addSgstFeedBack(uid,sgst);
			
			logger.debug(uid + " : " + sgst); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		
		
		return ret;
	}

}
