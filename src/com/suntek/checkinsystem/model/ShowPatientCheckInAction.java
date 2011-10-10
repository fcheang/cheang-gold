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

public class ShowPatientCheckInAction extends Action {

    public ShowPatientCheckInAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        boolean success = true;
        String errMsg = "";
        String patientCheckInTable = "";
        String retPage = super.patientCheckInPage;

        String clinic = req.getParameter("clinic");
        debug("getParameter(clinic) = "+clinic);
        if (clinic != null){
            session.setAttribute(csClinic, clinic);
        }else{
            clinic = (String)session.getAttribute(csClinic);
        }

        String refreshRate = req.getParameter("refreshRate");
        debug("getParameter(refreshRate) = "+refreshRate);
        if (refreshRate != null){
            session.setAttribute(csRefreshRate, refreshRate);
        }

        Calendar today = new GregorianCalendar();
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
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
        session.setAttribute(csApptDate, apptDateStr);

        try{
            session.setAttribute(csInvalidSSN, null); // reset
            session.setAttribute(csMessage, null); // reset
            List list = model.getPendingApptEntry(clinic, todayDate, tomorrowDate);
            if (list.size() == 0){
                patientCheckInTable = "<b>No Pending Appointment found!</b>";
            }else{
                // header
                patientCheckInTable += "<table border=1>"+
                "<tr><th width=20%><strong>Action</strong></th>"+
                    "<th width=20%><strong>Time</strong></th>"+
                    "<th width=30%><strong>Patient Name</strong></th>"+
                    "<th width=30%><strong>Provider</strong></th>"+
                "</tr>";

                DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);
                for (int i=0; i<list.size(); i++){
                    ApptEntry ae = (ApptEntry)list.get(i);
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

                    patientCheckInTable +=
                    "<tr><td width=20%><a href=\"PatientCheckInAction.do?"+csRefId+"="+ae.referralId+"\">CheckIn</a></td>"+
                    "<td width=20%>"+apptTimeStr+"</td>"+
                    "<td width=30%>"+fullName+"</td>"+
                    "<td width=30%>"+provider+"</td></tr>";
                }
                patientCheckInTable += "</table><p>";
            }
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute(csPatientCheckInTable, patientCheckInTable);
        }else{
            session.setAttribute(csMessage, "<font color=\"red\"><b>Problem displaying pending appointment!</b></font>");
            session.setAttribute(csError, errMsg);
        }
        return retPage;
    }

    public String getName() {
        return "ShowPatientCheckInAction.do";
    }
}