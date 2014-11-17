package ouc.drolo.action.staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.MyClothesDao;
import ouc.drolo.domain.MyClothes;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 物流人员  收取 一件 衣服 
 * @author jeep
 *
 */
public class StaffSaveClothesAction extends Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String staffId = request.getParameter("staffId");
		String orderId = request.getParameter("orderId");
		
		String clothesCat = request.getParameter("clothesCat");
		clothesCat = new String(clothesCat.getBytes("ISO-8859-1"), "UTF-8");
		
		String clothesName = request.getParameter("clothesName");
		clothesName = new String(clothesName.getBytes("ISO-8859-1"), "UTF-8");
		
		String price = request.getParameter("price");
		
		int number = Integer.parseInt(request.getParameter("number"));
		
		String note = request.getParameter("note");
		note = new String(note.getBytes("ISO-8859-1"), "UTF-8");
		
		String serviceName = request.getParameter("serviceName");
		serviceName = new String(serviceName.getBytes("ISO-8859-1"), "UTF-8");
		String servicePrice = request.getParameter("servicePrice");
 
		String  userTel = request.getParameter("userTel");
		MyClothes myClothes = new MyClothes(staffId, orderId, clothesCat, clothesName, 
				 	price, number, note, serviceName, servicePrice);
		
		Database db=null;
		String str="";
			try {
				 db=new SqlServer();
				 db.setAutoCommit(false);
				 MyClothesDao myClothesDao = new MyClothesDao(db);
				 str = myClothesDao.saveClothes(myClothes,userTel);
				 
				 System.out.println("用户电话  ： "+userTel+"        物流人员保存的衣服  ："+clothesName+"      附加服务 ："+serviceName);
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
