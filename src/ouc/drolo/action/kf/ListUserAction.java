package ouc.drolo.action.kf;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.util.XmlUtils;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.Page;
import wph.iframework.dao.db.SqlServer;

/**
 *  显示 用户人员 的信息  
 * @author jeep
 *
 */
public class ListUserAction extends Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		     
	     String pageMethod = request.getParameter("pageMethod");
	   
	     Page<UserInfo> page = new Page<UserInfo>();
	     page.setPageNumber(pageNumber);
	     page.setPageSize(pageSize);
	     page.setPageMethod(pageMethod);
	     
	     StringBuffer sb = new StringBuffer();
	     Database db = null;;
		try {
			db = new SqlServer();
		     UserInfoDao uiDao  = new UserInfoDao(db); 
		     if (!uiDao.userInfo(page)){     
		         logger.debug("操作数据库失败！");
		         return XmlUtils.buildXmlReturnValue(0, "操作数据库失败！");
		     }
		     Iterator<UserInfo> iterator = page.iterator();
		     
		     int count = 0;
		     String alt = "";
		     while (iterator.hasNext()){
		         if (count % 2 != 0)
		         {
		             alt = "alt";
		         }
		         else
		         {
		             alt = "";
		         }
		         count++;
		         UserInfo ui = iterator.next();
		         //用户信息	id	电话	 是否下单
		         int id = ui.getId();
		         String phone=ui.getPhone();
		         String orderId= ui.getOrderId();  
		         String address = ui.getAddress();
		         String zctime = ui.getZctime();
		         zctime = zctime.substring(0,16);  //截取时间，只到分钟
		         
		         String username = ui.getUsername();  //0630
		         if(null==username){
		        	 username="无";
		         }
		         
		         if(null==orderId){
		        	 orderId = "无" ;
		         }
		         if(null==address){
		        	 address = "无" ;
		         }
		         if(null == zctime){
		        	 zctime="";
		         }
		           
		         String tr = "<tr style=\"font-size: 14px;\">"
		        	 + "<td class=\"" + alt + "\">" + id + "</td> " + "<td class=\"" + alt + "\">"
		        	 + phone + "</td> " + "<td class=\"" + alt + "\">" + zctime + "</td> " 
		        	 +"<td class=\"" + alt + "\">"+  username + "</td> "
		        	 +"<td class=\"" + alt + "\">"+  orderId + "</td> "
		        	 + "<td class=\"" + alt + "\">"+ address + "</td> "  + "</tr>";
		         sb.append(tr);
		     }
		     logger.info("目标响应值：" + sb.toString());
		     
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page.toXml(sb.toString());
		
	 }
}