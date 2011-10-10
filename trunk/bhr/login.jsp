<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

<title>main login for bhr</title>
<script language="Javascript" type="text/javascript">
  if (top.location != self.location) {
    top.location.replace(self.location)
  }
</script>
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {
	color: #740104;
	font-weight: bold;
	font-size: 14px;
}
-->
</style>
</head>

<body>

<div align="center">
  <center>
  <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="901" id="AutoNumber1" bgcolor="#740104" height="81">
    <tr>
      <td width="545">
      <p style="margin-top: 0; margin-bottom: 0"></td>
      <td width="497" align="center" height="81">

<p style="margin-top: 0; margin-bottom: 0">&nbsp;</p>

<p style="margin-top: 0; margin-bottom: 0">
<img border="0" src="images/bhr_header.jpg" width="900" height="84"></p>

      <p style="margin-top: 0; margin-bottom: 0"></td>
      <td width="499" align="center" height="81">

<p style="margin-top: 0; margin-bottom: 0">&nbsp;</p>

<p style="margin-top: 0; margin-bottom: 0">&nbsp;</p>

<p style="margin-top: 0; margin-bottom: 0">&nbsp;</p>

      </td>
    </tr>
  </table>
  </center>
</div>
<script Language="JavaScript" Type="text/javascript">
function FrontPage_Form1_Validator(theForm)
{

  if (theForm.userid.value == "")
  {
    alert("Please enter a value for the \"UserId\" field.");
    theForm.userid.focus();
    return (false);
  }

  if (theForm.userid.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"UserId\" field.");
    theForm.userid.focus();
    return (false);
  }

  if (theForm.userid.value.length > 20)
  {
    alert("Please enter at most 20 characters in the \"UserId\" field.");
    theForm.userid.focus();
    return (false);
  }

  if (theForm.password.value == "")
  {
    alert("Please enter a value for the \"Password\" field.");
    theForm.password.focus();
    return (false);
  }

  if (theForm.password.value.length > 20)
  {
    alert("Please enter at most 20 characters in the \"Password\" field.");
    theForm.password.focus();
    return (false);
  }
  return (true);
}
</script>

<form name="FrontPage_Form1" method="POST" action="LoginAction.do" onsubmit="return FrontPage_Form1_Validator(this)" language="JavaScript">
  <p>&nbsp;</p>
  <table width="200" border="0" align="center" cellpadding="0" cellspacing="5">
    <tr>
      <td colspan="2"><div align="center" class="style1">IntakeSystem (v 1.15) </div></td>
    </tr>
    <tr>
      <td><strong>User ID:</strong></td>
      <td><input name="userid" type="text" id="userid2" size="20" maxlength="20"></td>
    </tr>
    <tr>
      <td><strong>Password:</strong></td>
      <td><input name="password" type="password" id="password" size="20" maxlength="20"></td>
    </tr>
  </table>
  <table width="72" border="0" align="center">
    <tr>
      <td width="66"><input name="Login" type="submit" id="Login3" value="Login"></td>
    </tr>
  </table>
  <p align="center">
    <% if (session.getAttribute("loginMessage") != null){
     out.print(session.getAttribute("loginMessage"));
   }
%>
</p>
  <p align="center">  <b> </b></p>
</form>
<p align="center">&nbsp;</p>
<h3 align="center">&nbsp;</h3>

<p>&nbsp;</p>
<p>&nbsp;</p>

</body>

</html>