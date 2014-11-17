package ouc.drolo.activity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ouc.drolo.util.CloseResource;
import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;

public class ActivityDao extends Dao{

	public ActivityDao(Database db) {
		super(db);
	}

	public int addActivity(ActivityBean activity) {
		int res = -1;
		String sql = "INSERT INTO activity(act_name,act_start,act_end,act_address,"
							+ "act_introd,act_image,act_imagexq) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, activity.getAct_name());
			ps.setString(2, activity.getAct_start());
			ps.setString(3, activity.getAct_end());
			ps.setString(4, activity.getAct_address());
			ps.setString(5, activity.getAct_introd());
			ps.setString(6, activity.getAct_image());
			ps.setString(7, activity.getAct_imagexq());
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, null);
		}
		
		return res;
	}
	

}
