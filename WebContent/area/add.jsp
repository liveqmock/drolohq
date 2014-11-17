<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加区域</title>
  <script type="text/javascript" src="../js/jquery-1.2.6.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=4bea048f39fe615815b7e20c661fc39f"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<script type="text/javascript" src="../js/work/area.js"></script>
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
</style>

<%-- 表格 行 颜色 --%>
<style type="text/css">
	.alt{
		background-color: #D1EEEE;
	}

</style>
</head> 
<body>
<div class="header" id="header"   width: 1000px; height: 100px;>
	<jsp:include page="../includes/managerheader.jsp" flush="true">
		<jsp:param name="navno" value="1" />
    </jsp:include>
</div>

<div id="left_top" class="left_top" style="border: 1px solid #dddddd; float: left; width: 27%; height: 600px;">
<form name="addone" id="addone" method="post"   class="addone">
	<table >
	
	  <tr>
	    <td >点1经度：</td>
	    <td  class="class2"><input type="text" size="10" name="p1lon" id="p1lon" "></td>
	    <td  class="class1">点1维度：</td>
	    <td class="class2"><input type="text"  size="9"name="p1lan" id="p1lan" "></td>
	  </tr>
	 <tr>
	    <td >点2经度：</td>
	    <td  class="class2"><input type="text" size="10" name="p2lon" id="p2lon" "></td>
	    <td  class="class1">点2维度：</td>
	    <td class="class2"><input type="text"  size="9"name="p2lan" id="p2lan" "></td>
	  </tr>
	  <tr>
	    <td >点3经度：</td>
	    <td  class="class2"><input type="text" size="10" name="p3lon" id="p3lon" "></td>
	    <td  class="class1">点3维度：</td>
	    <td class="class2"><input type="text"  size="9"name="p3lan" id="p3lan" "></td>
	  </tr>
	  <tr>
	    <td >点4经度：</td>
	    <td  class="class2"><input type="text" size="10" name="p4lon" id="p4lon" "></td>
	    <td  class="class1">点4维度：</td>
	    <td class="class2"><input type="text"  size="9"name="p4lan" id="p4lan" "></td>
	  </tr>
	  <tr>
	    <td >点5经度：</td>
	    <td  class="class2"><input type="text" size="10" name="p5lon" id="p5lon" "></td>
	    <td  class="class1">点5维度：</td>
	    <td class="class2"><input type="text"  size="9"name="p5lan" id="p5lan" "></td>
	  </tr><tr>
	    <td >点6经度：</td>
	    <td  class="class2"><input type="text" size="10" name="p6lon" id="p6lon" "></td>
	    <td  class="class1">点6维度：</td>
	    <td class="class2"><input type="text"  size="9"name="p6lan" id="p6lan" "></td>
	  </tr><tr>
	    <td >点7经度：</td>
	    <td  class="class2"><input type="text" size="10" name="p7lon" id="p7lon" "></td>
	    <td  class="class1">点7维度：</td>
	    <td class="class2"><input type="text"  size="9"name="p7lan" id="p7lan" "></td>
	  </tr><tr>
	    <td >点8经度：</td>
	    <td  class="class2"><input type="text" size="10" name="p8lon" id="p8lon" "></td>
	    <td  class="class1">点8维度：</td>
	    <td class="class2"><input type="text"  size="9"name="p8lan" id="p8lan" "></td>
	  </tr>
	 <tr>
	    <td >区域名：</td>
	    <td  class="class2"><input type="text" size="10" name="areaName" id="areaName" "></td>
	    <td  class="class1">物流号：</td>
	    <td class="class2"><input type="text"  size="9"name="staffId" id="staffId" "></td>
	  </tr> 
	   <tr>
	    <td >区域号：</td>
	    <td  class="class2"><input type="text" size="10" name="areaNum" id="areaNum" "></td>
	    <td colspan="2" align="center"><input type="button" name="areaShow" value="区域地图显示 "OnClick="areaAddShow();"></td>
	  </tr> 
	</table>
	<input type="hidden" value="" name="sid" id="sid" />
</form>

</div>
<div id="context" class="context" style="float: right; width:72.8%; height:500px;">
		<table width="100%" height="100%" border="1" bordercolor="#dddddd">
			<tr>

				<td style="width: 100%">
					<div style="width: 100%; height: 500px; display: block;">
						<div style="width: 100%; height: 100%; position: relative">
							<div id="assignMap"
								style="width: 100%; height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
						</div>
					</div></td>

			</tr>
		</table>
		
		<div style="text-align: center;">
				<input type="button" value="提交区域"
					onclick="javascript:submitArea();" class="buttom">
			 
		</div> 

	</div>
</body>
</html>