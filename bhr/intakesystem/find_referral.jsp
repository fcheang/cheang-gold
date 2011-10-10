<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Search Referral</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<form action="FindReferralAction.do" method="POST">
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  Please specify the search criteria for referral:</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  * Date attribute (<em>appointment date</em>, <em>date of birth</em>) is specified in the format 
    <strong>mm/dd/yyyy</strong> (example 1/15/2005) OR in the format<strong> mm-dd-yyyy</strong> (example 1-15-2005)</p>
  <p align="left" style="margin-top: 0">* DateTime attribute is specified in the format <strong>mm/dd/yyyy hh:mm:ss</strong> (example 1/15/2005 14:20:00) OR in the format <strong>mm-dd-yyyy hh:mm:ss</strong> (example 1-15-2005 14:20:00) </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b><font color="#FFFFFF"><span style="background-color: #800000">Search   criteria&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></font></b></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <b>Referral status = <select size="1" name="status" tabindex="1">
  <option selected value="null">any</option>
  <% if (session.getAttribute("status") != null){
       out.print(session.getAttribute("status"));
     } 
  %>  
  </select>&nbsp;&nbsp;&nbsp; AND</b></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <b>Clinic&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; =&nbsp; 
  <select size="1" name="clinic" tabindex="2">
  <option selected value="null">any</option>
<% if (session.getAttribute("clinicName") != null){
     out.print(session.getAttribute("clinicName"));
   }
