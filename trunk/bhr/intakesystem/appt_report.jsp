<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
<script language="javascript">
function checkAll(field)
{
for (i = 0; i < field.length; i++)
	field[i].checked = true ;
}

function uncheckAll(field)
{
for (i = 0; i < field.length; i++)
	field[i].checked = false ;
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

<script Language="JavaScript" Type="text/javascript">
function validateForm(theForm)
{
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
    alert("Please enter a value for the \"day\" field.");
    theForm.adDate.focus();
    return (false);
  }

  if (theForm.adDate.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"day\" field.");
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
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"day\" field.");
    theForm.adDate.focus();
    return (false);
  }

  if (theForm.adYear.value == "")
  {
    alert("Please enter a value for the \"year\" field.");
    theForm.adYear.focus();
    return (false);
  }

  if (theForm.adYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"year\" field.");
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
    alert("Please enter only digit characters in the \"year\" field.");
    theForm.adYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2100"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2100\" in the \"year\" field.");
    theForm.adYear.focus();
    return (false);
  }
  
  if (theForm.adMonth2.value != ""){
      if (theForm.adDate2.value == ""){
	    alert("Please enter a value for the \"Date\" field.");
		theForm.adDate2.focus();
		return false;
      }
	  if (theForm.adYear2.value == ""){
	    alert("Please enter a value for the \"Year\" field.");
		theForm.adYear2.focus();
		return false;
	  }
  }	  

  if (theForm.adDate2.value != ""){
      if (theForm.adMonth2.value == ""){
	    alert("Please enter a value for the \"Month\" field.");
		theForm.adMonth2.focus();
		return false;
      }
	  if (theForm.adYear2.value == ""){
	    alert("Please enter a value for the \"Year\" field.");
		theForm.adYear2.focus();
	  }
  }	  
  
  if (theForm.adYear2.value != ""){
      if (theForm.adDate2.value == ""){
	    alert("Please enter a value for the \"Date\" field.");
		theForm.adDate2.focus();
		return false;
      }
	  if (theForm.adMonth2.value == ""){
	    alert("Please enter a value for the \"Month\" field.");
		theForm.adMonth2.focus();
	  }
  }	  
  
  if (theForm.adMonth2.value != "")
  {
	  if (theForm.adMonth2.value.length > 2)
	  {
		alert("Please enter at most 2 characters in the \"month\" field.");
		theForm.adMonth2.focus();
		return (false);
	  }

	  var checkOK = "0123456789-";
	  var checkStr = theForm.adMonth2.value;
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
		theForm.adMonth2.focus();
		return (false);
	  }
	
	  var chkVal = allNum;
	  var prsVal = parseInt(allNum);
	  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
	  {
		alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"month\" field.");
		theForm.adMonth2.focus();
		return (false);
	  }
  }	  

  if (theForm.adDate2.value != "")
  {
	  if (theForm.adDate2.value.length > 2)
	  {
		alert("Please enter at most 2 characters in the \"day\" field.");
		theForm.adDate2.focus();
		return (false);
	  }
	
	  var checkOK = "0123456789-";
	  var checkStr = theForm.adDate2.value;
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
		theForm.adDate2.focus();
		return (false);
	  }
	
	  var chkVal = allNum;
	  var prsVal = parseInt(allNum);
	  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
	  {
		alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"day\" field.");
		theForm.adDate2.focus();
		return (false);
	  }
  }	

  if (theForm.adYear2.value != "")
  {
	  if (theForm.adYear2.value.length > 4)
	  {
		alert("Please enter at most 4 characters in the \"year\" field.");
		theForm.adYear2.focus();
		return (false);
	  }
	
	  var checkOK = "0123456789-";
	  var checkStr = theForm.adYear2.value;
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
		theForm.adYear2.focus();
		return (false);
	  }
	
	  var chkVal = allNum;
	  var prsVal = parseInt(allNum);
	  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2100"))
	  {
		alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2100\" in the \"year\" field.");
		theForm.adYear2.focus();
		return (false);
	  }
  }  
}
</script>


</head>

<body>
<p class="sidebarHeader">Appointment Day/Week View </p>
<form action="ViewApptReportAction.do" method="post" onSubmit="return validateForm(this)" language="JavaScript" name="apptForm" target="_blank">
   <p><strong>Please enter the search criteria for appointment:</strong></p>
   <table width="607" border="0">
     <tr>
       <td width="12">&nbsp;</td>
       <td colspan="2"><strong>Appointment Date Range:</strong></td>
       <td colspan="2"><p>
          <input name="adMonth" type="text" id="adMonth" value="<% 
		if (session.getAttribute("adMonth") != null){
		  out.print(session.getAttribute("adMonth"));
		}  
		%>" size="2" maxlength="2"	   
	   >
         -
         <input name="adDate" type="text" id="adDate" size="2" maxlength="2" value="<% 
		if (session.getAttribute("adDate") != null){
		  out.print(session.getAttribute("adDate"));
		}  
		%>">
         -
         <input name="adYear" type="text" id="adYear" size="4" maxlength="4" value="<% 
		if (session.getAttribute("adYear") != null){
		  out.print(session.getAttribute("adYear"));
		}  
		%>"> 
         (mm-dd-yyyy) to 
		 <input name="adMonth2" type="text" id="adMonth3" size="2" maxlength="2">
