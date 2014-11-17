package ouc.drolo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONStringer;

import ouc.drolo.util.CloseResource;
import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;

public class StaffExtraDao extends Dao{

	public StaffExtraDao(Database db) {
		super(db);
	}

	
	/**   v0927 
	 * 查询 分配给该物流人员 未被接收的订单
	 * @return  
	 */
	public String findUnOrder(String staffId) {
		String result = "";
		ResultSet rs = null;
		JSONStringer json = new JSONStringer();
		String sql = "SELECT _order.large,_order.amount,_order.address,_order.note,_order.orderId,_order.generateTime,_order.status ,_order.longitude,_order.latitude,_order.userName,_order.takePhone AS phone"
				+ " FROM _order WHERE ( _order.status = 30 OR _order.status = 41) AND _order.staffId=? "
				+ "ORDER BY _order.generateTime DESC";
		PreparedStatement ps = null;
		try {
			ps =  db.getPreparedStatement(sql);
			ps.setString(1, staffId);
			rs = ps.executeQuery();
			if (rs.next()) {
				json.array();
				json.object().key("large").value(rs.getString("large"))
				.key("amount").value(rs.getString("amount"))
				.key("address").value(rs.getString("address"))
				.key("note").value(rs.getString("note"))
				.key("orderId").value(rs.getString("orderId"))
				.key("generateTime").value(rs.getString("generateTime"))
				.key("userName").value(rs.getString("userName"))
				.key("userPhone").value(rs.getString("phone"))
				.key("status").value(rs.getString("status"))
				.key("longitude").value(rs.getString("longitude"))
				.key("latitude").value(rs.getString("latitude")).endObject();
				while (rs.next()) {
					json.object().key("large").value(rs.getString("large"))
					.key("amount").value(rs.getString("amount"))
					.key("address").value(rs.getString("address"))
					.key("note").value(rs.getString("note"))
					.key("orderId").value(rs.getString("orderId"))
					.key("generateTime").value(rs.getString("generateTime"))
					.key("userName").value(rs.getString("userName"))
					.key("userPhone").value(rs.getString("phone"))
					.key("status").value(rs.getString("status"))
					.key("longitude").value(rs.getString("longitude"))
					.key("latitude").value(rs.getString("latitude"))
					.endObject();
				}
				json.endArray();
				result =  json.toString();
			} else {
				result = "[]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseResource.close(ps, rs);
		}
		
		return  result;
	}

}
