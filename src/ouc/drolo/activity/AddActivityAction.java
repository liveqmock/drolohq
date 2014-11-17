package ouc.drolo.activity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ouc.drolo.util.FileUtils;
import ouc.drolo.util.MyConfig;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.SqlServer;
import wph.iframework.utils.WebsiteUtils;

/**
 * 添加 活动 信息
 * @author jeep
 *
 */
public class AddActivityAction extends Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String basePath = WebsiteUtils.getRootPath(request);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String flag = "-1";
		String image = "";
		String Path = MyConfig.activity;
		String[][] message = new String[10][2];
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request); 
	    if(isMultipart==true){ 
	        FileItemFactory factory = new DiskFileItemFactory(); 
	        ServletFileUpload upload = new ServletFileUpload(factory); 
	        
	        try {
				List items = upload.parseRequest(request); 
				Iterator iter = items.iterator(); 
				
				int i = 0 ;
				while (iter.hasNext()){ 
				    FileItem item = (FileItem) iter.next(); 
				    if (item.isFormField()) { //普通文本信息处理 
					     message[i][0] = item.getFieldName();
					     String paramValue = item.getString();  
					     message[i][1] = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8"); 
					     
					     logger.debug(message[i][0] +" --- "+ message[i][1]); 
				    } else { //上传文件信息处理 
				    	message[i][0] = item.getFieldName();
				        String fileName = item.getName(); 
				        String name = fileName.substring(fileName.indexOf("."));//格式
				        image = FileUtils.getFileName(); // 图片用时间命名
				        message[i][1] = image+name;
				    	logger.debug("fieldName : "+message[i][0] + " ====fileName " + message[i][1]);
				    	 
				         byte[] data = item.get();
				         String filePath = Path+message[i][1]; 
				         FileOutputStream fos = new FileOutputStream(filePath);
				         fos.write(data); 
				         fos.flush();
				         fos.close(); 
				    }
				    
				    i++;
				}  // end while
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
	        
	        ActivityBean activity = new ActivityBean(message[0][1], message[1][1],
	        		message[2][1], message[3][1], message[4][1], message[5][1], message[6][1]);
	        
	        Database db = null;
	        try {
				db = new SqlServer();
				ActivityDao aDao = new ActivityDao(db);
				int res = aDao.addActivity(activity);
				if(res == 1){
					flag = "1";
				}
				logger.debug("The result : "+ flag); 
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				db.close();
			}
	}
	  //跳转
	    if("1".equals(flag)){
	    	out.print("<script language='javascript'>alert('添加成功！');"
	    			+ "window.location='activity/act.jsp';</script>");
	    }else{
	    	out.print("<script language='javascript'>alert('添加失败，重新添加！！');"
	    			+ "window.location='activity/act.jsp';</script>");
	    }
  		out.flush();
  		out.close();
	    
	    return flag;
}
	
}
