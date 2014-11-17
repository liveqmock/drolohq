package ouc.drolo.action.kf;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

public class DeleteKfAction extends Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String kfid = request.getParameter("kfid");
		Database db = null; 
		String flag = "-1";
		
		System.err.println("删除的 客服 工号   ：  " + kfid); 
		
		try {
			db = new SqlServer();
			KfDao kDao = new KfDao(db);
			int flg = kDao.deleteKf(kfid);
			if(flg == 1){
				flag = "1" ;
			}
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
