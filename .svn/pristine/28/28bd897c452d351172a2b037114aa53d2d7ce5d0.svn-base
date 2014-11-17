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
 *  分类查询 客服 信息表
 * @author jeep
 *
 */
public class listkfflAction extends Action{
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	     int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	     int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	     String pageMethod = request.getParameter("pageMethod");
	    
	     int leibie= Integer.parseInt((request.getParameter("leibie")));
	     String questcontext= (request.getParameter("questcontext"));
	     System.out.println("请求内容     ：   "+questcontext +"  类别     ：　"+ leibie);
	     
	     
	     Page<Kf> page = new Page<Kf>();
	     page.setPageNumber(pageNumber);
	     page.setPageSize(pageSize);
	     page.setPageMethod(pageMethod);
	     
	     String str = "";
	     StringBuffer sb = new StringBuffer();;
	     
		try {
			Database db = new SqlServer();
			KfDao kDao = new KfDao(db);
			
			 if (!kDao.kffl(page,leibie,questcontext )){
			     logger.debug("操作数据库失败！");
			     return XmlUtils.buildXmlReturnValue(0, "操作数据库失败！");
			 }
			 Iterator<Kf> iterator = page.iterator();
			 
			 int count = 0;
			 String alt = "";
			 while (iterator.hasNext()){
			     if (count % 2 != 0){
			         alt = "alt";
			     }else{
			         alt = "";
			     }
			     count++;
			     Kf kf = iterator.next();
			     
			     String kfid = kf.getKfid();
			     String name=kf.getName();
			     String tel= kf.getTel();
			     String staffId = kf.getStaffId();
			     
			     String tr = "<tr style=\"font-size: 12px;\">"
			    	 + "<td class=\"" + alt + "\">" + kfid + "</td> " + "<td class=\"" + alt + "\">"
			    	 + name + "</td> " + "<td class=\"" + alt + "\">" + tel + "</td> " + "<td class=\"" + alt + "\">"
			    	 + staffId + "</td> " + "<td class=\"" 
			    	 + alt + "\">" + "<input type='button' value='编辑' onclick='javascript:edit_info(" + kfid + ");' >" + "</td> " + "<td class=\"" 
			    	 + alt + "\">" + "<input type='button' value='删除' onclick='javascript:delete_kf("  + kfid + " );' >"+ "</td> "  + "</tr>";
			     sb.append(tr);
			 }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     return page.toXml(sb.toString());
	 }

}
