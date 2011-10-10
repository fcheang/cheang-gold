<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../style.css" rel="stylesheet" type="text/css">
</head>

<body>
<form name="form1" method="post" action="EnterNewLogAction.do">
  <table width="568" border="0">
    <tr>
      <td width="10"><p>&nbsp;</p>      </td>
      <td width="225" class="subtitle"><strong class="subtitle">Enter patient tracking : </strong></td>
      <td width="319">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>Client's name: </strong></td>
      <td><input name="clientName" type="text" id="clientName" size="30" maxlength="30"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>Date of client's initial call : </strong></td>
      <td><input name="initialCallDate" type="text" id="initialCallDate" size="30" maxlength="30"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>Date confirmation letter was mailed: </strong></td>
      <td><input name="letterMailDate" type="text" id="letterMailDate" size="30" maxlength="30"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>1st reminder call date: </strong></td>
      <td><input name="firstCallDate" type="text" id="firstCallDate" size="30" maxlength="30"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>2nd reminder call date: </strong></td>
      <td><input name="secCallDate" type="text" id="secCallDate" size="30" maxlength="30"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>Made contact with client:</strong></td>
      <td><input name="contactClient" type="radio" value="yes">
        Yes 
        <input name="contactClient" type="radio" value="no">
        No</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong># of attempts: </strong></td>
      <td><input name="numOfAttempt" type="text" id="numOfAttempt" size="5"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>Misc. notes: </strong></td>
      <td><input name="notes" type="text" id="notes" size="50" maxlength="50"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><strong>
        <input type="submit" name="Submit" value="Submit">
        <input type="reset" name="Submit2" value="Reset">
      </strong></td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form>
</body>
</html>
