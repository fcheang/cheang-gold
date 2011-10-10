package com.suntek.checkinsystem.model;

import com.suntek.common.persistence.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ShowPatientCheckInForApptDateAction extends Action {

    public ShowPatientCheckInForApptDateAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        boolean success = true;
        String errMsg = "";
        String patientCheckInTable = "";
        String retPage = patientCheckInAdminPage;

        List clinicList = new ArrayList();
        String clinic = req.getParameter("clinic");
        debug("getParameter(clinic) = "+clinic);
        if (clinic.equalsIgnoreCase("All")){
            clinicList = model.getClinicName();
        }else{
            clinicList.add(clinic);
        }

        String adMonth = req.getParameter("rdMonth");
        int month = Integer.parseInt(adMonth);
        String adDate = req.getParameter("rdDate");
        int date = Integer.parseInt(adDate);
        String adYear = req.getParameter("rdYear");
        int year = Integer.parseInt(adYear);

        Calendar today = new GregorianCalendar();
        today.set(year, month-1, date, 0, 0, 0);
        today.set(Calendar.MILLISECOND, 0);
        Date todayDate = today.getTime();

        Calendar tomorrow = new GregorianCalendar();
        int todayDom = today.get(Calendar.DATE);
        tomorrow.set(Calendar.DATE, todayDom+1);
        tomorrow.set(Calendar.HOUR, 0);
        tomorrow.set(Calendar.MINUTE, 0);
        tomorrow.set(Calendar.SECOND, 0);
        tomorrow.set(Calendar.MILLISECOND, 0);
        Date tomorrowDate = tomorrow.getTime();

        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        String apptDateStr = df.format(todayDate);
        session.setAttribute(csaApptDate, apptDateStr);

        try{
            for (int nc=0; nc<clinicList.size(); nc++){
                String clinicName = (String)clinicList.get(nc);
                debug("clinicName = "+clinicName);
                patientCheckInTable += "<strong>Clinic: "+clinicName+"</strong><br>";

                List list = model.getAllApptEntry(clinicName, todayDate, tomorrowDate);
                if (list.size() == 0){
                    patientCheckInTable += "<b>No Appointment found!</b><p></p>";
                }else{
                    // header
                    patientCheckInTable += "<table border=1>"+
                    "<tr><th width=10%><strong>Referral Id</strong></th>"+
                        "<th width=10%><strong>Appt. Time</strong></th>"+
                        "<th width=20%><strong>Patient Name</strong></th>"+
                        "<th width=20%><strong>Provider</strong></th>"+
                        "<th width=20%><strong>Is CheckedIn</strong></th>"+
                        "<th width=20%><strong>CheckIn Time</strong></th>"+
                    "</tr>";

                    DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);
                    for (int j=0; j<list.size(); j++){
                        ApptEntry ae = (ApptEntry)list.get(j);
                        String apptTimeStr =  tf.format(ae.apptDate);
                        String fullName = "&nbsp;";
                        if (ae.firstName != null){
                            fullName += ae.firstName;
                            fullName += " ";
                        }
                        if (ae.lastName != null){
                            fullName += ae.lastName;
                        }
                        String provider = "";
                        if (ae.provider != null){
                            provider = ae.provider;
                        }else{
                            provider = "&nbsp;";
                        }
                        String isCheckedIn = "";
                        if (ae.isCheckedIn){
                            isCheckedIn = "Yes";
                        }else{
                            isCheckedIn = "No";
                        }
                        String checkInTimeStr = "&nbsp;";
                        if (ae.checkInTime != null){
                            checkInTimeStr = tf.format(ae.checkInTime);
                        }

                        patientCheckInTable +=
                        "<tr><td width=10%><a href=\"ViewReadOnlyReferralAction.do?refId="+ae.referralId+"\">"+ae.referralId+"</a></td>"+
                        "<td width=10%>"+apptTimeStr+"</td>"+
                        "<td width=20%>"+fullName+"</td>"+
                        "<td width=20%>"+provider+"</td>"+
                        "<td width=20%>"+isCheckedIn+"</td>"+
                        "<td width=20%>"+checkInTimeStr+"</td></tr>";
                    }
                    patientCheckInTable += "</table><p></p>";
                }
            }
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute(csaPatientCheckInTable, patientCheckInTable);
        }else{
            session.setAttribute(csMessage, "<font color=\"red\"><b>Problem displaying pending appointment!</b></font>");
            session.setAttribute(csError, errMsg);
        }
        return retPage;
    }

    public String getName() {
        return "ShowPatientCheckInForApptDateAction.do";
    }
}