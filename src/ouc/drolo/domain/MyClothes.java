package ouc.drolo.domain;

public class MyClothes {
	private String staffId ;
	private String orderId ;
	private String clothesCat ;
	private String clothesName ;
	private String price ;
	private int number ;
	private String note ;
	private String barcode ;  // 衣物条形码
	private String status ;
	
	private String serviceName ;  //附加服务名
	private String servicePrice ;
	
	
	public MyClothes() {
	}

	public MyClothes(String staffId, String orderId, String clothesCat,
			String clothesName, String price, int number, String note,
			String serviceName, String servicePrice) {
		this.staffId = staffId;
		this.orderId = orderId;
		this.clothesCat = clothesCat;
		this.clothesName = clothesName;
		this.price = price;
		this.number = number;
		this.note = note;
		this.serviceName = serviceName;
		this.servicePrice = servicePrice;
	}

	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getClothesCat() {
		return clothesCat;
	}
	public void setClothesCat(String clothesCat) {
		this.clothesCat = clothesCat;
	}

	public String getClothesName() {
		return clothesName;
	}
	public void setClothesName(String clothesName) {
		this.clothesName = clothesName;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}

}
