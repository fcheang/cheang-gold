<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../style.css" rel="stylesheet" type="text/css">
</head>

<body>
 <p class="subtitle">Patient Tracking:</p>
<% if (session.getAttribute("logTable") != null){
     out.print(session.getAttribute("logTable"));
   }
%>
<% if (session.getAttribute("nextAction") != null){
     out.print(session.getAttribute("nextAction"));
   }
%>
</body>
</html>
