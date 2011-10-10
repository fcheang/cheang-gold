<jsp:useBean id="ref" class="com.suntek.common.persistence.Referral" scope="session" />
<jsp:setProperty name="ref" property="*" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>New Referral</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<script language="javascript" src="validate_ref.js"></script>
<script language="javascript" src="autojump.js"></script>

</head>

<body>
<p><strong>Enter new referral</strong><br>
Fields marked with (<span class="style1">*</span>) is required </p>

<form method="post" action="view_referral.jsp" onSubmit="return validateForm(this)" language="JavaScript">
  <p class="sidebarHeader">General Information: </p>
  <table width="748" border="0">
    <tr>
      <td width="188"><strong>Referral  date:<span class="style1"> *</span></strong> </td>
      <td width="550">
        <input name="rdMonth" type="text" size="2" maxlength="2" tabindex="1"
  <%
  	if (session.getAttribute("rdMonth") != null){
		out.print("value=\""+session.getAttribute("rdMonth")+"\"");
	}
  %> >
/
<input name="rdDate" type="text" size="2" maxlength="2" tabindex="2"
  <%
  	if (session.getAttribute("rdDate") != null){
		out.print("value=\""+session.getAttribute("rdDate")+"\"");
	}
  %> >
/
<input name="rdYear" type="text" size="4" maxlength="4" tabindex="3"
  <%
  	if (session.getAttribute("rdYear") != null){
		out.print("value=\""+session.getAttribute("rdYear")+"\"");
	}
  %> >
<span class="style3">(MM/ DD/ YYYY)</span></td>
    </tr>
    <tr>
      <td><strong>First name: <span class="style1">*</span></strong> </td>
      <td><input name="firstName" type="text" id="firstName3" tabindex="4" size="30" maxlength="30"></td>
    </tr>
    <tr>
      <td><strong>Middle initial: </strong></td>
      <td><input name="mi" type="text" id="mi" size="5" maxlength="5" tabindex="5">      </td>
    </tr>
    <tr>
      <td><strong>Last name: <span class="style1">*</span></strong> </td>
      <td><input name="lastName" type="text" id="lastName" size="30" maxlength="30" tabindex="6" ></td>
    </tr>
    <tr>
      <td><strong>Gender: </strong></td>
      <td><b>
        <input type="radio" value="male" name="gender" tabindex="7" >
      </b>Male&nbsp;
      <input type="radio" name="gender" value="female" tabindex="8" >
Female</td>
    </tr>
    <tr>
      <td><b>Social security number:<span class="style1">*&nbsp;</span></b></td>
      <td><input type="text" name="ssnFirst" size="3" maxlength="3" tabindex="9">
-
  <input type="text" name="ssnSecond" size="2" maxlength="2" tabindex="10">
-
<input type="text" name="ssnThird" size="4" maxlength="4" tabindex="11"></td>
    </tr>
    <tr>
      <td><b>Date of Birth: <font color="#FF0000">*</font></b></td>
      <td><b>
        <input type="text" name="dobMonth" size="2" tabindex="12"  maxlength="2">
/
<input type="text" name="dobDay" size="2" tabindex="13"  maxlength="2">
/
<input type="text" name="dobYear" size="4" tabindex="14"  maxlength="4">
      </b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>
    <tr>
      <td><b>Street address:&nbsp;&nbsp;</b></td>
      <td><b>
        <input type="text" name="streetAddress" size="30" tabindex="15" maxlength="30" >
      </b></td>
    </tr>
    <tr>
      <td><b>Apartment number:&nbsp;</b></td>
      <td><b>
        <input type="text" name="apartmentNumber" size="30" tabindex="16" maxlength="30" >
      </b></td>
    </tr>
    <tr>
      <td><b>City: <font color="#FF0000">* </font></b></td>
      <td><b>
        <input type="text" name="city" size="20" tabindex="17"  maxlength="20" >
      </b></td>
    </tr>
    <tr>
      <td><b>State: <font color="#FF0000">* </font></b></td>
      <td><b>
        <input name="state" type="text" tabindex="18" value="CA" size="2" maxlength="2">
      </b></td>
    </tr>
    <tr>
      <td><b>Zip code:&nbsp;</b></td>
      <td><b>
        <input type="text" name="zipcode" size="20" tabindex="19" maxlength="20" >
      </b></td>
    </tr>
    <tr>
      <td><b>Phone number: <font color="#FF0000">*</font>&nbsp;</b></td>
      <td>(
        <input type="text" name="dpnFirst" size="3" tabindex="20"  maxlength="3">
)
<input type="text" name="dpnSecond" size="3" tabindex="21"  maxlength="3">
-
<input type="text" name="dpnThird" size="4" tabindex="22"  maxlength="4"></td>
    </tr>
    <tr>
      <td><b>Email address:&nbsp;</b></td>
      <td><b>
        <input type="text" name="email" size="30" tabindex="23" maxlength="30" >
      </b></td>
    </tr>
    <tr>
      <td><b>Legal guardian:</b><i>&nbsp;&nbsp;</i></td>
      <td>First name:    
        <input type="text" name="lgFirstName" size="30" tabindex="24" maxlength="30" ></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Middle initial:   
        <input type="text" name="lgMi" size="5" tabindex="25" maxlength="5" ></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td> Last name:    
        <input type="text" name="lgLastName" size="30" tabindex="26" maxlength="30" ></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>Contact phone number: (
        <input type="text" name="lgpnFirst" size="3" tabindex="27" maxlength="3" >
)
<input type="text" name="lgpnSecond" size="3" tabindex="28" maxlength="3" >
-
<input type="text" name="lgpnThird" size="4" tabindex="29" maxlength="4" ></td>
    </tr>
  </table>
  <p class="sidebarHeader">Insurance Information: </p>
  <table width="749" border="0">
    <tr>
      <td><strong>Admission Date: </strong></td>
      <td colspan="3"><b>
        <input type="text" name="refMonth" size="2" tabindex="30" maxlength="2" >
