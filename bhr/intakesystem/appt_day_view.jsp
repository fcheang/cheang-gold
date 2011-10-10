<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Intake System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style7 {font-size: 16px}
.style9 {font-size: 9px}
-->
</style>
</head>

<body>
 <p align="center" class="dingbat style7">Appointment Day View</p>
 <p align="center"><strong><% 
 if (session.getAttribute("doctorAndClinic") != null)
  out.print(session.getAttribute("doctorAndClinic"));
 %>
 <br>
 <% 
 if (session.getAttribute("apptDateRange") != null)
  out.print(session.getAttribute("apptDateRange"));
 %> 
 </strong></p>
 
 <div align="left">
 <%
 if (session.getAttribute("apptDayViewTable") != null)
 out.print(session.getAttribute("apptDayViewTable"));
 %>
 </div>
 
</body>
</html>
