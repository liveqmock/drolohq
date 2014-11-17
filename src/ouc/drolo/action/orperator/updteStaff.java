package ouc.drolo.action.orperator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import net.sf.json.JSONObject;

import ouc.drolo.action.diaodu.Dispatcher;
import ouc.drolo.dao.OperatorDao;
import ouc.drolo.dao.OrderDao;
import ouc.drolo.domain.Order;
import ouc.drolo.domain.orperator;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.push.staffPush;

public class updteStaff extends Action {
	// staffid=" + staffid+ "&orderId=" +ordrid,
	private String oldstaffid;

	public String getOldstaffid() {
		return oldstaffid;
	}

	public void setOldstaffid(String oldstaffid) {
		this.oldstaffid = oldstaffid;
	}

	private String staffid;
	private String lon;
	private String lan;
	private String stel;
	private String orderId;

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLan() {
		return lan;
	}

	public void setLan(String lan) {
		this.lan = lan;
	}

	public String getStel() {
		return stel;
	}

	public void setStel(String stel) {
		this.stel = stel;
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Database db = getDatabase();
		OrderDao dao = new OrderDao(db);
		OperatorDao oDao = new OperatorDao(db);
		String sid = oDao.getstaffid(orderId);
		String flag;
	//	if (sid.equals(oldstaffid)) {
			// System.out.println("ddddddddd");
		flag = dao.updteStaff(staffid, orderId);
		
		int stid = Integer.parseInt(staffid);
		String pushId = dao.getPushId(stid);
		String addres = dao.getAddres(orderId);
		// System.out.println("pushID："+pushId);
		JSONObject js = new JSONObject();

		Dispatcher dispatcher = Dispatcher.getInstance();
		Order order = dispatcher.getOrder(orderId);
		order.cancel();
		dispatcher.remove(orderId);

		// OperatorDao dao=new OperatorDao(db);
		Order oder = new Order();
		oder.setStaffId(staffid);
		oder.setOrderId(orderId);
		oder.setStaffphone(stel);
		oder.setSlatitude(lan);
		oder.setSlongitude(lon);
		dispatcher.addnewOrder(oder);
		oder.schedule();
		// int flag=O
		js.put("orderId", orderId);
		js.put("address", addres);
		staffPush.tixing(pushId, js.toString());
		/*} else {
			flag = 2+"";
		}
*/
		return flag;
	}
}
