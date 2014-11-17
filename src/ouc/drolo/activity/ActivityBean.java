package ouc.drolo.activity;

public class ActivityBean {

	private String act_name;
	private String act_start;
	private String act_end;
	private String act_address;
	private String act_introd;
	private String act_image ;
	private String act_imagexq;
	
	public ActivityBean() {
		
	}

	public ActivityBean(String act_name, String act_start, String act_end,
			String act_address, String act_introd, String act_image,
			String act_imagexq) {
		super();
		this.act_name = act_name;
		this.act_start = act_start;
		this.act_end = act_end;
		this.act_address = act_address;
		this.act_introd = act_introd;
		this.act_image = act_image;
		this.act_imagexq = act_imagexq;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getAct_start() {
		return act_start;
	}

	public void setAct_start(String act_start) {
		this.act_start = act_start;
	}

	public String getAct_end() {
		return act_end;
	}

	public void setAct_end(String act_end) {
		this.act_end = act_end;
	}

	public String getAct_address() {
		return act_address;
	}

	public void setAct_address(String act_address) {
		this.act_address = act_address;
	}

	public String getAct_introd() {
		return act_introd;
	}

	public void setAct_introd(String act_introd) {
		this.act_introd = act_introd;
	}

	public String getAct_image() {
		return act_image;
	}

	public void setAct_image(String act_image) {
		this.act_image = act_image;
	}

	public String getAct_imagexq() {
		return act_imagexq;
	}

	public void setAct_imagexq(String act_imagexq) {
		this.act_imagexq = act_imagexq;
	}

	@Override
	public String toString() {
		return "ActivityBean [act_name=" + act_name + ", act_start="
				+ act_start + ", act_end=" + act_end + ", act_address="
				+ act_address + ", act_introd=" + act_introd + ", act_image="
				+ act_image + ", act_imagexq=" + act_imagexq + "]";
	}
	
}
