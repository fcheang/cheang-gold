<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Insurance Provider Registration</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<p align="left" class="sidebarHeader" style="margin-top: 0; margin-bottom: 0"><b>Enter New Insurance Provider</b></p>
<p align="left" style="margin-top: 0">Fields marked with 
an (<font color="#FF0000">*</font>) is required.</p>
<!--webbot BOT="GeneratedScript" PREVIEW=" " startspan --><script Language="JavaScript" Type="text/javascript"><!--
function FrontPage_Form1_Validator(theForm)
{

  if (theForm.name.value == "")
  {
    alert("Please enter a value for the \"Provider name\" field.");
    theForm.name.focus();
    return (false);
  }

  if (theForm.name.value.length > 30)
  {
    alert("Please enter at most 30 characters in the \"Provider name\" field.");
    theForm.name.focus();
    return (false);
  }

  if (theForm.pn1_first.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"Phone number\" field.");
    theForm.pn1_first.focus();
    return (false);
  }

  var checkOK = "0123456789-,";
  var checkStr = theForm.pn1_first.value;
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
    if (ch != ",")
      allNum += ch;
  }
  if (!allValid)
  {
    alert("Please enter only digit characters in the \"Phone number\" field.");
    theForm.pn1_first.focus();
    return (false);
  }

  if (theForm.pn1_second.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"Phone number\" field.");
    theForm.pn1_second.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.pn1_second.value;
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
    alert("Please enter only digit characters in the \"Phone number\" field.");
    theForm.pn1_second.focus();
    return (false);
  }

  if (theForm.pn1_third.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"Phone number\" field.");
    theForm.pn1_third.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.pn1_third.value;
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
    alert("Please enter only digit characters in the \"Phone number\" field.");
    theForm.pn1_third.focus();
    return (false);
  }

  if (theForm.pn2_first.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"Phone number\" field.");
    theForm.pn2_first.focus();
    return (false);
  }

  var checkOK = "0123456789-,";
  var checkStr = theForm.pn2_first.value;
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
    if (ch != ",")
      allNum += ch;
  }
  if (!allValid)
  {
    alert("Please enter only digit characters in the \"Phone number\" field.");
    theForm.pn2_first.focus();
    return (false);
  }

  if (theForm.pn2_second.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"Phone number\" field.");
    theForm.pn2_second.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.pn2_second.value;
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
    alert("Please enter only digit characters in the \"Phone number\" field.");
    theForm.pn2_second.focus();
    return (false);
  }

  if (theForm.pn2_third.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"Phone number\" field.");
    theForm.pn2_third.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.pn2_third.value;
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
    alert("Please enter only digit characters in the \"Phone number\" field.");
    theForm.pn2_third.focus();
    return (false);
  }

  if (theForm.fax_first.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"Fax number\" field.");
    theForm.fax_first.focus();
    return (false);
  }

  var checkOK = "0123456789-,";
  var checkStr = theForm.fax_first.value;
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
    if (ch != ",")
      allNum += ch;
  }
  if (!allValid)
  {
    alert("Please enter only digit characters in the \"Fax number\" field.");
    theForm.fax_first.focus();
    return (false);
  }

  if (theForm.fax_second.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"Fax number\" field.");
    theForm.fax_second.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.fax_second.value;
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
    alert("Please enter only digit characters in the \"Fax number\" field.");
    theForm.fax_second.focus();
    return (false);
  }

  if (theForm.fax_third.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"Fax number\" field.");
    theForm.fax_third.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.fax_third.value;
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
    alert("Please enter only digit characters in the \"Fax number\" field.");
    theForm.fax_third.focus();
    return (false);
  }

  if (theForm.notes.value.length > 100)
  {
    alert("Please enter at most 100 characters in the \"notes\" field.");
    theForm.notes.focus();
    return (false);
  }
  return (true);
}
//--></script><!--webbot BOT="GeneratedScript" endspan --><form method="POST" action="NewInsuranceProviderAction.do" onsubmit="return FrontPage_Form1_Validator(this)" language="JavaScript" name="FrontPage_Form1">

  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0">
  <b>Provider name:<font color="#FF0000">*&nbsp; &nbsp;&nbsp;</font></b>
  <!--webbot bot="Validation" s-display-name="Provider name" b-value-required="TRUE" i-maximum-length="30" -->
  <input name="name" type="text" tabindex="1" size="30" maxlength="30"></p>
  
  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>Street address:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
  <input type="text" name="streetaddress" size="30" tabindex="2" maxlength="30"></p>
  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>City:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
  <input type="text" name="city" size="20" tabindex="3" maxlength="20">
  </p>

  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>State:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
  <select size="1" name="state" tabindex="4">
  <option selected>CA</option>
  <option>DC</option>
  <option>CT</option>
  <option>DE</option>
  <option>HI</option>
  <option>IL</option>
  <option>MI</option>
  <option>MN</option>
  <option>PA</option>
  <option>NH</option>
  <option>NY</option>
  <option>OR</option>
  <option>ME</option>
  <option>MA</option>
  <option>WI</option>
  <option>WA</option>
  <option>RI</option>
  <option>NJ</option>
  <option>MD</option>
  <option>VA</option>
  <option>FL</option>
  <option>SC</option>
  <option>AK</option>
  <option>NC</option>
  <option>VT</option>
  <option>AZ</option>
  <option>WV</option>
  <option>IN</option>
  <option>AR</option>
  <option>KY</option>
  <option>AL</option>
  <option>CO</option>
  <option>OH</option>
  <option>TX</option>
  <option>NM</option>
  <option>UT</option>
  <option>ID</option>
  <option>NV</option>
  <option>MT</option>
  <option>TN</option>
  <option>GA</option>
  <option>MS</option>
  <option>LA</option>
  <option>MO</option>
  <option>IA</option>
  <option>ND</option>
  <option>SD</option>
  <option>NE</option>
  <option>KS</option>
  </select></p>

  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>Zip code:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
  <input type="text" name="zipcode" size="10" tabindex="5" maxlength="10"></p>

  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>Phone number 
  1:&nbsp;&nbsp; </b>(
  <!--webbot bot="Validation" s-display-name="Phone number" s-data-type="Integer" s-number-separators="," i-maximum-length="3" --><input type="text" name="pn1_first" size="3" tabindex="6" maxlength="3"> )
  <!--webbot bot="Validation" s-display-name="Phone number" s-data-type="Integer" s-number-separators="x" i-maximum-length="3" --><input type="text" name="pn1_second" size="3" tabindex="7" maxlength="3"> -
  <!--webbot bot="Validation" s-display-name="Phone number" s-data-type="Integer" s-number-separators="x" i-maximum-length="4" --><input type="text" name="pn1_third" size="4" tabindex="8" maxlength="4"></p>
  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>Phone number 
  2:&nbsp;&nbsp; </b>(
  <!--webbot bot="Validation" s-display-name="Phone number" s-data-type="Integer" s-number-separators="," i-maximum-length="3" --><input type="text" name="pn2_first" size="3" tabindex="9" maxlength="3"> )
  <!--webbot bot="Validation" s-display-name="Phone number" s-data-type="Integer" s-number-separators="x" i-maximum-length="3" --><input type="text" name="pn2_second" size="3" tabindex="10" maxlength="3"> -
  <!--webbot bot="Validation" s-display-name="Phone number" s-data-type="Integer" s-number-separators="x" i-maximum-length="4" --><input type="text" name="pn2_third" size="4" tabindex="11" maxlength="4"></p>
  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>Fax number:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </b>(
  <!--webbot bot="Validation" s-display-name="Fax number" s-data-type="Integer" s-number-separators="," i-maximum-length="3" --><input type="text" name="fax_first" size="3" tabindex="12" maxlength="3"> )
  <!--webbot bot="Validation" s-display-name="Fax number" s-data-type="Integer" s-number-separators="x" i-maximum-length="3" --><input type="text" name="fax_second" size="3" tabindex="13" maxlength="3"> -
  <!--webbot bot="Validation" s-display-name="Fax number" s-data-type="Integer" s-number-separators="x" i-maximum-length="4" --><input type="text" name="fax_third" size="4" tabindex="14" maxlength="4"></p>
  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>Notes:</b></p>
  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0">
  <!--webbot bot="Validation" i-maximum-length="100" --><textarea rows="3" name="notes" cols="40" tabindex="15"></textarea></p>
  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0">&nbsp;</p>
  <p style="line-height: 200%; margin-top: 0; margin-bottom: 0">
  <input type="submit" value="Submit" name="sbumit" tabindex="16"><input type="reset" value="Reset" name="reset" tabindex="17"></p>
</form>
<p>&nbsp;</p>

</body>

</html>