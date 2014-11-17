package ouc.drolo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.util.JSONStringer;
import ouc.drolo.util.CloseResource;
import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;

public class UserExtraDao extends Dao{

	public UserExtraDao(Database db) {
		super(db);
	}

	/*  v0927
	 * 查询用户的当前订单信息 
	 */
	public String findCurrentOrder(int userId) {
		String result = "[]";
		ResultSet rs = null;
		JSONStringer json = new JSONStringer();
		String sql = "SELECT orderId,status,runNo FROM _order WHERE userId = ? AND status<>37  AND status<>29 order by generateTime desc";
		PreparedStatement ps = null;
		try {
			ps = db.getPreparedStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				json.array();
				json.object()
				.key("orderId").value(rs.getString("orderId"))
				.key("status").value(rs.getString("status")).endObject();
				while (rs.next()) {
						json.object()
						.key("orderId").value(rs.getString("orderId"))
						.key("status").value(rs.getString("status")).endObject();
					}
				json.endArray();
				result = json.toString();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseResource.close(ps, rs); 
		}
		
		return  result;
	}

	
	/**
	 * 添加邀请码
	 * @param phone
	 * @param inviteCode 
	 * @return
	 */
	public int addInviteCode(String phone, String inviteCode) {
		String sql = "UPDATE _users SET inviteCode='"+inviteCode+"' where phone='"+phone+"'";
		
		int flag = db.executeUpdate(sql);
		
		return flag;
	}

	
	
}
