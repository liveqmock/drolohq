package ouc.drolo.action.orperator;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.OperatorDao;
import ouc.drolo.dao.OrderDao;
import ouc.drolo.domain.Order;
import ouc.drolo.domain.Staff;
import ouc.drolo.util.XmlUtils;
import ouc.drolo.util.order.FileEveryDaySerialNumber;
import ouc.drolo.util.order.SerialNumber;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.Page;

public class dingdanorderPage extends Action {@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	     int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	     int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	     String pageMethod = request.getParameter("pageMethod");
	     
	    // int oid=(Integer)request.getSession().getAttribute("oid");
	     Page<Order> page = new Page<Order>();
	     page.setPageNumber(pageNumber);
	     page.setPageSize(pageSize);
	     page.setPageMethod(pageMethod);
	     Database db = getDatabase();
	     String str = "";
	     StringBuffer sb = new StringBuffer();
	     OrderDao pDao  = new  OrderDao(db);
	    OperatorDao operatorDao=new OperatorDao(db);
	     if (!operatorDao.dingdanorder(page ))
	     {
	         logger.debug("操作数据库失败！");
	         return XmlUtils.buildXmlReturnValue(0, "操作数据库失败！");
	     }
	     
	     Iterator<Order> iterator = page.iterator();
	     
