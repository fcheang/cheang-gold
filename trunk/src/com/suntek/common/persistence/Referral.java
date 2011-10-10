package com.suntek.common.persistence;

import java.math.*;
import java.text.*;
import java.util.*;



public class Referral {

    private int refId;

    // general info
    private String firstName;
    private String mi;
    private String lastName;
    private String gender;
    private String ssnFirst;
    private String ssnSecond;
    private String ssnThird;
    private String ssn;
    private int rdMonth;
    private int rdDate;
    private int rdYear;
    private int dobMonth;
    private int dobDay;
    private int dobYear;
    private String streetAddress;
    private String apartmentNumber;
    private String city;
    private String state;
    private String zipcode;
    private String dpnFirst;
    private String dpnSecond;
    private String dpnThird;
    private String email;
    private String lgFirstName;
    private String lgMi;
    private String lgLastName;
    private String lgpnFirst;
    private String lgpnSecond;
    private String lgpnThird;
    private String userId;

    // insurance info
    private int insId;
    private int refMonth;
    private int refDay;
    private int refYear;    
    private int esdMonth;
    private int esdDay;
    private int esdYear;
    private int eedMonth;
    private int eedDay;
    private int eedYear;
    private String insurance;
    private String memberId;
    private String copay;
    private String copayParity;    
    private String inspnFirst;
    private String inspnSecond;
    private String inspnThird;
    private String authNumForMD;
    private String authNumForMA;
    private int numAuthVisitForMD;
    private int numAuthVisitForMA;
    private String medicalId;
    private int mdMonth;
    private int mdDay;
    private int mdYear;
    private String insNotes;

    // medical history
    private String prevPsychiatrist;
    private int lsMonth;
    private int lsDay;
    private int lsYear;
    private String currMed;
    private int daysLeft;
    private String prevMed;
    private String prevDx;
    private String pp;
    private boolean mmNeeded;
    private boolean tpNeeded;
    private String urgent;
    private String clinic;
    private String comments;
    private boolean nts;

    // referral current status
    private String status;
    private boolean isActive;
    private Date createDate;
    private Date removeDate;
    private String notes;

    private DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
    private DateFormat dtf = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

    public Referral(){
    }
        
    /** 
     * Use to get around the jsp:setProperty problem of not setting
     * empty field.
     */
    public void setReset(String dummy){
    	mi = null;
    	streetAddress = null;
    	apartmentNumber = null;
    	zipcode = null;
    	email = null;
    	lgFirstName = null;
    	lgLastName = null;
    	lgpnFirst = null;
    	lgpnSecond = null;
    	lgpnThird = null;
    	memberId = null;
    	copayParity = null;
    	copay = null;
    	inspnFirst = null;
    	inspnSecond = null;
    	inspnThird = null;
    	authNumForMD = null;
    	authNumForMA = null;    	
    	medicalId = null;
    	prevPsychiatrist = null;
    	currMed = null;
    	prevMed = null;
    	prevDx = null;
    	pp = null;
    	comments = null;
    }
    
    public String getInsNotes(){
    	return insNotes;
    }
        
    public void setInsNotes(String n){
    	insNotes = n;
    }
    
    public String getStatus(){
    	if (status != null){
    		return status;
    	}else{
    		return "";
    	}
    }
    public void setStatus(String s){
    		status = s;
    }
    public boolean getIsActive(){
        return isActive;
    }
    public void setIsActive(boolean a){
        isActive = a;
    }
    public String getCreateDate(){
        if (createDate != null)
            return dtf.format(createDate);
        else
            return "";
    }
    public Date getCreateDateForDB(){
        return createDate;
    }
    public void setCreateDate(Date d){
    		createDate = d;
    }
    public Date getRemoveDateForDB(){
        return removeDate;
    }
    public String getRemoveDate(){
        if (removeDate != null)
            return dtf.format(removeDate);
        else
            return "";
    }
    public void setRemoveDate(Date d){
        removeDate = d;
    }
    public String getNotes(){
    	if (notes != null)
    		return notes;
    	else
    		return "";
    }
    public void setNotes(String n){
    	if (n != null){
    		notes = n;
    	}else{
    		notes = "";
    	}
    }

    private String getFirst(String pn){
        if (pn.length() == 10){
            return pn.substring(0, 3);
        }else{
            return null;
        }
    }

