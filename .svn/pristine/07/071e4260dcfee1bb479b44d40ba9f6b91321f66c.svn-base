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
 * 用户端  查询  衣物类别
 * @author jeep
 *
 */
public class UFindClothesCatAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ClothesCat = "" ;
		Database db = null;
		
		try {
			db = new SqlServer();
			
			ClothesDao cd = new ClothesDao(db);
			ClothesCat = cd.findClothesCat();
			System.out.println("衣物类别                "+ClothesCat); 
			
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ClothesCat;
	}

}
