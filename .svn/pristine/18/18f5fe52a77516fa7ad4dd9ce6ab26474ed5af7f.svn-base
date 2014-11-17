package ouc.drolo.action.orperator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.OperatorDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;

public class initHuifang  extends Action{

	private int oid;

	public int getOid() {
	return oid;
}

public void setOid(int oid) {
	this.oid = oid;
}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Database db=getDatabase();
		OperatorDao dao=new OperatorDao(db);
		String str=dao.initHuifang(oid);
		return str;
	}

}
