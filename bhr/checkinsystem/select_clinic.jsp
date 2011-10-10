<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<script Language="JavaScript" Type="text/javascript">
function validateForm(theForm)
{
  if (theForm.clinic.selectedIndex <= 0)
  {
    alert("Please select one of the \"Clinic\" options.");
    theForm.clinic.focus();
    return (false);
  }

  if (theForm.refreshRate.value == "")
  {
    alert("Please enter a value for the Refresh rate.");
    theForm.refreshRate.focus();
    return (false);
  }

  var checkOK = "0123456789";
  var checkStr = theForm.refreshRate.value;
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
    alert("Please enter only digit characters in the refresh rate.");
    theForm.refreshRate.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "5"))
  {
    alert("Refresh rate must be at least 5 seconds");
    theForm.refreshRate.focus();
    return (false);
  }

  return (true);
}
</script>
 <p class="sidebarHeader">Select Clinic :</p>
 <form name="form1" method="post" action="ShowPatientCheckInAction.do" onSubmit="return validateForm(this)" language="JavaScript">
   <p>Please specify a clinic and the refresh rate for the CheckIn Page: </p>
   <table width="619" border="0" cellspacing="5">
     <tr>
       <td width="139">Scheduled Clinic:</td>
       <td width="461"><select name="clinic" id="clinic">
         <option selected>- Select -</option>
		  <% if (session.getAttribute("csClinicList") != null){
			   out.print(session.getAttribute("csClinicList"));
			 } 
		  %>		 
       </select></td>
     </tr>
     <tr>
       <td>Refresh Rate: </td>
       <td><input name="refreshRate" type="text" id="refreshRate" value="3600" size="10" maxlength="10">
         (seconds)</td>
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
