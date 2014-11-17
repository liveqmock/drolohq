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
 *  按条件 查询 客服 人员 信息
 * @author jeep
 *
 */
public class ListKfAction extends Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		     
	     String pageMethod = request.getParameter("pageMethod");
	   
	     Page<Kf> page = new Page<Kf>();
	     page.setPageNumber(pageNumber);
	     page.setPageSize(pageSize);
	     page.setPageMethod(pageMethod);
	     
	     StringBuffer sb = new StringBuffer();
	     Database db = null;;
		try {
			db = new SqlServer();
		     KfDao kDao  = new KfDao(db); 
		     if (!kDao.order(page )){     
		         logger.debug("操作数据库失败！");
		         return XmlUtils.buildXmlReturnValue(0, "操作数据库失败！");
		     }
		     Iterator<Kf> iterator = page.iterator();
		     
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
		         Kf kf = iterator.next();
		         //客服工号	姓名	电话	物流人员	修改	删除
		         String kfid = kf.getKfid();
		         String name=kf.getName();
		         String tel= kf.getTel();
		         String staffId = kf.getStaffId();
		         String tr = "<tr style=\"font-size: 12px;\">"
		        	 + "<td class=\"" + alt + "\">" + kfid + "</td> " + "<td class=\"" + alt + "\">"
		        	 + name + "</td> " + "<td class=\"" + alt + "\">" + tel + "</td> " + "<td class=\"" + alt + "\">"
		        	 + staffId + "</td> " + "<td class=\""    
		        	 + alt + "\">" + "<input type='button' value='修改' onclick= 'javascript:edit_info(" + kfid + ");'>" + "</td> " + "<td class=\"" 
		        	 + alt + "\">" + "<input type='button' value='删除'  onclick='javascript:delete_kf("  + kfid + ");'>"+ "</td> "  + "</tr>";
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