/
<input type="text" name="refDay" size="2" tabindex="31" maxlength="2" >
/
<input type="text" name="refYear" size="4" tabindex="32" maxlength="4"  >
      </b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>
  
    <tr>
      <td><strong>Elig Eff Date: </strong></td>
      <td colspan="3"><b>
        <input type="text" name="esdMonth" size="2" tabindex="33" maxlength="2" >
/
<input type="text" name="esdDay" size="2" tabindex="34" maxlength="2" >
/
<input type="text" name="esdYear" size="4" tabindex="35" maxlength="4"  >
      </b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>
    <tr>
      <td><strong>Elig Term Date: </strong></td>
      <td colspan="3"><b>
        <input type="text" name="eedMonth" size="2" tabindex="36" maxlength="2" >
/
<input type="text" name="eedDay" size="2" tabindex="37" maxlength="2" >
/
<input type="text" name="eedYear" size="4" tabindex="38" maxlength="4"  >
      </b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>
    <tr>
      <td width="187"><b>Insurance: <font color="#FF0000">*</font></b></td>
      <td colspan="2"><b>
        <select size="1" name="insurance" tabindex="39" >
          <option selected>- Select -</option>
          <% if (session.getAttribute("insuranceProvider") != null){
       out.print(session.getAttribute("insuranceProvider"));
     } 
  %>
        </select>
      </b></td>
    </tr>
    <tr>
      <td><b>Member ID/ PSP Number</b></td>
      <td colspan="2"><b>
        <input type="text" name="memberId" size="30" tabindex="40" maxlength="30" >
      </b></td>
    </tr>
    <tr>
      <td><b>Copay Parity:&nbsp;</b></td>
      <td colspan="2">$ <b>
      <input type="text" name="copayParity" size="5" tabindex="41" maxlength="8" >
      </b></td>
    </tr>	
	
    <tr>
      <td><b>Copay non-Parity:&nbsp;</b></td>
      <td colspan="2">$ <b>
      <input type="text" name="copay" size="5" tabindex="42" maxlength="8" >
      </b></td>
    </tr>
    <tr>
      <td><b>Insurance phone #:</b></td>
      <td colspan="2"><b> </b>(
        <input type="text" name="inspnFirst" size="3" maxlength="3" tabindex="43" >
)
<input type="text" name="inspnSecond" size="3" maxlength="3" tabindex="44" >
-
<input type="text" name="inspnThird" size="4" maxlength="4" tabindex="45" ></td>
    </tr>
    <tr>
      <td><b>MD # authorized visit:</b></td>
      <td width="133"><b>
        <input type="text" name="numAuthVisitForMD" size="5" tabindex="46" maxlength="5" >
      </b></td>
      <td width="415"><b>Auth #: 
          <input type="text" name="authNumForMD" size="30" tabindex="47" maxlength="30" >
      </b></td>
    </tr>
    <tr>
      <td><b>MA # authorized visit:&nbsp;</b></td>
      <td><b>
        <input type="text" name="numAuthVisitForMA" size="5" tabindex="48" maxlength="5" >
      </b></td>
      <td><b>Auth #:
          <input type="text" name="authNumForMA" size="30" tabindex="49" maxlength="30" >
      </b></td>
    </tr>
    <tr>
      <td><strong>Medi-Cal CIN #: </strong></td>
      <td colspan="2"><b>
        <input type="text" name="medicalId" size="30" tabindex="50" maxlength="30" >
      </b></td>
    </tr>
    <tr>
      <td><strong>Medi-Cal Issue Date: </strong></td>
      <td colspan="2"><b>
        <input type="text" name="mdMonth" size="2" tabindex="51" maxlength="2" >
