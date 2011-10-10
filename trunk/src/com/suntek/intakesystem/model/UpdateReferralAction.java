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

public class UpdateReferralAction extends Action {

    public UpdateReferralAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "UpdateReferralAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.successPage;

        Referral ref = (Referral)req.getSession(false).getAttribute(REF);
        Referral oldRef = model.getReferral(ref.getRefId());
        try{
            model.updateReferral(oldRef, ref);
        }catch(Throwable t){
            success = false;
            errMsg = t.getMessage();
        }

        if (success){
            session.setAttribute("message", "<font color=\"blue\"><b>Referral <em>"+ref.getFullName()+"</em> was updated successfully!</b></font>");
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem update referral <em>"+
                ref.getFullName()+"</em></b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

    public void debug(String msg){
        if (Model.debugOn){
            System.out.println("[UpdateReferralAction]: " + msg);
        }
    }

}
