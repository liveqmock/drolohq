package ouc.drolo.action.staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.StaffDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 物流人员  修改 经纬度  信息
 * @author jeep
 *
 */
public class StaffChangeGpsAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = "-1";
		String staffId = request.getParameter("staffId");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		
		System.err.println("物流人员上传 经纬度信息      "+longitude+"  "+latitude);
		
		Database db = null;
		try {
			db = new SqlServer();
			
			db.setAutoCommit(false);
			StaffDao sd = new StaffDao(db);
			flag = sd.changeGps(staffId,longitude,latitude);
			db.commit();
			
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return flag;
	}
	
}
