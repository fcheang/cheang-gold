<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Select Clinic</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<script Language="JavaScript" Type="text/javascript">
function validateForm(theForm)
{
  if (theForm.rdMonth.value == "")
  {
    alert("Please enter a value for the \"month\" field.");
    theForm.rdMonth.focus();
    return (false);
  }

  if (theForm.rdMonth.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"month\" field.");
    theForm.rdMonth.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.rdMonth.value;
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
    alert("Please enter only digit characters in the \"month\" field.");
    theForm.rdMonth.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"month\" field.");
    theForm.rdMonth.focus();
    return (false);
  }

  if (theForm.rdDate.value == "")
  {
    alert("Please enter a value for the \"day\" field.");
    theForm.rdDate.focus();
    return (false);
  }

  if (theForm.rdDate.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"day\" field.");
    theForm.rdDate.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.rdDate.value;
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
    alert("Please enter only digit characters in the \"date\" field.");
    theForm.rdDate.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"day\" field.");
    theForm.rdDate.focus();
    return (false);
  }

  if (theForm.rdYear.value == "")
  {
    alert("Please enter a value for the \"year\" field.");
    theForm.rdYear.focus();
    return (false);
  }

  if (theForm.rdYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"year\" field.");
    theForm.rdYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.rdYear.value;
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
    alert("Please enter only digit characters in the \"year\" field.");
    theForm.rdYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2100"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2100\" in the \"year\" field.");
    theForm.rdYear.focus();
    return (false);
  }

  return (true);
}
</script>
<p class="sidebarHeader">Select Clinic and Appointment Date:</p>
<form name="form1" method="post" action="ShowPatientCheckInForApptDateAction.do" onSubmit="return validateForm(this)" language="JavaScript">
  <p>Please specify a clinic and appointment date:</p>
  <table width="619" border="0" cellspacing="5">
    <tr>
      <td width="139">Scheduled Clinic:</td>
      <td width="461"><select name="clinic" id="clinic">
          <option value="All" selected>All</option>
          <% if (session.getAttribute("csaClinicList") != null){
			   out.print(session.getAttribute("csaClinicList"));
			 } 
		  %>
      </select></td>
    </tr>
    <tr>
      <td>Appointment Date : </td>
      <td><input name="rdMonth" type="text" id="rdMonth" size="2" maxlength="2" 
  <%
  	if (session.getAttribute("csaMonth") != null){
		out.print("value=\""+session.getAttribute("csaMonth")+"\"");
	}
  %> 	  
	  >
        /
        <input name="rdDate" type="text" id="rdDate" size="2" maxlength="2"
  <%
  	if (session.getAttribute("csaDate") != null){
		out.print("value=\""+session.getAttribute("csaDate")+"\"");
	}
  %> 	  		
		>
        /
        <input name="rdYear" type="text" id="rdYear" size="4" maxlength="4"
  <%
  	if (session.getAttribute("csaYear") != null){
		out.print("value=\""+session.getAttribute("csaYear")+"\"");
	}
  %> 	  		
		></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="Submit" value="Submit"></td>
    </tr>
  </table>
  <p>&nbsp;</p>
</form>
<p>&nbsp; </p>

</body>
</html>
