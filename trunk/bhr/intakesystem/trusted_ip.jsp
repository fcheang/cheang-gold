<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<p class="sidebarHeader"><strong><font color="#FFFFFF"> Trusted IP: </font></strong></p>
<p>
  <% 
   if (session.getAttribute("message") != null){
     out.print(session.getAttribute("message"));
   }
%>
</p>
<form name="form1" method="post" action="AddTrustedIPAction.do">
  <p>Add trusted IP:</p>
  <p>New IP:
      <input name="ip" type="text" id="ip" size="30" maxlength="30">
  </p>
  <p>
    <input name="add" type="submit" id="add" value="Add">
    <input name="reset" type="reset" id="reset" value="Reset">
  </p>
</form>
<p>&nbsp;</p>
</body>
</html>
