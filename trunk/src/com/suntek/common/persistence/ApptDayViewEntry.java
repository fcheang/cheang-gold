package com.suntek.common.persistence;

import java.util.*;
import java.text.DateFormat;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ApptDayViewEntry {

    public int apptId;
    public int refId;
    public Date apptDate;
    public Date createDate;
    public String lastName;
    public String firstName;
    public boolean isNew;
    public String phoneNum;
    public String ins;
    public String provider;
    public String clinic;
    public String copay;
    public String copayParity;
    public boolean isChild;
    public String lgLastName;
    public String lgFirstName;
    private String lgPhoneNum;
    public String memberId;

    private String ssn;
    private String ssnFirst;
    private String ssnSecond;
    private String ssnThird;

    private Date dob;

    private Date endTime;
    private String isUrgent;
    private boolean needTranSvc;
    private boolean collRcv;
    private String notes;
    private String status;
    
    private String medicalId;
    private Date medicalIssueDate;
    private Date eligEffDate;
    private Date eligTermDate;
    

    public Integer getApptIdInt(){
    	return new Integer(apptId);
    }
    
    public void setStatus(String s){
    	status = s;
    }
    
    public String getStatus(){
    	if (status != null){
    		return status;
    	}else{
    		return "";
    	}
    }

    public void setLgPhoneNum(String p){
        lgPhoneNum = p;
    }
    public String getLgPhoneNum(){
        if (lgPhoneNum != null){
            if (lgPhoneNum.length() == 10){
                return lgPhoneNum.substring(0, 3)+"-"+lgPhoneNum.substring(3, 6)+"-"+
                    lgPhoneNum.substring(6);
            }else if (lgPhoneNum.length() == 7){
                return lgPhoneNum.substring(0, 3)+"-"+lgPhoneNum.substring(3);
            }else{
                return lgPhoneNum;
            }
        }else{
            return "&nbsp;";
        }
    }

    public void setIsUrgent(String u){
        isUrgent = u;
    }
    public String getIsUrgent(){
        return isUrgent;
    }

    public void setNeedTranSvc(boolean b){
        needTranSvc = b;
    }
    public String getNeedTranSvc(){
        if (needTranSvc){
            return "yes";
        }else{
            return "no";
        }
    }

    public void setCollRcv(boolean b){
        collRcv = b;
    }
    public String getCollRcv(){
        if (collRcv){
            return "yes";
        }else{
            return "no";
        }
    }

    public void setApptNotes(String n){
        notes = n;
    }
    public String getApptNotes(){
        if (notes != null){
            return notes;
        }else{
            return "&nbsp;";
        }
    }

    public void setDob(Date d){
        dob = d;
    }

    public String getDob(){
        if (dob != null){
            return Model.df.format(dob);
        }else{
            return "&nbsp;";
        }
    }
    
    public void setMedicalId(String medId){
    	medicalId = medId;
    }
    
    public String getMedicalId(){
    	if (medicalId != null){
    		return medicalId;
    	}else{
    		return "&nbsp;";
    	}
    }
    
    public void setMedicalIssueDate(Date d){
    	medicalIssueDate = d;
    }
    
    public String getMedicalIssueDate(){
    	if (medicalIssueDate != null){
    		return Model.df.format(medicalIssueDate);
    	}else{
    		return "&nbsp;";
    	}
    }

    public void setEligEffDate(Date d){
    	eligEffDate = d;
    }
    
    public String getEligEffDate(){
    	if (eligEffDate != null){
    		return Model.df.format(eligEffDate);
    	}else{
    		return "&nbsp;";
    	}
    }

    public void setEligTermDate(Date d){
    	eligTermDate = d;
    }
    
    public String getEligTermDate(){
    	if (eligTermDate != null){
    		return Model.df.format(eligTermDate);
    	}else{
    		return "&nbsp;";
    	}
    }        

    public void setSsn(String aSsn){
        ssn = aSsn;
        if (ssn != null){
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

    public String getSSN(){
        if (ssnFirst != null && ssnSecond != null && ssnThird != null) {
            return ssnFirst + "-" + ssnSecond + "-" + ssnThird;
        }
        else {
            return "&nbsp;";
        }
    }

    public String getMemberId(){
        if (memberId != null){
            return memberId;
        }else{
            return "&nbsp;";
        }
    }

    public String getFullName(){
        if (lastName != null && firstName != null){
            return lastName + ", " + firstName;
        }else if (lastName != null){
            return lastName;
        }else if (firstName != null){
            return firstName;
        }else{
            return "&nbsp;";
        }
    }

    public String getCopay(){
        if (copay != null){
            return "$"+copay;
        }else{
            return "&nbsp;";
        }
    }

    public String getCopayParity(){
        if (copayParity != null){
            return "$"+copayParity;
        }else{
            return "&nbsp;";
        }
    }
    
    public String getPhoneNum(){
        if (phoneNum != null){
            if (phoneNum.length() == 10){
                return phoneNum.substring(0, 3)+"-"+phoneNum.substring(3, 6)+"-"+
                    phoneNum.substring(6);
            }else if (phoneNum.length() == 7){
                return phoneNum.substring(0, 3)+"-"+phoneNum.substring(3);
            }else{
                return phoneNum;
            }
        }else{
            return "&nbsp;";
        }
    }

    public String getLgFullName(){
        if (lgLastName != null && lgFirstName != null){
            return lgLastName+", "+lgFirstName;
        }else if (lgLastName != null){
            return lgLastName;
        }else if (lgFirstName != null){
            return lgFirstName;
        }else{
            return "&nbsp;";
        }
    }

    public String getDoctor(){
        if (this.provider != null){
            return provider;
        }else{
            return "&nbsp;";
        }
    }

    public String getIsChild(){
        if (dob == null){
            return "?";
        }else{
            Calendar today = new GregorianCalendar();
            Calendar dobCal = new GregorianCalendar();
            dobCal.setTime(dob);
            dobCal.add(Calendar.YEAR, 18);

            today.set(Calendar.HOUR, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);
            dobCal.set(Calendar.HOUR, 0);
            dobCal.set(Calendar.MINUTE, 0);
            dobCal.set(Calendar.SECOND, 0);
            dobCal.set(Calendar.MILLISECOND, 0);

            if (compareTo(dobCal, today) <= 0){
                // dob + 18 years is before or equal to today
                return "no";
            }else{
                return "yes";
            }
        }
    }

    public int compareTo(Calendar cal1, Calendar cal2) {
        Calendar c1 = (Calendar)cal1.clone();
        Calendar c2 = (Calendar)cal2.clone();
        c1.setLenient(true);
        c2.setLenient(true);
        long thisTime = c1.getTimeInMillis();
        long t = c2.getTimeInMillis();
        return (thisTime > t) ? 1 : (thisTime == t) ? 0 : -1;
    }

    public String getIsNew(){
        Model m = Model.getInstance();
        if (m.isNewPatient(refId, createDate)){
            return "yes";
        }else{
            return "no";
        }
    }

    public void setEndTime(Date d){
        endTime = d;
    }
    public String getEndTime(){
        int ad_hour = getHour(endTime); // 24 hr
        int ad_min = getMin(endTime);
        String ad_ampm = getAMPM(endTime);
        // convert to 12 hr
        if (ad_hour == 0 && ad_ampm.equals("AM")){
            ad_hour = 12;
        }
        if (ad_hour > 12 && ad_ampm.equals("PM")){
            ad_hour -= 12;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(ad_hour).append(":");
        if (ad_min == 0){
            sb.append("00");
        }else{
            sb.append(ad_min);
        }
        sb.append(" ").append(ad_ampm);
        return sb.toString();
    }

    public String getApptTime(){
        int ad_hour = getHour(apptDate); // 24 hr
        int ad_min = getMin(apptDate);
        String ad_ampm = getAMPM(apptDate);
        // convert to 12 hr
        if (ad_hour == 0 && ad_ampm.equals("AM")){
            ad_hour = 12;
        }
        if (ad_hour > 12 && ad_ampm.equals("PM")){
            ad_hour -= 12;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(ad_hour).append(":");
        if (ad_min == 0){
            sb.append("00");
        }else{
            sb.append(ad_min);
        }
        sb.append(" ").append(ad_ampm);
        return sb.toString();
    }

    private int getHour(Date date){
        int val = 0;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        val = cal.get(Calendar.HOUR_OF_DAY);
        return val;
    }

    private int getMin(Date date){
        int val = 0;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        val = cal.get(Calendar.MINUTE);
        return val;
    }

    private String getAMPM(Date date){
        int ampm = 0;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        ampm = cal.get(Calendar.AM_PM);
        if (ampm == Calendar.AM){
            return "AM";
        }else{
            return "PM";
        }
    }

}
