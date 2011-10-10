<jsp:useBean id="ref" class="com.suntek.common.persistence.Referral" scope="session" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
-->
</style>
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
</head>

<body>
<p><strong>Update referral</strong></p>
<p>Fields marked with (<span class="style1">*</span>) is required
<script language="javascript" src="validate_ref.js"></script>
<script language="javascript" src="autojump.js"></script>
</p>

<form method="post" action="update_referral.jsp" onSubmit="return validateForm(this)" language="JavaScript">
  <p class="sidebarHeader">General Information: </p>
  <table width="748" border="0">
    <tr>
      <td width="188"><strong>Referral date:<span class="style1"> *</span></strong> </td>
      <td width="550"><input name="rdMonth" type="text" size="2" maxlength="2" value="<jsp:getProperty name="ref" property="rdMonth"/>" >
        /
          <input name="rdDate" type="text" size="2" maxlength="2" value="<jsp:getProperty name="ref" property="rdDate"/>" >
        /
        <input name="rdYear" type="text" size="4" maxlength="4" value="<jsp:getProperty name="ref" property="rdYear"/>" >
        <span class="style6">(MM/ DD/ YYYY)</span></td>
    </tr>
    <tr>
      <td><strong>First name: <span class="style1">*</span></strong> </td>
      <td><b><font color="#FF0000">
        <input name="firstName" type="text" tabindex="1" value="<jsp:getProperty name="ref" property="firstName"/>" size="30" maxlength="30">
      </font></b></td>
    </tr>
    <tr>
      <td><strong>Middle initial: </strong></td>
      <td><b><span style="background-color: #FFFF99">
        <input type="text" name="mi" size="5" tabindex="2" maxlength="5" value="<jsp:getProperty name="ref" property="mi"/>">
      </span></b>
      </td>
    </tr>
    <tr>
      <td><strong>Last name: <span class="style1">*</span></strong> </td>
      <td><b>
        <input name="lastName" type="text" tabindex="3" value="<jsp:getProperty name="ref" property="lastName"/>" size="30" maxlength="30">
      </b></td>
    </tr>
    <tr>
      <td><strong>Gender: </strong></td>
      <td><b>
      <input name="gender" type="radio" tabindex="4" value="male"
<%
  if (ref.getGender() != null && ref.getGender().equals("male")){
    out.print("checked=\"true\"");
  }
%>
>
</b>Male&nbsp;
<input name="gender" type="radio" tabindex="5" value="female"
<%
  if (ref.getGender() != null && ref.getGender().equals("female")){
    out.print("checked=\"true\"");
  }
%>
>
Female</td>
    </tr>
    <tr>
      <td><b>Social security number:<span class="style1">*&nbsp;</span></b></td>
      <td><input type="text" name="ssnFirst" size="3" maxlength="3" tabindex="6" value="<jsp:getProperty name="ref" property="ssnFirst"/>">
-
  <input type="text" name="ssnSecond" size="2" maxlength="2" tabindex="7" value="<jsp:getProperty name="ref" property="ssnSecond"/>">
-
<input type="text" name="ssnThird" size="4" maxlength="4" tabindex="8" value="<jsp:getProperty name="ref" property="ssnThird"/>"></td>
    </tr>
    <tr>
      <td><b>Date of Birth: <font color="#FF0000">*</font></b></td>
      <td><b>
        <input name="dobMonth" type="text" tabindex="9" value="<jsp:getProperty name="ref" property="dobMonth"/>" size="2" maxlength="2">
