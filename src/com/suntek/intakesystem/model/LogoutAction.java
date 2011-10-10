package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;

/**
 * Action for logging into the system
 */
public class LogoutAction extends Action {

    public LogoutAction(Model model){
	super(model);
    }

    public String getName(){
	return "LogoutAction.do";
    }

    public String perform(HttpServletRequest req){
	HttpSession session = req.getSession(false);
	if (session != null){
	    session.invalidate();
	}
        session = req.getSession(true); // new session use to return the message
	session.setAttribute("loginMessage", "<font color=\"blue\"><b>Logout successful!</b></font>");
        debug("returning "+loginPage);
        return loginPage;
    }
}
