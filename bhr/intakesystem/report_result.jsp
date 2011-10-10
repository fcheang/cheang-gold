<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Report result</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<p><font color="#FFFFFF">
</font>
  <% 
   if (session.getAttribute("message") != null){
     out.print(session.getAttribute("message"));
   }
%>
<%
if (session.getAttribute("currentTime") != null){
	out.print(session.getAttribute("currentTime"));
}
%>
</p>
</body>

</html>