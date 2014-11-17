package ouc.drolo.action.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.UserDao;
import ouc.drolo.util.FileUtils;
import ouc.drolo.util.MyConfig;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;

public class UserUpdateInfoAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = "-1";
		
		String uid = request.getParameter("uid"); 
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		birthday = new String(birthday.getBytes("ISO-8859-1"), "UTF-8");
		
        String headimages = FileUtils.getFileName() + ".jpg"; // 图片用时间命名
        String fileurl = "";
        String filePath = MyConfig.headimages;
        fileurl = filePath + headimages ;
        
        File file = new File(fileurl);
        logger.info(sex+"  birthday: "+birthday+"  图片名字： "+headimages+"     图片路径:" + fileurl);
		
        
        try {
			InputStream is = request.getInputStream();
			FileOutputStream out = new FileOutputStream(file);
			byte[] data = new byte[1024];
			int count = -1;
			while ((count = is.read(data, 0, 1024)) != -1)
			{
			    out.write(data, 0, count);
			}
			out.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        Database db = null;
		try {
			db = new SqlServer();
			UserDao uDao = new UserDao(db);
			str = uDao.updateUserInfo(uid,sex,birthday,headimages);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
        
		return str;
	}

}
