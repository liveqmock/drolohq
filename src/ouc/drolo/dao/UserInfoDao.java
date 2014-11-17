package ouc.drolo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONStringer;

import ouc.drolo.util.CloseResource;
import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;

public class UserInfoDao extends Dao {

	public UserInfoDao(Database db) {
		super(db);
	}
	
	public String getPhone(String orderId){
		String phone = "";
		String sql = "SELECT takePhone FROM _order WHERE orderid=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, orderId);
			rs = ps.executeQuery();
			if(rs.next()){
				phone = rs.getString("takePhone");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		
		
		return phone;
	}

	/**
	 * 添加异常信息
	 * @param exception
	 * @return
	 */
	public int addException(String exception) {
		String sql = "INSERT INTO iosexception VALUES(?)";
		PreparedStatement ps = null;
		int flag = -1;
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, exception);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	/** v0925
	 * 根据用户ID 查询 订单号 和 状态 
	 * 
	 * @param userId
	 * @return
	 */
	public String findOrder(int userId) {
		ResultSet rs = null;
		JSONStringer json = new JSONStringer();
		String sql = "SELECT orderId,status FROM _order WHERE userId = ? order by generateTime desc";
		PreparedStatement ps = db.getPreparedStatement(sql);
		try {
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				json.array();
				json.object()
				.key("orderId").value(rs.getString("orderId"))
				.key("status").value(rs.getString("status"))
				.endObject();
				
				while (rs.next()) {
					json.object()
					.key("orderId").value(rs.getString("orderId"))
					.key("status").value(rs.getString("status"))
					.endObject();
				}
				json.endArray();

				return json.toString();
			} else {
				return "[]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseResource.close(ps, rs); 
		}
		return "[]";
	}
	
	
}
