package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;

public class NewApptAction extends Action {

    public NewApptAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "NewApptAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = successPage;

        Appointment appt = (Appointment)req.getSession(false).getAttribute(APPT);
        String refId = (String)req.getSession(false).getAttribute(REF_ID);
        appt.setRefId(Integer.parseInt(refId));
        appt.setUserId((String)session.getAttribute(super.USER_ID));

        try{
            model.scheduleAppt(appt);
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message",
                "<font color=\"blue\"><b>Scheduled appointment for referral ID<em> "+
                appt.getRefId()+"</em>!</b></font>");
        }else{
            session.setAttribute("message",
                "<font color=\"red\"><b>Problem create appointment for referral "+
                appt.getRefId()+"!</b></font>");
            session.setAttribute("error", errMsg);
        }
        resetAttr(session);
        return retPage;
    }

    private void resetAttr(HttpSession session){
        session.setAttribute(APPT, null);
        session.setAttribute(REF_ID, null);
    }

    public void debug(String msg){
        if (Model.debugOn){
            System.out.println("[NewApptAction]: " + msg);
        }
    }
}
