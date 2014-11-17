package ouc.drolo.domain;

public class Pay {

	private String staffId ;
	private String runNo ;
	private String orderId ;
	private String payDate ;
	private String lastPrice ;
	private String totalPrice ;
	private String discount ;
	private String clothesNum ;
	private String userTel ;
	
	
	public Pay(String staffId, String runNo, String orderId, String payDate,
			String lastPrice, String totalPrice,String discount,String clothesNum,String userTel) {
		this.staffId = staffId;
		this.runNo = runNo;
		this.orderId = orderId;
		this.payDate = payDate;
		this.lastPrice = lastPrice;
		this.totalPrice = totalPrice;
		this.discount = discount ;
		this.clothesNum = clothesNum;
		this.userTel = userTel ;
	}

	public Pay() {
	}
	
	
	
	public String getClothesNum() {
		return clothesNum;
	}

	public void setClothesNum(String clothesNum) {
		this.clothesNum = clothesNum;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getRunNo() {
		return runNo;
	}
	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
}
