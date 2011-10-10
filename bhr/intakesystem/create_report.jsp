<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Create Report</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<form method="POST" action="CreateReportAction.do">
  <p align="left" style="margin-top: 5; margin-bottom: 5; line-height:200%">
  <span style="background-color: #800000"><font color="#FFFFFF"><b>Aggregation
  </b></font></span>
  <b><font color="#FFFFFF"><span style="background-color: #800000">function&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></font></b></p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">
  <select size="1" name="aggregate" tabindex="1">
  <option selected value="null">-Pick an aggregation function-</option>
  <option value="sum">sum</option>
  <option value="count">count</option>
  <option value="average">average</option>
  </select></p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">&nbsp;
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b><font color="#FFFFFF"><span style="background-color: #800000">Filter 
  criteria&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></font></b></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="op1" tabindex="2">
  <option selected value="null">&nbsp;</option>
  <option value="(">(</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="attr1" tabindex="3">
  <option selected value="null">-Pick an attribute-</option>
  <option value="referralId">referral ID</option>
  <option value="referralStatus">referral status</option>
  <option value="referralDate">referral date</option>
  <option value="ssn">SSN</option>
  <option value="firstName">first name</option>
  <option value="middleInitial">middle initial</option>
  <option value="lastName">last name</option>
  <option value="birthDate">birth Date</option>
  <option value="gender">gender</option>
  <option value="martialStatus">martial status</option>
  <option value="medication">medication</option>
  <option value="medicalMgntNeeded">need medical mgnt</option>
  <option value="therapyNeeded">need therapy</option>
  <option value="urgentReferral">urgent referral</option>
  <option value="presentingProblem">presenting problem</option>
  <option value="streetAddress">street</option>
  <option value="apartmentNumber">apartment #</option>
  <option value="city">city</option>
  <option value="state">state</option>
  <option value="zipCode">zip code</option>
  <option value="daytimePhoneNumber">phone number</option>
  <option value="email">email</option>
  <option value="legalGardianFirstName">legal gardian firstname</option>
  <option value="legalGardianLastName">legal gardian lastName</option>
  <option value="legalGardianPhoneNumber">legal gardian phone#</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="pred1" tabindex="4">
  <option selected value="null">-Pick a predicate-</option>
  <option value="equals">equals</option>
  <option value="contains">contains</option>
  <option value="startsWith">starts with</option>
  <option value="endsWith">ends with</option>
  <option value="=">=</option>
  <option value="&lt;"><</option>
  <option value="&lt;="><=</option>
  <option value="&gt;">></option>
  <option value="&gt;=">>=</option>
  <option value="sameDate">same date as</option>
  <option value="afterOrOnDate">after or on date of</option>
  <option value="beforeOrOnDate">before or on date of</option>
  <option value="afterDateOf">after date of</option>
  <option value="beforeDateOf">before date of</option>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="text" name="val1" size="40" tabindex="5">&nbsp;&nbsp;&nbsp;
  <select size="1" name="cp1" tabindex="6">
  <option selected value="null">&nbsp</option>
  <option value=")">)</option>
  </select></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="rel1" tabindex="7">
  <option selected value="null">-Pick a relation</option>
  <option value="AND">AND</option>
  <option value="OR">OR</option>
  </select></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="op2" tabindex="8">
  <option selected value="null">&nbsp;</option>
  <option value="(">(</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="attr2" tabindex="9">
  <option selected value="null">-Pick an attribute-</option>
  <option value="referralId">referral ID</option>
  <option value="referralStatus">referral status</option>
  <option value="referralDate">referral date</option>
  <option value="ssn">SSN</option>
  <option value="firstName">first name</option>
  <option value="middleInitial">middle initial</option>
  <option value="lastName">last name</option>
  <option value="birthDate">birth Date</option>
  <option value="gender">gender</option>
  <option value="martialStatus">martial status</option>
  <option value="medication">medication</option>
  <option value="medicalMgntNeeded">need medical mgnt</option>
  <option value="therapyNeeded">need therapy</option>
  <option value="urgentReferral">urgent referral</option>
  <option value="presentingProblem">presenting problem</option>
  <option value="streetAddress">street</option>
  <option value="apartmentNumber">apartment #</option>
  <option value="city">city</option>
  <option value="state">state</option>
  <option value="zipCode">zip code</option>
  <option value="daytimePhoneNumber">phone number</option>
  <option value="email">email</option>
  <option value="legalGardianFirstName">legal gardian firstname</option>
  <option value="legalGardianLastName">legal gardian lastName</option>
  <option value="legalGardianPhoneNumber">legal gardian phone#</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="pred2" tabindex="10">
  <option selected value="null">-Pick a predicate-</option>
  <option value="equals">equals</option>
  <option value="contains">contains</option>
  <option value="startsWith">starts with</option>
  <option value="endsWith">ends with</option>
  <option value="=">=</option>
  <option value="&lt;"><</option>
  <option value="&lt;="><=</option>
  <option value="&gt;">></option>
  <option value="&gt;=">>=</option>
  <option value="sameDate">same date as</option>
  <option value="afterOrOnDate">after or on date of</option>
  <option value="beforeOrOnDate">before or on date of</option>
  <option value="afterDateOf">after date of</option>
  <option value="beforeDateOf">before date of</option>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="text" name="val2" size="40" tabindex="11">&nbsp;&nbsp;&nbsp;
  <select size="1" name="cp2" tabindex="12">
  <option selected value="null">&nbsp</option>
  <option value=")">)</option>
  </select></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="rel2" tabindex="13">
  <option selected value="null">-Pick a relation</option>
  <option value="AND">AND</option>
  <option value="OR">OR</option>
  </select></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="op3" tabindex="14">
  <option selected value="null">&nbsp;</option>
  <option value="(">(</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="attr3" tabindex="15">
  <option selected value="null">-Pick an attribute-</option>
  <option value="referralId">referral ID</option>
  <option value="referralDate">referral date</option>
  <option value="referralStatus">referral status</option>
  <option value="ssn">SSN</option>
  <option value="firstName">first name</option>
  <option value="middleInitial">middle initial</option>
  <option value="lastName">last name</option>
  <option value="birthDate">birth Date</option>
  <option value="gender">gender</option>
  <option value="martialStatus">martial status</option>
  <option value="medication">medication</option>
  <option value="medicalMgntNeeded">need medical mgnt</option>
  <option value="therapyNeeded">need therapy</option>
  <option value="urgentReferral">urgent referral</option>
  <option value="presentingProblem">presenting problem</option>
  <option value="streetAddress">street</option>
  <option value="apartmentNumber">apartment #</option>
  <option value="city">city</option>
  <option value="state">state</option>
  <option value="zipCode">zip code</option>
  <option value="daytimePhoneNumber">phone number</option>
  <option value="email">email</option>
  <option value="legalGardianFirstName">legal gardian firstname</option>
  <option value="legalGardianLastName">legal gardian lastName</option>
  <option value="legalGardianPhoneNumber">legal gardian phone#</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="pred3" tabindex="16">
  <option selected value="null">-Pick a predicate-</option>
  <option value="equals">equals</option>
  <option value="contains">contains</option>
  <option value="startsWith">starts with</option>
  <option value="endsWith">ends with</option>
  <option value="=">=</option>
  <option value="&lt;"><</option>
  <option value="&lt;="><=</option>
  <option value="&gt;">></option>
  <option value="&gt;=">>=</option>
  <option value="sameDate">same date as</option>
  <option value="afterOrOnDate">after or on date of</option>
  <option value="beforeOrOnDate">before or on date of</option>
  <option value="afterDateOf">after date of</option>
  <option value="beforeDateOf">before date of</option>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="text" name="val3" size="40" tabindex="17">&nbsp;&nbsp;&nbsp;
  <select size="1" name="cp3" tabindex="18">
  <option selected value="null">&nbsp</option>
  <option value=")">)</option>
  </select></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="rel3" tabindex="19">
  <option selected value="null">-Pick a relation</option>
  <option value="AND">AND</option>
  <option value="OR">OR</option>
  </select></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="op4" tabindex="20">
  <option selected value="null">&nbsp;</option>
  <option value="(">(</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="attr4" tabindex="21">
  <option selected value="null">-Pick an attribute-</option>
  <option value="referralId">referral ID</option>
  <option value="referralDate">referral date</option>
  <option value="referralStatus">referral status</option>
  <option value="ssn">SSN</option>
  <option value="firstName">first name</option>
  <option value="middleInitial">middle initial</option>
  <option value="lastName">last name</option>
  <option value="birthDate">birth Date</option>
  <option value="gender">gender</option>
  <option value="martialStatus">martial status</option>
  <option value="medication">medication</option>
  <option value="medicalMgntNeeded">need medical mgnt</option>
  <option value="therapyNeeded">need therapy</option>
  <option value="urgentReferral">urgent referral</option>
  <option value="presentingProblem">presenting problem</option>
  <option value="streetAddress">street</option>
  <option value="apartmentNumber">apartment #</option>
  <option value="city">city</option>
  <option value="state">state</option>
  <option value="zipCode">zip code</option>
  <option value="daytimePhoneNumber">phone number</option>
  <option value="email">email</option>
  <option value="legalGardianFirstName">legal gardian firstname</option>
  <option value="legalGardianLastName">legal gardian lastName</option>
  <option value="legalGardianPhoneNumber">legal gardian phone#</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="pred4" tabindex="22">
  <option selected value="null">-Pick a predicate-</option>
  <option value="equals">equals</option>
  <option value="contains">contains</option>
  <option value="startsWith">starts with</option>
  <option value="endsWith">ends with</option>
  <option value="=">=</option>
  <option value="&lt;"><</option>
  <option value="&lt;="><=</option>
  <option value="&gt;">></option>
  <option value="&gt;=">>=</option>
  <option value="sameDate">same date as</option>
  <option value="afterOrOnDate">after or on date of</option>
  <option value="beforeOrOnDate">before or on date of</option>
  <option value="afterDateOf">after date of</option>
  <option value="beforeDateOf">before date of</option>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="text" name="val4" size="40" tabindex="23">&nbsp;&nbsp;&nbsp;
  <select size="1" name="cp4" tabindex="24">
  <option selected value="null">&nbsp</option>
  <option value=")">)</option>
  </select></p>
  <p align="left" style="line-height: 200%; margin-top: 5; margin-bottom: 5">
  <select size="1" name="rel4" tabindex="25">
  <option selected value="null">-Pick a relation</option>
  <option value="AND">AND</option>
  <option value="OR">OR</option>
  </select></p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">
  <select size="1" name="op5" tabindex="26">
  <option selected value="null">&nbsp;</option>
  <option value="(">(</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="attr5" tabindex="27">
  <option selected value="null">-Pick an attribute-</option>
  <option value="referralId">referral ID</option>
  <option value="referralDate">referral date</option>
  <option value="referralStatus">referral status</option>
  <option value="ssn">SSN</option>
  <option value="firstName">first name</option>
  <option value="middleInitial">middle initial</option>
  <option value="lastName">last name</option>
  <option value="birthDate">birth Date</option>
  <option value="gender">gender</option>
  <option value="martialStatus">martial status</option>
  <option value="medication">medication</option>
  <option value="medicalMgntNeeded">need medical mgnt</option>
  <option value="therapyNeeded">need therapy</option>
  <option value="urgentReferral">urgent referral</option>
  <option value="presentingProblem">presenting problem</option>
  <option value="streetAddress">street</option>
  <option value="apartmentNumber">apartment #</option>
  <option value="city">city</option>
  <option value="state">state</option>
  <option value="zipCode">zip code</option>
  <option value="daytimePhoneNumber">phone number</option>
  <option value="email">email</option>
  <option value="legalGardianFirstName">legal gardian firstname</option>
  <option value="legalGardianLastName">legal gardian lastName</option>
  <option value="legalGardianPhoneNumber">legal gardian phone#</option>
  </select>&nbsp;&nbsp;&nbsp; <select size="1" name="pred5" tabindex="28">
  <option selected value="null">-Pick a predicate-</option>
  <option value="equals">equals</option>
  <option value="contains">contains</option>
  <option value="startsWith">starts with</option>
  <option value="endsWith">ends with</option>
  <option value="=">=</option>
  <option value="&lt;"><</option>
  <option value="&lt;="><=</option>
  <option value="&gt;">></option>
  <option value="&gt;=">>=</option>
  <option value="sameDate">same date as</option>
  <option value="afterOrOnDate">after or on date of</option>
  <option value="beforeOrOnDate">before or on date of</option>
  <option value="afterDateOf">after date of</option>
  <option value="beforeDateOf">before date of</option>
  </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="text" name="val5" size="40" tabindex="29">&nbsp;&nbsp;&nbsp;
  <select size="1" name="cp5" tabindex="30">
  <option selected value="null">&nbsp</option>
  <option value=")">)</option>
  </select></p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">&nbsp;
  </p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">
  <span style="background-color: #800000"><font color="#FFFFFF"><b>Grouping</b></font></span><font color="#FFFFFF"><b><span style="background-color: #800000"> 
  criteria&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></b></font></p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">
  <select size="1" name="grouping" tabindex="31">
  <option selected value="null">-Pick the grouping criteria-</option>
  <option value="referralId">referral ID</option>
  <option value="referralStatus">referral status</option>
  <option value="referralDate">referral date</option>
  <option value="ssn">SSN</option>
  <option value="firstName">first name</option>
  <option value="middleInitial">middle initial</option>
  <option value="lastName">last name</option>
  <option value="birthDate">birth Date</option>
  <option value="gender">gender</option>
  <option value="martialStatus">martial status</option>
  <option value="medication">medication</option>
  <option value="medicalMgntNeeded">need medical mgnt</option>
  <option value="therapyNeeded">need therapy</option>
  <option value="urgentReferral">urgent referral</option>
  <option value="presentingProblem">presenting problem</option>
  <option value="streetAddress">street</option>
  <option value="apartmentNumber">apartment #</option>
  <option value="city">city</option>
  <option value="state">state</option>
  <option value="zipCode">zip code</option>
  <option value="daytimePhoneNumber">phone number</option>
  <option value="email">email</option>
  <option value="legalGardianFirstName">legal gardian firstname</option>
  <option value="legalGardianLastName">legal gardian lastName</option>
  <option value="legalGardianPhoneNumber">legal gardian phone#</option>
  </select></p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">&nbsp;</p>
  <p align="left" style="margin-top: 5; margin-bottom: 5">
  <input type="submit" value="Search" name="search" tabindex="32"><input type="reset" value="Reset" name="reset" tabindex="33"></p>
</form>

</body>

</html>