package ouc.drolo.action.kf;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ouc.drolo.util.CloseResource;
import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.Page;
import wph.iframework.dao.db.ResultSetCallback;

public class UserInfoDao  extends Dao {

	public UserInfoDao(Database db) {
		super(db);
	}

	public boolean userInfo(final Page<UserInfo> page)
	{
	    logger.debug("通过分页bean，获取 用户 信息 " + page + "linker====" + db);
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
	    final List<UserInfo> listUser = new ArrayList<UserInfo>();
	  //  sb.append("SELECT u.id AS id,u.phone AS phone,u.zctime AS zctime,o.orderId AS orderId,o.address AS address,o.userName AS name "
	  // 				+ "FROM  _users u LEFT JOIN _order o ON o.userId = u.id ");  //0630
	    sb.append("select u.id AS id,u.phone AS phone,u.zctime AS zctime,"
	    		  + "_order.orderId as orderId,_order.address as address ,u.name as name "
	    		  + "from _users u "
	    		  + "outer apply(select top 1 *  from _order o  where  o.userId=u.id )_order");  //0630
	    
	    String sql = sb.toString();
        String col = "zctime";   //按注册时间 Desc 排序
        
	    int ret = db.executeQuery1(sql, col, page, new ResultSetCallback()
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
	                	
	                	UserInfo user = new UserInfo(rs.getString("phone"), rs.getString("orderId"), 
	                					rs.getInt("id"), rs.getString("address"),rs.getString("zctime"),rs.getString("name")); 
	                	listUser.add(user);
						
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
	        page.setDataList(listUser);
	        return true;
	    }
	}
	
	/**
	 * 返回所有 客服人员信息
	 * @return
	 */
	public List<UserInfo> listUser(){
		List<UserInfo> listuser = new ArrayList<UserInfo>();
		String sql = "	select u.id AS id,u.phone AS phone,u.zctime AS zctime, o.orderId as orderId,"
					+ "o.address as address ,u.name as name  "
					+ "from _users u "
					+ "outer apply(select top 1 *  from _order o  where  o.userId=u.id )_order"; //0630
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		
		try {
			ps = db.getPreparedStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				UserInfo user = new UserInfo(rs.getString("phone"), rs.getString("orderId"), 
						rs.getInt("id"), rs.getString("address"),rs.getString("zctime"),rs.getString("name"));
				listuser.add(user);
				while(rs.next()){
					UserInfo useri = new UserInfo(rs.getString("phone"), rs.getString("orderId"), 
							rs.getInt("id"), rs.getString("address"),rs.getString("zctime"),rs.getString("name"));
					listuser.add(useri);
				} 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseResource.close(ps, rs); 
		}
		
		return listuser;
	}
	
	
}