/
<input name="dobDay" type="text" tabindex="10" value="<jsp:getProperty name="ref" property="dobDay"/>" size="2" maxlength="2">
/
<input name="dobYear" type="text" tabindex="11" value="<jsp:getProperty name="ref" property="dobYear"/>" size="4" maxlength="4">
</b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>
    <tr>
      <td><b>Street address:&nbsp;&nbsp;</b></td>
      <td><b>
        <input type="text" name="streetAddress" size="30" tabindex="12" maxlength="30" value="<jsp:getProperty name="ref" property="streetAddress"/>">
      </b></td>
    </tr>
    <tr>
      <td><b>Apartment number:&nbsp;</b></td>
      <td><b>
        <input type="text" name="apartmentNumber" size="30" tabindex="13" maxlength="30" value="<jsp:getProperty name="ref" property="apartmentNumber"/>">
      </b></td>
    </tr>
    <tr>
      <td><b>City: <font color="#FF0000">* </font></b></td>
      <td><b>
        <font color="#FF0000">
        <input name="city" type="text" tabindex="14" value="<jsp:getProperty name="ref" property="city"/>" size="20" maxlength="20">
      </font>      </b></td>
    </tr>
    <tr>
      <td><b>State: <font color="#FF0000">* </font></b></td>
      <td><input name="state" type="text" tabindex="15" value="<jsp:getProperty name="ref" property="state"/>" size="2" maxlength="2"></td>
    </tr>
    <tr>
      <td><b>Zip code:&nbsp;</b></td>
      <td><b>
        <input type="text" name="zipcode" size="20" tabindex="16" maxlength="20" value="<jsp:getProperty name="ref" property="zipcode"/>">
      </b></td>
    </tr>
    <tr>
      <td><b>Phone number: <font color="#FF0000">*</font>&nbsp;</b></td>
      <td><b> </b>(
        <input name="dpnFirst" type="text" tabindex="17" value="<jsp:getProperty name="ref" property="dpnFirst"/>" size="3" maxlength="3">
)
<input name="dpnSecond" type="text" tabindex="18" value="<jsp:getProperty name="ref" property="dpnSecond"/>" size="3" maxlength="3">
-
<input name="dpnThird" type="text" tabindex="19" value="<jsp:getProperty name="ref" property="dpnThird"/>" size="4" maxlength="4"></td>
    </tr>
    <tr>
      <td><b>Email address:&nbsp;</b></td>
      <td><b>
        <input type="text" name="email" size="30" tabindex="20" maxlength="30" value="<jsp:getProperty name="ref" property="email"/>">
      </b></td>
    </tr>
    <tr>
      <td><b>Legal guardian:</b><i>&nbsp;&nbsp;</i></td>
      <td>First name:
          <input type="text" name="lgFirstName" size="30" tabindex="21" maxlength="30" value="<jsp:getProperty name="ref" property="lgFirstName"/>"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Middle initial:
          <input type="text" name="lgMi" size="5" tabindex="22" maxlength="5" value="<jsp:getProperty name="ref" property="lgMi"/>"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td> Last name:
          <input type="text" name="lgLastName" size="30" tabindex="23" maxlength="30" value="<jsp:getProperty name="ref" property="lgLastName"/>"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Contact phone number: (
        <input type="text" name="lgpnFirst" size="3" tabindex="24" maxlength="3" value="<jsp:getProperty name="ref" property="lgpnFirst"/>">
)
<input type="text" name="lgpnSecond" size="3" tabindex="25" maxlength="3" value="<jsp:getProperty name="ref" property="lgpnSecond"/>">
-
<input type="text" name="lgpnThird" size="4" tabindex="26" maxlength="4" value="<jsp:getProperty name="ref" property="lgpnThird"/>"></td>
    </tr>
  </table>
  <p class="sidebarHeader">Insurance Information: </p>
  <table width="749" border="0">
    <tr>
      <td width="187"><b>Insurance Id: <font color="#FF0000">*</font></b></td>
      <td colspan="2"><b>
          <jsp:getProperty name="ref" property="insId"/>
      </b></td>
    </tr>   

    <tr>
      <td><strong>Admission Date: </strong></td>
      <td colspan="2"><b>
        <input type="text" name="refMonth" size="2" tabindex="41" maxlength="2" 
		<%
		  if (ref.getRefMonth() != 0){
			out.print("value="+ref.getRefMonth());
		  }
		%>
		>
/
<input type="text" name="refDay" size="2" tabindex="42" maxlength="2" 
<%
  if (ref.getRefDay() != 0){
	out.print("value="+ref.getRefDay());
  }
%>
>
/
<input type="text" name="refYear" size="4" tabindex="43" maxlength="4" 
		<%
		  if (ref.getRefYear() != 0){
			out.print("value="+ref.getRefYear());
		  }
		%>
 >
      </b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>


    <tr>
      <td><strong>Elig Eff Date: </strong></td>
      <td colspan="2"><b>
        <input type="text" name="esdMonth" size="2" tabindex="41" maxlength="2" 
		<%
		  if (ref.getEsdMonth() != 0){
			out.print("value="+ref.getEsdMonth());
		  }
		%>
		>
