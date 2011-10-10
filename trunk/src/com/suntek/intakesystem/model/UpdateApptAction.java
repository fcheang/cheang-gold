package com.suntek.intakesystem.model;

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

public class UpdateApptAction extends Action {

    public UpdateApptAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
      /*
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String message = "";
        String retPage = super.successPage;

        Appointment appt = (Appointment)req.getSession(false).getAttribute(APPT);
        String refId = (String)req.getSession(false).getAttribute(REF_ID);
        appt.setRefId(Integer.parseInt(refId));

        Appointment oldAppt = model.getApptById(appt.getRefId());
        debug("oldAppt: "+oldAppt);
        debug("newAppt: "+appt);
        try{
            model.updateAppt(oldAppt, appt);
        }catch(Throwable t){
            success = false;
            errMsg = t.getMessage();
        }

        if (success){
            session.setAttribute("message", "<font color=\"blue\"><b>Appointment for referral <em>"+
                appt.getRefId()+"</em> was updated successfully!</b></font>");
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem update appointment for referral <em>"+
                appt.getRefId()+"</em></b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
       */
      return "";
    }

    public void debug(String msg){
        if (Model.debugOn){
            System.out.println("[UpdateApptAction]: " + msg);
        }
    }

    public String getName() {
        return "UpdateApptAction.do";
    }
}
