package ouc.drolo.action.staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.MyClothesDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 * 物流人员 查询   订单下  所有的衣物信息 
 * @author jeep
 *
 */
public class StaffFindAllClothesAction extends Action{
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String allClothes = "" ;
		String orderId = request.getParameter("orderId");
		Database db = null; 
		
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			
			MyClothesDao mcd = new MyClothesDao(db);
			allClothes = mcd.allClothes(orderId); 
			System.out.println("订单号  "+orderId+"   所有衣物信息 ："+allClothes); 
			
			db.commit();  
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allClothes;
	}
}
