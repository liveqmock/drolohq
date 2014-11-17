package ouc.drolo.action.area;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.areaDao;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;

public class test2  extends Action{
	private String lon;
	private String lan;
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
@Override
public String execute(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	Database dbDatabase=getDatabase();
	areaDao dao=new areaDao(dbDatabase);
	
	String stafid=dao.getAreaId(lon, lan);
	System.out.println("分配的staffid是"+stafid);
	return stafid;
}
}
