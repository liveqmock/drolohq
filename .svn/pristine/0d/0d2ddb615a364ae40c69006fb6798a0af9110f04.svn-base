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

public class StaffFindMaincatAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String maincat = "" ;
		Database db = null;
		
		try {
			db = new SqlServer();
			db.setAutoCommit(false);
			
			ClothesDao cd = new ClothesDao(db);
			maincat = cd.findMaincat();
			System.out.println("*********maincat :"+maincat.toString()); 
			
			db.commit(); 
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return maincat;
	}

}
