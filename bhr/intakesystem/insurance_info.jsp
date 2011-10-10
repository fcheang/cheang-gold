<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Insurance Provider information</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<p class="sidebarHeader"><font color="#FFFFFF">
<b><span class="style1">Insurance Provider Information: </span></b></font></p>

<p>&nbsp;</p>

<% 
   if (session.getAttribute("message") != null){
     out.print(session.getAttribute("message"));
   }
%>

</body>

</html>