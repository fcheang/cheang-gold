<html>

<head>
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>New Page 1</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<p align="center">&nbsp;</p>
<h3 align="center">
<% if (session.getAttribute("csMessage") != null){
     out.print(session.getAttribute("csMessage"));
   }
%>
</h3>
</body>

</html>