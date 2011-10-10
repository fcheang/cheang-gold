package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

public class NewReferralAction extends Action {

    public NewReferralAction(Model model){
	    super(model);
    }

    public String getName(){
	    return "NewReferralAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        Referral ref = (Referral)session.getAttribute(REF);
        debug(ref.toString());
        ref.setStatus(Action.NOT_SCHEDULED);
        ref.setIsActive(true);
        ref.setCreateDate(new Date());
        ref.setRemoveDate(Model.getEndOfTime());
        ref.setUserId((String)session.getAttribute(Action.USER_ID));

        boolean success = true;
        String errMsg = "";
        String retPage;

        try{
            model.insertReferral(ref);
            retPage = successPage;
        }catch(Throwable e){
            e.printStackTrace();
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
        	String msg = "<font color=\"blue\"><b>New Referral <em>"+ref.getFullName()+"</em> was created successfully!</b></font>";        	
        	if (ref.getInsurance() != null && ref.getInsurance().startsWith("HealthPac")){
        		msg += "<br><br><font color=\"red\"><b>This is a HealthPac client, contact PTW case manager at 510-872-2422</b></font>";
        	}
        	session.setAttribute("message", msg);

        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem create referral <em>"+ref.getFullName()+"</em> with the following error:</b></font>");
            session.setAttribute("error", errMsg);
        }
        resetAttr(session);
        return retPage;
    }

    private void resetAttr(HttpSession session){
        session.setAttribute(REF, null);
    }

    public void debug(String msg){
        if (Model.debugOn){
            System.out.println("[NewReferralAction]: " + msg);
        }
    }
}
