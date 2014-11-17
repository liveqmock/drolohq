package ouc.drolo.action.orperator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.OrderDao;

import wph.iframework.Action;
import wph.iframework.dao.db.Database;

public class insertEidtStafInf extends Action {
	public static String trull(String str) {
		return str == null ? "" : str.trim();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = trull(request.getParameter("name"));
		String phone = trull(request.getParameter("phone"));
		String quipNum = trull(request.getParameter("editequipNum"));
		String ereaNun = trull(request.getParameter("ereaNun"));
		String staffid = trull(request.getParameter("sid"));
		
		Database db = getDatabase();
		OrderDao dao = new OrderDao(db);
		String flag = dao.updateOneStaffInfo(name, phone, quipNum, ereaNun,staffid);

		return flag;
	}

}
