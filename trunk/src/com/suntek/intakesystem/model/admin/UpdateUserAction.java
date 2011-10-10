package com.suntek.intakesystem.model.admin;

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

public class UpdateUserAction extends Action {

    public UpdateUserAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        boolean success = true;
        String errMsg = "";
        String message = "";
        String retPage = null;

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String fName = req.getParameter("firstname");
        String lName = req.getParameter("lastname");
        String desc = req.getParameter("description");
        String[] roles = req.getParameterValues("role");

        try{
            model.updateUser(userId, password, fName, lName, desc, roles);
        }catch(Throwable t){
            success = false;
            errMsg = t.getMessage();
        }

        if (success){
            retPage = super.successPage;
            session.setAttribute("message", "<font color=\"blue\"><b>User <em>"+userId+"</em> was updated successfully!</b></font>");
        }else{
            retPage = super.errorPage;
            session.setAttribute("message", "<font color=\"red\"><b>Problem update User <em>"+
                userId+"</em></b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

    public String getName() {
        return "UpdateUserAction.do";
    }
}