    private String getSecond(String pn){
        if (pn.length() == 10){
            return pn.substring(3, 6);
        }else if (pn.length() == 7){
            return pn.substring(0, 3);
        }else{
            return null;
        }
    }

    private String getThird(String pn){
        if (pn.length() == 10){
            return pn.substring(6, 10);
        }else if (pn.length() == 7){
            return pn.substring(3, 7);
        }else{
            return pn;
        }
    }

    private int getMonth(Date date){
        int val = 0;
        // gc is zero based
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        val = cal.get(Calendar.MONTH);
        return val + 1;
    }

    private int getDate(Date date){
        int val = 0;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        val = cal.get(Calendar.DATE);
        return val;
    }

    private int getYear(Date date){
        int val = 0;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        val = cal.get(Calendar.YEAR);
        return val;
    }

    public int getRefId(){
        return refId;
    }

    // general info
    public String getFirstName(){
        return firstName;
    }

    public String getMi(){
    	if (mi != null)
    		return mi;
    	else
    		return "";
    }

    public String getLastName(){
        return lastName;
    }

    public String getFullName(){
        String fullName = null;
        if (firstName != null){
            fullName = firstName + " ";
        }
        if (mi != null){
            if (fullName == null)
                fullName = mi + " ";
            else
                fullName += mi + " ";
        }
        if (lastName != null){
            if (fullName == null)
                fullName = lastName + " ";
            else
                fullName += lastName + " ";
        }
        return fullName;
    }

    public String getGender(){
        return gender;
    }

    public String getSsnFirst(){
        return ssnFirst;
    }
    public String getSsnSecond(){
        return ssnSecond;
    }
    public String getSsnThird(){
        return ssnThird;
    }

    public String getSsnForDB(){
        if (ssnFirst != null && ssnSecond != null && ssnThird != null){
            return ssnFirst + ssnSecond + ssnThird;
        }else{
            return null;
        }
    }
    public String getSsn(){
        if (ssnFirst != null && ssnSecond != null && ssnThird != null)
            return ssnFirst+"-"+ssnSecond+"-"+ssnThird;
        else
            return null;
    }
    public void setSsnFirst(String sf){
        ssnFirst = sf;
    }
    public void setSsnSecond(String ss){
        ssnSecond = ss;
    }
    public void setSsnThird(String st){
        ssnThird = st;
    }

    public int getRdMonth(){
        return rdMonth;
    }

    public int getRdDate(){
        return rdDate;
    }

    public int getRdYear(){
        return rdYear;
    }

    public int getDobMonth(){
        return dobMonth;
    }

    public int getDobDay(){
        return dobDay;
    }

    public int getDobYear(){
        return dobYear;
    }

    public String getRd(){
        Date rd = null;
        if (rdYear != 0){
            rd = new GregorianCalendar(rdYear, rdMonth - 1, rdDate).getTime();
        }
        if (rd != null){
            return df.format(rd);
        }else{
            return null;
        }
    }

    public String getDob(){
        Date dob = null;
        if (dobYear != 0){
            dob = new GregorianCalendar(dobYear, dobMonth-1, dobDay).getTime();
        }
        if (dob !=  null){
            return df.format(dob);
        }else{
            return null;
        }
    }

    public Date getRdForDB(){    	
        Date rd = null;
        if (rdYear != 0){
            rd = new GregorianCalendar(rdYear, rdMonth - 1, rdDate).getTime();
        }
        return rd;
    }

    public Date getDobForDB(){
        Date dob = null;
        if (dobYear != 0){
            dob = new GregorianCalendar(dobYear, dobMonth - 1, dobDay).getTime();
        }
        return dob;
    }

    public boolean isChild(){
    	return getAgeGroup().equals("Child");
    }
    
