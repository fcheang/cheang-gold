<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Referral Report</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
<p align="left" class="sidebarHeader" style="margin-top: 0; margin-bottom: 0; background-color: #800000;">
<span style=""><font color="#FFFFFF">Weekly and Monthly Report Generation: </font></span></p>

<script Language="JavaScript" Type="text/javascript">
function FrontPage_Form1_Validator(theForm)
{

  if (theForm.esdMonth.value == "")
  {
    alert("Please enter a value for the \"effective start date\" field.");
    theForm.esdMonth.focus();
    return (false);
  }

  if (theForm.esdMonth.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"effective start date\" field.");
    theForm.esdMonth.focus();
    return (false);
  }

  if (theForm.esdMonth.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"effective start date\" field.");
    theForm.esdMonth.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.esdMonth.value;
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
    alert("Please enter only digit characters in the \"effective start date\" field.");
    theForm.esdMonth.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"effective start date\" field.");
    theForm.esdMonth.focus();
    return (false);
  }

  if (theForm.esdDate.value == "")
  {
    alert("Please enter a value for the \"effective start date\" field.");
    theForm.esdDate.focus();
    return (false);
  }

  if (theForm.esdDate.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"effective start date\" field.");
    theForm.esdDate.focus();
    return (false);
  }

  if (theForm.esdDate.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"effective start date\" field.");
    theForm.esdDate.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.esdDate.value;
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
    alert("Please enter only digit characters in the \"effective start date\" field.");
    theForm.esdDate.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"effective start date\" field.");
    theForm.esdDate.focus();
    return (false);
  }

  if (theForm.esdYear.value == "")
  {
    alert("Please enter a value for the \"effective start date\" field.");
    theForm.esdYear.focus();
    return (false);
  }

  if (theForm.esdYear.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"effective start date\" field.");
    theForm.esdYear.focus();
    return (false);
  }

  if (theForm.esdYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"effective start date\" field.");
    theForm.esdYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.esdYear.value;
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
    alert("Please enter only digit characters in the \"effective start date\" field.");
    theForm.esdYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2200"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2200\" in the \"effective start date\" field.");
    theForm.esdYear.focus();
    return (false);
  }

  if (theForm.eedMonth.value == "")
  {
    alert("Please enter a value for the \"effective start date\" field.");
    theForm.eedMonth.focus();
    return (false);
  }

  if (theForm.eedMonth.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"effective start date\" field.");
    theForm.eedMonth.focus();
    return (false);
  }

  if (theForm.eedMonth.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"effective start date\" field.");
    theForm.eedMonth.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.eedMonth.value;
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
    alert("Please enter only digit characters in the \"effective start date\" field.");
    theForm.eedMonth.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"effective start date\" field.");
    theForm.eedMonth.focus();
    return (false);
  }

  if (theForm.eedDate.value == "")
  {
    alert("Please enter a value for the \"effective start date\" field.");
    theForm.eedDate.focus();
    return (false);
  }

  if (theForm.eedDate.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"effective start date\" field.");
    theForm.eedDate.focus();
    return (false);
  }

  if (theForm.eedDate.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"effective start date\" field.");
    theForm.eedDate.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.eedDate.value;
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
    alert("Please enter only digit characters in the \"effective start date\" field.");
    theForm.eedDate.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"effective start date\" field.");
    theForm.eedDate.focus();
    return (false);
  }

  if (theForm.eedYear.value == "")
  {
    alert("Please enter a value for the \"effective start date\" field.");
    theForm.eedYear.focus();
    return (false);
  }

  if (theForm.eedYear.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"effective start date\" field.");
    theForm.eedYear.focus();
    return (false);
  }

  if (theForm.eedYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"effective start date\" field.");
    theForm.eedYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.eedYear.value;
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
    alert("Please enter only digit characters in the \"effective start date\" field.");
    theForm.eedYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2200"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2200\" in the \"effective start date\" field.");
    theForm.eedYear.focus();
    return (false);
  }

  if (theForm.reportType.selectedIndex < 0)
  {
    alert("Please select one of the \"report type\" options.");
    theForm.reportType.focus();
    return (false);
  }

  if (theForm.reportType.selectedIndex == 0)
  {
    alert("The first \"report type\" option is not a valid selection.  Please choose one of the other options.");
    theForm.reportType.focus();
    return (false);
  }
  return (true);
}
</script>

<SCRIPT TYPE="text/javascript">
var downStrokeField;
function autojump(fieldName,nextFieldName,fakeMaxLength)
{
var myForm=document.forms[document.forms.length - 1];
var myField=myForm.elements[fieldName];
myField.nextField=myForm.elements[nextFieldName];

if (myField.maxLength == null)
   myField.maxLength=fakeMaxLength;

myField.onkeydown=autojump_keyDown;
myField.onkeyup=autojump_keyUp;
}

function autojump_keyDown()
{
this.beforeLength=this.value.length;
downStrokeField=this;
}

function autojump_keyUp()
{
if (
   (this == downStrokeField) && 
   (this.value.length > this.beforeLength) && 
   (this.value.length >= this.maxLength)
   )
   this.nextField.focus();
downStrokeField=null;
}
</SCRIPT>

<form action="ReportAction.do" method="POST" name="FrontPage_Form1" target="_blank" onsubmit="return FrontPage_Form1_Validator(this)" language="JavaScript">
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  * Report Start Date is inclusive and start from midnight</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  &nbsp;&nbsp; Report End Date is exclusive and end at midnight</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  (example, for reports for the period from 1/1/2005 to 1/15/2005 inclusive, you 
  should</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  specify Report Start Date='1/1/2005' and Report End Date='1/16/2005')</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
  </p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0">
  <b>Date Range:</b></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0">&nbsp;&nbsp;&nbsp; 
  Report Start Date:&nbsp;&nbsp;&nbsp;
  <input type="text" name="esdMonth" size="2" value="1" tabindex="1" maxlength="2"> 
  -
  <input type="text" name="esdDate" size="2" value="1" tabindex="2" maxlength="2"> 
  -
  <input type="text" name="esdYear" size="4" value="1900" tabindex="3" maxlength="4"> 
  (mm-dd-yyyy)</p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0">&nbsp;&nbsp;&nbsp; 
  Report End Date:&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="text" name="eedMonth" size="2" value="1" tabindex="4" maxlength="2"> 
  -
  <input type="text" name="eedDate" size="2" value="1" tabindex="5" maxlength="2"> 
  -
  <input type="text" name="eedYear" size="4" value="2200" tabindex="6" maxlength="4"> 
  (mm-dd-yyyy)</p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>
  Report type:</b></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0">&nbsp;&nbsp;&nbsp;
  <select size="1" name="reportType" tabindex="7">
    <option selected>- Select a report type - </option>
	<%
		out.print(session.getAttribute("reportList"));
	%>
  </select></p>
  <p align="left" style="line-height: 200%;"><strong>Report title:</strong>    
    <input name="reportTitle" type="text" id="reportTitle" size="60" maxlength="60" tabindex="8">
  </p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0">
  <input type="submit" value="Submit" name="Submit" tabindex="9"><input type="reset" value="Reset" name="B2" tabindex="10"></p>
</form>
<SCRIPT TYPE="text/javascript">
<!--
autojump('esdMonth', 'esdDate', 2);
autojump('esdDate', 'esdYear', 2);
autojump('esdYear', 'eedMonth', 4);
autojump('eedMonth', 'eedDate', 2);
autojump('eedDate', 'eedYear', 2);
autojump('eedYear', 'reportType', 4);
//-->
</SCRIPT>

</body>

</html>