	     int count = 0;
	     String alt = "d";
	     while (iterator.hasNext())
	     {
	        /* if (count % 2 != 0)
	         {
	             alt = "";
	         }
	         else
	         {
	             alt = "";
	         }*/
	         count++;
	         Order ad = iterator.next();
	       //  物流人员工号 	姓名 	电话 	所持设备 	所在区域 	修改 	删除
	         String orderid = ad.getOrderId();
	       // String or1=orderid.substring(0, 7);
	        // String or2=orderid.substring(8,15);
	         String staffid=ad.getStaffId();
	         String username=ad.getUserName();
	         String address=ad.getAddress();
	         String tel=ad.getTakePhone();
	         String  status=ad.getStatus();
	         if(status.equals("待确认")&&orderid.charAt(0)=='s'){
	        	 status="送衣完成";
	         }
	         if(status.equals("已取衣")){
	        	// System.out.println("dd");
	        	 String time=ad.getGenerateTime();
		         String tr = "<tr   style=\"font-size: 12px;\">"
		        	 + "<td  class=\"" + alt + "\">" + orderid+ "</td> " + "<td class=\"" + alt + "\">"
		        	 + tel + "</td> " + "<td class=\"" + alt + "\">"
		        	 + username + "</td> "+ "<td class=\"" + alt + "\">"
		        	 + address + "</td> "+ "<td class=\"" + alt + "\">" + staffid + "</td> " + "<td class=\"" + alt + "\">"
		        	 + "<input type='button' value='已取衣' id='"+orderid+"' onclick=\"javascript:gaizhuangtai('"+orderid+"' );\" >" + "</td> " + "<td class=\"" + alt + "\">" + time+ "</td> " + "<td class=\"" 
		        	 + alt + "\">"  + "<input type='button' value='重新派单' disabled='true' id='"+orderid+"' onclick=\"javascript:repaidanhh('"+orderid+"',"+ staffid +" );\" >"+ "</td> "  + "</tr>";
		         sb.append(tr);	 
	         }
	         
	         
	         else   if(status.equals("已下单")){
	         String time=ad.getGenerateTime();
	         String tr = "<tr bgcolor=\"70eb40\" style=\"font-size: 12px;\">"
	        	 + "<td  class=\"" + alt + "\">" + orderid+ "</td> " + "<td class=\"" + alt + "\">"
	        	 + tel + "</td> "  + "<td class=\"" + alt + "\">"
	        	 + username + "</td> "+ "<td class=\"" + alt + "\">"
	        	 + address + "</td> "+ "<td class=\"" + alt + "\">" + staffid + "</td> " + "<td class=\"" + alt + "\">"
	        	 + status + "</td> " + "<td class=\"" + alt + "\">" + time+ "</td> " + "<td class=\"" 
	        	 + alt + "\">"  + "<input type='button' value='重新派单' id='"+orderid+"' onclick=\"javascript:repaidanhh('"+orderid+"',"+ staffid +" );\" >"+ "</td> "  + "</tr>";
	         sb.append(tr);
	       
	           }
                      
	         else  if(status.equals("拒单")){
             	         String time=ad.getGenerateTime();
             	         String tr = "<tr bgcolor=\"eb4054\" style=\"font-size: 12px;\">"
             	        	 + "<td  class=\"" + alt + "\">" + orderid+ "</td> " + "<td class=\"" + alt + "\">"
             	        	 + tel + "</td> " + "<td class=\"" + alt + "\">"
        		        	 + username + "</td> "+ "<td class=\"" + alt + "\">"
        		        	 + address + "</td> " + "<td class=\"" + alt + "\">" + staffid + "</td> " + "<td class=\"" + alt + "\">"
             	        	 + status + "</td> " + "<td class=\"" + alt + "\">" + time+ "</td> " + "<td class=\"" 
             	        	 + alt + "\">"  + "<input type='button' value='重新派单' id='"+orderid+"' onclick=\"javascript:repaidanhh('"+orderid+"',"+ staffid +" );\" >"+ "</td> "  + "</tr>";
             	         sb.append(tr);
             	           }   
	         else  if(status.equals("未分物流人员")){
     	         String time=ad.getGenerateTime();
     	         String tr = "<tr bgcolor=\"f7d92d\" style=\"font-size: 12px;\">"
     	        	 + "<td  class=\"" + alt + "\">" + orderid+ "</td> " + "<td class=\"" + alt + "\">"
     	        	 + tel + "</td> "  + "<td class=\"" + alt + "\">"
		        	 + username + "</td> "+ "<td class=\"" + alt + "\">"
		        	 + address + "</td> "+ "<td class=\"" + alt + "\">" + staffid + "</td> " + "<td class=\"" + alt + "\">"
     	        	 + status + "</td> " + "<td class=\"" + alt + "\">" + time+ "</td> " + "<td class=\"" 
     	        	 + alt + "\">"  + "<input type='button' value='重新派单' id='"+orderid+"' onclick=\"javascript:repaidanhh('"+orderid+"',"+ staffid +" );\" >"+ "</td> "  + "</tr>";
     	         sb.append(tr);
     	           }   
	         else  if(status.equals("物流超时未响应")){
     	         String time=ad.getGenerateTime();
     	         String tr = "<tr bgcolor=\"f7d92d\" style=\"font-size: 12px;\">"
     	        	 + "<td  class=\"" + alt + "\">" + orderid+ "</td> " + "<td class=\"" + alt + "\">"
     	        	 + tel + "</td> "  + "<td class=\"" + alt + "\">"
		        	 + username + "</td> "+ "<td class=\"" + alt + "\">"
		        	 + address + "</td> "+ "<td class=\"" + alt + "\">" + staffid + "</td> " + "<td class=\"" + alt + "\">"
     	        	 + status + "</td> " + "<td class=\"" + alt + "\">" + time+ "</td> " + "<td class=\"" 
     	        	 + alt + "\">"  + "<input type='button' value='重新派单' id='"+orderid+"' onclick=\"javascript:repaidanhh('"+orderid+"',"+ staffid +" );\" >"+ "</td> "  + "</tr>";
     	         sb.append(tr);
     	           }   
              else{
            	  String time=ad.getGenerateTime();
             	         String tr = "<tr  style=\"font-size: 12px;\">"
             	        	 + "<td  class=\"" + alt + "\">" + orderid+ "</td> " + "<td class=\"" + alt + "\">"
             	        	 + tel + "</td> "  + "<td class=\"" + alt + "\">"
        		        	 + username + "</td> "+ "<td class=\"" + alt + "\">"
        		        	 + address + "</td> "+ "<td class=\"" + alt + "\">" + staffid + "</td> " + "<td class=\"" + alt + "\">"
             	        	 + status + "</td> " + "<td class=\"" + alt + "\">" + time+ "</td> " + "<td class=\"" 
             	        	 + alt + "\">"  + "<input type='button' disabled='true' value='重新派单' id='"+orderid+"' onclick=\"javascript:repaidanhh('"+orderid+"',"+ staffid +" );\" >"+ "</td> "  + "</tr>";
             	         sb.append(tr);
             	         }
	    }
	    // logger.info("目标响应值：" + sb.toString());
	     return page.toXml(sb.toString());
	 }
	}



