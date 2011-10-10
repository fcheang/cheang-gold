<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Patient Authentication</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="refresh" content="60;url=ShowPatientCheckInAction.do">
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<script language="javascript" type="text/javascript">
function validateForm(theForm){
  if (theForm.lastFourSSN.value == "")
  {
    alert("Please enter a value for the \"SSN\" field.");
    theForm.lastFourSSN.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.lastFourSSN.value;
  var allValid = true;
  var validGroups = true;
  var decPoints = 0;
  var allNum = "";
  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
    allNum += ch;
  }
  if (!allValid)
  {
    alert("Please enter only digit characters in the \"SSN\" field.");
    theForm.lastFourSSN.focus();
    return (false);
  }
  
  if (theForm.lastFourSSN.value.length < 4){
  	alert("Please enter four digit in the \"SSN\" field.");
	theForm.lastFourSSN.focus();
	return (false);
  }
  
  return true;
}
</script>

<p class="sidebarHeader">CheckIn Process: </p>
<p><strong>Patient Name: <% out.print(session.getAttribute("csPatientName")); %><br>
  Provider: <% out.print(session.getAttribute("csProvider")); %> <br>
  Appointment Date: <% out.print(session.getAttribute("csMyApptDate")); %><br>
  Appointment Time: <% out.print(session.getAttribute("csMyApptTime")); %></strong></p>
<form name="form1" method="post" action="PatientVerificationAction.do">  
  <p>
  <strong>
  <%
  if (session.getAttribute("csInvalidSSN") != null){
  	out.print(session.getAttribute("csInvalidSSN"));
  }
  %>
  </strong><br>
  <strong>Patient Verification:</strong><br>
  <strong>Please enter the last 4 digit of your Social Security Number and click the OK button:</strong></p>
  <p>
    &nbsp;&nbsp;<input name="lastFourSSN" type="password" id="lastFourSSN" size="4" maxlength="4">
</p>
  <p>
    <input name="OK" type="submit" id="OK" value="OK">
    <input name="Cancel" type="submit" id="Cancel" value="Cancel">
  </p>
</form>
<p>&nbsp; </p>
</body>
</html>
