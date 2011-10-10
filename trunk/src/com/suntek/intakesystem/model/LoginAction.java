package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;
import java.util.*;

/**
 * Action for logging into the system
 */
public class LoginAction extends Action {

    public LoginAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "LoginAction.do";
    }

    public String perform(HttpServletRequest req){
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        String ip = req.getRemoteAddr();
        debug("ip "+ip);
        String retPage = null;

        boolean success = model.login(userid, password);
        HttpSession session = req.getSession(true);
        if (success){
            boolean isRemoteUser = model.isRemoteUser(userid);
            if (!isRemoteUser){
                boolean allowAccess = model.allowLogin(ip);
                if (!allowAccess) {
                    session.setAttribute("loginMessage", "<font color=\"red\"><b>Error: User is not allowed to login on this machine!</b></font>");
                    debug("returing " + loginMainPage);
                    return loginMainPage;
                }
            }

            // authorized
            session.setAttribute(USER_ID, userid);
            User u = model.getUser(userid);
            session.setAttribute(SESSION_USER, u);
            HashMap<String, String> cap = model.getCapability(userid);
            session.setAttribute(CAPABILITY, cap);
            if (super.hasPermission(session, USER, CREATE)) {
                retPage = userAdminPage;
            }
            else {
                retPage = super.intake_indexPage;
            }
            debug("returning " + retPage);
            return retPage;
        }else{
            session.setAttribute("loginMessage", "<font color=\"red\"><b>Error: Invalid User ID or Password!</b></font>");
            debug("returing "+loginMainPage);
            return loginMainPage;
        }
    }

}
