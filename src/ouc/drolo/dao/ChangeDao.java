package ouc.drolo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import net.sf.json.util.JSONStringer;
import ouc.drolo.util.CloseResource;
import ouc.drolo.util.Yzm;
import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;

public class ChangeDao extends Dao{

	public ChangeDao(Database db) {
		super(db);
	}

	/**
	 * 获取到唯一的邀请码
	 * @return
	 */
	public String getInviteCode(){
		String sql = "SELECT COUNT(*)AS number FROM _users WHERE inviteCode=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = true ;
		String inviteCode = null;
		while(flag){
			inviteCode = Yzm.getRandomString(6);
			try {
				ps = db.getPreparedStatement(sql);
				ps.setString(1, inviteCode);
				rs = ps.executeQuery();
				if(rs.next()){
					int number = rs.getInt("number");
					if(number==0){
						flag = false;
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				CloseResource.close(ps, rs); 
			}
		}
		
		return inviteCode;
	}
	
	/**
	 * 根据邀请码查询到用户手机号
	 * @param inviteCode
	 * @return
	 */
	public String getPhoneByInviteCode(String inviteCode){
		String phone ="1111";
		String sql = "SELECT phone FROM _users WHERE inviteCode=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, inviteCode);
			rs = ps.executeQuery();
			if(rs.next()){
				phone = rs.getString("phone");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		
		return phone ;
	}
	
	
	/**
	 * 根据包号 获取对应的orderID,和 用户手机号
	 * @param packageID
	 * @return
	 */
	public String getOrderIDPhone(String packageID) {
		String maxOrderID = "";
		HashMap<String, String> map = null;
		HashMap<String, String> priceMap = null;
		String sql = "SELECT MAX(orderId) AS maxOrderID  FROM  _order WHERE runNo=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONStringer json = null;
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, packageID); 
			rs = ps.executeQuery();
			if(rs.next()){
				maxOrderID = rs.getString("maxOrderID");
				priceMap = getPhone(maxOrderID); //根据订单号获取 手机号
				String phone = priceMap.get("phone");
				String lastprice = priceMap.get("lastPrice");
				String disrate = priceMap.get("disrate");
				String userName = priceMap.get("userName");
				
				map = getStaffInfo(maxOrderID);  //根据订单号获取 物流工号 、物流手机号
				String staffID = map.get("staffID");
				String staffPhone = map.get("staffPhone");
				
				json = new JSONStringer();
				json.object().key("packageID").value(packageID)
				.key("maxOrderID").value(maxOrderID)
				.key("phone").value(phone)
				.key("StaffID").value(staffID)
				.key("staffPhone").value(staffPhone)
				.key("lastprice").value(lastprice)
				.key("disrate").value(disrate) 
				.key("userName").value(userName) 
				.endObject();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		
		return json.toString();
	}
	
	/**
	 * 根据订单号获取 手机号
	 * @param orderID
	 * @return
	 */
	public HashMap<String, String> getPhone(String orderID){
//		String phone ="";
		HashMap<String, String> map = new HashMap<String, String>();
//		String sql = "SELECT takePhone  FROM _order WHERE orderId=?";
		String sql = "SELECT o.takePhone AS takephone,o.userName AS userName,f.lastPrice AS lastprice,f.disRate AS disrate  "
						+ "FROM _order o,_financial f WHERE o.orderId=f.orderId and o.orderId=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, orderID);
			rs = ps.executeQuery();
			if(rs.next()){
				map.put("userName", rs.getString("userName")); 
				map.put("phone", rs.getString("takePhone"));
				map.put("lastPrice", rs.getString("lastprice")); 
				map.put("disrate", rs.getString("disrate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		
		return map;
	}
	
	/**
	 * 根据订单号获取 物流工号 、物流手机号
	 * @param orderID
	 * @return
	 */
	public HashMap<String,String> getStaffInfo(String orderID){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "SELECT  o.staffId AS staffID,s.tel AS staffPhone FROM _order o,_staff s"
						+ " WHERE o.staffId=s.staffId and orderId=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, orderID);
			rs = ps.executeQuery(); 
			if(rs.next()){
				map.put("staffID",rs.getString("staffID"));
				map.put("staffPhone",rs.getString("staffPhone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		
		return map;
	}

	/**
	 * 订单状态改变
	 * @param orderID
	 * @param nextStatus
	 * @return
	 */
	public int statusChange(String orderID, String nextStatus) {
		int ret = -1;
		PreparedStatement ps = null;
		String sql = "UPDATE _order SET status=? WHERE orderId=?";
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, nextStatus);
			ps.setString(2, orderID);
			ret = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, null); 
		}
		
		return ret;
	}
	
	/**
	 * 获取衣物信息
	 * @param orderID
	 * @return
	 */
	public String getClothesInfo(String orderID) {
		String info = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM _myclothes  WHERE orderId=?";
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, orderID);
			rs = ps.executeQuery();
			if(rs.next()){
				JSONStringer json = new JSONStringer();
				json.array();
				json.object()
				.key("clothesID").value(rs.getInt("id"))  
				.key("category").value(rs.getString("clothesCat"))
				.key("clothesName").value(rs.getString("clothesName"))
				.key("price").value(rs.getString("price"))
				.endObject();
				while(rs.next()){
					json.object()
					.key("clothesID").value(rs.getInt("id"))  
					.key("category").value(rs.getString("clothesCat"))
					.key("clothesName").value(rs.getString("clothesName"))
					.key("price").value(rs.getString("price"))
					.endObject();
				}
				json.endArray();
				info = json.toString();
			}  // end if
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		
		return info;
	}

	
}