/
<input type="text" name="esdDay" size="2" tabindex="42" maxlength="2" 
<%
  if (ref.getEsdDay() != 0){
	out.print("value="+ref.getEsdDay());
  }
%>
>
/
<input type="text" name="esdYear" size="4" tabindex="43" maxlength="4" 
		<%
		  if (ref.getEsdYear() != 0){
			out.print("value="+ref.getEsdYear());
		  }
		%>
 >
      </b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>



    <tr>
      <td><strong>Elig Term Date: </strong></td>
      <td colspan="2"><b>
        <input type="text" name="eedMonth" size="2" tabindex="41" maxlength="2" 
		<%
		  if (ref.getEedMonth() != 0){
			out.print("value="+ref.getEedMonth());
		  }
		%>
		>
/
<input type="text" name="eedDay" size="2" tabindex="42" maxlength="2" 
<%
  if (ref.getEedDay() != 0){
	out.print("value="+ref.getEedDay());
  }
%>
>
/
<input type="text" name="eedYear" size="4" tabindex="43" maxlength="4" 
		<%
		  if (ref.getEedYear() != 0){
			out.print("value="+ref.getEedYear());
		  }
		%>
 >
      </b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>



  
    <tr>
      <td><b>Insurance Company: <font color="#FF0000">*</font></b></td>
      <td colspan="2"><b>
        <select size="1" name="insurance" tabindex="27" >
          <jsp:getProperty name="ref" property="insuranceList"/>        
</select>
      </b></td>
    </tr>
    <tr>
      <td><b>Member ID/ PSP Number</b></td>
      <td colspan="2"><b>
        <input type="text" name="memberId" size="30" tabindex="28" maxlength="30" value="<jsp:getProperty name="ref" property="memberId"/>">
      </b></td>
    </tr>
    <tr>
      <td><b>Copay Parity:&nbsp;</b></td>
      <td colspan="2"><b> </b>$ <b>
      <input type="text" name="copayParity" size="5" tabindex="29" maxlength="8" value="<jsp:getProperty name="ref" property="copayParityStr"/>">
      </b></td>
    </tr>	
    <tr>
      <td><b>Copay non-parity:&nbsp;</b></td>
      <td colspan="2"><b> </b>$ <b>
      <input type="text" name="copay" size="5" tabindex="30" maxlength="8" value="<jsp:getProperty name="ref" property="copayStr"/>">
      </b></td>
    </tr>	
    <tr>
      <td><b>Insurance phone #:</b></td>
      <td colspan="2"><b> </b><b> </b>( <b>
      <input type="text" name="inspnFirst" size="3" maxlength="3" tabindex="31" value="<jsp:getProperty name="ref" property="inspnFirst"/>">
      </b>)
      <input type="text" name="inspnSecond" size="3" maxlength="3" tabindex="32" value="<jsp:getProperty name="ref" property="inspnSecond"/>">
-
<input type="text" name="inspnThird" size="4" maxlength="4" tabindex="33" value="<jsp:getProperty name="ref" property="inspnThird"/>"></td>
    </tr>
    <tr>
      <td><b>MD # authorized visit:&nbsp;</b></td>
      <td width="119"><b>
        <input type="text" name="numAuthVisitForMD" size="5" tabindex="35" maxlength="5" value="<jsp:getProperty name="ref" property="numAuthVisitForMD"/>">
      </b></td>
	  <td width="429"><b>Auth #:   
        <input type="text" name="authNumForMD" size="30" tabindex="34" maxlength="30" value="<jsp:getProperty name="ref" property="authNumForMD"/>">
	  </b></td>
    </tr>
    <tr>
      <td><b>MA # authorized visit:&nbsp;</b></td>
      <td><b>
        <input type="text" name="numAuthVisitForMA" size="5" tabindex="36" maxlength="5" value="<jsp:getProperty name="ref" property="numAuthVisitForMA"/>">
      </b></td>	  
      <td><b>Auth #:  
          <input type="text" name="authNumForMA" size="30" tabindex="34" maxlength="30" value="<jsp:getProperty name="ref" property="authNumForMA"/>">
      </b></td>
    </tr>
    <tr>
      <td><strong>Medi-Cal CIN #: </strong></td>
      <td colspan="2"><b>
        <input type="text" name="medicalId" size="30" tabindex="40" maxlength="30" value="<jsp:getProperty name="ref" property="medicalId"/>">
      </b></td>
    </tr>
    <tr>
      <td><strong>Medi-Cal Issue Date: </strong></td>
      <td colspan="2"><b>
        <input type="text" name="mdMonth" size="2" tabindex="41" maxlength="2" 
		<%
		  if (ref.getMdMonth() != 0){
			out.print("value="+ref.getMdMonth());
		  }
		%>
		>
