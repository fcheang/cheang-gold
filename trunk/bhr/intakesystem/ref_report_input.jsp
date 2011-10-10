<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Referral Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style3 {font-size: 14px}
.style5 {font-size: 14px; font-weight: bold; }
-->
</style>
</head>

<body>
<p align="left" class="sidebarHeader" style="margin-top: 0; margin-bottom: 0">Generate Referral Report: </p>
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
<form name="form1" method="post" action="GenRefCountReportAction.do">
  <table width="100%"  border="0">
    <tr>
      <td width="2%"><p>&nbsp;</p>      </td>
      <td width="98%"><span class="style5">Total # of child, adult, no-show referrals breakdown by month:</span></td>
    </tr>
    <tr>
      <td><p>&nbsp;</p>
      <p>&nbsp;</p></td>
      <td><p><span class="style3">for which month(s):    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;from </span>            <select name="fromMonth" size="1" id="fromMonth">
	        <% out.print(session.getAttribute("fromMonthList")); %>
            </select>
            <select name="fromYear" size="1" id="fromYear">
			<% out.print(session.getAttribute("fromYearList")); %>
            </select> 
&nbsp;&nbsp;            <span class="style3">to</span>          
            <select name="toMonth" size="1" id="toMonth">
			<% out.print(session.getAttribute("toMonthList")); %>
            </select>
            <select name="toYear" size="1" id="toYear">
			<% out.print(session.getAttribute("toYearList")); %>
          </select>
      </p>      </td>
    </tr>
    <tr>
      <td><p>&nbsp;</p>
      <p>&nbsp;</p></td>
      <td><span class="style3">for which insurance provider:
      </span>        <select name="insProvider" size="1" id="insProvider">
		<% out.print(session.getAttribute("insProviderList")); %>
        </select></td>
    </tr>
    <tr>
      <td><p>&nbsp;</p>
      <p>&nbsp;</p></td>
      <td><span class="style3">report title (optional):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>      <input name="reportTitle" type="text" id="reportTitle" value="Total # of child, adult, no-show referrals breakdown by month" size="80"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="Submit" value="Generate Report"></td>
    </tr>
  </table>
</form>
</body>
</html>
