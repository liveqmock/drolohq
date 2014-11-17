package ouc.drolo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONStringer;

import ouc.drolo.domain.MyClothes;
import ouc.drolo.util.CloseResource;
import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;

public class MyClothesDao extends Dao{

	public MyClothesDao(Database db) {
		super(db);
	}

	/**
	 * 物流人员存入一件衣服
	 * @param myClothes
	 * @return
	 */
	public String saveClothes(MyClothes myClothes,String userTel) {
		
		PreparedStatement ps = null ;
		
		String  staffId = myClothes.getStaffId();
		String orderId = myClothes.getOrderId();
		String clothesCat = myClothes.getClothesCat();
		String clothesName = myClothes.getClothesName();
		String price = myClothes.getPrice();
		int number = myClothes.getNumber();
		String note = myClothes.getNote();
		
		String serviceName = myClothes.getServiceName();
		String servicePrice = myClothes.getServicePrice();
		
		String flag = "-1";
		try {

			String sql = "INSERT INTO _myclothes (staffid,orderid,clothescat,clothesname,price,numbers,note,serviceName,servicePrice,userTel,status) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,32)";
				ps = db.getPreparedStatement(sql);
				
				ps.setString(1, staffId);
				ps.setString(2, orderId);
				ps.setString(3,clothesCat);
				ps.setString(4, clothesName);
				ps.setString(5,price);
				
				ps.setInt(6, number);
				ps.setString(7, note);
				ps.setString(8, serviceName);
				ps.setString(9, servicePrice); 
				ps.setString(10, userTel);
								
				int flg = ps.executeUpdate();
				ps.close();
				
				if(flg == 1){
					flag = "1";//成功
				}else{
					flag = "-1";//失败
				}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return "-2";
		}
		return flag; 
	}

	/**
	 * 获取订单生成时间
	 * @param orderId
	 * @return
	 */
	public String generateTime(String orderId){
		String time = "" ;
		
		String sql = "SELECT generateTime FROM _order where orderId = ?";
		ResultSet  rs = null ;
		PreparedStatement ps = null;
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, orderId);
			rs = ps.executeQuery();
			if(rs.next()){
				time = rs.getString("generateTime");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		
		return time; 
	}
	
	
	/**
	 * 一个订单下所有的衣服信息
	 * @param orderId
	 * @return
	 */
	public String allClothes(String orderId){
		JSONStringer json = new JSONStringer();
		String generateTime = ""; 
		String clothesNum = "" ;
		String totalPrice = "" ;
		
		String sql1 ="SELECT clothesName ,numbers,price,serviceName ,servicePrice,Id ,note FROM _myclothes WHERE orderId = ?";
		String sql2 = "SELECT SUM(numbers) ,SUM(price)+ SUM(servicePrice) FROM _myclothes WHERE orderId=? GROUP BY orderId" ;
		
		PreparedStatement ps1 = db.getPreparedStatement(sql1);
		PreparedStatement ps2 = db.getPreparedStatement(sql2);
		ResultSet rs2 = null ;
		ResultSet rs1 = null ;
		
		try {
			generateTime = generateTime(orderId) ;//获取订单生成时间
			
			ps2.setString(1, orderId);
			rs2 = ps2.executeQuery();
			if(rs2.next()){
				clothesNum = rs2.getString(1);
				totalPrice = rs2.getString(2);
			}
			
			ps1.setString(1, orderId);
			rs1 = ps1.executeQuery();
			if(rs1.next()){
				json.array();
				json.object().key("generateTime").value(generateTime).
				key("clothesName").value(rs1.getString("clothesName")).
				key("numbers").value(rs1.getString("numbers")).
				key("price").value(rs1.getString("price")).
				key("serviceName").value(rs1.getString("serviceName")).
				key("Id").value(rs1.getString("Id")).
				key("note").value(rs1.getString("note")).
				key("servicePrice").value(rs1.getString("servicePrice")).endObject();
				while(rs1.next()){
					json.object().key("generateTime").value(generateTime).
					key("clothesName").value(rs1.getString("clothesName")).
					key("numbers").value(rs1.getString("numbers")).
					key("price").value(rs1.getString("price")).
					key("Id").value(rs1.getString("Id")).
					key("serviceName").value(rs1.getString("serviceName")).
					key("note").value(rs1.getString("note")).
					key("servicePrice").value(rs1.getString("servicePrice")).endObject();
				}
				json.object().key("clothesNum").value(clothesNum).endObject();
				json.object().key("totalPrice").value(totalPrice).endObject();
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
			CloseResource.close(ps2, rs2);
			CloseResource.close(ps1, rs1);
		}
		return "" ;
	}

	/**
	 *  删除一件衣服 
	 * @param orderId
	 * @param clothesId
	 * @return
	 */
	public String deleteClothes(String orderId,String clothesId) {
		
		String flag = "-1";
		String sql = " DELETE from _myclothes WHERE orderId=? AND Id=? ";
		
		PreparedStatement ps = db.getPreparedStatement(sql);
		try {
			ps.setString(1, orderId);
			ps.setString(2, clothesId);
			
			 int flg = ps.executeUpdate();
			 if(flg==1){
				 flag = "1" ;  //删除成功
			 }else{
				 flag = "-1";
			 }
			ps.close();
						
		} catch (Exception e) { 
			System.out.println(e.getStackTrace());
			return "-2"; 
		}
		return flag;
	}
}
