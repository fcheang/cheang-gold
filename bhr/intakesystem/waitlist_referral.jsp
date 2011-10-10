<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<p align="left" style="margin-top: 0; margin-bottom: 0"><b>Referral ID:</b>
    <%
  if (session.getAttribute("refId") != null){
    out.print(session.getAttribute("refId")); 
  }
%>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp; </p>
<p align="left" class="sidebarHeader" style="margin-top: 0; margin-bottom: 0; background-color: #800000;"> <span style=""><font color="#FFFFFF">Waitlist Detail: </font> </span></p>
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp; </p>
<form method="POST" action="WaitlistRefAction.do">
  <p align="left" style="margin-top: 0; margin-bottom: 0"> Notes:</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
    <textarea rows="5" name="notes" cols="40" tabindex="1"></textarea>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
    <input name="Submit" type="submit" tabindex="2" value="Save">
    <input type="reset" value="Reset" name="reset" tabindex="3">
  </p>
</form>

</body>
</html>
