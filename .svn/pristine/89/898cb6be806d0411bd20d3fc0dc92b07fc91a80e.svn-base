package ouc.drolo.action.diaodu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wph.iframework.dao.Dao;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.ResultSetCallback;

public class DispatcherDao extends Dao
{
    
    public DispatcherDao(Database db)
    {
        super(db);
    }
    
    /**
     * 获取用户信息
     * 
     * @param uid
     * @return
     */
    
    public Map<String, Object> getUser(int uid)
    {
        String sql = "SELECT push_id, username, account, longitude, latitude FROM users WHERE pid=?";
        List<Object> items = new ArrayList<Object>();
        items.add(uid);
        final Map<String, Object> map = new HashMap<String, Object>();
        int flag = db.executeQuery(sql, items, new ResultSetCallback()
        {
            @Override
            public int handle(ResultSet rs) throws SQLException
            {
                if (rs.next())
                {
                    map.put("pushId", rs.getString("push_id"));
                    map.put("name", rs.getString("username"));
                    map.put("phone", rs.getString("account"));
                    map.put("longitude", rs.getFloat("longitude"));
                    map.put("latitude", rs.getFloat("latitude"));
                    return 1;
                }
                else
                {
                    logger.info("定时任务中，没有查出该用户信息");
                    return 0;
                }
            }
        });
        
        if (1 == flag)
        {
            return map;
        }
        else if (0 == flag)
        {
            
            return null;
        }
        else
        {
            throw new RuntimeException();
        }
    }
    
    /**
     * 获取接线员今天已完成的任务量
     * 
     * @param oid
     * @return
     */

    public int getTaskCountOfOperator(int oid)
    {
        String sql = "SELECT COUNT(1) count FROM rescue_history WHERE operator=? AND CONVERT(VARCHAR(10),time,120)=CONVERT(VARCHAR(10),GETDATE(),120)";
        
        List<Object> items = new ArrayList<Object>();
        items.add(oid);
        
        int ret = db.executeQuery(sql, items, new ResultSetCallback()
        {
            
            @Override
            public int handle(ResultSet rs) throws SQLException
            {
                int ret = rs.next() ? rs.getInt("count") : 0;
                return ret;
            }
        });
        
        return ret == -1 ? 0 : ret;
    }
    
    /**
     * 获取配送员信息
     * 
     * @param rid
     * @return
     */
    public Map<String, Object> getStaff(String  rid)
    {
        String sql = "SELECT push_id, name,tel, longitude, latitude FROM _staff WHERE staffid=?";
        List<Object> items = new ArrayList<Object>();
        items.add(rid);
        final Map<String, Object> map = new HashMap<String, Object>();
        int flag = db.executeQuery(sql, items, new ResultSetCallback()
        {
            
            @Override
            public int handle(ResultSet rs) throws SQLException
            {
                if (rs.next())
                {
                    map.put("pushId", rs.getString("push_id"));
                    map.put("name", rs.getString("name"));
                    map.put("phone", rs.getString("tel"));
                    map.put("longitude", rs.getString("longitude"));
                    map.put("latitude", rs.getString("latitude"));
                    System.out.println("te"+rs.getString("tel"));
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        });
        
        if (flag == 1)
        {
            return map;
        }
        else if (0 == flag)
        {
            
            return null;
        }
        else
        {
            throw new RuntimeException();
        }
    }
    
  /*  RescueRequest rew_request(int his_id){
    	RescueRequest req=new RescueRequest();
    	String sql = "SELECT uid, name, phone, longitude, latitude FROM rescue WHERE pid=?";
        List<Object> items = new ArrayList<Object>();
        items.add(rid);
    	RescueRequests
 	
    	return req;
    }*/
    
    /**
     * 分配救援车
     * 
     * @param hid
     * @param rid
     * @return
     */
    public int assignRescueCar(int hid, int rid)
    {
        String sql = "UPDATE rescue_history SET jiuyuan_id=?, status=1, start_time=GETDATE() WHERE pid=?";
        List<Object> items = new ArrayList<Object>();
        items.add(rid);
        items.add(hid);
        return db.executeUpdate(sql, items);
    }
    
  
    
    /**
     * 分配接线员
     * 
     * @param hid
     * @param oid
     * @return
     */
    public int assignOperator(int hid, int oid)
    {
        String sql = "UPDATE rescue_history SET operator=? WHERE pid=?";
        List<Object> items = new ArrayList<Object>();
        items.add(oid);
        items.add(hid);
        return db.executeUpdate(sql, items);
    }
    
    /**
     * 判断用户是否已经请求过啦
     * 
     * @param uid
     * @return
     */
    public int userHasRequested(int uid)
    {
        String sql = "SELECT pid FROM rescue_history WHERE uid=? AND status=1";
        List<Object> items = new ArrayList<Object>();
        items.add(uid);
        int flag = db.executeQuery(sql, items, new ResultSetCallback()
        {
            
            @Override
            public int handle(ResultSet rs) throws SQLException
            {
                return rs.next() ? 1 : 0;
            }
        });
        
        if (flag == -1)
        {
            throw new RuntimeException();
        }
        
        return flag;
    }
}
