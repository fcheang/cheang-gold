<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Clinic Registration</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<p align="left" style="margin-top: 0; margin-bottom: 0"><b>Clinic Registration
</b></p>
<p align="left" style="margin-top: 0; margin-bottom: 0">Please fill out the 
registration information below.</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">The fields marked with 
an (<font color="#FF0000">*</font>) must be filled in.</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
<p align="left" style="margin-top: 0; margin-bottom: 0"><font color="#FFFFFF">
<b><span style="background-color: #800000">Clinic information&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></b></font></p>
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
<!--webbot BOT="GeneratedScript" PREVIEW=" " startspan --><script Language="JavaScript" Type="text/javascript"><!--
function FrontPage_Form1_Validator(theForm)
{

  if (theForm.name.value == "")
  {
    alert("Please enter a value for the \"name\" field.");
    theForm.name.focus();
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
//--></script>
<form method="POST" action="NewClinicAction.do" onsubmit="return FrontPage_Form1_Validator(this)" language="JavaScript" name="FrontPage_Form1">
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0">
  <b>Name:<font color="#FF0000">*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
  <input name="name" type="text" tabindex="1" size="30"></font></b></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>
  Street address:&nbsp; 
  <input type="text" name="streetaddress" size="30" tabindex="2"></b></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>
  City:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
   <input name="city" type="text" tabindex="3" size="20" maxlength="20">
  </b></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>
  State:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
  <input name="state" type="text" id="state" tabindex="4" value="CA" size="2" maxlength="2">
  </b></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>
  Zip code:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="text" name="zipcode" size="10" tabindex="5"></b></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>
  Phone number 1:&nbsp;&nbsp; </b>( 
  <input type="text" name="pn1_first" size="3" tabindex="6" maxlength="3"> )
  <input type="text" name="pn1_second" size="3" tabindex="7" maxlength="3"> -
  <input type="text" name="pn1_third" size="4" tabindex="8" maxlength="4"></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>
  Phone number 2:&nbsp;&nbsp; </b>( 
  <input type="text" name="pn1_first" size="3" tabindex="9" maxlength="3"> )
  <input type="text" name="pn2_second" size="3" tabindex="10" maxlength="3"> -
  <input type="text" name="pn2_third" size="4" tabindex="11" maxlength="4"></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>
  Fax number:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </b>( 
  <input type="text" name="fax_first" size="3" tabindex="12" maxlength="3"> )
  <input type="text" name="fax_second" size="3" tabindex="13" maxlength="3"> -
  <input type="text" name="fax_third" size="4" tabindex="14" maxlength="4"></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>
  Notes:</b></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0"><b>&nbsp;<!--webbot bot="Validation" i-maximum-length="100" --><textarea rows="4" name="notes" cols="30" tabindex="15"></textarea></b></p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0">&nbsp;</p>
  <p align="left" style="line-height: 200%; margin-top: 0; margin-bottom: 0">
  <input type="submit" value="Submit" name="submit" tabindex="16"><input type="reset" value="Reset" name="reset" tabindex="17"></p>
</form>

</body>

</html>