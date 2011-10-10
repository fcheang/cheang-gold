<html>

<head>
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Error Page</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<p align="center">&nbsp;</p>
<h3 align="center">
<% if (session.getAttribute("message") != null){
     out.print(session.getAttribute("message"));
   }
%>
</h3>

<p align="center">&nbsp;</p>
<h3 align="center">
<% if (session.getAttribute("error") != null){
     out.print(session.getAttribute("error"));
   }
%>
</h3>

</body>

</html>