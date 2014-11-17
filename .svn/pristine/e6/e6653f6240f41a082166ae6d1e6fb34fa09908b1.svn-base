package ouc.drolo.action.user;

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
 * 物流人员 查询 衣物 名称  
 * @author jeep
 *
 */
public class UFindClothesPriceAction extends Action{
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String clothesPrice = "" ;
		String clothesCat = request.getParameter("clothesCat");
		clothesCat = new String(clothesCat.getBytes("ISO-8859-1"), "UTF-8");
		Database db = null; 
		
		try {
			db = new SqlServer();
			
			ClothesDao cd = new ClothesDao(db);
			clothesPrice = cd.findClothesPrice(clothesCat);   
			
			System.out.println("衣物种类 ： "+clothesCat+"      衣物价格  : "+clothesPrice); 
			
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clothesPrice;
	}
}
