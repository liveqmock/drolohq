package ouc.drolo.action.area;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.areaDao;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;

public class editAddArea extends Action{
	private String p1lon;
	private String p2lon;
	private String p3lon;
	private String p4lon;
	private String p5lon;
	private String p6lon;
	private String p7lon;
	private String p8lon;
	private String p1lan;
	private String p2lan;
	private String p3lan;
	private String p4lan;
	private String p5lan;
	private String p6lan;
	private String p7lan;
	private String p8lan;
	private String areaName;
	private String staffId;
	private String oldstaffid;
	private String oldareaName;
	private String areaId;
	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getOldareaName() {
		return oldareaName;
	}

	public void setOldareaName(String oldareaName) {
		try {
			this.oldareaName =URLDecoder.decode(oldareaName , "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	 
	}

	public String getOldstaffid() {
		return oldstaffid;
	}

	public void setOldstaffid(String oldstaffid) {
		this.oldstaffid = oldstaffid;
	}

	private String areaNum;
	public String getAreaNum() {
		return areaNum;
	}

	public void setAreaNum(String areaNum) {
		this.areaNum = areaNum;
	}

	private int num;

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("areaname"+areaName);
		System.out.println("oldareaname"+oldareaName);
		System.out.println("staaid"+staffId);
		System.out.println("oldstafid"+oldstaffid);
		


		String lon[]={p1lon,p2lon,p3lon,p4lon,p5lon,p6lon,p7lon,p8lon};
		String lan[]={p1lan,p2lan,p3lan,p4lan,p5lan,p6lan,p7lan,p8lan};
		if(num<8)
			{for(int i=num;i<8;i++)
		      {
		     	lon[i]=lon[i-1];
		     	lan[i]=lan[i-1];
			  }
			
		     }
		//System.out.println("lan1dddddddd"+lan[0]+lan[1]+lan[2]+"   "+lan[3]);

		Database db=getDatabase();
		areaDao dao=new areaDao(db);
		//OperatorDao dao2=new OperatorDao(db);
		
		int flag=dao.editAddArea(areaId,lon, lan, staffId,oldstaffid,areaName,oldareaName);
	
		
		System.out.println("flag"+flag);
		return flag+"";
	}

	public String getP1lon() {
		return p1lon;
	}

	public void setP1lon(String p1lon) {
		this.p1lon = p1lon;
	}

	public String getP2lon() {
		return p2lon;
	}

	public void setP2lon(String p2lon) {
		this.p2lon = p2lon;
	}

	public String getP3lon() {
		return p3lon;
	}

	public void setP3lon(String p3lon) {
		this.p3lon = p3lon;
	}

	public String getP4lon() {
		return p4lon;
	}

	public void setP4lon(String p4lon) {
		this.p4lon = p4lon;
	}

	public String getP5lon() {
		return p5lon;
	}

	public void setP5lon(String p5lon) {
		this.p5lon = p5lon;
	}

	public String getP6lon() {
		return p6lon;
	}

	public void setP6lon(String p6lon) {
		this.p6lon = p6lon;
	}

	public String getP7lon() {
		return p7lon;
	}

	public void setP7lon(String p7lon) {
		this.p7lon = p7lon;
	}

	public String getP8lon() {
		return p8lon;
	}

	public void setP8lon(String p8lon) {
		this.p8lon = p8lon;
	}

	public String getP1lan() {
		return p1lan;
	}

	public void setP1lan(String p1lan) {
		this.p1lan = p1lan;
	}

	public String getP2lan() {
		return p2lan;
	}

	public void setP2lan(String p2lan) {
		this.p2lan = p2lan;
	}

	public String getP3lan() {
		return p3lan;
	}

	public void setP3lan(String p3lan) {
		this.p3lan = p3lan;
	}

	public String getP4lan() {
		return p4lan;
	}

	public void setP4lan(String p4lan) {
		this.p4lan = p4lan;
	}

	public String getP5lan() {
		return p5lan;
	}

	public void setP5lan(String p5lan) {
		this.p5lan = p5lan;
	}

	public String getP6lan() {
		return p6lan;
	}

	public void setP6lan(String p6lan) {
		this.p6lan = p6lan;
	}

	public String getP7lan() {
		return p7lan;
	}

	public void setP7lan(String p7lan) {
		this.p7lan = p7lan;
	}

	public String getP8lan() {
		return p8lan;
	}

	public void setP8lan(String p8lan) {
		this.p8lan = p8lan;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