/
<input type="text" name="mdDay" size="2" tabindex="42" maxlength="2" 
<%
  if (ref.getMdDay() != 0){
	out.print("value="+ref.getMdDay());
  }
%>
>
/
<input type="text" name="mdYear" size="4" tabindex="43" maxlength="4" 
		<%
		  if (ref.getMdYear() != 0){
			out.print("value="+ref.getMdYear());
		  }
		%>
 >
      </b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>
  </table>
  <p class="sidebarHeader">Medical History:</p>
  <table width="759" border="0">
    <tr>
      <td width="185"><b>Previous Psychiatrist:</b></td>
      <td width="564"><b>
        <input type="text" name="prevPsychiatrist" size="40" maxlength="65" tabindex="37" value="<jsp:getProperty name="ref" property="prevPsychiatrist"/>">
      </b></td>
    </tr>
    <tr>
      <td><b>Last seen:&nbsp;&nbsp;</b></td>
      <td><b>
      <input type="text" name="lsMonth" size="2" tabindex="38" maxlength="2" 
<%
  if (ref.getLsMonth() != 0){
    out.print("value="+ref.getLsMonth());
  }
%>
>
      <input type="text" name="lsDay" size="2" tabindex="39" maxlength="2" 
<%
  if (ref.getLsDay() != 0){
    out.print("value="+ref.getLsDay());
  }
%>
>
      <input type="text" name="lsYear" size="4" tabindex="40" maxlength="4" 
<%
  if (ref.getLsYear() != 0){
    out.print("value="+ref.getLsYear());
  }
%>
>
</b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>
    <tr>
      <td><b>Current medications:&nbsp;&nbsp;</b></td>
      <td><b>
        <input type="text" name="currMed" size="40" maxlength="100" tabindex="41" value="<jsp:getProperty name="ref" property="currMed"/>">
      </b></td>
    </tr>
    <tr>
      <td><b>How many days left?</b></td>
      <td><b>
        <input type="text" name="daysLeft" size="5" tabindex="42" value="<jsp:getProperty name="ref" property="daysLeft"/>">
      </b></td>
    </tr>
    <tr>
      <td><b>Previous medications:&nbsp;</b></td>
      <td><b>
        <input type="text" name="prevMed" size="40" maxlength="100" tabindex="43" value="<jsp:getProperty name="ref" property="prevMed"/>">
      </b></td>
    </tr>
    <tr>
      <td><b>Previous Dx: &nbsp;</b></td>
      <td><b>
        <input type="text" name="prevDx" size="20" maxlength="100" tabindex="44" value="<jsp:getProperty name="ref" property="prevDx"/>">
      </b></td>
    </tr>
    <tr>
      <td><b>Presenting problem:&nbsp;&nbsp;&nbsp;</b></td>
      <td><b>
        <textarea rows="8" name="pp" cols="50" tabindex="45"><jsp:getProperty name="ref" property="pp"/></textarea>
      </b></td>
    </tr>
    <tr>
      <td><b>Need Medical mgnt services? <font color="#FF0000">*</font></b></td>
      <td><b>
        <input name="mmNeeded" type="radio" tabindex="46" value="true" 
<%
  if (ref.getMmNeeded()){
    out.print("checked=\"true\"");
  }
%>
>
</b>Yes&nbsp;&nbsp;&nbsp;
<input name="mmNeeded" type="radio" tabindex="47" value="false" 
<%
  if (!ref.getMmNeeded()){
    out.print("checked=\"true\"");
  }
%>
>
No</td>
    </tr>
    <tr>
      <td><b>Need Therapy services? <font color="#FF0000">*</font></b></td>
      <td><b>
      <input name="tpNeeded" type="radio" tabindex="48" value="true" 
<%
  if (ref.getTpNeeded()){
    out.print("checked=\"true\"");
  }
