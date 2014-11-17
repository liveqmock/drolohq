package ouc.drolo.action.orperator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.OperatorDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;

public class gaizhuangtai extends Action {
	private String oid;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Database db = getDatabase();
		OperatorDao dao = new OperatorDao(db);
		String flag = dao.gaizhuangtai(oid);

		return flag;
	}
}
