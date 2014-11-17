package ouc.drolo.action.staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.StaffDao;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;
import wph.iframework.startup.Config;

public class StaffUpdateVersionAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort();
//				+ request.getContextPath() + "/";
		System.err.println("basePath : " + basePath); 
		
		String version = request.getParameter("version");
		int type=Integer.parseInt(request.getParameter("type"));//类型
		int role=Integer.parseInt(request.getParameter("role"));//客户端还是物流端
		
		float level = Float.parseFloat(request.getParameter("level")); //判断是ios 的系统
		System.err.println("IOS的版本号  ：       " + level);
		
		Database db=null;
		String str="";
		
		try {
			 db=new SqlServer();
			 db.setAutoCommit(false);
			 StaffDao sDao = new StaffDao(db);
			 if(version.length()>=1){
				 str = sDao.checkUpdateInfo(version,type,role);
			 }
		
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
		
		StringBuffer sb = new StringBuffer();
		sb.append("{\"update\":\"");
		
		 if (str.equals("1")) {
				// 不是最新的
			 sb.append(true);
				sb.append("\",\"apkUrl\":\"");
				if(type==1){
					if(role==1){
						sb.append(Config.TEMP_PATH).append("sdrolohq.apk");
					}else{
						sb.append(Config.TEMP_PATH).append("duola.apk");
					}
				}else if(type==2){
						sb.append(basePath).append("/down.html");
				}
				sb.append("\"}");
				
		} else if (str.equals("0")) {
				// 是最新的
				sb.append(false);
				sb.append("\"}");
		}
		return sb.toString();
	}
	
}
