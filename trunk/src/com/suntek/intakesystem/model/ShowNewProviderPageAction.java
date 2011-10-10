package com.suntek.intakesystem.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.InsuranceProvider;
import com.suntek.common.persistence.Model;
import com.suntek.common.persistence.User;

public class ShowNewProviderPageAction extends Action {

	public ShowNewProviderPageAction(Model m){
		super(m);
	}
	
	@Override
	public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        User u = (User)session.getAttribute(SESSION_USER);

        String editableFlag = "";
        if (!u.roles.contains(CREDENTIAL)){
        	editableFlag = "disabled";
        }
        
        List<InsuranceProvider> allCredentials = model.getAllCredentials();        
    	StringBuilder sb = new StringBuilder();      	
    	for (InsuranceProvider c : allCredentials){
    		sb.append("<input type=\"checkbox\" name=\""+c.name+"\" value=\"true\" "+editableFlag+">"+c.name+"<br>");
    	}    	        
        session.setAttribute("allCredentialsCheckBox", sb.toString());
		return "new_provider.jsp";
	}

}
