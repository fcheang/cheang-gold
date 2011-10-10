package com.suntek.intakesystem.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.Model;
import com.suntek.common.persistence.Provider;
import com.suntek.common.persistence.User;

public class ViewProviderDetailAction extends Action {

    public ViewProviderDetailAction(Model m) {
        super(m);
    }

	public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        String pId = req.getParameter(Action.PROVIDER_ID);        
        Provider p = model.getProviderById(Integer.parseInt(pId));
        p.setAllCredentials(model.getAllCredentials());
        User u = (User)session.getAttribute(SESSION_USER);
        if (u.roles.contains(CREDENTIAL)){
        	p.setCanEditCredential(true);
        }
        
        session.setAttribute("provider", p);
		return super.viewProviderDetailPage;
	}

}
