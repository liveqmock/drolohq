package ouc.drolo.domain;





/*物流人员**/
public class Staff {
	private String staffId ; //物流人员 工号
	private String password ;
	private String name ;
	private String tel ;
	private String equipNum ;
	private String ereaNun;
    public static final int STATUS_login = 1;
    public static final int STATUS_loginout=0;
    private int   status;
    private int id;
	private String           longitude;
    private String           latitude;
    private  String push_id ;
    public String getPush_id() {
		return push_id;
	}
	public void setPush_id(String push_id) {
		this.push_id = push_id;
	}
	public int getId() {
		return id;
	}
	public void setId() {
		this.id = Integer.parseInt(staffId);
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

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public static int getStatusLogin() {
		return STATUS_login;
	}
	public static int getStatusLoginout() {
		return STATUS_loginout;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEquipNum() {
		return equipNum;
	}
	public void setEquipNum(String equipNum) {
		this.equipNum = equipNum;
	}
	public String getEreaNun() {
		return ereaNun;
	}
	public void setEreaNun(String ereaNun) {
		this.ereaNun = ereaNun;
	}
	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 public Staff()
	    {
	      
	    }
	 public Staff(String id)
	    {
	        this.staffId = id;
	    }
	 public Staff cloneIt()
	    {
		 Staff staff = new Staff(this.staffId);
		 staff.push_id=new  String(this.push_id);
		 staff.name= new String(this.name);
		 staff.latitude = new String(this.latitude);
		 staff.longitude = new String(this.longitude);
		staff.tel =new String(this.tel);
	      
	        return staff;
	    }
	 public int status()
	    {
	        return status;
	    }
	public static class Comparator implements java.util.Comparator<Staff>
    {
        
        @Override
        public int compare(Staff staff1, Staff staff2)
        {
            if (staff1 == null || staff2 == null)
            {
                throw new NullPointerException();
            }
            
           
            if ((Integer.parseInt(staff1.staffId)) < (Integer.parseInt(staff2.staffId)))
            {
                return -1;
            }
            else  if ((Integer.parseInt(staff1.staffId))>(Integer.parseInt(staff2.staffId)))
            {
                return 1;
            }
            
            return 0;
        }
    }
}
