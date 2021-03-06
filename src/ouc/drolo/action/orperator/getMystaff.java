package ouc.drolo.action.orperator;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ouc.drolo.action.diaodu.Dispatcher;
import ouc.drolo.dao.OperatorDao;
import ouc.drolo.domain.Staff;
import net.sf.json.util.JSONStringer;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;


public class getMystaff extends Action {
	private String lon;
	private String lat;

	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response) {
		
		Dispatcher dispatcher = Dispatcher.getInstance();
		// Boolean a=dispatcher.getstaffs().contains("123");
		
		Database db = getDatabase();
		OperatorDao dao = new OperatorDao(db);

		List<Staff> staffs = dao.getTopN(lon, lat, 5);

		JSONStringer json = new JSONStringer();
		json.array();
		Iterator<Staff> i = staffs.iterator();
		int a = 0;
		
		while (i.hasNext()) {
			Staff staff = i.next();
			json.object();
			json.key("staffid").value(staff.getStaffId());
			json.key("name").value(staff.getName());
			json.key("phone").value(staff.getTel());
			json.key("lon").value(staff.getLongitude());
			json.key("lat").value(staff.getLatitude());
			json.endObject();
			System.out.println("aa" + a);
		}
		json.endArray();

		return json.toString();
	}

	//--------------------------
	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

}
