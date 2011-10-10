// JavaScript Document
function validateForm(theForm)
{
  if (theForm.rdMonth.value == "")
  {
    alert("Please enter a value for the \"month\" field.");
    theForm.rdMonth.focus();
    return (false);
  }

  if (theForm.rdMonth.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"month\" field.");
    theForm.rdMonth.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.rdMonth.value;
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
    theForm.rdMonth.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"month\" field.");
    theForm.rdMonth.focus();
    return (false);
  }

  if (theForm.rdDate.value == "")
  {
    alert("Please enter a value for the \"day\" field.");
    theForm.rdDate.focus();
    return (false);
  }

  if (theForm.rdDate.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"day\" field.");
    theForm.rdDate.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.rdDate.value;
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
    theForm.rdDate.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"day\" field.");
    theForm.rdDate.focus();
    return (false);
  }

  if (theForm.rdYear.value == "")
  {
    alert("Please enter a value for the \"year\" field.");
    theForm.rdYear.focus();
    return (false);
  }

  if (theForm.rdYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"year\" field.");
    theForm.rdYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.rdYear.value;
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
    theForm.rdYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2100"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2100\" in the \"year\" field.");
    theForm.rdYear.focus();
    return (false);
  }

  if (theForm.esdMonth.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"month\" field.");
    theForm.esdMonth.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.esdMonth.value;
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
    theForm.esdMonth.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"month\" field.");
    theForm.esdMonth.focus();
    return (false);
  }

  if (theForm.esdDay.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"day\" field.");
    theForm.esdDay.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.esdDay.value;
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
    theForm.esdDay.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"day\" field.");
    theForm.esdDay.focus();
    return (false);
  }

  if (theForm.esdYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"year\" field.");
    theForm.esdYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.esdYear.value;
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
    theForm.esdYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2100"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2100\" in the \"year\" field.");
    theForm.esdYear.focus();
    return (false);
  }

  if (theForm.eedMonth.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"month\" field.");
    theForm.eedMonth.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.eedMonth.value;
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
    theForm.eedMonth.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"month\" field.");
    theForm.eedMonth.focus();
    return (false);
  }

  if (theForm.eedDay.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"day\" field.");
    theForm.eedDay.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.eedDay.value;
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
    theForm.eedDay.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"day\" field.");
    theForm.eedDay.focus();
    return (false);
  }

  if (theForm.eedYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"year\" field.");
    theForm.eedYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.eedYear.value;
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
    theForm.eedYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2100"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2100\" in the \"year\" field.");
    theForm.eedYear.focus();
    return (false);
  }






  if (theForm.firstName.value == "")
  {
    alert("Please enter a value for the \"First name\" field.");
    theForm.firstName.focus();
    return (false);
  }

  if (theForm.firstName.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"First name\" field.");
    theForm.firstName.focus();
    return (false);
  }

  if (theForm.firstName.value.length > 30)
  {
    alert("Please enter at most 30 characters in the \"First name\" field.");
    theForm.firstName.focus();
    return (false);
  }

  if (theForm.lastName.value == "")
  {
    alert("Please enter a value for the \"Last name\" field.");
    theForm.lastName.focus();
    return (false);
  }

  if (theForm.lastName.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"Last name\" field.");
    theForm.lastName.focus();
    return (false);
  }

  if (theForm.lastName.value.length > 30)
  {
    alert("Please enter at most 30 characters in the \"Last name\" field.");
    theForm.lastName.focus();
    return (false);
  }


  if (theForm.ssnFirst.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"ssnFirst\" field.");
    theForm.ssnFirst.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.ssnFirst.value;
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
    alert("Please enter only digit characters in the \"SSN\" field.");
    theForm.ssnFirst.focus();
    return (false);
  }

  if (theForm.ssnSecond.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"SSN\" field.");
    theForm.ssnSecond.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.ssnSecond.value;
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
    alert("Please enter only digit characters in the \"SSN\" field.");
    theForm.ssnSecond.focus();
    return (false);
  }

  if (theForm.ssnThird.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"SSN\" field.");
    theForm.ssnThird.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.ssnThird.value;
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
    alert("Please enter only digit characters in the \"SSN\" field.");
    theForm.ssnThird.focus();
    return (false);
  }

  var ssnLength = theForm.ssnFirst.value.length + theForm.ssnSecond.value.length + theForm.ssnThird.value.length;
  if (ssnLength < 9)
  {
      alert("Please enter a value for Social Security Number field.");
      theForm.ssnFirst.focus();
      return(false);
  }  

  if (theForm.dobMonth.value == "")
  {
    alert("Please enter a value for the \"month\" field.");
    theForm.dobMonth.focus();
    return (false);
  }

  if (theForm.dobMonth.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"month\" field.");
    theForm.dobMonth.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.dobMonth.value;
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
    theForm.dobMonth.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"month\" field.");
    theForm.dobMonth.focus();
    return (false);
  }

  if (theForm.dobDay.value == "")
  {
    alert("Please enter a value for the \"day\" field.");
    theForm.dobDay.focus();
    return (false);
  }

  if (theForm.dobDay.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"day\" field.");
    theForm.dobDay.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.dobDay.value;
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
    alert("Please enter only digit characters in the \"day\" field.");
    theForm.dobDay.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"day\" field.");
    theForm.dobDay.focus();
    return (false);
  }

  if (theForm.dobYear.value == "")
  {
    alert("Please enter a value for the \"year\" field.");
    theForm.dobYear.focus();
    return (false);
  }

  if (theForm.dobYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"year\" field.");
    theForm.dobYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.dobYear.value;
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
    theForm.dobYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2100"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2100\" in the \"year\" field.");
    theForm.dobYear.focus();
    return (false);
  }

  if (theForm.city.value == "")
  {
    alert("Please enter a value for the \"City\" field.");
    theForm.city.focus();
    return (false);
  }

  if (theForm.city.value.length > 20)
  {
    alert("Please enter at most 20 characters in the \"City\" field.");
    theForm.city.focus();
    return (false);
  }

  if (theForm.dpnFirst.value == "")
  {
    alert("Please enter a value for the \"Phone number\" field.");
    theForm.dpnFirst.focus();
    return (false);
  }

  if (theForm.dpnFirst.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"Phone number\" field.");
    theForm.dpnFirst.focus();
    return (false);
  }

  if (theForm.dpnFirst.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"Phone number\" field.");
    theForm.dpnFirst.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.dpnFirst.value;
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
    theForm.dpnFirst.focus();
    return (false);
  }

  if (theForm.dpnSecond.value == "")
  {
    alert("Please enter a value for the \"Phone number\" field.");
    theForm.dpnSecond.focus();
    return (false);
  }

  if (theForm.dpnSecond.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"Phone number\" field.");
    theForm.dpnSecond.focus();
    return (false);
  }

  if (theForm.dpnSecond.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"Phone number\" field.");
    theForm.dpnSecond.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.dpnSecond.value;
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
    theForm.dpnSecond.focus();
    return (false);
  }

  if (theForm.dpnThird.value == "")
  {
    alert("Please enter a value for the \"Phone number\" field.");
    theForm.dpnThird.focus();
    return (false);
  }

  if (theForm.dpnThird.value.length < 1)
  {
    alert("Please enter at least 1 characters in the \"Phone number\" field.");
    theForm.dpnThird.focus();
    return (false);
  }

  if (theForm.dpnThird.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"Phone number\" field.");
    theForm.dpnThird.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.dpnThird.value;
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
    theForm.dpnThird.focus();
    return (false);
  }

  var today = new Date();
  var today_year = today.getFullYear();
  var today_month = today.getMonth();
  var today_date = today.getDate();
  var isChild = false;
  var yearDiff = today_year - theForm.dobYear.value;
  var monthDiff = today_month + 1 - theForm.dobMonth.value;
  var dateDiff = today_date - theForm.dobDay.value;
  if (yearDiff < 18){
    isChild = true;
  }else if (yearDiff == 18){
    if (monthDiff < 0){
      isChild = true;
    }else if (monthDiff == 0){
      if (dateDiff < 0){
        isChild = true;
      }
    }
  }
  
  if (isChild){
    if (theForm.lgFirstName.value == ""){
      alert("Legal gardian is required for patient less than 18 years old");
      theForm.lgFirstName.focus();
      return(false);
    }     
    if (theForm.lgLastName.value == ""){
      alert("Legal gardian is required for patient less than 18 years old");
      theForm.lgLastName.focus();
      return(false);
    }
  }    

  if (theForm.lgpnFirst.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"contact phone number\" field.");
    theForm.lgpnFirst.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.lgpnFirst.value;
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
    alert("Please enter only digit characters in the \"contact phone number\" field.");
    theForm.lgpnFirst.focus();
    return (false);
  }

  if (theForm.lgpnSecond.value.length > 3)
  {
    alert("Please enter at most 3 characters in the \"contact phone number\" field.");
    theForm.lgpnSecond.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.lgpnSecond.value;
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
    alert("Please enter only digit characters in the \"contact phone number\" field.");
    theForm.lgpnSecond.focus();
    return (false);
  }

  if (theForm.lgpnThird.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"contact phone number\" field.");
    theForm.lgpnThird.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.lgpnThird.value;
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
    alert("Please enter only digit characters in the \"contact phone number\" field.");
    theForm.lgpnThird.focus();
    return (false);
  }

  if (theForm.insurance.selectedIndex <= 0)
  {
    alert("The first \"Insurance Company\" option is not a valid selection.  Please choose one of the other options.");
    theForm.insurance.focus();
    return (false);
  }

  if (theForm.clinic.selectedIndex <= 0)
  {
    alert("Please select one of the \"Clinic\" options.");
    theForm.clinic.focus();
    return (false);
  }



  if (theForm.copayParity.value.length > 8)
  {
    alert("Please enter at most 8 characters in the \"copayParity\" field.");
    theForm.copayParity.focus();
    return (false);
  }

  var checkOK = "0123456789-.";
  var checkStr = theForm.copayParity.value;
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
    if (ch == ".")
    {
      allNum += ".";
      decPoints++;
    }
    else
      allNum += ch;
  }
  if (!allValid)
  {
    alert("Please enter only digit characters in the \"copayParity\" field.");
    theForm.copayParity.focus();
    return (false);
  }

  if (decPoints > 1 || !validGroups)
  {
    alert("Please enter a valid number in the \"copayParity\" field.");
    theForm.copayParity.focus();
    return (false);
  }




  if (theForm.copay.value.length > 8)
  {
    alert("Please enter at most 8 characters in the \"copay\" field.");
    theForm.copay.focus();
    return (false);
  }

  var checkOK = "0123456789-.";
  var checkStr = theForm.copay.value;
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
    if (ch == ".")
    {
      allNum += ".";
      decPoints++;
    }
    else
      allNum += ch;
  }
  if (!allValid)
  {
    alert("Please enter only digit characters in the \"copay\" field.");
    theForm.copay.focus();
    return (false);
  }

  if (decPoints > 1 || !validGroups)
  {
    alert("Please enter a valid number in the \"copay\" field.");
    theForm.copay.focus();
    return (false);
  }

  if (theForm.numAuthVisitForMD.value.length > 5)
  {
    alert("Please enter at most 5 characters in the \"numAuthVisitForMD\" field.");
    theForm.numAuthVisitForMD.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.numAuthVisitForMD.value;
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
    alert("Please enter only digit characters in the \"numAuthVisitForMD\" field.");
    theForm.numAuthVisitForMD.focus();
    return (false);
  }


  if (theForm.numAuthVisitForMA.value.length > 5)
  {
    alert("Please enter at most 5 characters in the \"numAuthVisitForMA\" field.");
    theForm.numAuthVisitForMA.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.numAuthVisitForMA.value;
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
    alert("Please enter only digit characters in the \"numAuthVisitForMA\" field.");
    theForm.numAuthVisitForMA.focus();
    return (false);
  }



  if (theForm.lsMonth.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"month\" field.");
    theForm.lsMonth.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.lsMonth.value;
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
    theForm.lsMonth.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "12"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"12\" in the \"month\" field.");
    theForm.lsMonth.focus();
    return (false);
  }

  if (theForm.lsDay.value.length > 2)
  {
    alert("Please enter at most 2 characters in the \"day\" field.");
    theForm.lsDay.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.lsDay.value;
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
    alert("Please enter only digit characters in the \"day\" field.");
    theForm.lsDay.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1" && prsVal <= "31"))
  {
    alert("Please enter a value greater than or equal to \"1\" and less than or equal to \"31\" in the \"day\" field.");
    theForm.lsDay.focus();
    return (false);
  }

  if (theForm.lsYear.value.length > 4)
  {
    alert("Please enter at most 4 characters in the \"year\" field.");
    theForm.lsYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.lsYear.value;
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
    theForm.lsYear.focus();
    return (false);
  }

  var chkVal = allNum;
  var prsVal = parseInt(allNum);
  if (chkVal != "" && !(prsVal >= "1900" && prsVal <= "2100"))
  {
    alert("Please enter a value greater than or equal to \"1900\" and less than or equal to \"2100\" in the \"year\" field.");
    theForm.lsYear.focus();
    return (false);
  }

  var checkOK = "0123456789-";
  var checkStr = theForm.daysLeft.value;
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
    alert("Please enter only digit characters in the \"daysLeft\" field.");
    theForm.daysLeft.focus();
    return (false);
  }

  if (theForm.pp.value.length > 800)
  {
    alert("Please enter at most 800 characters in the \"Presenting Problem\" field.");
    theForm.pp.focus();
    return (false);
  }

  var radioSelected = false;
  for (i = 0;  i < theForm.mmNeeded.length;  i++)
  {
    if (theForm.mmNeeded[i].checked)
        radioSelected = true;
  }
  if (!radioSelected)
  {
    alert("Please select one of the \"medical management services\" options.");
    return (false);
  }

  var radioSelected = false;
  for (i = 0;  i < theForm.tpNeeded.length;  i++)
  {
    if (theForm.tpNeeded[i].checked)
        radioSelected = true;
  }
  if (!radioSelected)
  {
    alert("Please select one of the \"therapy services\" options.");
    return (false);
  }

  var radioSelected = false;
  for (i = 0;  i < theForm.urgent.length;  i++)
  {
    if (theForm.urgent[i].checked)
        radioSelected = true;
  }
  if (!radioSelected)
  {
    alert("Please select one of the \"urgent\" options.");
    return (false);
  }

  var radioSelected = false;
  for (i = 0;  i < theForm.nts.length;  i++)
  {
    if (theForm.nts[i].checked)
        radioSelected = true;
  }
  if (!radioSelected)
  {
    alert("Please select one of the \"Need translation service\" options.");
    return (false);
  }

  return (true);
}

