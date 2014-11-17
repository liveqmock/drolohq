package ouc.drolo.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ouc.drolo.dao.AddressDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/** 14/08/04
 *  删除   地址
 * @author jeep
 *
 */
public class UserDelAddressAction extends Action{
	private static Log logger = LogFactory.getLog(UserDelAddressAction.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "0";
		String uid = request.getParameter("uid");
		String address = request.getParameter("add");
		address = new String(address.getBytes("ISO-8859-1"), "UTF-8");
		
		Database db = null;
		try {
			db =  new SqlServer();
			AddressDao aDao = new AddressDao(db);
			result = aDao.delAddress(uid, address);
			
			logger.debug("用户编号："+uid+"   删除地址  ： "+address+"  结果："+result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return result;
	}
	
}
