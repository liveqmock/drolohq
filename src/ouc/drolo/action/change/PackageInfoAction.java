package ouc.drolo.action.change;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.ChangeDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 根据包号获取 订单号 和 用户电话 
 * @author jeep
 *
 */
public class PackageInfoAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String packageID = request.getParameter("packageID");
		String pop ="";
		
		Database db = null;
		
		try {
			db = new SqlServer();
			ChangeDao cDao = new ChangeDao(db);
			pop = cDao.getOrderIDPhone(packageID);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		
		logger.debug("packageID:" +packageID +"  RESULT:"+pop);
		
		return pop;
	}

}
