package ouc.drolo.action.kf;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import ouc.drolo.util.CloseResource;
import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.Page;
import wph.iframework.dao.db.ResultSetCallback;

public class KfDao extends Dao{

	
	public KfDao(Database db) {
		super(db);
	}
	
	
	public boolean order(final Page<Kf> page)
	{
	    logger.debug("通过分页bean，获取ad中全部信息" + page + "linker====" + db);
	    if (page == null)
	    {
	        throw new NullPointerException();
	    }
	    
	    if (db == null)
	    {
	        return false;
	    }
	    
	    // 构建 SQL
	    StringBuffer sb = new StringBuffer();
	    final List<Kf> listKf = new ArrayList<Kf>();
	    sb.append("SELECT id,name,tel,sex,kfid,staffid FROM kf");
	    String sql = sb.toString();
        String col = "id";
	    int ret = db.executeQuery(sql, col, page, new ResultSetCallback()
	    {
	        @Override
	        public int handle(ResultSet rs) throws SQLException{
	            if (rs == null)
	            {
	                return -1;
	            }
	            
	            try
	            {
	                while (rs.next()){ 
	                	System.err.println("czzzzzzzzzzzzz"+rs.getString("name"));
	                	Kf kfu = new Kf(rs.getString("name"), rs.getString("tel"), rs.getInt("sex"),
								rs.getString("kfid"), rs.getString("staffId"));
						listKf.add(kfu);
	                    page.setRowCount(rs.getInt("__count")); 
	                }
	            }
	            catch (SQLException e)
	            {
	                e.printStackTrace();
	                return -1;
	            }
	            finally
	            {
	                if (rs != null)
	                {
	                    try
	                    {
	                        rs.close();
	                    }
	                    catch (SQLException e)
	                    {
	                        e.printStackTrace();
	                    }
	                }
	            }
	            return 0;
	        }
	    });
	    
	    if (ret == -1)
	    {
	        return false;
	    }
	    else
	    {
	        page.setDataList(listKf);
	        return true;
	    }
	}
	/**
	 * 返回所有 客服人员信息
	 * @return
	 */
	public List<Kf> listKf(){
		List<Kf> listKf = new ArrayList<Kf>();
		String sql = "SELECT * FROM kf";
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		
		try {
			ps = db.getPreparedStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				Kf kf = new Kf(rs.getString("name"), rs.getString("tel"), rs.getInt("sex"),
						rs.getString("kfid"), rs.getString("staffId"));
				listKf.add(kf);
				while(rs.next()){
					Kf kfu = new Kf(rs.getString("name"), rs.getString("tel"), rs.getInt("sex"),
							rs.getString("kfid"), rs.getString("staffId"));
					listKf.add(kfu);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		
		return listKf;
	}
	
	/**
	 * 更新一个 客服人员  信息
	 * @param name
	 * @param tel
	 * @param staffId
	 * @param kfid 
	 * @return
	 */
	public String updateKf(String name, String tel, String staffId, String kfid) {
		PreparedStatement ps = null;
		String sql = "update kf set name=?,tel=?,staffId=? where kfid=?";
		String flg = "-1";
		
		System.err.println("客服 工号   :　　" + kfid+"  姓名 ：     "+name+"  电话   ：    "+tel);
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, name);
			ps.setString(2, tel);
			ps.setString(3, staffId);
			ps.setString(4, kfid);
			
			int res = ps.executeUpdate();
			if(res == 1){
				flg = "1" ;
			}
			System.err.println(res+"          "+"xxxxxxxxxxxx   " + flg); 
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flg ;
	}
	
	/**
	 *  根据 客服工号 查询  客服人员 信息
	 * @param kfid
	 * @return
	 */
	public String findKfBykfid(String kfid) {
		String sql = "SELECT * FROM kf WHERE kfid=?";
		PreparedStatement ps = null; 
		ResultSet rs = null; 
		
		JSONStringer json = new JSONStringer();
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, kfid);
			
			rs = ps.executeQuery();
			if(rs.next()){
				json.object().key("name").value(rs.getString("name")).
				key("tel").value(rs.getString("tel")).key("kfid").value(rs.getString("kfid")).
				key("staffId").value(rs.getString("staffId")).endObject();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs);
		}
		return json.toString();
		
	}
	
	/**
	 * 删除一个 客服人员 信息
	 * @param kfid
	 * @return
	 */
	public int deleteKf(String kfid) {
		int res = -1 ;
		PreparedStatement ps = null ;
		String sql = "DELETE FROM kf WHERE kfid=?";
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, kfid);
			
			res = ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	/**
	 * 添加 一个客服人员 信息 
	 * @param name
	 * @param tel
	 * @param staffId
	 * @param kfid
	 * @param password
	 * @return
	 */
	public int addKf(String name, String tel, String staffId, String kfid,
			String password) {
		int res = -1 ;
		PreparedStatement ps = null ;
		String sql = "INSERT INTO kf(name,password,tel,staffId,kfid) VALUES(?,?,?,?,?)";
		
		try {
			ps = db.getPreparedStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, tel);
			ps.setString(4, staffId);
			ps.setString(5, kfid);
			
			res = ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 *   分类 查询  客服信息
	 * @param page
	 * @param leibie
	 * @param questcontext
	 * @return
	 */
	public boolean kffl(final Page<Kf> page, int leibie, String questcontext) {
		boolean flag=false;
		if (page == null)
	    {
	        throw new NullPointerException();
	    }
	    
	    if (db == null)
	    {
	        return false;
	    }
	    
	    final List<Kf> list = new ArrayList<Kf>();
	    String sql;
	    String col;
	    if(leibie==1){ // 按姓名查询
		    StringBuffer sb = new StringBuffer();
		    sb.append("SELECT id,name,password,tel,staffId,kfid,sex FROM kf WHERE name='"+questcontext+"' ");
		    sql = sb.toString();
		    col = "id";
	    }else{   //  按 电话 查询 
	    	StringBuffer sb = new StringBuffer();
	  	    sb.append("SELECT id,name,password,tel,staffId,kfid,sex FROM kf WHERE tel='"+questcontext+"' ");
	  	    sql = sb.toString();
	  	    col = "id";
	    }
	   
		int ret = db.executeQuery(sql, col, page, new ResultSetCallback(){
	        @Override
	        public int handle(ResultSet rs) throws SQLException
	        {
	            if (rs == null)
	            {
	                return -1;
	            }
	            
	            try
	            {
	                while (rs.next())
	                {
	                    page.setRowCount(rs.getInt("__count"));
	                	
	                	Kf kfu = new Kf(rs.getString("name"), rs.getString("tel"), rs.getInt("sex"),
								rs.getString("kfid"), rs.getString("staffId"));
	                    
	                    list.add(kfu);
	                }
	            }
	            catch (SQLException e)
	            {
	                e.printStackTrace();
	                return -1;
	            }
	            finally
	            {
	                if (rs != null)
	                {
	                    try
	                    {
	                        rs.close();
	                    }
	                    catch (SQLException e)
	                    {
	                        e.printStackTrace();
	                    }
	                }
	            }
	            return 0;
	        }
	    });
	    
	    if (ret == -1){
	        return false;
	    }else{
	        page.setDataList(list);
	        return true;
	    }
		
	}
	
	
}