/
<input type="text" name="mdDay" size="2" tabindex="52" maxlength="2" >
/
<input type="text" name="mdYear" size="4" tabindex="53" maxlength="4" >
      </b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>	
  </table>
  <p class="sidebarHeader">Medical History:</p>
  <table width="759" border="0">
    <tr>
      <td width="186"><b>Previous Psychiatrist:</b></td>
      <td width="563"><b>
        <input type="text" name="prevPsychiatrist" size="40" maxlength="65" tabindex="54" >
      </b></td>
    </tr>
    <tr>
      <td><b>Last seen:&nbsp;&nbsp;</b></td>
      <td><b>
        <input type="text" name="lsMonth" size="2" tabindex="55" maxlength="2" >
/
<input type="text" name="lsDay" size="2" tabindex="56" maxlength="2" >
/
<input type="text" name="lsYear" size="4" tabindex="57" maxlength="4" >
      </b><font size="1">(MM / DD / YYYY)</font></td>
    </tr>
    <tr>
      <td><b>Current medications:&nbsp;&nbsp;</b></td>
      <td><b>
        <input type="text" name="currMed" size="40" maxlength="100" tabindex="58" >
      </b></td>
    </tr>
    <tr>
      <td><b>How many days left?</b></td>
      <td><b>
        <input type="text" name="daysLeft" size="5" tabindex="59" >
      </b></td>
    </tr>
    <tr>
      <td><b>Previous medications:&nbsp;</b></td>
      <td><b>
        <input type="text" name="prevMed" size="40" maxlength="100" tabindex="60" >
      </b></td>
    </tr>
    <tr>
      <td><b>Previous Dx: &nbsp;</b></td>
      <td><b>
        <input type="text" name="prevDx" size="20" maxlength="100" tabindex="61" >
      </b></td>
    </tr>
    <tr>
      <td><b>Presenting problem:&nbsp;&nbsp;&nbsp;</b></td>
      <td><b>
        <textarea rows="8" name="pp" cols="50" tabindex="62"></textarea>
      </b></td>
    </tr>
    <tr>
      <td><b>Need Medical mgnt service? <font color="#FF0000">*</font></b></td>
      <td><b>
        <input type="radio" name="mmNeeded" value="true" tabindex="63" >
      </b>Yes&nbsp;&nbsp;&nbsp;
      <input type="radio" name="mmNeeded" value="false" tabindex="64" >
No</td>
    </tr>
    <tr>
      <td><b>Need Therapy service? <font color="#FF0000">*</font></b></td>
      <td><b>
        <input type="radio" name="tpNeeded" value="true" tabindex="65" >
      </b>Yes&nbsp;&nbsp;&nbsp;
      <input type="radio" name="tpNeeded" value="false" tabindex="66" >
No</td>
    </tr>
    <tr>
      <td><b>Is referral urgent? <font color="#FF0000">*</font>&nbsp;&nbsp;</b></td>
      <td><b>
        <input type="radio" name="urgent" value="yes" tabindex="67" >
      </b>Yes&nbsp;&nbsp;&nbsp;
      <input type="radio" name="urgent" value="no" tabindex="68" >
No&nbsp;&nbsp;&nbsp;
<input type="radio" name="urgent" value="48hrs" tabindex="69" >
48 hrs</td>
    </tr>
    <tr>
      <td><b>Clinic accepted:<font color="#FF0000">*</font>&nbsp;</b></td>
      <td><b>
        <select size="1" name="clinic" tabindex="70" >
          <option selected>-Select-</option>
          <% if (session.getAttribute("clinicName") != null){
     out.print(session.getAttribute("clinicName"));
   }
%>        </select>
      </b></td>
    </tr>
    <tr>
      <td><p><b>Comments:</b></p>
      <p>&nbsp;</p></td>
      <td><b>
        <textarea rows="4" name="comments" cols="50" tabindex="71"></textarea>
      </b></td>
    </tr>
    <tr>
      <td><strong>Need Translation service? <span class="style1">*</span> </strong></td>
      <td><b>
        <input type="radio" name="nts" value="true" tabindex="72" >
      </b>Yes&nbsp;&nbsp;&nbsp;
      <input type="radio" name="nts" value="false" tabindex="73" >
No</td>
    </tr>
  </table>
  <p>
    <input name="Next" type="submit" id="Next" value="Next" tabindex="74">
    <input name="Clear" type="reset" id="Clear" value="Clear" tabindex="75">
  </p>
</form>
<p>.</p>
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
