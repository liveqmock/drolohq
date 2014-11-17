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
 * 获取 客服人员 信息
 * @author jeep
 *
 */
public class EditKfInfoAction extends Action {

	public static String trull(String str)
    {
        return str == null ? "" : str.trim();
    }
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String kfid = trull(request.getParameter("kfid"));
		String flag = "";
		
		try {
			Database db = null; 
			db = new SqlServer();
			KfDao kDao = new KfDao(db);
			
			flag = kDao.findKfBykfid(kfid);
			System.err.println("客服人员信息 :　　" + flag); 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

}
