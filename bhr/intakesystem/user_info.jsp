<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>User information</title>
<link href="style.css" rel="stylesheet" type="text/css">

<body>

<p class="sidebarHeader"><strong><font color="#FFFFFF">User Information: </font></strong></p>

<% 
   if (session.getAttribute("message") != null){
     out.print(session.getAttribute("message"));
   }
%>


</body>

</html>