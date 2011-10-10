<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Patient CheckIn</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
<meta http-equiv="refresh" content="<% out.print(session.getAttribute("csRefreshRate")); %>;url=ShowPatientCheckInAction.do">
</head>

<body>
 <p align="center" class="title">Patient Self CheckIn System</p>
 <p align="center"><strong>
 <%
  if (session.getAttribute("csClinic") != null){
  	out.print(session.getAttribute("csClinic"));
  }
 %>
&nbsp; Clinic </strong></p>
 <p align="center"><strong>
  <%
 if (session.getAttribute("csApptDate") != null){
 	out.print(session.getAttribute("csApptDate"));
 }
 %>
 </strong></p>
 <p align="center"><strong>Please click the CheckIn link to start the CheckIn process:</strong></p>
 <div align="center">
    <%
 if (session.getAttribute("csPatientCheckInTable") != null){
 	out.print(session.getAttribute("csPatientCheckInTable"));
 }
 %>
 </div>
</body>
</html>
