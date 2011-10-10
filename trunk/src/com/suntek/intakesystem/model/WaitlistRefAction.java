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

public class WaitlistRefAction extends Action {

    public WaitlistRefAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = successPage;

        String refIdStr = (String)req.getSession().getAttribute("refId");
        String notes = req.getParameter("notes");
        try{
            model.waitlistReferral(Integer.parseInt(refIdStr), notes);
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message",
                "<font color=\"blue\"><b>Referral "+refIdStr+" was changed to "+Action.WAITLISTED+" state!</b></font>");
        }else{
            session.setAttribute("message",
                "<font color=\"red\"><b>Problem change referral "+refIdStr+" to "+Action.WAITLISTED+" state!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

    public String getName() {
        return "WaitlistRefAction.do";
    }
}