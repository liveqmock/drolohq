package ouc.drolo.action.change;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.ChangeDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 查询订单下衣物信息
 * @author jeep
 *
 */
public class ClothesInfoAction extends Action{
	private static Log logger = LogFactory.getLog(ClothesInfoAction.class);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderID = request.getParameter("orderID");
		String clothesInfo ="";
		
		Database db = null;
		
		try {
			db = new SqlServer();
			ChangeDao cDao = new ChangeDao(db);
			clothesInfo = cDao.getClothesInfo(orderID);
			
			logger.debug("orderID:"+orderID+" clothesInfo:"+clothesInfo); 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		
		return clothesInfo;
	}

}
