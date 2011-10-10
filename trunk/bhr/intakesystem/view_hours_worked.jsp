<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../style.css" rel="stylesheet" type="text/css">

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
  if (theForm.workMonth.value == "")
  {
    alert("Please enter a value for the \"month\" field.");
    theForm.workMonth.focus();
    return (false);
  }

  if (theForm.workMonth.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"month\" field.");
    theForm.workMonth.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.workMonth.value;
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
    theForm.workMonth.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"month\" field.");
    theForm.workMonth.focus();
    return (false);
  }

  if (theForm.workDay.value == "")
  {
    alert("Please enter a value for the \"day\" field.");
    theForm.workDay.focus();
    return (false);
  }

  if (theForm.workDay.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"day\" field.");
    theForm.workDay.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.workDay.value;
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
    theForm.workDay.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"day\" field.");
    theForm.workDay.focus();
    return (false);
  }

  if (theForm.workYear.value == "")
  {
    alert("Please enter a value for the \"year\" field.");
    theForm.workYear.focus();
    return (false);
  }

  if (theForm.workYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"year\" field.");
    theForm.workYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.workYear.value;
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
    theForm.workYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2100"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2100\" in the \"year\" field.");
    theForm.workYear.focus();
    return (false);
  }
}
</script>

</head>

<body>
 <p class="subtitle">View Hours worked by Staff:</p>
 <form method="post" action="ViewHoursWorkedAction.do" onSubmit="return validateForm(this)" language="JavaScript">
   <table width="568" border="0">
     <tr>
       <td width="6" height="45">&nbsp;</td>
       <td width="122"><strong>Date: </strong></td>
       <td width="426"><input name="workMonth" type="text" id="workMonth" value="<% out.print(session.getAttribute("workMonth")); %>" size="2" maxlength="2" >
        /
          <input name="workDay" type="text" id="workDay" value="<% out.print(session.getAttribute("workDay")); %>" size="2" maxlength="2" >
        /
        <input name="workYear" type="text" id="workYear" value="<% out.print(session.getAttribute("workYear")); %>" size="4" maxlength="4" >
        <span class="style6">(MM/ DD/ YYYY)</span></td>
     </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>
        <input type="submit" name="Submit" value="Submit">
      </strong></td>
      <td>&nbsp;</td>
    </tr>
   </table>
</form>
<SCRIPT TYPE="text/javascript">
autojump('workMonth', 'workDay', 2);
autojump('workDay', 'workYear', 2);
</script>

 <p>
   <% if (session.getAttribute("hoursWorkedTable") != null){
     out.print(session.getAttribute("hoursWorkedTable"));
   }
 %>
 </p>
</body>
</html>
