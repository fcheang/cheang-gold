<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<p class="sidebarHeader"><font color="#FFFFFF"> <b><span class="style1">Provider Information:</span></b></font></p>
<% 
   if (session.getAttribute("message") != null){
     out.print(session.getAttribute("message"));
   }
%>

</body>
</html>
