package ouc.drolo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.util.JSONStringer;
import ouc.drolo.util.CloseResource;
import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;

public class ContactDao extends Dao{

	public ContactDao(Database db) {
		super(db);
	}
	
	public int isExist(String uid,String cTel){
		int res = -2;
		String sql = " SELECT * FROM _contact WHERE user_id=? AND contact_tel=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, cTel);
			rs = ps.executeQuery();
			
			if(rs.next()){
				res = -2;
			}else{
				res = 1 ;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	/**  14/07/07
	 * 用户添加 联系人信息
	 * @param uid
	 * @param address
	 * @return
	 */
	public String addContact(String uid, String cName,String cTel) {
		String flag ="-1";
		ResultSet rs = null ;
		String sql = "INSERT INTO _contact(user_id,contact_name,contact_tel)VALUES(?,?,?) ";
		PreparedStatement ps = db.getPreparedStatement(sql);
		
		if(isExist(uid, cTel)==1){
			try {
				ps.setString(1, uid);
				ps.setString(2, cName);
				ps.setString(3, cTel);
				
				int flg = ps.executeUpdate();
				if(flg == 1){
					flag = "1";//成功
				}
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
				flag = "-2";
			}finally{
				CloseResource.close(ps, rs); 
			}
		}else{
			flag = "-1";
		}

		return flag;
	}

	/*
	 * 用户查询联系人信息 
	 */
	public String findContact(int uid) {
		ResultSet rs = null;
		JSONStringer json = new JSONStringer();     
		String sql = "SELECT contact_name,contact_tel  FROM _contact WHERE user_id=?";
		PreparedStatement ps = db.getPreparedStatement(sql);
		try {
			ps.setInt(1, uid);
			rs = ps.executeQuery(); 
			if(rs.next()){
				json.array();
				json.object().key("contact_name").value(rs.getString("contact_name")).
				key("contact_tel").value(rs.getString("contact_tel")).endObject();
				while(rs.next()){
					json.object().key("contact_name").value(rs.getString("contact_name")).
					key("contact_tel").value(rs.getString("contact_tel")).endObject();
				}
				json.endArray();
				return json.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		
		return "[]";
	}
	
	/*  14/07/07
	 *   删除一个联系人 
	 */
	public String delContact(String uid,String cTel){
		String res = "-1";
		String sql ="DELETE FROM _contact WHERE user_id=? AND contact_tel=?";
		PreparedStatement ps = null;
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, cTel);
			int ret = ps.executeUpdate();
			if(ret != 0){
				res ="1";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			res = "-2";
		}finally{
			CloseResource.close(ps, null);
		}
		
		return res;
	}

}
