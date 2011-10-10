<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>View Provider Detail</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">

<script language="javascript">
function confirmDelete()
{
	if (confirm("Delete Provider? Please click the OK button to confirm.")){
		return true;
    } else {
		return false;
	}
}

function submitFunction(i) {
	if (i==1) {
		document.theForm.action="SaveProviderAction.do";
		document.theForm.submit()		
	}
	if (i==2) {
		document.theForm.action="DeleteProviderAction.do";
		if (confirmDelete()){
			document.theForm.submit();
		}
	}
	if (i==3) {
		document.theForm.action="ViewAllProviderAction.do";
		document.theForm.submit()		
	}
}
</script>

</head>

<body>

<jsp:useBean id="provider" class="com.suntek.common.persistence.Provider" scope="session" />

<p align="left" class="sidebarHeader">Provider Detail </p>
<form name="theForm" method="post" action="">
  <table width="530" border="0">
    <tr>
      <td width="10">&nbsp;</td>
      <td width="85"><b>Id:</b></td>
      <td width="421"><jsp:getProperty name="provider" property="providerId"/>
	  <input type="hidden" name="providerId" value="<jsp:getProperty name="provider" property="providerId"/>" />
	  </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><b>Name:</b></td>
      <td><input name="providerName" type="text" value="<jsp:getProperty name="provider" property="name"/>" size="30" maxlength="60"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><b>Title:</b></td>
      <td><input name="providerTitle" type="text" value="<jsp:getProperty name="provider" property="title"/>" size="30" maxlength="30"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><b>Credentials:</b></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td><jsp:getProperty name="provider" property="credentialsAsHTML"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>  
  <table width="529" border="0">
    <tr>
      <td width="10">&nbsp;</td>
      <td width="509">
	  <input type="button" name="Save" value="Save" onClick="submitFunction(1)">
      <input type="button" name="Delete" value="Delete" onClick="submitFunction(2)">
      <input type="button" name="Cancel" value="Cancel" onClick="submitFunction(3)">
	  
	  </td>
    </tr>
  </table>
</form>
</body>
</html>
