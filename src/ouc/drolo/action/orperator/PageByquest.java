package ouc.drolo.action.orperator;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.drolo.dao.OrderDao;
import ouc.drolo.domain.Staff;
import ouc.drolo.util.XmlUtils;
import wph.iframework.Action;
import wph.iframework.dao.db.Database;
import wph.iframework.dao.db.Page;

public class PageByquest extends Action{
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	     int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	     int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	     String pageMethod = request.getParameter("pageMethod");
	    
	     int leibie= Integer.parseInt((request.getParameter("leibie")));
	     System.out.println("leibie"+leibie);
	     String questcontext= (request.getParameter("questcontext"));
	     System.out.println("questcontext"+questcontext);
	     Page<Staff> page = new Page<Staff>();
	     page.setPageNumber(pageNumber);
	     page.setPageSize(pageSize);
	     page.setPageMethod(pageMethod);
	     
	     Database db = getDatabase();
	     String str = "";
	     StringBuffer sb = new StringBuffer();
	     OrderDao pDao  = new  OrderDao(db);
	     
	     if (!pDao.Byquest(page,leibie,questcontext ))
	     {
	         
	         logger.debug("操作数据库失败！");
	         return XmlUtils.buildXmlReturnValue(0, "操作数据库失败！");
	     }
	     
	     Iterator<Staff> iterator = page.iterator();
	     
	     int count = 0;
	     String alt = "";
	     while (iterator.hasNext())
	     {
	         if (count % 2 != 0)
	         {
	             alt = "alt";
	         }
	         else
	         {
	             alt = "";
	         }

	         count++;
	         Staff ad = iterator.next();
	       //  物流人员工号 	姓名 	电话 	所持设备 	所在区域 	修改 	删除
	         String sid = ad.getStaffId();
	         String name=ad.getName();
	         String tel=ad.getTel();
	         String  eqNum=ad.getEquipNum();
	         String eraNum=ad.getEreaNun();
	         String tr = "<tr style=\"font-size: 12px;\">"
	        	 + "<td class=\"" + alt + "\">" + sid + "</td> " + "<td class=\"" + alt + "\">"
	        	 + name + "</td> " + "<td class=\"" + alt + "\">" + tel + "</td> " + "<td class=\"" + alt + "\">"
	        	 + eqNum + "</td> " + "<td class=\"" + alt + "\">" + eraNum+ "</td> " + "<td class=\"" 
	        	 + alt + "\">" + "<input type='button' value='编辑' onclick='javascript:editInfo(" + sid + ");' >" + "</td> " + "<td class=\"" 
	        	 + alt + "\">" + "<input type='button' value='删除'  onclick='javascript:deleteInfo("  + sid + " );' >"+ "</td> "  + "</tr>";
	         sb.append(tr);
	        //System.out.println("d规范灌灌灌灌灌灌灌灌灌灌"+randnum);
	     }
	     logger.info("目标响应值：" + sb.toString());
	     return page.toXml(sb.toString());
	 }

}
