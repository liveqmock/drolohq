package ouc.drolo.action.kf;

public class Kf {

	private String name;
	private String tel ;
	private int sex ;
	private String kfid ;
	private String staffId;
	private String password ;
	
	public Kf() {
	} 
	
	public Kf(String name, String tel, int sex, String kfid, String staffId) {
		super();
		this.name = name;
		this.tel = tel;
		this.sex = sex;
		this.kfid = kfid;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getKfid() {
		return kfid;
	}
	public void setKfid(String kfid) {
		this.kfid = kfid;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Override
	public String toString() {
		return "Kf [name=" + name + ", tel=" + tel + ", sex=" + sex + ", kfid="
				+ kfid + ", staffId=" + staffId + ", password=" + password
				+ "]";
	}

}
