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
 * 物流人员 查询 衣物 名称  
 * @author jeep
 *
 */
public class StaffFindClothesNameAction extends Action{
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String clothesName = "" ;
		String clothesCat = request.getParameter("clothesCat");
		clothesCat = new String(clothesCat.getBytes("ISO-8859-1"), "UTF-8");
		Database db = null; 
		
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			
			ClothesDao cd = new ClothesDao(db);
			clothesName = cd.findClothesName(clothesCat);  
			
			System.out.println("衣物种类 ： "+clothesCat+"      衣物名称  : "+clothesName); 
			
			db.commit();  
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clothesName;
	}
}
