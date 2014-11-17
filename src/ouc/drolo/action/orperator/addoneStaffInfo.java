package ouc.drolo.action.orperator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.OrderDao;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;

public class addoneStaffInfo extends  Action{
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String name=request.getParameter("editnameaddone");
		String pswd=request.getParameter("editpasswdaddone");
		String phone=request.getParameter("editphoneaddone");
		String staffid=request.getParameter("editstaffNumaddone");
		String equipNum=request.getParameter("editequipNumaddone");
		String ereaNun=request.getParameter("editereaNunaddone");
		Database db=getDatabase();
		OrderDao dao=new OrderDao(db);
		int flag= dao.addonestaffInfo(name,pswd,phone,staffid,equipNum,ereaNun);
		return flag+"";
	}

}
