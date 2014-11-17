package ouc.drolo.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.CzkDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 根据 卡号
 * 		用户 绑定  充值卡
 * 		 
 * @author jeep
 *
 */
public class UserBdCzkAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String tel = request.getParameter("tel");
		String czkNum = request.getParameter("czkNum");
		
		System.out.println("用户电话 : "+tel+"           充值卡 : "+czkNum); 
		
		Database db=null;
		String str="";
			try {
				 db=new SqlServer();
				 db.setAutoCommit(false);
				 
				CzkDao cd = new CzkDao(db);
				 str = cd.bdCzk(tel, czkNum) ;
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
