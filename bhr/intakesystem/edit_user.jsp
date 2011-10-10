<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #D4D0C8}
.style2 {color: #FF0000}
-->
</style>
</head>

<body>
<p align="left" class="sidebarHeader" style="margin-top: 0; margin-bottom: 0">Update  User: </p>
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
<script Language="JavaScript" Type="text/javascript">
function FrontPage_Form1_Validator(theForm)
{

  if (theForm.userid.value == "")
  {
    alert("Please enter a value for the \"User ID\" field.");
    theForm.userid.focus();
    return (false);
  }

  if (theForm.password.value == "")
  {
    alert("Please enter a value for the \"Password\" field.");
    theForm.password.focus();
    return (false);
  }
  return (true);
}
</script>
<form method="POST" action="UpdateUserAction.do" onsubmit="return FrontPage_Form1_Validator(this)" language="JavaScript" name="FrontPage_Form1">
  <table width="636" border="0">
    <tr>
      <th width="132" scope="row"><b>User ID:<font color="#FF0000">*</font></b></th>
      <td width="494">
	  <input type="hidden" name="userId" value="<% out.print(session.getAttribute("edit_user_userId")); %>"/>
	  <b><%
	  out.print(session.getAttribute("edit_user_userId"));
	  %></b>
	  </td>
    </tr>
    <tr>
      <th scope="row"><b>Password:<font color="#FF0000">*</font></b></th>
      <td><b><font color="#FF0000">
        <input name="password" type="text" tabindex="2" size="20" value="<% 
		if (session.getAttribute("edit_user_password") != null){
		  out.print(session.getAttribute("edit_user_password"));
		}  
		%>">
      </font></b></td>
    </tr>
    <tr>
      <th scope="row"><b>First name:&nbsp;&nbsp;</b></th>
      <td><b>
        <input type="text" name="firstname" size="30" tabindex="3" value="<%
		if (session.getAttribute("edit_user_firstName") != null){
		  out.print(session.getAttribute("edit_user_firstName"));
		}
		%>">
      </b></td>
    </tr>
    <tr>
      <th scope="row"><b>Last name:&nbsp;&nbsp;</b></th>
      <td><b>
        <input type="text" name="lastname" size="30" tabindex="4" value="<%
		if (session.getAttribute("edit_user_lastName") != null){
		  out.print(session.getAttribute("edit_user_lastName"));
		}  
		%>">
      </b></td>
    </tr>
    <tr>
      <th scope="row"><b>Description:&nbsp;</b></th>
      <td><b>
        <input type="text" name="description" size="50" tabindex="5" value="<%
		if (session.getAttribute("edit_user_description") != null){
		  out.print(session.getAttribute("edit_user_description"));
		}
		%>">
      </b></td>
    </tr>
    <tr>
      <th scope="row">Role:<span class="style2">*</span><span class="style1">*</span></th>
      <td>
		<%
		if (session.getAttribute("edit_user_roleList") != null){
		  out.print(session.getAttribute("edit_user_roleList"));
		}
		%>
      </td>
    </tr>
    <tr>
      <th scope="row">&nbsp;</th>
      <td><input type="submit" value="Update" name="submit" tabindex="6">      </td>
    </tr>
  </table>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0">&nbsp;</p>
</form>

</body>
</html>
