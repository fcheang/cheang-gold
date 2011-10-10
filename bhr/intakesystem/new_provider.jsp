<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
</head>

<body>
<p align="left" class="sidebarHeader" style="margin-top: 0; margin-bottom: 0"><b>Create Provider</b></p>
<p align="left" style="margin-top: 0">Fields marked with an (<font color="#FF0000">*</font>) is required.</p>

<script Language="JavaScript" Type="text/javascript">
function validateForm(theForm)
{
  if (theForm.providerName.value == "") {
	alert("Please enter a value for the \"Name\" field.");
    theForm.providerName.focus();
    return false;
  } else {
	return true;
  }
}
</script>

<form method="POST" action="NewProviderAction.do" onSubmit="return validateForm(this)" language="JavaScript">
<br>
  <table width="728" border="0">
    <tr>
      <td width="80"><strong>Name:</strong> <span class="style1">*</span> </td>
      <td width="638">
	  <input name="providerName" type="text" size="30" maxlength="60"></td>
    </tr>
    <tr>
      <td><strong>Title: </strong></td>
      <td><input name="providerTitle" type="text" size="30" maxlength="30"> 
      (Example: MD, MFT, MHS) </td>
    </tr>
    <tr>
      <td><b>Credentials:</b></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><% out.print(session.getAttribute("allCredentialsCheckBox")); %></td>
    </tr>
  </table>
  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0">
    <input type="submit" value="Submit" name="sbumit" tabindex="16">
    <input type="reset" value="Reset" name="reset" tabindex="17">
  </p>
</form>
<p>&nbsp;</p>

</body>
</html>
