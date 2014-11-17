package ouc.drolo.action.kf;

public class UserInfo {

	private String phone;
	private String orderId;
	private int id ;
	private String address;
	private String zctime;
	private String username;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZctime() {
		return zctime;
	}

	public void setZctime(String zctime) {
		this.zctime = zctime;
	}

	public UserInfo() {
	}
	
	public UserInfo(String phone, String orderId, int id, String address,
			String zctime, String username) {
		super();
		this.phone = phone;
		this.orderId = orderId;
		this.id = id;
		this.address = address;
		this.zctime = zctime;
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserInfo [phone=" + phone + ", orderId=" + orderId + ", id="
				+ id + ", address=" + address + ", zctime=" + zctime
				+ ", username=" + username + "]";
	}
	
}
