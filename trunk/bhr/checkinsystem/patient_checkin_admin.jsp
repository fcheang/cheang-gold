<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Patient CheckIn</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<p align="left" class="sidebarHeader">Patient CheckIn Status: </p>
<p align="left"><strong>
  </strong><strong>Appointment Date: 
  <%
 if (session.getAttribute("csaApptDate") != null){
 	out.print(session.getAttribute("csaApptDate"));
 }
 %>
</strong></p>
<div align="left">
  <%
 if (session.getAttribute("csaPatientCheckInTable") != null){
 	out.print(session.getAttribute("csaPatientCheckInTable"));
 }
 %>
  </div>
</body>
</html>
