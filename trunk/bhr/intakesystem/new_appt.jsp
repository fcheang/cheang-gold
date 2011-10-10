<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>New Appointment</title>
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
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
<span style=""><font color="#FFFFFF">Appointment Detail: </font>
</span></p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
  <script Language="JavaScript" Type="text/javascript"><!--
function FrontPage_Form1_Validator(theForm)
{

  if (theForm.clinic.selectedIndex == 0)
  {
    alert("The first \"Clinic\" option is not a valid selection.  Please choose one of the other options.");
    theForm.clinic.focus();
    return (false);
  }

  if (theForm.provider.selectedIndex == 0)
  {
    alert("The first \"Provider\" option is not a valid selection.  Please choose one of the other options.");
    theForm.provider.focus();
    return (false);
  }

  if (theForm.adMonth.value == "")
  {
    alert("Please enter a value for the \"month\" field.");
    theForm.adMonth.focus();
    return (false);
  }

  if (theForm.adMonth.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"month\" field.");
    theForm.adMonth.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.adMonth.value;
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
    theForm.adMonth.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"month\" field.");
    theForm.adMonth.focus();
    return (false);
  }

  if (theForm.adDate.value == "")
  {
    alert("Please enter a value for the \"date\" field.");
    theForm.adDate.focus();
    return (false);
  }

  if (theForm.adDate.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"date\" field.");
    theForm.adDate.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.adDate.value;
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
    theForm.adDate.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"date\" field.");
    theForm.adDate.focus();
    return (false);
  }

  if (theForm.adYear.value == "")
  {
    alert("Please enter a value for the \"adYear\" field.");
    theForm.adYear.focus();
    return (false);
  }

  if (theForm.adYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"adYear\" field.");
    theForm.adYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.adYear.value;
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
    alert("Please enter only digit characters in the \"adYear\" field.");
    theForm.adYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2200"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2200\" in the \"adYear\" field.");
    theForm.adYear.focus();
    return (false);
  }

  if (theForm.adHour.value == "")
  {
    alert("Please enter a value for the \"hour\" field.");
    theForm.adHour.focus();
    return (false);
  }

  if (theForm.adHour.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"hour\" field.");
    theForm.adHour.focus();
    return (false);
  }

  if (theForm.adHour.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"hour\" field.");
    theForm.adHour.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.adHour.value;
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
    alert("Please enter only digit characters in the \"hour\" field.");
    theForm.adHour.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"hour\" field.");
    theForm.adHour.focus();
    return (false);
  }

  if (theForm.adMin.value == "")
  {
    alert("Please enter a value for the \"minute\" field.");
    theForm.adMin.focus();
    return (false);
  }

  if (theForm.adMin.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"minute\" field.");
    theForm.adMin.focus();
    return (false);
  }

  if (theForm.adMin.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"minute\" field.");
    theForm.adMin.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.adMin.value;
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
    alert("Please enter only digit characters in the \"minute\" field.");
    theForm.adMin.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "0" && prsVal <= "59"))
  {
    alert("Please enter a value greater than or equal to \"0\" and less than or equal to \"59\" in the \"minute\" field.");
    theForm.adMin.focus();
    return (false);
  }

  var radioSelected = false;
  for (i = 0;  i < theForm.needTranslationSvc.length;  i++)
  {
    if (theForm.needTranslationSvc[i].checked)
        radioSelected = true;
  }
  if (!radioSelected)
  {
    alert("Please select one of the \"translation service\" options.");
    return (false);
  }

  var radioSelected = false;
  for (i = 0;  i < theForm.collateralReceived.length;  i++)
  {
    if (theForm.collateralReceived[i].checked)
        radioSelected = true;
  }
  if (!radioSelected)
  {
    alert("Please select one of the \"Collateral received\" options.");
    return (false);
  }
  return (true);
}
//--></script>

<SCRIPT TYPE="text/javascript">
<!--
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
//-->
</SCRIPT>

</p>
<form method="POST" action="view_appt.jsp" onsubmit="return FrontPage_Form1_Validator(this)" language="JavaScript" name="FrontPage_Form1">
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Clinic:&nbsp;<span class="style1">*</span>&nbsp;&nbsp;
  <select size="1" name="clinic" tabindex="1">
<option selected value="null">-Select-</option>
<% if (session.getAttribute("clinicName") != null){
     out.print(session.getAttribute("clinicName"));
   }
%>
</select></b></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Appointment date:&nbsp;<span class="style1">*</span>&nbsp;&nbsp;
  <input type="text" name="adMonth" size="2" tabindex="2" maxlength="2"> -
  <input type="text" name="adDate" size="2" tabindex="3" maxlength="2"> -
  <input type="text" name="adYear" size="4" tabindex="4" maxlength="4"></b><i>&nbsp; (month-date-year)</i></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Appointment time:&nbsp;<span class="style1">*</span>&nbsp;&nbsp;
  <input type="text" name="adHour" size="2" tabindex="5" maxlength="2">&nbsp; :
 <input type="text" name="adMin" size="2" tabindex="6" maxlength="2">&nbsp; <select size="1" name="adAMPM" tabindex="7">
  <option selected>AM</option>
  <option>PM</option>
  </select> </b><i>(hour : minute AM/PM)</i></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Provider:<span class="style1">*</span> &nbsp;   
  <select size="1" name="provider" tabindex="8">
 <option selected value="null">-Select-</option>
 <% if (session.getAttribute("providerList") != null){
      out.print(session.getAttribute("providerList"));
    }
 %>
 </select>    
  </b></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Translation service needed?&nbsp;<span class="style1">*</span>&nbsp;&nbsp;
  <input type="radio" value="true" name="needTranslationSvc" tabindex="9">
  </b>Yes&nbsp;&nbsp;&nbsp;
  <input type="radio" name="needTranslationSvc" value="false" tabindex="10"> No</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Collateral received?&nbsp;<span class="style1">*</span>&nbsp;&nbsp;
  <input type="radio" name="collateralReceived" value="true" tabindex="11"> </b>
  Yes&nbsp;&nbsp;&nbsp;
  <input type="radio" name="collateralReceived" value="false" tabindex="12"> No</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Notes:&nbsp;&nbsp;&nbsp; </b></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <textarea rows="4" name="notes" cols="25" tabindex="13"></textarea></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <input type="submit" value="Submit" name="submit" tabindex="14"><input type="reset" value="Reset" name="reset" tabindex="15"></p>
</form>
<SCRIPT TYPE="text/javascript">
<!--
autojump('adMonth', 'adDate', 2);
autojump('adDate', 'adYear', 2);
autojump('adYear', 'adHour', 4);
autojump('adHour', 'adMin', 2);
autojump('adMin', 'adAMPM', 2);
//-->
</SCRIPT>

</body>

</html>