package com.suntek.common.persistence;

import java.text.*;
import java.util.*;

/**
 * Java Bean for Appointment
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class Appointment {

    private int refId;
    private String clinic;
    private String provider;
    private boolean needTranslationSvc;
    private boolean collateralReceived;
    private String notes;

    private int ad_month;
    private int ad_date;
    private int ad_year;
    private int ad_hour;
    private int ad_min;
    private int ad_sec;
    public String ad_ampm;

    private DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
    private DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);

    private String userId;
    private String status;
    private String reasonCode;

    private String firstName;
    private String lastName;
    private String mi;

    public Appointment(){
    }

    public void setStatus(String s){
      status = s;
    }
    public void setReasonCode(String r){
        reasonCode = r;
    }
    public String getStatus(){
      return status;
    }

    public String getReasonCode(){
        if (reasonCode != null){
            return reasonCode;
        }else{
            return "&nbsp;";
        }
    }

    public int getRefId(){
        return refId;
    }

    public void setRefId(int i){
        refId = i;
    }

    public String getClinic(){
        return clinic;
    }

    public String getClinicList(){
        StringBuffer sb = new StringBuffer();
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

    public void setClinic(String c){
        clinic = c;
    }

    public String getApptDateStr(){
        GregorianCalendar cal = new GregorianCalendar(ad_year, ad_month - 1, ad_date);
        return df.format(cal.getTime());
    }

    public String getApptTimeStr(){
        int hour24 = ad_hour;
        // convert to 24 hr clock
        if (ad_hour == 12 && ad_ampm.equals("AM")){
            hour24 = 0;
        }
        if (ad_hour != 12 && ad_ampm.equals("PM")){
            hour24 += 12;
        }

        GregorianCalendar cal = new GregorianCalendar(ad_year, ad_month - 1, ad_date,
            hour24, ad_min, ad_sec);
        return tf.format(cal.getTime());
    }

    public Date getApptDate(){
        int hour24 = ad_hour;
        // convert to 24 hr clock
        if (ad_hour == 12 && ad_ampm.equals("AM")){
            hour24 = 0;
        }
        if (ad_hour != 12 && ad_ampm.equals("PM")){
            hour24 += 12;
        }
        GregorianCalendar cal = new GregorianCalendar(ad_year, ad_month - 1, ad_date,
            hour24, ad_min, ad_sec);
        return cal.getTime();
    }

    public void setApptDate(Date ad){
        if (ad != null){
            ad_year = getYear(ad);
            ad_month = getMonth(ad);
            ad_date = getDate(ad);
            ad_hour = getHour(ad); // 24 hr
            ad_min = getMin(ad);
            ad_ampm = getAMPM(ad);
            // convert to 12 hr
            if (ad_hour == 0 && ad_ampm.equals("AM")){
                ad_hour = 12;
            }
            if (ad_hour > 12 && ad_ampm.equals("PM")){
                ad_hour -= 12;
            }
        }
    }

    public int getAdYear(){
        return ad_year;
    }

    public void setAdYear(int y){
        ad_year = y;
    }

    public int getAdMonth(){
        return ad_month;
    }

    public void setAdMonth(int m){
        ad_month = m;
    }

    public int getAdDate(){
        return ad_date;
    }

    public void setAdDate(int d){
        ad_date = d;
    }

    public int getAdHour(){
        return ad_hour;
    }

    public void setAdHour(int h){
        ad_hour = h;
    }

    public int getAdMin(){
        return ad_min;
    }

    public void setAdMin(int m){
        ad_min = m;
    }

    public String getAdAMPM(){
        if (ad_ampm != null && ad_ampm.equals("AM")){
            return "<option selected>AM</option><option>PM</option>";
        }else{
            return "<option>AM</option><option selected>PM</option>";
        }
    }

    public void setAdAMPM(String ampm){
        ad_ampm = ampm;
    }

    public String getProvider(){
        return provider;
    }

    public void setProvider(String p){
        provider = p;
    }

    public boolean getCollateralReceived(){
        return collateralReceived;
    }

    public void setCollateralReceived(boolean c){
        collateralReceived = c;
    }

    public boolean getNeedTranslationSvc(){
        return needTranslationSvc;
    }

    public void setNeedTranslationSvc(boolean n){
        needTranslationSvc = n;
    }

    public String getNotes(){
        return notes;
    }

    public void setNotes(String n){
        notes = n;
    }

    public void setUserId(String u){
        userId = u;
    }

    public String getUserId(){
        return userId;
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

    public void setLastName(String ln){
        lastName = ln;
    }

    public void setFirstName(String fn){
        firstName = fn;
    }

    public void setMi(String m){
        mi = m;
    }

    public String getFullName(){
        StringBuffer sb = new StringBuffer();
        if (firstName != null){
            sb.append(firstName).append(" ");
        }
        if (mi != null){
            sb.append(mi).append(" ");
        }
        if (lastName != null){
            sb.append(lastName).append(" ");
        }
        return sb.toString();
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(getRefId()).append(" ");
        sb.append(getClinic()).append(" ");
        sb.append(getApptDateStr()).append(" ").append(getApptTimeStr());
        return sb.toString();
    }

}