%>
>
</b>Yes&nbsp;&nbsp;&nbsp;
<input name="tpNeeded" type="radio" tabindex="49" value="false" 
<%
  if (!ref.getTpNeeded()){
    out.print("checked=\"true\"");
  }
%>
>
No</td>
    </tr>
    <tr>
      <td><b>Is referral urgent? <font color="#FF0000">*</font>&nbsp;&nbsp;</b></td>
      <td><b>
        <input name="urgent" type="radio" tabindex="50" value="yes" 
<%
  if (ref.getUrgent().equals("yes")){
    out.print("checked=\"true\"");
  }
%>
>
</b>Yes&nbsp;&nbsp;&nbsp;
<input name="urgent" type="radio" tabindex="51" value="no" 
<%
  if (ref.getUrgent().equals("no")){
    out.print("checked=\"true\"");
  }
%>
>
No&nbsp;&nbsp;&nbsp;
<input name="urgent" type="radio" tabindex="52" value="48hrs" 
<%
  if (ref.getUrgent().equals("48hrs")){
    out.print("checked=\"true\"");
  }
%>
>
48 hrs</td>
    </tr>
    <tr>
      <td><b>Clinic accepted:<font color="#FF0000">*</font>&nbsp;</b></td>
      <td><b>
        <select size="1" name="clinic" tabindex="53">
          <jsp:getProperty name="ref" property="clinicList"/>        
</select>
      </b></td>
    </tr>
    <tr>
      <td><p><b>Comments:</b></p>
          <p>&nbsp;</p></td>
      <td><b>
        <textarea rows="4" name="comments" cols="50" tabindex="54"><jsp:getProperty name="ref" property="comments"/></textarea>
      </b></td>
    </tr>
    <tr>
      <td><strong>Need Translation service? <span class="style1">*</span> </strong></td>
      <td><b>
        <input type="radio" name="nts" value="true" tabindex="62" 
		<%
		  if (ref.getNts()){
			out.print("checked=\"true\"");
		  }
		%>		
		>
      </b>Yes&nbsp;&nbsp;&nbsp;
      <input type="radio" name="nts" value="false" tabindex="63" 
		<%
		  if (!ref.getNts()){
			out.print("checked=\"true\"");
		  }
		%>	  
	  >
No</td>
    </tr>
  </table>
  <p>
    <input type="submit" value="Update" name="Update" tabindex="55">
  </p>
</form>
<p>&nbsp;</p>
<SCRIPT TYPE="text/javascript">
autojump('rdMonth', 'rdDate', 2);
autojump('rdDate', 'rdYear', 2);
autojump('rdYear', 'firstName', 4);
autojump('ssnFirst', 'ssnSecond', 3);
autojump('ssnSecond', 'ssnThird', 2);
autojump('ssnThird', 'dobMonth', 4);
autojump('dobMonth', 'dobDay', 2);
autojump('dobDay', 'dobYear', 2);
autojump('dobYear', 'streetAddress', 4);
autojump('dpnFirst', 'dpnSecond', 3);
autojump('dpnSecond', 'dpnThird', 2);
autojump('dpnThird', 'email', 4);
autojump('lgpnFirst', 'lgpnSecond', 3);
autojump('lgpnSecond', 'lgpnThird', 2);
autojump('lgpnThird', 'insurance', 4);
autojump('inspnFirst', 'inspnSecond', 3);
autojump('inspnSecond', 'inspnThird', 2);
autojump('inspnThird', 'authorizationNumber', 4);
autojump('lsMonth', 'lsDay', 2);
autojump('lsDay', 'lsYear', 2);
autojump('lsYear', 'currMed', 4);
autojump('mdMonth', 'mdDay', 2);
autojump('mdDay', 'mdYear', 2);
autojump('mdYear', 'prevPsychiatrist', 4)

autojump('refMonth', 'refDay', 2);
autojump('refDay', 'refYear', 2);
autojump('refYear', 'esdMonth', 4);

autojump('esdMonth', 'esdDay', 2);
autojump('esdDay', 'esdYear', 2);
autojump('esdYear', 'eedMonth', 4);

autojump('eedMonth', 'eedDay', 2);
autojump('eedDay', 'eedYear', 2);
autojump('eedYear', 'insurance', 4)

</SCRIPT>

</body>
</html>
