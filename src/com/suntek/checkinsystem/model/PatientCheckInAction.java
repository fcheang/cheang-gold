package com.suntek.checkinsystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class PatientCheckInAction extends Action {

    public PatientCheckInAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
      /*
        HttpSession session = req.getSession(false);

        boolean success = true;
        String errMsg = "";
        String retPage = super.patientAuthenticationPage;

        String refIdStr = req.getParameter(super.csRefId);
        debug("getParameter(csRefId) = "+refIdStr);
        session.setAttribute(super.csRefId, refIdStr);

        try{
            int refId = Integer.parseInt(refIdStr);
            Referral ref = model.getReferral(refId);
            Appointment appt = model.getApptById(refId);
            session.setAttribute(super.csPatientName, ref.getFullName());
            session.setAttribute(super.csProvider, appt.getProvider());
            session.setAttribute(super.csMyApptDate, appt.getApptDateStr());
            session.setAttribute(super.csMyApptTime, appt.getApptTimeStr());
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (!success){
            session.setAttribute(csMessage, "<font color=\"red\"><b>Problem checkIn!</b></font>");
            session.setAttribute(csError, errMsg);
        }
        return retPage;
       */
      return "";
    }

    public String getName() {
        return "PatientCheckInAction.do";
    }
}
