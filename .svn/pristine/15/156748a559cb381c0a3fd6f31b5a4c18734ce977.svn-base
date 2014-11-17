package ouc.drolo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONStringer;

import ouc.drolo.util.CloseResource;
import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;

public class ClothesDao  extends Dao{

	public ClothesDao(Database db) {
		super(db);
	}
	
	/**
	 * 查询大件
	 * @return
	 */
	public String findMaincat(){
		ResultSet rs = null;
		JSONStringer json = new JSONStringer();
		String sql = "SELECT id,maincat FROM _clothes_cat";
		PreparedStatement ps = db.getPreparedStatement(sql);
		try {
			rs = ps.executeQuery();
			if(rs.next()){
				try {
					json.array();
					json.object().key("id").value(rs.getInt("id")).
					key("clothesCat").value(rs.getString("clothes_cat")).endObject();
					while(rs.next()){
						json.object().key("id").value(rs.getInt("id")).
						key("clothesCat").value(rs.getString("clothes_cat")).endObject();
					}
					json.endArray();
					return json.toString();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}else{
				return "[]";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		return "[]";
	}

	/**
	 *在  laundry中     查询衣物类别
	 *
	 *           在 app 衣物类别中 查询 0608
	 *
	 * @return
	 */
	public String findClothesCat(){
		ResultSet rs = null;
		JSONStringer json = new JSONStringer();
		String sql = "SELECT id,clothes_cat FROM _clothes_cat";
		PreparedStatement ps = db.getPreparedStatement(sql);
		try {
			rs = ps.executeQuery();
			if(rs.next()){
				try {
					json.array();
					json.object().key("id").value(rs.getInt("id")).
					key("clothesCat").value(rs.getString("clothes_cat")).endObject();
					while(rs.next()){
						json.object().key("id").value(rs.getInt("id")).
						key("clothesCat").value(rs.getString("clothes_cat")).endObject();
					}
					json.endArray();
					return json.toString();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}else{
				return "[]";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		return "[]";
	}
	
	/** 用户端
	 * 根据衣物类别查询 衣物名称  、 衣物价格
	 * @param clothesCat
	 * @return
	 */
	public String findClothesPrice(String clothesCat){
		ResultSet rs = null;
		JSONStringer json = new JSONStringer();     
		
		String sql = "SELECT clothes_name,clothes_price FROM _clothes_price  WHERE clothes_cat =?";
		PreparedStatement ps = db.getPreparedStatement(sql);
		try {
			ps.setString(1, clothesCat);
			rs = ps.executeQuery();
			if(rs.next()){
				try {
					json.array();
					json.object().key("clothes_name").value(rs.getString("clothes_name"))
					.key("clothes_price").value(rs.getString("clothes_price")).endObject();
					while(rs.next()){
						json.object().key("clothes_name").value(rs.getString("clothes_name"))
						.key("clothes_price").value(rs.getString("clothes_price")).endObject();
					}
					json.endArray();
					return json.toString();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}else{
				return "[]";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			 CloseResource.close(ps, rs); 
		}
		return "[]";
	}
	
	/**
	 * 在 laundry 中   查询  衣物价格(改了)
	 * 
	 *   在app 中 查询 衣物 价格  0608
	 * @return
	 */
	public String findClothesPrice(){
		ResultSet rs = null;
		JSONStringer json = new JSONStringer();     
		
		String sql = "SELECT clothes_cat AS clothesCat, clothes_name  AS clothesName ,clothes_price AS clothesPrice  FROM  _clothes_price";
		PreparedStatement ps = db.getPreparedStatement(sql);
		try {
			rs = ps.executeQuery();
			if(rs.next()){  
				try {
					json.array();
					json.object().key("clothesCat").value(rs.getString("clothesCat")).
					key("clothesName").value(rs.getString("clothesName")).
					key("clothesPrice").value(rs.getString("clothesPrice")).endObject();
					
					while(rs.next()){
						json.object().key("clothesCat").value(rs.getString("clothesCat")).
						key("clothesName").value(rs.getString("clothesName")).
						key("clothesPrice").value(rs.getString("clothesPrice")).endObject();
					}
					json.endArray();
					return json.toString();
				} catch (JSONException e) { 
					e.printStackTrace();
				}
				
			}else{
				return "{}";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "{}";
	}

	public String findClothesName(String clothesCat) {
		return null;
	}
}
