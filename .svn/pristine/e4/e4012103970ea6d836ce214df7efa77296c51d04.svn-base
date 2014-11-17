package ouc.drolo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONStringer;

import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;

public class AddressDao extends Dao{

	public AddressDao(Database db) {
		super(db);
	}

	/**
	 * 用户根据uid查询 地址
	 * @param uid
	 * @return
	 */
	public String findAddress(int uid) {
		ResultSet rs = null;
		JSONStringer json = new JSONStringer();     
		String sql = "SELECT address,longitude,latitude FROM _users_address WHERE userId = ?";
		PreparedStatement ps = db.getPreparedStatement(sql);
		try {
			ps.setInt(1, uid);
			rs = ps.executeQuery(); 
			if(rs.next()){
				json.array();
				json.object().key("address").value(rs.getString("address")).
				key("longitude").value(rs.getString("longitude")).
				key("latitude").value(rs.getString("latitude")).endObject();
				while(rs.next()){
					json.object().key("address").value(rs.getString("address")).
					key("longitude").value(rs.getString("longitude")).
					key("latitude").value(rs.getString("latitude")).endObject();
				}
				json.endArray();
				return json.toString();
			}else{
				return "[]";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "[]";
	}
	
	/**
	 * 用户添加地址
	 * @param uid
	 * @param address
	 * @return
	 */
	public String addAddress(String uid, String address,String longitude,String latitude) {
		String flag ="-1";
		ResultSet rs = null ;
		String sql = "INSERT INTO _users_address(userId,address,longitude,latitude)VALUES(?,?,?,?)";
		PreparedStatement ps = db.getPreparedStatement(sql);
		
		String  addSql = "SELECT * FROM _users_address WHERE address= ? AND userId=?" ;
		PreparedStatement addPs = db.getPreparedStatement(addSql);
		
		try {
			
			addPs.setString(1, address);
			addPs.setString(2, uid);
			rs = addPs.executeQuery();
			if(rs.next()){
				flag ="-3" ;  //地址已经存在 
			}else{
				ps.setString(1, uid);
				ps.setString(2, address);
				ps.setString(3, longitude);
				ps.setString(4, latitude);
				
				int flg = ps.executeUpdate();
					if(flg == 1){
						flag = "1";//成功
					}
					else{
						flag = "-1";//失败
					}
			}
						
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return "-2";
		}finally{
			try {
				rs.close();
				addPs.close();
				ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
		}
		return flag;
	}

	/*
	 * 删除 地址
	 */
	public String delAddress(String uid, String address) {
		String res = "-1";
		String sql = "DELETE FROM _users_address WHERE userId=? AND address=?";
		PreparedStatement ps = null ;
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, address);
			int ret = ps.executeUpdate();
			if(ret==1){
				res = "1";
			}
		} catch (SQLException e) {
			res = "-2" ;
			e.printStackTrace();
			
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

}
