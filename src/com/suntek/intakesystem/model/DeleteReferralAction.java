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

public class DeleteReferralAction extends Action {

    public DeleteReferralAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "DeleteReferralAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";

        Referral ref = (Referral)req.getSession(false).getAttribute(REF);
        try{
            int numAppt = model.getNumAppt(ref.getRefId());
            if (numAppt == 0){
                model.deleteReferral(ref.getRefId());
            }else{
                success = false;
                errMsg = numAppt + " appointment exist for referral! Delete referral is not allowed!";
            }
        }catch(Throwable t){
            success = false;
            errMsg = t.getMessage();
        }

        if (success){
            session.setAttribute("message", "<font color=\"blue\"><b>Referral <em>"+ref.getFullName()+"</em> was deleted!</b></font>");
            return super.successPage;
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem delete referral <em>"+
                ref.getFullName()+"</em></b></font>");
            session.setAttribute("error", errMsg);
            return super.errorPage;
        }
    }

}
