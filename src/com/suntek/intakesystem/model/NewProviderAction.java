package com.suntek.intakesystem.model;

import java.util.List;

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

public class NewProviderAction extends Action {

    public NewProviderAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        String pname = req.getParameter("providerName");
        String ptitle = req.getParameter("providerTitle");

        try{
            model.insertProvider(pname, ptitle);
            int pId = model.getProviderIdByName(pname);
            List<InsuranceProvider> allCreds = model.getAllCredentials();
            for (InsuranceProvider c : allCreds){
            	if (req.getParameter(c.name) != null){ // checked
            		model.insertCredential(pId, c.id);
            	}
            }
        }catch(Throwable e){
            session.setAttribute("message", "<font color=\"red\"><b>Problem create Provider <em>"+pname+"</em>!</b></font>");
            session.setAttribute("error", e.getMessage());
            e.printStackTrace();
            return super.errorPage; 
        }
        session.setAttribute("message", "<font color=\"blue\"><b>Provider <em>"+pname+"</em> was created successfully!</b></font>");
        return super.successPage;
    }

}