%>  
  </select>&nbsp;&nbsp;&nbsp; AND</b></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <b>Insurance&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; =&nbsp; <select size="1" name="insurance" tabindex="3">
  <option selected value="null">any</option>
  <% if (session.getAttribute("insuranceProvider") != null){
       out.print(session.getAttribute("insuranceProvider"));
     } 
  %>  
  </select>&nbsp;&nbsp;&nbsp; AND</b></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <b>Age Group&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; =&nbsp;
  <select size="1" name="ageGroup">
  <option selected value="null">any</option>
  <option>Child</option>
  <option>Adult</option>
  </select>&nbsp; AND</b></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="attr1" tabindex="4">
  <option value="null" selected>-Pick an attribute-</option>
  <option value="i.medicalId">Medi-Cal CIN#</option>
  <option value="r.referralId">referral ID</option>
  <option value="a.appointmentDate">appointment date</option>
  <option value="a.clinicName">scheduled clinic</option>
  <option value="a.provider">provider</option>
  <option value="a.translationSvcNeeded">need translation service</option>
  <option value="a.collateralReceived">collateral received</option>
  <option value="a.notes">appointment notes</option>
  <option value="r.ssn">SSN</option>
  <option value="r.firstName">first name</option>
  <option value="r.lastName">last name</option>
  <option value="r.birthDate">date of birth</option>
  <option value="r.gender">gender</option>
  <option value="r.needMedicalMgntSvc">need medical mgnt</option>
  <option value="r.needTherapy">need therapy</option>
  <option value="r.isUrgent">is urgent</option>
  <option value="r.presentingProblem">presenting problem</option>
  <option value="r.currentMedications">current medications</option>
  <option value="r.previousMedications">previous medications</option>
  <option value="r.streetAddress">street</option>
  <option value="r.apartmentNumber">apartment #</option>
  <option value="r.city">city</option>
  <option value="r.state">state</option>
  <option value="r.zipCode">zip code</option>
  <option value="r.phoneNumber">phone number</option>
  <option value="r.email">email</option>
  <option value="r.legalGardianFirstName">legal gardian firstname</option>
  <option value="r.legalGardianLastName">legal gardian lastName</option>
  <option value="r.legalGardianPhoneNumber">legal gardian phone#</option>
  </select>&nbsp;&nbsp;&nbsp; <select name="pred1" size="1" tabindex="5">
  <option value="=" selected>equal</option>
  <option value="!=">not equal</option>
  <option value="&lt;=">less or equal</option>
  <option value="&gt;=">greater or equal</option>
  <option value="&lt;">less than</option>
  <option value="&gt;">greater than</option>
  <option value="like">like</option>
  </select> &nbsp;&nbsp;<input type="text" name="val1" size="40" tabindex="6">&nbsp;&nbsp;&nbsp; <b>
  AND</b></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="attr2" tabindex="7">
  <option selected value="null">-Pick an attribute-</option>
  <option value="i.medicalId">Medi-Cal CIN#</option>
  <option value="r.referralId">referral ID</option>
  <option value="a.appointmentDate">appointment date</option>
  <option value="a.clinicName">scheduled clinic</option>
  <option value="a.provider">provider</option>
  <option value="a.translationSvcNeeded">need translation service</option>
  <option value="a.collateralReceived">collateral received</option>
  <option value="a.notes">appointment notes</option>
  <option value="r.ssn">SSN</option>
  <option value="r.firstName">first name</option>
  <option value="r.lastName">last name</option>
  <option value="r.birthDate">date of birth</option>
  <option value="r.gender">gender</option>
  <option value="r.needMedicalMgntSvc">need medical mgnt</option>
  <option value="r.needTherapy">need therapy</option>
  <option value="r.isUrgent">is urgent</option>
  <option value="r.presentingProblem">presenting problem</option>
  <option value="r.currentMedications">current medications</option>
  <option value="r.previousMedications">previous medications</option>
  <option value="r.streetAddress">street</option>
  <option value="r.apartmentNumber">apartment #</option>
  <option value="r.city">city</option>
  <option value="r.state">state</option>
  <option value="r.zipCode">zip code</option>
  <option value="r.phoneNumber">phone number</option>
  <option value="r.email">email</option>
  <option value="r.legalGardianFirstName">legal gardian firstname</option>
  <option value="r.legalGardianLastName">legal gardian lastName</option>
  <option value="r.legalGardianPhoneNumber">legal gardian phone#</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="pred2" tabindex="8">
  <option value="=" selected>equal</option>
  <option value="!=">not equal</option>
  <option value="&lt;=">less or equal</option>
  <option value="&gt;=">greater or equal</option>
  <option value="&lt;">less than</option>
  <option value="&gt;">greater than</option>
  <option value="like">like</option>
  </select>&nbsp;&nbsp;
  <input type="text" name="val2" size="40" tabindex="9">&nbsp;&nbsp;&nbsp; <b>
  AND</b></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="attr3" tabindex="10">
  <option selected value="null">-Pick an attribute-</option>
  <option value="i.medicalId">Medi-Cal CIN#</option>
  <option value="r.referralId">referral ID</option>
  <option value="a.appointmentDate">appointment date</option>
  <option value="a.clinicName">scheduled clinic</option>
  <option value="a.provider">provider</option>
  <option value="a.translationSvcNeeded">need translation service</option>
  <option value="a.collateralReceived">collateral received</option>
  <option value="a.notes">appointment notes</option>
  <option value="r.ssn">SSN</option>
  <option value="r.firstName">first name</option>
  <option value="r.lastName">last name</option>
  <option value="r.birthDate">date of birth</option>
  <option value="r.gender">gender</option>
  <option value="r.needMedicalMgntSvc">need medical mgnt</option>
  <option value="r.needTherapy">need therapy</option>
  <option value="r.isUrgent">is urgent</option>
  <option value="r.presentingProblem">presenting problem</option>
  <option value="r.currentMedications">current medications</option>
  <option value="r.previousMedications">previous medications</option>
  <option value="r.streetAddress">street</option>
  <option value="r.apartmentNumber">apartment #</option>
  <option value="r.city">city</option>
  <option value="r.state">state</option>
  <option value="r.zipCode">zip code</option>
  <option value="r.phoneNumber">phone number</option>
  <option value="r.email">email</option>
  <option value="r.legalGardianFirstName">legal gardian firstname</option>
  <option value="r.legalGardianLastName">legal gardian lastName</option>
  <option value="r.legalGardianPhoneNumber">legal gardian phone#</option>
  </select>&nbsp;&nbsp;&nbsp; 
  <select size="1" name="pred3" tabindex="11">
    <option value="=" selected>equal</option>
    <option value="!=">not equal</option>
    <option value="&lt;=">less or equal</option>
    <option value="&gt;=">greater or equal</option>
    <option value="&lt;">less than</option>
    <option value="&gt;">greater than</option>
    <option value="like">like</option>
    </select>
  &nbsp;&nbsp;
  <input type="text" name="val3" size="40" tabindex="12">&nbsp;&nbsp;&nbsp; <b>
  AND</b></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="attr4" tabindex="13">
  <option selected value="null">-Pick an attribute-</option>
  <option value="i.medicalId">Medi-Cal CIN#</option>
  <option value="r.referralId">referral ID</option>
  <option value="a.appointmentDate">appointment date</option>
  <option value="a.clinicName">scheduled clinic</option>
  <option value="a.provider">provider</option>
  <option value="a.translationSvcNeeded">need translation service</option>
  <option value="a.collateralReceived">collateral received</option>
  <option value="a.notes">appointment notes</option>
  <option value="r.ssn">SSN</option>
  <option value="r.firstName">first name</option>
  <option value="r.lastName">last name</option>
  <option value="r.birthDate">date of birth</option>
  <option value="r.gender">gender</option>
  <option value="r.needMedicalMgntSvc">need medical mgnt</option>
  <option value="r.needTherapy">need therapy</option>
  <option value="r.isUrgent">is urgent</option>
  <option value="r.presentingProblem">presenting problem</option>
  <option value="r.currentMedications">current medications</option>
  <option value="r.previousMedications">previous medications</option>
  <option value="r.streetAddress">street</option>
  <option value="r.apartmentNumber">apartment #</option>
  <option value="r.city">city</option>
  <option value="r.state">state</option>
  <option value="r.zipCode">zip code</option>
  <option value="r.phoneNumber">phone number</option>
  <option value="r.email">email</option>
  <option value="r.legalGardianFirstName">legal gardian firstname</option>
  <option value="r.legalGardianLastName">legal gardian lastName</option>
  <option value="r.legalGardianPhoneNumber">legal gardian phone#</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="pred4" tabindex="14">
  <option value="=" selected>equal</option>
  <option value="!=">not equal</option>
  <option value="&lt;=">less or equal</option>
  <option value="&gt;=">greater or equal</option>
  <option value="&lt;">less than</option>
  <option value="&gt;">greater than</option>
  <option value="like">like</option>
  </select>&nbsp;&nbsp;
  <input type="text" name="val4" size="40" tabindex="15">&nbsp;&nbsp;&nbsp; <b>
  AND</b></p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">
  <select size="1" name="attr5" tabindex="16">
  <option selected value="null">-Pick an attribute-</option>
  <option value="i.medicalId">Medi-Cal CIN#</option>
  <option value="r.referralId">referral ID</option>
  <option value="a.appointmentDate">appointment date</option>
  <option value="a.clinicName">scheduled clinic</option>
  <option value="a.provider">provider</option>
  <option value="a.translationSvcNeeded">need translation service</option>
  <option value="a.collateralReceived">collateral received</option>
  <option value="a.notes">appointment notes</option>
  <option value="r.ssn">SSN</option>
  <option value="r.firstName">first name</option>
  <option value="r.lastName">last name</option>
  <option value="r.birthDate">date of birth</option>
  <option value="r.gender">gender</option>
  <option value="r.needMedicalMgntSvc">need medical mgnt</option>
  <option value="r.needTherapy">need therapy</option>
  <option value="r.isUrgent">is urgent</option>
  <option value="r.presentingProblem">presenting problem</option>
  <option value="r.currentMedications">current medications</option>
  <option value="r.previousMedications">previous medications</option>
  <option value="r.streetAddress">street</option>
  <option value="r.apartmentNumber">apartment #</option>
  <option value="r.city">city</option>
  <option value="r.state">state</option>
  <option value="r.zipCode">zip code</option>
  <option value="r.phoneNumber">phone number</option>
  <option value="r.email">email</option>
  <option value="r.legalGardianFirstName">legal gardian firstname</option>
  <option value="r.legalGardianLastName">legal gardian lastName</option>
  <option value="r.legalGardianPhoneNumber">legal gardian phone#</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="pred5" tabindex="17">
  <option value="=" selected>equal</option>
  <option value="!=">not equal</option>
  <option value="&lt;=">less or equal</option>
  <option value="&gt;=">greater or equal</option>
  <option value="&lt;">less than</option>
  <option value="&gt;">greater than</option>
  <option value="like">like</option>
  </select>&nbsp;&nbsp;
  <input type="text" name="val5" size="40" tabindex="18"></p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">&nbsp;
  </p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">
  <font color="#FFFFFF"><b><span style="background-color: #800000">Sort criteria&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></b></font></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">&nbsp;<select size="1" name="sort1" tabindex="19">
  <option selected value="null">-Pick a sort criteria-</option>
  <option value="r.referralId">referral ID</option>
  <option value="rs.referralStatus">referral status</option>
  <option value="r.birthDate">date of birth</option>
  <option value="a.appointmentDate">appointment date</option>
  <option value="a.clinicName">appointment clinic</option>
  <option value="a.provider">provider</option>
  <option value="r.firstName">first name</option>
  <option value="r.lastName">last name</option>
  <option value="clinic">clinic</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="order1" tabindex="20">
  <option value="asc" selected>ascending</option>
  <option value="desc">descending</option>
  </select></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">&nbsp;<select size="1" name="sort2" tabindex="21">
  <option selected value="null">-Pick a sort criteria-</option>
  <option value="r.referralId">referral ID</option>
  <option value="rs.referralStatus">referral status</option>
  <option value="r.birthDate">date of birth</option>
  <option value="a.appointmentDate">appointment date</option>
  <option value="a.clinicName">appointment clinic</option>
  <option value="a.provider">provider</option>
  <option value="r.firstName">first name</option>
  <option value="r.lastName">last name</option>
  <option value="clinic">clinic</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="order2" tabindex="22">
  <option value="asc" selected>ascending</option>
  <option value="desc">descending</option>
  </select></p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">&nbsp;</p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">
  <input type="submit" value="Search" name="search" tabindex="23"><input type="reset" value="Reset" name="reset" tabindex="24"></p>
</form>
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>

</body>

</html>