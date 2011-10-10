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

public class DeleteClinicAction extends Action {

    public DeleteClinicAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "DeleteClinicAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.successPage;

        String clinic = (String)req.getParameter(CLINIC);
        try{
        	int numIns = model.getNumReferralForClinic(clinic);
        	if (numIns > 0){
        		success = false;
        		errMsg = "Delete Clinic is not allowed because it is referenced by Referral!";
        		retPage = super.errorPage;
        	}else{
        		int numAppt = model.getNumApptForClinic(clinic);
            	if (numAppt > 0){
            		success = false;
            		errMsg = "Delete Clinic is not allowed because it is referenced by Appointment!";
            		retPage = super.errorPage;
            	}else{        		
            		model.deleteClinic(clinic);
            	}
        	}        	        	
        }catch(Throwable t){
            success = false;
            errMsg = t.getMessage();
        }

        if (success){
            session.setAttribute("message", "<font color=\"blue\"><b>Clinic <em>"+clinic+"</em> was deleted!</b></font>");
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem delete clinic <em>"+
                clinic+"</em></b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}