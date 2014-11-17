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
 * 编辑客服人员信息
 * @author jeep
 *
 */
public class EditKfAction extends Action {

	public static String trull(String str)
    {
        return str == null ? "" : str.trim();
    }
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = "";
		String name = trull(request.getParameter("editname"));
		String tel = trull(request.getParameter("editphone"));
		String staffId = trull(request.getParameter("editStaffId"));
		String kfid = trull(request.getParameter("kfid"));
		
		try {
			Database db = null; 
			db = new SqlServer();
			KfDao kDao = new KfDao(db);
			
			flag = kDao.updateKf(name,tel,staffId,kfid);
			
			System.err.println(" 更新 结果    ：    " + flag); 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
