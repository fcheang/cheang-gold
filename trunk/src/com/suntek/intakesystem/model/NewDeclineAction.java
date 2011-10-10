package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;

public class NewDeclineAction extends Action {

    public NewDeclineAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "NewDeclineAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = successPage;

        String refIdStr = (String)req.getSession().getAttribute("refId");
        String notes = req.getParameter("notes");
        try{
            model.declineReferral(Integer.parseInt(refIdStr), notes);
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message",
                "<font color=\"blue\"><b>Referral "+refIdStr+" was changed to "+Action.DECLINED+" state!</b></font>");
        }else{
            session.setAttribute("message",
                "<font color=\"red\"><b>Problem declining referral "+refIdStr+"!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}
