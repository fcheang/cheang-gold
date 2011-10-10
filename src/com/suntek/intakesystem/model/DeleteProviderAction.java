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

public class DeleteProviderAction extends Action {

    public DeleteProviderAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.successPage;

        String providerName = (String)req.getParameter("providerName");        
        try{
        	int numAppt = model.getApptCountForProvider(providerName);
        	if (numAppt > 0){
        		success = false;
        		errMsg = "Delete provider is not allowed because provider has appointments scheduled!";
        		retPage = super.errorPage;
        	}else{
        		model.deleteProvider(providerName);
        	}
        }catch(Throwable t){
            success = false;
            errMsg = t.getMessage();
            retPage = super.errorPage;
        }

        if (success){
            session.setAttribute("message", "<font color=\"blue\"><b>Provider <em>"+providerName+"</em> was deleted!</b></font>");
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem delete Provider <em>"+
            		providerName+"</em></b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

    public String getName() {
        return "DeleteProviderAction.do";
    }
}