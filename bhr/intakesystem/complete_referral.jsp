<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Complete Referral</title>
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
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
</p>
<p align="left" class="sidebarHeader" style="margin-top: 0; margin-bottom: 0; background-color: #800000;">
<span style=""><font color="#FFFFFF"><b>Showup for Appointment detail: </b></font></span></p>
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
</p>
<form method="POST" action="NewCompleteAction.do">
  <p align="left" style="margin-top: 0; margin-bottom: 0;">
  Notes:       </p>
  <p align="left" style="margin-left: 5 ">
  <textarea rows="5" name="notes" cols="40" tabindex="1"></textarea>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0"> 
  <input type="submit" value="Submit" name="submit" tabindex="2"><input type="reset" value="Reset" name="reset" tabindex="3"></p>
</form>

</body>

</html>