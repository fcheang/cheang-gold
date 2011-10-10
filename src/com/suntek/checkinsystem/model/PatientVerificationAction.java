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

public class PatientVerificationAction extends Action {

    public PatientVerificationAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        boolean success = true;
        String errMsg = "";
        String retPage = null;

        String refIdStr = (String)session.getAttribute(csRefId);
        String lastFourSSN = req.getParameter("lastFourSSN");
        debug("getParameter(lastFourSSN) = "+lastFourSSN);

        String okButtonStr = req.getParameter("OK");
        debug("getParameter(okButtonStr) = "+okButtonStr);

        String cancelButtonStr = req.getParameter("Cancel");
        debug("getParameter(cancelButtonStr) = "+cancelButtonStr);
        if (cancelButtonStr != null){
            return "ShowPatientCheckInAction.do";
        }

        try{
            int refId = Integer.parseInt(refIdStr);
            boolean isValid = model.verifyPatient(refId, lastFourSSN);
            if (isValid){
                model.checkInPatient(refId);
                session.setAttribute(csMessage, "Patient CheckIn sucessful!");
                retPage = checkInSuccessPage;
            }else{
                session.setAttribute(csInvalidSSN, "<font color=\"red\">"+
                    "Error: Social Security Number does not match! Please try again.</font>");
                retPage = "PatientCheckInAction.do?"+csRefId+"="+refIdStr;
            }
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (!success){
            session.setAttribute(csError, errMsg);
            session.setAttribute(csMessage,
                "<font color=\"red\"><b>Problem verify Patient!</b></font>");
        }
        return retPage;
    }

    public String getName() {
        return "PatientVerificationAction.do";
    }
}