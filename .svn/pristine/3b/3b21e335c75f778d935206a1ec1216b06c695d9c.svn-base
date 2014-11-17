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

/**
 * 用户  评价 
 * @author jeep
 *
 */
public class UserJudgmentAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		String wlfw = request.getParameter("wlfw");
		String xypz = request.getParameter("xypz");
		
		String pjxq = request.getParameter("pjxq");
		pjxq = new String(pjxq.getBytes("ISO-8859-1"), "UTF-8");
		
		Database db=null;
		String str="-1";

			try {
				 db=new SqlServer();
				 db.setAutoCommit(false);
				 
				 UserDao userDao = new UserDao(db);
				 str = userDao.judge(orderId,wlfw,xypz,pjxq);
				 db.commit();
	
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
					try {
						db.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}finally{
				if(db!=null){
					db.close();
				}
			}

		return str; 
		
	}
		
}
