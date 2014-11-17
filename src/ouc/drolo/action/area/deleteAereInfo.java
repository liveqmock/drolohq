package ouc.drolo.action.area;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.OrderDao;
import ouc.drolo.dao.areaDao;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;

public class deleteAereInfo  extends Action{
	private String  areaid;
	public String  getAreaid() {
		return areaid;
	}
	public void setAreaid(String  areaid) {
		this.areaid = areaid;
	}
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("areaid"+areaid);
		Database dbDatabase = getDatabase();
		areaDao dao = new areaDao(dbDatabase);
		int flag = dao.deleteInfo(areaid);

		return flag + "";
	}

}