    public String getAgeGroup(){
        boolean isChild = false;
        Calendar dob = null;
        if (dobYear != 0){
            dob = new GregorianCalendar(dobYear, dobMonth - 1, dobDay);
        }
        Calendar today = new GregorianCalendar();
        int yearDiff = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        int monthDiff = today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
        int dateDiff = today.get(Calendar.DATE) - dob.get(Calendar.DATE);
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
            return "Child";
        }else{
            return "Adult";
        }
    }

    public String getStreetAddress(){
    	if (streetAddress != null)
    		return streetAddress;
    	else
    		return "";
    }

    public String getApartmentNumber(){
    	if (apartmentNumber != null)
    		return apartmentNumber;
    	else
    		return "";
    }

    public String getCity(){
    	if (city != null)
    		return city;
    	else
    		return "";
    }

    public String getState(){
    	if (state != null)
    		return state;
    	else
    		return "";
    }

    public String getZipcode(){
    	if (zipcode != null)
    		return zipcode;
    	else
    		return "";
    }

    public String getFullAddress(){
        String fullAddress = null;
        if (streetAddress != null){
            fullAddress = streetAddress + "<br>";
        }
        if (apartmentNumber != null){
            if (fullAddress == null)
                fullAddress = apartmentNumber + "<br>";
            else
                fullAddress += apartmentNumber + "<br>";
        }
        if (city != null){
            if (fullAddress == null)
                fullAddress = city + " ";
            else
                fullAddress += city + " ";
        }
        if (state != null){
            if (fullAddress == null)
                fullAddress = state + " ";
            else
                fullAddress += state + " ";
        }
        if (zipcode != null){
            if (fullAddress == null)
                fullAddress = zipcode + "<br>";
            else
                fullAddress += zipcode + "<br>";
        }
        return fullAddress;
    }


    public String getDpnFirst(){
    	if (dpnFirst != null)
    		return dpnFirst;
    	else
    		return "";
    }

    public String getDpnSecond(){
    	if (dpnSecond != null)
    		return dpnSecond;
    	else
    		return "";
    }

    public String getDpnThird(){
    	if (dpnThird != null)
    		return dpnThird;
    	else
    		return "";
    }

    public String getDpnForDB(){
        String retVal = null;
        if (dpnFirst != null){
            retVal = dpnFirst;
        }
        if (dpnSecond != null){
            if (retVal == null)
                retVal = dpnSecond;
            else
                retVal += dpnSecond;
        }
        if (dpnThird != null){
            if (retVal == null)
                retVal = dpnThird;
            else
                retVal += dpnThird;
        }
        return retVal;
    }

    public String getDpn(){
        String dpn = null;
        if (dpnFirst != null){
            dpn = dpnFirst + "-";
        }
        if (dpnSecond != null){
            if (dpn == null)
                dpn = dpnSecond + "-";
            else
                dpn += dpnSecond + "-";
        }
        if (dpnThird != null){
            if (dpn == null)
                dpn = dpnThird;
            else
                dpn += dpnThird;
        }
        return dpn;
    }

    public String getEmail(){
    	if (email != null)
    		return email;
    	else
    		return "";
    }

    public String getLgFirstName(){
    	if (lgFirstName != null)
    		return lgFirstName;
    	else
    		return "";
    }

    public String getLgMi(){
    	if (lgMi != null)
    		return lgMi;
    	else
    		return "";
    }

    public String getLgLastName(){
    	if (lgLastName != null)
    		return lgLastName;
    	else
    		return "";
    }

    public String getLgFullName(){
        String fullName = "";
        if (lgFirstName != null){
            fullName = lgFirstName + " ";
        }
        if (lgMi != null){
            if (fullName == null)
                fullName = lgMi;
            else
                fullName += lgMi + " ";
        }
        if (lgLastName != null){
            if (fullName == null)
                fullName = lgLastName;
            else
                fullName += lgLastName + " ";
        }
        return fullName;
    }

    public String getLgpnFirst(){
    	if (lgpnFirst != null)
    		return lgpnFirst;
    	else
    		return "";
    }

    public String getLgpnSecond(){
    	if (lgpnSecond != null)
    		return lgpnSecond;
    	else
    		return "";
    }

    public String getLgpnThird(){
    	if (lgpnThird != null)
    		return lgpnThird;
    	else
    		return "";
    }

    public String getLgpnForDB(){
        String retVal = null;
        if (lgpnFirst != null){
            retVal = lgpnFirst;
        }
        if (lgpnSecond != null){
            if (retVal == null)
                retVal = lgpnSecond;
            else
                retVal += lgpnSecond;
        }
        if (lgpnThird != null){
            if (retVal == null)
                retVal = lgpnThird;
            else
                retVal += lgpnThird;
        }
        return retVal;
    }

    public String getLgpn(){
        String lgpn = "";
        if (lgpnFirst != null){
            lgpn = lgpnFirst + "-";
        }
        if (lgpnSecond != null){
            if (lgpn == null)
                lgpn = lgpnSecond + "-";
            else
                lgpn += lgpnSecond + "-";
        }
        if (lgpnThird != null){
            if (lgpn == null)
                lgpn = lgpnThird;
            else
                lgpn += lgpnThird;
        }
        return lgpn;
    }

    // insurance info
    public String getInsurance(){
    	if (insurance != null)
    		return insurance;
    	else
    		return "";
    }

    public String getInsuranceList(){
        StringBuffer sb = new StringBuffer("<option>-Select-</option>");
        List allIns = Model.getInstance().getAllInsuranceProvider();
        InsuranceProvider ins = null;
        for (int i=0; i<allIns.size(); i++){
            ins = (InsuranceProvider)allIns.get(i);
            if (ins.name.equalsIgnoreCase(this.insurance)){
                sb.append("<option selected>").append(ins.name).append("</option>");
            }else{
                sb.append("<option>").append(ins.name).append("</option>");
            }
        }
        return sb.toString();
    }

    public String getMemberId(){
    	if (memberId != null)
    		return memberId;
    	else
    		return "";
    }

    public String getCopay(){
        if (copay != null){
            return "$"+copay;
        }else{
            return "";
        }
    }

    public String getCopayStr(){
        if (copay != null){
            return copay;
        }else{
            return "";
        }
    }

    public BigDecimal getCopayForBD(){
        BigDecimal copayBD = null;
        if (copay != null){
            copayBD = new BigDecimal(copay);
        }
        return copayBD;
    }

    public String getCopayParity(){
        if (copayParity != null){
            return "$"+copayParity;
        }else{
            return "";
        }
    }

    public String getCopayParityStr(){
        if (copayParity != null){
            return copayParity;
        }else{
            return "";
        }
    }

    public BigDecimal getCopayParityForBD(){
        BigDecimal copayParityBD = null;
        if (copayParity != null){
            copayParityBD = new BigDecimal(copayParity);
        }
        return copayParityBD;
    }
    
    public String getInspnFirst(){
    	if (inspnFirst != null)
    		return inspnFirst;
    	else
    		return "";
    }

    public String getInspnSecond(){
    	if (inspnSecond != null)
    		return inspnSecond;
    	else
    		return "";
    }

    public String getInspnThird(){
    	if (inspnThird != null)
    		return inspnThird;
    	else
    		return "";
    }

    public String getInspnForDB(){
        String retVal = null;
        if (inspnFirst != null && !inspnFirst.equals("")){
            retVal = inspnFirst;
        }
        if (inspnSecond != null && !inspnSecond.equals("")){
            if (retVal == null)
                retVal = inspnSecond;
            else
                retVal += inspnSecond;
        }
        if (inspnThird != null && !inspnThird.equals("")){
            if (retVal == null)
                retVal = inspnThird;
            else
                retVal += inspnThird;
        }
        return retVal;
    }

    public String getInspn(){
        String inspn = "";
        if (inspnFirst != null && !inspnFirst.equals("")){
            inspn = inspnFirst + "-";
        }
        if (inspnSecond != null && !inspnSecond.equals("")){
            if (inspn == null)
                inspn = inspnSecond + "-";
            else
                inspn += inspnSecond + "-";
        }
        if (inspnThird != null && !inspnThird.equals("")){
            if (inspn == null)
                inspn = inspnThird;
            else
                inspn += inspnThird;
        }
        return inspn;
    }

    public String getAuthNumForMD(){
    	if (authNumForMD != null)
    		return authNumForMD;
    	else
    		return "";
    }
    
    public String getAuthNumForMA(){
    	if (authNumForMA != null)
    		return authNumForMA;
    	else
    		return "";
    }    

    public int getNumAuthVisitForMD(){
        return numAuthVisitForMD;
    }

    public int getNumAuthVisitForMA(){
        return numAuthVisitForMA;
    }
    
    public String getMedicalId(){
    	if (medicalId != null)
    		return medicalId;
    	else
    		return "";
    }
    
    // MedicalIssueDate
    
    public int getMdMonth(){
    	return mdMonth;
    }
    
    public int getMdDay(){
    	return mdDay;
    }
    
    public int getMdYear(){
    	return mdYear;
    }
    
    public Date getMedicalIssueDateForDB(){
    	if (mdYear != 0){
    		return new GregorianCalendar(mdYear, mdMonth - 1, mdDay).getTime();
    	}else{
    		return null;
    	}
    }
    
    public String getMedicalIssueDate(){
        if (mdYear != 0){
            Date gc = new GregorianCalendar(mdYear, mdMonth - 1, mdDay).getTime();
            return df.format(gc);
        }else{
            return "";
        }
    }
        
    // ReferralDate
    
    public int getRefMonth(){
    	return refMonth;
    }
    
    public int getRefDay(){
    	return refDay;
    }
    
    public int getRefYear(){
    	return refYear;
    }
    
    public Date getRefDateForDB(){
    	if (refYear != 0){
    		return new GregorianCalendar(refYear, refMonth - 1, refDay).getTime();
    	}else{
    		return null;
    	}
    }
    
    public String getRefDate(){
        if (refYear != 0){
            Date gc = new GregorianCalendar(refYear, refMonth - 1, refDay).getTime();
            return df.format(gc);
        }else{
            return "";
        }
    }
    
    
    // EligEffDate
    
    public int getEsdMonth(){
    	return esdMonth;
    }
    
    public int getEsdDay(){
    	return esdDay;
    }
    
    public int getEsdYear(){
    	return esdYear;
    }
    
    public Date getEligEffDateForDB(){
    	if (esdYear != 0){
    		return new GregorianCalendar(esdYear, esdMonth - 1, esdDay).getTime();
    	}else{
    		return null;
    	}
    }
    
    public String getEligEffDate(){
        if (esdYear != 0){
            Date gc = new GregorianCalendar(esdYear, esdMonth - 1, esdDay).getTime();
            return df.format(gc);
        }else{
            return "";
        }
    }
    
    // Elig Term Date
    
    public int getEedMonth(){
    	return eedMonth;
    }
    
    public int getEedDay(){
    	return eedDay;
    }
    
    public int getEedYear(){
    	return eedYear;
    }
    
    public Date getEligTermDateForDB(){
    	if (eedYear != 0){
    		return new GregorianCalendar(eedYear, eedMonth - 1, eedDay).getTime();
    	}else{
    		return null;
    	}
    }
    
    public String getEligTermDate(){
        if (eedYear != 0){
            Date gc = new GregorianCalendar(eedYear, eedMonth - 1, eedDay).getTime();
            return df.format(gc);
        }else{
            return "";
        }
    }
    
    
    
    // medical history
    public String getPrevPsychiatrist(){
    	if (prevPsychiatrist != null)
    		return prevPsychiatrist;
    	else
    		return "";
    }

    public int getLsMonth(){
        return lsMonth;
    }

    public int getLsDay(){
        return lsDay;
    }

    public int getLsYear(){
        return lsYear;
    }

    public Date getLastSeenForDB(){
        if (lsYear != 0){
            return new GregorianCalendar(lsYear, lsMonth - 1, lsDay).getTime();
        }else{
            return null;
        }
    }

    public String getLastSeen(){
        if (lsYear != 0){
            Date gc = new GregorianCalendar(lsYear, lsMonth - 1, lsDay).getTime();
            return df.format(gc);
        }else{
            return "";
        }
    }

    public String getCurrMed(){
    	if (currMed != null)
    		return currMed;
    	else
    		return "";
    }

    public int getDaysLeft(){
        return daysLeft;
    }

    public String getPrevMed(){
    	if (prevMed != null)
    		return prevMed;
    	else
    		return "";
    }

    public String getPrevDx(){
    	if (prevDx != null)
    		return prevDx;
    	else
    		return "";
    }

    public String getPp(){
    	if (pp != null)
    		return pp;
    	else
    		return "";
    }

    public boolean getMmNeeded(){
        return mmNeeded;
    }

    public boolean getTpNeeded(){
        return tpNeeded;
    }
    
    public boolean getNts(){
    	return nts;
    }

    public String getUrgent(){
    	if (urgent != null)
    		return urgent;
    	else
    		return "";
    }

    public String getClinic(){
    	if (clinic != null)
    		return clinic;
    	else
    		return "";
    }

    public String getClinicList(){
        StringBuffer sb = new StringBuffer("<option>-Select-</option>");
        List allClinic = Model.getInstance().getAllClinic();
        Clinic c = null;
        for (int i=0; i<allClinic.size(); i++){
            c = (Clinic)allClinic.get(i);
            if (c.getName().equalsIgnoreCase(this.clinic)){
                sb.append("<option selected>").append(c.getName()).append("</option>");
            }else{
                sb.append("<option>").append(c.getName()).append("</option>");
            }
        }
        return sb.toString();
    }

    public String getComments(){
    	if (comments != null)
    		return comments;
    	else
    		return "";
    }

    public void setRefId(int r){
        refId = r;
    }

    // general info
    public void setFirstName(String fn){
        firstName = fn;
    }
    public void setMi(String aMi){
    		mi = aMi;
    }
    public void setLastName(String ln){
        lastName = ln;
    }
    public void setGender(String g){
        gender = g;
    }
    public void setSsn(String aSsn){
        ssn = aSsn;
        if (ssn != null && !ssn.equals("")){
            int len = ssn.length();
            if (len < 4){
                ssnFirst = ssn;
            }else{
                if (len < 6){
                    ssnFirst = ssn.substring(0, 3);
                    ssnSecond = ssn.substring(3);
                }else{
                    // len > = 6
                    ssnFirst = ssn.substring(0, 3);
                    ssnSecond = ssn.substring(3, 5);
                    ssnThird = ssn.substring(5);
                }
            }
        }
    }

    public void setRdMonth(int m){
        rdMonth = m;
    }

    public void setRdDate(int d){
        rdDate = d;
    }

    public void setRdYear(int y){
        rdYear = y;
    }

    public void setRd(Date rd){
        if (rd != null){
            createDate = rd;        	
            rdMonth = getMonth(rd);
            rdDate = getDate(rd);
            rdYear = getYear(rd);
        }
    }

    public void setDobMonth(int m){
        dobMonth = m;
    }
    public void setDobDay(int d){
        dobDay = d;
    }
    public void setDobYear(int y){
        dobYear = y;
    }
    public void setDob(Date dob){
        if (dob != null){
            dobMonth = getMonth(dob);
            dobDay = getDate(dob);
            dobYear = getYear(dob);
        }
    }
    public void setStreetAddress(String a){
        streetAddress = a;
    }
    public void setApartmentNumber(String a){
    		apartmentNumber = a;
    }
    public void setCity(String c){
    		city = c;
    }
    public void setState(String s){
        state = s;
    }
    public void setZipcode(String z){
        zipcode = z;
    }
    public void setDpnFirst(String f){
        dpnFirst = f;
    }
    public void setDpnSecond(String s){
        dpnSecond = s;
    }
    public void setDpnThird(String t){
        dpnThird = t;
    }
    public void setDpn(String pn){
        if (pn != null && !pn.equals("")){
            this.dpnFirst = getFirst(pn);
            this.dpnSecond = getSecond(pn);
            this.dpnThird = getThird(pn);
        }
    }
    public void setEmail(String e){
        email = e;
    }
    public void setLgFirstName(String f){
        lgFirstName = f;
    }
    public void setLgMi(String m){
        lgMi = m;
    }
    public void setLgLastName(String l){
        lgLastName = l;
    }
    public void setLgpnFirst(String f){
        lgpnFirst = f;
    }
    public void setLgpnSecond(String s){
        lgpnSecond = s;
    }
    public void setLgpnThird(String t){
        lgpnThird = t;
    }
    public void setLgpn(String l){
        if (l != null && !l.equals("")){
            this.lgpnFirst = getFirst(l);
            this.lgpnSecond = getSecond(l);
            this.lgpnThird = getThird(l);
        }
    }

    // insurance info
    public void setInsId(int id){
    	insId = id;
    }
    
    public int getInsId(){
    	return insId;
    }
    
    public void setInsurance(String i){
        insurance = i;
    }
    public void setMemberId(String m){
        memberId = m;
    }
    public void setCopay(String cp){
        copay = cp;
    }
    public void setCopayBD(BigDecimal c){
        if (c != null){
            copay = c.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
    }
    public void setCopayParity(String cp){
        copayParity = cp;
    }
    public void setCopayParityBD(BigDecimal c){
        if (c != null){
            copayParity = c.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
    }    
    public void setInspnFirst(String f){
        inspnFirst = f;
    }
    public void setInspnSecond(String s){
        inspnSecond = s;
    }
    public void setInspnThird(String t){
        inspnThird = t;
    }
    public void setInspn(String i){
        if (i != null && !i.equals("")){
            this.inspnFirst = getFirst(i);
            this.inspnSecond = getSecond(i);
            this.inspnThird = getThird(i);
        }
    }
    public void setAuthNumForMD(String an){
    		authNumForMD = an;
    }
    public void setAuthNumForMA(String an){
		authNumForMA = an;
    }    
    public void setNumAuthVisitForMD(int av){
        numAuthVisitForMD = av;
    }
    public void setNumAuthVisitForMA(int av){
        numAuthVisitForMA = av;
    }
    public void setMedicalId(String m){
    	medicalId = m;
    }
    
    // MedicalIssueDate
    public void setMdMonth(int m){
    	mdMonth = m;
    }
    public void setMdDay(int d){
    	mdDay = d;
    }
    public void setMdYear(int y){
    	mdYear = y;
    }
    public void setMedicalIssueDate(Date issueDate){
    	if (issueDate != null){
    		mdMonth = getMonth(issueDate);
    		mdDay = getDate(issueDate);
    		mdYear = getYear(issueDate);
    	}
    }

    
    // RefDate
    public void setRefMonth(int m){
    	refMonth = m;
    }
    public void setRefDay(int d){
    	refDay = d;
    }
    public void setRefYear(int y){
    	refYear = y;
    }
    public void setRefDate(Date date){
    	if (date != null){
    		refMonth = getMonth(date);
    		refDay = getDate(date);
    		refYear = getYear(date);
    	}
    }
    
    
    // EligEffDate
    public void setEsdMonth(int m){
    	esdMonth = m;
    }
    public void setEsdDay(int d){
    	esdDay = d;
    }
    public void setEsdYear(int y){
    	esdYear = y;
    }
    public void setEligEffDate(Date date){
    	if (date != null){
    		esdMonth = getMonth(date);
    		esdDay = getDate(date);
    		esdYear = getYear(date);
    	}
    }
    
    // EligTermDate
    public void setEedMonth(int m){
    	eedMonth = m;
    }
    public void setEedDay(int d){
    	eedDay = d;
    }
    public void setEedYear(int y){
    	eedYear = y;
    }
    public void setEligTermDate(Date date){
    	if (date != null){
    		eedMonth = getMonth(date);
    		eedDay = getDate(date);
    		eedYear = getYear(date);
    	}
    }    
    

    // medical history
    public void setPrevPsychiatrist(String p){
        prevPsychiatrist = p;
    }
    public void setLsMonth(int m){
        lsMonth = m;
    }
    public void setLsDay(int d){
        lsDay = d;
    }
    public void setLsYear(int y){
        lsYear = y;
    }
    public void setLastSeen(Date ls){
        if (ls != null){
            lsMonth = getMonth(ls);
            lsDay = getDate(ls);
            lsYear = getYear(ls);
        }
    }
    public void setCurrMed(String c){
        currMed = c;
    }
    public void setDaysLeft(int d){
        daysLeft = d;
    }
    public void setPrevMed(String p){
        prevMed = p;
    }
    public void setPrevDx(String p){
        prevDx = p;
    }
    public void setPp(String p){
        pp = p;
    }
    public void setMmNeeded(boolean m){
        mmNeeded = m;
    }
    public void setTpNeeded(boolean t){
        tpNeeded = t;
    }
    public void setUrgent(String u){
        urgent = u;
    }
    public void setClinic(String c){
    		clinic = c;
    }
    public void setComments(String c){
    		comments = c;
    }
    public void setNts(boolean b){
    	nts = b;
    }
    
    
    public void setUserId(String u){
        userId = u;
    }

    public String getUserId(){
        return userId;
    }

    public String toString(){
        String s = "";
        s+="refId="+refId+",";
        // general info
        s+="firstName="+firstName+",";
        s+="mi="+mi+",";
        s+="lastName="+lastName+",";
        return s;
    }

}