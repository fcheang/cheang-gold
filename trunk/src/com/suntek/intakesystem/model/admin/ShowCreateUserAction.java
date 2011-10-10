package com.suntek.intakesystem.model.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.InsuranceProvider;
import com.suntek.common.persistence.Model;

public class ShowCreateUserAction extends Action {
    public ShowCreateUserAction(Model model){
    	super(model);
    }

    public String perform(HttpServletRequest req){    	
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage;
        List<String> roles = null;
        
        try{
        	roles = model.getAllRoleNames();
            retPage = "new_user.jsp";
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
        	int numRoles = roles.size();
        	
        	StringBuffer sb = new StringBuffer();
        	sb.append("<select name=\"role\" id=\"role\" size="+numRoles+" multiple>\n");

        	for (int i=0; i<numRoles; i++){
        		String role = (String)roles.get(i);
        		//if (i == 0){
        		//	sb.append("<option value=\"").append(role).append("\" selected>"+role+"</option>\n");
        		//}else{
        			sb.append("<option value=\"").append(role).append("\" >"+role+"</option>\n");
        		//}
        	}
        	sb.append("</select>\n");
        	/*
        	StringBuffer sb = new StringBuffer();        	
        	for (String r : roles){
        		sb.append("<input type=\"checkbox\" name=\""+r+"\" value=\"true\">"+r+"<br>");
        	} 
        	*/   	               	        	
            session.setAttribute("roles", sb.toString());
        }else{
            session.setAttribute("message",
                "<font color=\"red\"><b>Problem showing page!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

}
