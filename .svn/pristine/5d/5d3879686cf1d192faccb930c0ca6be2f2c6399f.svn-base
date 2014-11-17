package ouc.drolo.action.kf;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 添加  客服人员信息
 * @author jeep
 *
 */
public class AddKfAction extends Action {

	public static String trull(String str){
        return str == null ? "" : str.trim();
    }
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = trull(request.getParameter("editnameaddone"));
		String tel = trull(request.getParameter("editphoneaddone"));
		String staffId = trull(request.getParameter("editstaffNumaddone"));
		String kfid = trull(request.getParameter("editkfNumaddone"));
		String password=trull(request.getParameter("editpasswdaddone"));
		
		System.err.println("客服工号   ： " + kfid +" 姓名       "+name); 
		
		int res = -1;
		
		try {
			Database db = null; 
			db = new SqlServer();
			KfDao kDao = new KfDao(db);
			
			res = kDao.addKf(name,tel,staffId,kfid,password);
			
			System.err.println(" 添加 结果    ：    " + res); 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res+"";
	}

}
