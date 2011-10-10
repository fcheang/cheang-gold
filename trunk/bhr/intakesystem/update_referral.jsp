<jsp:useBean id="ref" class="com.suntek.common.persistence.Referral" scope="session" /> 
<jsp:setProperty name="ref" property="reset" value="dummy"/>
<jsp:setProperty name="ref" property="*"/>

<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Update Referral</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

  <p align="left" style="margin-top: 0; margin-bottom: 0">
<b>Please check if all the information is correct and click the <i>Save </i>
button.</b></p>

  <p align="left" style="margin-top: 0; margin-bottom: 0">
<b>Referral is not updated until you click the <i>Save </i> button.</b></p>

  <p align="left" style="margin-top: 0; margin-bottom: 0">
<b>To make correction, use the browser's Back button. </b></p>

  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
</p>

  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
<span style="background-color: #800000"><font color="#FFFFFF"><b>General information</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>
</span></p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
<b>Referral Date: </b>

<jsp:getProperty name="ref" property="rd"/></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Name: </b><jsp:getProperty name="ref" property="fullName"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Gender: </b><jsp:getProperty name="ref" property="gender"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Social security number: </b><jsp:getProperty name="ref" property="ssn"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Date of Birth: </b><jsp:getProperty name="ref" property="dob"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Age Group: </b><jsp:getProperty name="ref" property="ageGroup"/></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Address: </b></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
<jsp:getProperty name="ref" property="fullAddress"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Phone number: </b><jsp:getProperty name="ref" property="dpn"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Email address: </b><jsp:getProperty name="ref" property="email"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Legal guardian:</b><br>
<jsp:getProperty name="ref" property="lgFullName"/><br>
<jsp:getProperty name="ref" property="lgpn"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;
  </p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
<span style="background-color: #800000"><b><font color="#FFFFFF">Insurance information&nbsp;&nbsp;&nbsp; </font></b><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</font>
</span></p>
<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>

<p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Admission Date: </b>
  <jsp:getProperty name="ref" property="refDate"/>
</p>  

<p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Elig Eff Date: </b>
  <jsp:getProperty name="ref" property="eligEffDate"/>
</p>  

<p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Elig Term Date: </b>
  <jsp:getProperty name="ref" property="eligTermDate"/>
</p>  

  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Insurance Id: </b><jsp:getProperty name="ref" property="insId"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Insurance Company: </b><jsp:getProperty name="ref" property="insurance"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Member ID/ PSP Number: </b>
  <jsp:getProperty name="ref" property="memberId"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Copay Parity: </b><jsp:getProperty name="ref" property="copayParity"/>
  </p>  
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Copay non-Parity: </b><jsp:getProperty name="ref" property="copay"/>
  </p>  
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>Insurance phone #: </b><jsp:getProperty name="ref" property="inspn"/>
  </p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>MD # auth visit: </b>
  <jsp:getProperty name="ref" property="numAuthVisitForMD"/>
  &nbsp;&nbsp;&nbsp;<strong>Auth #</strong>: <jsp:getProperty name="ref" property="authNumForMD"/></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">
  <b>MA # auth visit: </b>
  <jsp:getProperty name="ref" property="numAuthVisitForMA"/>
  &nbsp;&nbsp;&nbsp;<strong>Auth #</strong>: <jsp:getProperty name="ref" property="authNumForMA"/>  
  </p>  
  <p align="left" style="margin-top: 0; margin-bottom: 0"><b>Medi-Cal CIN#: </b>
    <jsp:getProperty name="ref" property="medicalId"/>    
</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0"> <b>Medical Issue Date: </b>
      <jsp:getProperty name="ref" property="medicalIssueDate"/>    
</p>
  <p align="left" style="margin-top: 0; margin-bottom: 0"></p>
  <p align="left" style="margin-bottom: 0"><span style="background-color: #800000"><b><font color="#FFFFFF">Medical History&nbsp;&nbsp; </font></b>
      <font color="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </font>
  </span></p>
  <p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;</p>
<p align="left" style="margin-top: 0; margin-bottom: 0"><b>Previous Psychiatrist: </b>
<jsp:getProperty name="ref" property="prevPsychiatrist"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0"><b>Last seen:
</b>
<jsp:getProperty name="ref" property="lastSeen"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0"><b>Current medications:
</b>
<jsp:getProperty name="ref" property="currMed"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0"><b>How many days left:
</b>
<jsp:getProperty name="ref" property="daysLeft"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0"><b>Previous medications:
</b>
<jsp:getProperty name="ref" property="prevMed"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0"><b>Previous Dx: </b>
<jsp:getProperty name="ref" property="prevDx"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0"><b>Presenting problem:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</b></p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
<jsp:getProperty name="ref" property="pp"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
<b>Medical management services needed? </b>
<jsp:getProperty name="ref" property="mmNeeded"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
<b>Therapy services needed? </b>
<jsp:getProperty name="ref" property="tpNeeded"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
<b>Is referral urgent? </b>
<jsp:getProperty name="ref" property="urgent"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
<b>Clinic accepted: </b>
<jsp:getProperty name="ref" property="clinic"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
<b>Comments:</b></p>
<p align="left" style="margin-top: 0; margin-bottom: 0">
<jsp:getProperty name="ref" property="comments"/>
</p>
<p align="left" style="margin-top: 0; margin-bottom: 0"><b>Need translation service? </b>
  <jsp:getProperty name="ref" property="nts"/>  
</p>
<form method="POST" action="UpdateReferralAction.do"><input type="submit" name="Confirm" value="Save" tabindex="1">
</form>
</body>

</html>