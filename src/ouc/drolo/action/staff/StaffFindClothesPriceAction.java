package ouc.drolo.action.staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.ClothesDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

/**
 *  查询          衣物价格    
 *  	 把 app 中 衣物价格表  全部返回给 物流人员 0608
 * @author jeep
 *
 */
public class StaffFindClothesPriceAction extends Action{
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String price = "" ;
		
		Database db = null; 
		
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			
			ClothesDao cd = new ClothesDao(db);
			price = cd.findClothesPrice();  
			
			System.out.println("衣物价格 : "+price); 
			
			db.commit();  
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return price;
	}
}
