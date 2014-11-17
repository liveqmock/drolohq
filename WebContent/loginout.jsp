<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.sql.*,java.util.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="author" content="wyjsusan">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登出页面</title>
</head>
<body>
<%
	Enumeration Attributes =  session.getAttributeNames();
	
	
	while(Attributes.hasMoreElements())
	{
		String ParaName = (String)Attributes.nextElement();
		System.out.println("ParaName========" + ParaName);
		session.removeAttribute(ParaName);//清空session
	}
	
    
		response.sendRedirect("login.jsp");
   
     
%>
</body>
</html>