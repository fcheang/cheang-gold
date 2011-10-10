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

public class DeleteInsProviderAction extends Action {

    public DeleteInsProviderAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "DeleteInsProviderAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.successPage;

        String insProvider = (String)req.getParameter(INS_PROVIDER);
        try{
        	int numIns = model.getNumInsuranceForProvider(insProvider);
        	if (numIns > 0){
        		success = false;
        		errMsg = "Delete Insurance Provider is not allowed because it is referenced by Insurance!";
        		retPage = super.errorPage;
        	}else{
                model.deleteInsProvider(insProvider);
        	}        	
        }catch(Throwable t){
            success = false;
            errMsg = t.getMessage();
        }

        if (success){
            session.setAttribute("message", "<font color=\"blue\"><b>Insurance Provider <em>"+insProvider+"</em> was deleted!</b></font>");
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem delete Insurance Provider <em>"+
                insProvider+"</em></b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}