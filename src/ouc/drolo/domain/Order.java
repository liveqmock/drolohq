package ouc.drolo.domain;


import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.json.JSONObject;
import ouc.drolo.action.diaodu.Dispatcher;
import ouc.drolo.dao.OperatorDao;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;
import wph.iframework.push.OperatorPush;

public class Order {
	private String large ;
	private String amount ;
	private String  address ;
	private String takePhone ;
	private String note;
	private String orderId;
	private String userId;
	private String generateTime ;
	private String status;
	private String longitude;
	private String latitude;
	
	public String getSlongitude() {
		return slongitude;
	}
	public void setSlongitude(String slongitude) {
		this.slongitude = slongitude;
	}
	public String getSlatitude() {
		return slatitude;
	}
	public void setSlatitude(String slatitude) {
		this.slatitude = slatitude;
	}
	
	private String slongitude;
	private String slatitude;
	private String userName ;
	private String staffphone;
	
	public String getStaffphone() {
		return staffphone;
	}
	public void setStaffphone(String staffphone) {
		this.staffphone = staffphone;
	}
	
	private int life;
	private  Task task;
	
	 public Order(String id){
	        this.orderId = id;
	 }
	 
	public Order()
    {
        task = new Task();
    }
	
	/**
     * 自动提醒到期
     * 
     * @author WPH
     * 
     */

	public  void autotixing(){
		JSONObject json = new JSONObject();
		Database db = null;
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			OperatorDao dao = new OperatorDao(db); 
			int flag=dao.weixiangying(orderId);
			db.commit();
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			db.close();
		}
				
		 json.put("ordrId", orderId);
		 json.put("phone",getStaffphone());
		 json.put("stafid", StaffId);
		 json.put("lon", longitude);
		 json.put("lan", latitude);
		  
		 String oid=1+"";
		 OperatorPush.noanser(oid, json.toString());	
	}
	
	/**
     * 启动定时器1
     */
    public synchronized void schedule()
    {  
        Timer timer = Dispatcher.getTimer();
      
        Calendar c = Calendar.getInstance();
     	int shi= c.get(Calendar.HOUR_OF_DAY);
     	int fen=c.get(Calendar.MINUTE);
       
		timer.schedule(task, 5* 60 * 1000);//5分钟后提醒
    }
    
    public synchronized void schedule2()
    {  
    	System.out.println("进入定时器：");
        Timer timer = Dispatcher.getTimer();
        System.out.println("sddf：");
     //  System.out.println(timer.);
        Calendar c = Calendar.getInstance();
     	int shi= c.get(Calendar.HOUR_OF_DAY);
     	int fen=c.get(Calendar.MINUTE);
       
    	if(shi<20&&shi>=7){
    		timer.schedule(task, 30 * 1000);//1分钟后提醒
    	}
    }
    
    public void a(){
    	System.out.println("得得得");
    }
    
    /**
     * 取消定时器
     * 
     * @return
     */
    public synchronized boolean cancel()
    {
    	System.out.println("取消订单定时器task哈哈哈");
        return task.cancel();
    }
    
    class Task extends TimerTask{
        @Override
        public void run(){
        	System.out.println("执行了订单定时器task");
            autotixing();
        }
    }
    
    public static class Comparator implements java.util.Comparator<Order>
    {
        @Override
        public int compare(Order Order1, Order Order2)
        {
            if (Order1 == null || Order2 == null)
            {
                throw new NullPointerException();
            }
            
           
            if ((Integer.parseInt(Order1.generateTime)) < (Integer.parseInt(Order2.generateTime)))
            {
                return -1;
            }
            else  if ((Integer.parseInt(Order1.generateTime)) > (Integer.parseInt(Order2.generateTime)))
            {
                return 1;
            }
            
            return 0;
        }
    }
  
	public String getStaffId() {
		return StaffId;
	}
	public void setStaffId(String staffId) {
		StaffId = staffId;
	}
	private String StaffId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}
	public String getLarge() {
		return large;
	}
	public void setLarge(String large) {
		this.large = large;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmount() {
		return amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTakePhone() {
		return takePhone;
	}
	public void setTakePhone(String takePhone) {
		this.takePhone = takePhone;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}