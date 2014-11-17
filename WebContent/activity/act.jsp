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
	<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../activity/addact.js"></script>
<title>活动管理</title>

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
	
 	#tb_act td{
 		height: 30px;
 		background-color:#F1F1F1;
 		text-align: center;
 		
 	}
	
</style>

</head>
<body>
	
<div class="header" id="header"  width= "1000px;" heigh="100px;">
<jsp:include page="../includes/managerheader.jsp" flush="true">
		<jsp:param name="navno" value="1" />
  </jsp:include>
</div>
	<br>
	   <div class="out_act" style="margin:0 30%;width:60%">
	     <form action="../addact.u" method="post" name="addact" id="addact" enctype="multipart/form-data" onSubmit="return mycheck()">
	        <table id="tb_act" width="60%" height="100%" border="0" cellspacing="1" style="background-color: #a0c6e5">
	            <tr>
	              <td colspan="2">活动信息</td>
	            </tr>
	            <tr>
	              <td>活动名称: <input type="text" size="30" id="act_name" name="act_name" ></td>
	            </tr>
	             <tr>
	              <td>
	              时间范围：  <input type="text" name="act_start" id="date" size="10" onClick="WdatePicker()">——<input type="text" name="act_end" id="date" size="10"  onClick="WdatePicker()">
	              </td>
	            </tr>
	            
	            <tr>
	            <!--  
	              <td><textarea cols="28" rows="13" name="act_introd"></textarea></td>
	             -->
	              <td><input type="hidden" size="30" name="act_address" value="sdjinan"></td>
	              <td><input type="hidden" size="30" name="act_introd" value="act_introd"></td>
	            </tr>
	            <tr>
	              <td>活动图片：<input type="file" name="act_image"></td>
	            </tr>
	            <tr>
	              <td>活动详情网页：<input type="file" name="act_iamgexq"></td>
	            </tr>
	            <tr>
	            <td>
		            <input type="submit" class="" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="" type="reset" class="" onclick="javascript:refresh();" value="重置">
				</td>
				</tr>
	          </table>
	      </form> 
	</div>
	
</body>
</html>