-
<input name="adDate2" type="text" id="adDate2" size="2" maxlength="2">
-
<input name="adYear2" type="text" id="adYear2" size="4" maxlength="4">
(mm-dd-yyyy)</p>
         </td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><strong>Clinic:</strong></td>
       <td colspan="2"><select name="apptClinic" id="select">
         <option value="any" selected>Any</option>
         <% 
		if (session.getAttribute("apptClinic") != null){
		  out.print(session.getAttribute("apptClinic"));
		}  
		%>
       </select></td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><strong>Provider:</strong></td>
       <td colspan="2"><select name="apptProvider" id="select2">
         <option value="any" selected>Any</option>
         <% 
		if (session.getAttribute("apptProvider") != null){
		  out.print(session.getAttribute("apptProvider"));
		}  
		%>
       </select></td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><strong>Insurance:</strong></td>
       <td colspan="2"><select name="insuranceOption" size="5" multiple>
         <option value="any">Any</option>
		 <%
		 if (session.getAttribute("insuranceList") != null){
		 	out.print(session.getAttribute("insuranceList"));
		}
		%>
       </select></td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><strong>Display Column: </strong></td>
       <td colspan="2">&nbsp;</td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td width="76"><input name="CheckAll" type="button" id="CheckAll" value="Check All" onClick="checkAll(document.apptForm.list)"></td>
       <td width="91"><input name="UnCheckAll" type="button" id="UnCheckAll" value="Uncheck All" onClick="uncheckAll(document.apptForm.list)"></td>
       <td colspan="2">&nbsp;</td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><input type="checkbox" name="list" value="a.endTime"> 
         End time </td>
       <td width="180"><input type="checkbox" name="list" value="r.ssn">
         SSN</td>
       <td width="226"><input type="checkbox" name="list" value="r.birthDate">
         Birth Date </td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><input type="checkbox" name="list" value="r.isNew">       
       Is new patient </td>
       <td><input type="checkbox" name="list" value="r.phoneNumber">
         Phone # </td>
       <td><input type="checkbox" name="list" value="i.insurance">
         Insurance</td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><input type="checkbox" name="list" value="i.memberId">         
       MemberId / PSP#</td>
       <td><input type="checkbox" name="list" value="i.eligEffDate">
         Elig Eff Date </td>
       <td><input type="checkbox" name="list" value="i.eligTermDate">
         Elig Term Date </td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><input type="checkbox" name="list" value="i.copayParity">
Copay Parity </td>
       <td><input type="checkbox" name="list" value="i.copay">
Copay non-Parity </td>
       <td><input type="checkbox" name="list" value="r.isChild">
Is Child </td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><input type="checkbox" name="list" value="r.legalGardian">
Legal Gardian </td>
       <td><input type="checkbox" name="list" value="r.legalGardianPhoneNumber">
LegalGardian Phone#</td>
       <td><input type="checkbox" name="list" value="a.isUrgent">
Is Urgent </td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><input type="checkbox" name="list" value="a.needTranslationSvc">
Need translation service </td>
       <td><input type="checkbox" name="list" value="a.collateralReceived">
Collateral received </td>
       <td><input type="checkbox" name="list" value="a.notes">
Appointment notes </td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="2"><input name="list" type="checkbox" value="rs.status">
Current Status</td>
       <td><input name="list" type="checkbox" value="i.medicalId">
Medi-Cal CIN#</td>
       <td><input name="list" type="checkbox" value="i.medicalIssueDate">
Medi-Cal Issue Date</td>
     </tr>
  </table>
   <table width="607" border="0">
     <tr>
       <td height="51"><input type="submit" name="Submit" value="Submit">
       <input name="Reset" type="reset" id="Reset2" value="Reset"></td>
     </tr>
   </table>
   <p>&nbsp;</p>
</form>
<p>&nbsp;</p>

<SCRIPT TYPE="text/javascript">
autojump('adMonth', 'adDate', 2);
autojump('adDate', 'adYear', 2);
autojump('adYear', 'adMonth2', 4);
autojump('adMonth2', 'adDate2', 2);
autojump('adDate2', 'adYear2', 2);
</script>

</body>
</html>
