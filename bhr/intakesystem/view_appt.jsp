<jsp:useBean id="appt" class="com.suntek.common.persistence.Appointment" scope="session"/>
<jsp:setProperty name="appt" property="*"/>

<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Preview Appointment</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<p align="left" style="margin-top: 0; margin-bottom: 0">
<b>Referral ID:</b>
<%
  if (session.getAttribute("refId") != null){
    out.print(session.getAttribute("refId")); 
  }
%>
</p>
<p align="left" class="sidebarHeader">Appointment Information: </p>
<form method="POST" action="NewApptAction.do">
  <p align="left" style="line-height: 150%; margin-top: 0; margin-bottom: 0"><b>Clinic: </b>
      <jsp:getProperty name="appt" property="clinic"/>    
  </p>
  <p align="left" style="line-height: 150%; margin-top: 0; margin-bottom: 0"><b> Appointment date: </b>
      <jsp:getProperty name="appt" property="apptDateStr"/>    
</p>
  <p align="left" style="line-height: 150%; margin-top: 0; margin-bottom: 0"><b> Appointment time: </b>
      <jsp:getProperty name="appt" property="apptTimeStr"/>    
</p>
  <p align="left" style="line-height: 150%; margin-top: 0; margin-bottom: 0"><b> Provider: </b>
      <jsp:getProperty name="appt" property="provider"/>    
</p>
  <p align="left" style="line-height: 150%; margin-top: 0; margin-bottom: 0"><b> Need translation service: </b>
      <jsp:getProperty name="appt" property="needTranslationSvc"/>    
</p>
  <p align="left" style="line-height: 150%; margin-top: 0; margin-bottom: 0"><b> Collateral received: </b>
      <jsp:getProperty name="appt" property="collateralReceived"/>    
</p>
  <p align="left" style="line-height: 150%; margin-top: 0; margin-bottom: 0"><b> Notes: </b>
      <jsp:getProperty name="appt" property="notes"/>    
</p>
  <p align="left" style="line-height: 150%; margin-top: 0; margin-bottom: 0">&nbsp;</p>
  <p>
    <input type="submit" name="Confirm" value="Save appointment" tabindex="1">
    </p>
</form>

</body>

</html>