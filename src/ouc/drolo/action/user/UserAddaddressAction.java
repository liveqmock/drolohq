package ouc.drolo.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.AddressDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 *  用户 添加 地址 （经纬度 ）
 * @author jeep
 *
 */
public class UserAddaddressAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String uid = request.getParameter("uid");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		
		String address = request.getParameter("address");
		
		address = new String(address.getBytes("ISO-8859-1"), "UTF-8");
		
		System.out.println("添加 地址  用户 ID: "+uid+"         地址  : "+address); 
		
		Database db=null;
		String str="";

			try {
				 db=new SqlServer();
				 db.setAutoCommit(false);
				 
				 AddressDao aDao = new  AddressDao(db);
				 str = aDao.addAddress(uid,address,longitude,latitude);
				 db.commit();
	
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
					try {
						db.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}finally{
				if(db!=null){
					db.close();
				}
			}
		return str; 
	}
		
}
