<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="../js/jquery-1.2.6.min.js"></script>
	<script type="text/javascript" src="../js/kf.js"></script>
	<script  type="text/javascript"src="../js/PageUtils.js" ></script>
	<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<title>数据统计</title>

<style type="text/css">
	/* common styling */
	/* set up the overall width of the menu div, the font and the margins */
	.menu {
	font-family: arial, sans-serif; 
	 /*width:420px;*/ 
	margin:0; 
	margin:10px 0;
	}
	/* remove the bullets and set the margin and padding to zero for the unordered list */
	.menu ul {
	padding:0; 
	margin:0;
	list-style-type: none;
	}
	/* float the list so that the items are in a line and their position relative so that the drop down list will appear in the right place underneath each list item */
	.menu ul li {
	float:left; 
	position:relative;
	}
	/* style the links to be 104px wide by 30px high with a top and right border 1px solid white. Set the background color and the font size. */
	.menu ul li a, .menu ul li a:visited {
	display:block; 
	text-align:center; 
	text-decoration:none; 
	width:104px; 
	height:30px; 
	color:#000; 
	border:1px solid #fff;
	border-width:1px 1px 0 0;
	
	line-height:30px; 
	font-size:11px;
	}
	/* make the dropdown ul invisible */
	.menu ul li ul {
	display: none;
	}
	/* specific to non IE browsers */
	/* set the background and foreground color of the main menu li on hover */
	.menu ul li:hover a {
	color:#fff; 
	background:#b3ab79;
	}
	/* make the sub menu ul visible and position it beneath the main menu list item */
	.menu ul li:hover ul {
	display:block; 
	position:absolute; 
	top:31px; 
	left:0; 
	width:105px;
	}
	/* style the background and foreground color of the submenu links */
	.menu ul li:hover ul li a {
	display:block; 
	background:#faeec7; 
	color:#000;
	}
	/* style the background and forground colors of the links on hover */
	.menu ul li:hover ul li a:hover {
	background:#dfc184; 
	color:#000;
	}
	
	/*  page css */
	.text1{
		width:80px;
		
	}
	
</style>

</head>
<body>
	
<div class="header" id="header"   width: 1000px; height: 100px;>
<jsp:include page="../includes/managerheader.jsp" flush="true">
		<jsp:param name="navno" value="1" />
  </jsp:include>
</div>

<%-- 条件查询  --%>
<div id="left_top" class="left_top" style="border:1px solid #dddddd;width:81%;margin:10px auto;">
      <form name="form1" method="post" action="<%=basePath%>DataListServlet">
        <table width="100%%" border="0" cellspacing="0" cellpadding="0">
          <tr>
          	<td width="12%"></td>
          	<td>
          	   时间范围：  <input type="text"  name="datefrom" id="date" class="text1" onClick="WdatePicker()">-<input type="text"  name="dateto" id="date" class="text1" onClick="WdatePicker()">
          	   
          	   &nbsp;&nbsp;
          	     客服工号： <input name="kfid" type="text"  class="text1" id="kfid">&nbsp;&nbsp;
    		      物流工号：<input name="wlid" type="text"  class="text1" id="wlid">
    		      <input type="submit" name="button" id="button" value="查询">
    		      <input type="reset" name="btn_reset" id="btn_reset" value="重置">
    		 </td>
          </tr>
        </table>
      </form>
</div>

<%-- 数据 显示 --%>  
<div align="center" id="jieguo" class="jieguo" style="border:1px solid #dddddd; width:95%;margin:0px auto;">
	<div id="order" style="text-align:center;">
	   <table width="99%" border="0" align="center" id="order_Id2"> 
	    <tr bgcolor="#E0FFFF" height="40">
	      <th width="61">客服工号</th>
	      <th width="61">物流工号</th>
	      <th width="61">服务区域</th>
	      <th width="61">用户下单量</th>
		  <th width="78">取衣单量</th>
		  <th width="90">取衣数量</th>
		  <th width="90">取衣总金额</th>
		  <th width="90">送衣单量</th>
		  <th width="90">拒单数量</th>
		  <th width="90">用户评价总量</th>
		  <th width="90">用户洗衣差评总量</th>
		  <th width="90">用户洗衣中评总量</th>
		  <th width="90">用户物流差评总量</th>
		  <th width="90">用户物流中评总量</th>
	    </tr>
	    <c:forEach  items="${sessionScope.dlist}" var="data" varStatus="status">
	    	<c:if test="${status.index%2!=0}">
		    	<tr bgcolor="#ECFAF9" height="25">
	              <td>${data.kfid}</td>
	              <td>${data.wlid}</td>
	              <td>${data.fwArea}</td>
	              <td>${data.yhdl}</td>
	              <td>${data.qydl}</td>
	              <td>${data.qysl}</td>
	              <td>${data.zje}</td>
	              <td>${data.sydl}</td>
	              <td>${data.jdsl}</td>
	              <td>${data.pjzl}</td>
	              <td>${data.xycpzl}</td>
	              <td>${data.xyzpzl}</td>
	              <td>${data.wlcpzl}</td>
	              <td>${data.wlzpzl}</td>
	             </tr>
             </c:if>
             
             <c:if test="${status.index % 2 ==0}">
		    	<tr bgcolor="#FFFFFF" height="25">
	              <td>${data.kfid}</td>
	              <td>${data.wlid}</td>
	              <td>${data.fwArea}</td>
	              <td>${data.yhdl}</td>
	              <td>${data.qydl}</td>
	              <td>${data.qysl}</td>
	              <td>${data.zje}</td>
	              <td>${data.sydl}</td>
	              <td>${data.jdsl}</td>
	              <td>${data.pjzl}</td>
	              <td>${data.xycpzl}</td>
	              <td>${data.xyzpzl}</td>
	              <td>${data.wlcpzl}</td>
	              <td>${data.wlzpzl}</td>
	             </tr>
             </c:if>
	    </c:forEach>
	    
	  </table>
	</div>
</div>
	<div  align="center" style="margin-top: 20px"> 
		<form action="<%=basePath%>OutputExcel" method="post">
			<input type="submit" name="" value="导出到excel" />
		</form>
	</div>

</body>
</html>