package ouc.drolo.action.orperator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.OperatorDao;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;

public class huifangupdate extends Action {
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	private int xiyimanyidu;

	public int getXiyimanyidu() {
		return xiyimanyidu;
	}

	public void setXiyimanyidu(int xiyimanyidu) {
		this.xiyimanyidu = xiyimanyidu;
	}

	private int sendmanyi;
	
	public int getSendmanyi() {
		return sendmanyi;
	}

	public void setSendmanyi(int sendmanyi) {
		this.sendmanyi = sendmanyi;
	}


	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Database db = getDatabase();
		OperatorDao dao = new OperatorDao(db);
		
		int flag = dao.huifangupdate(orderId, xiyimanyidu, sendmanyi);
		System.out.println("回访flag  " + flag);
		
		return flag + "";
	}

}
