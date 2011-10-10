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

public class DeleteUserAction extends Action {

    public DeleteUserAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "DeleteUserAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String message = "";
        String retPage = super.successPage;

        String user = (String)req.getParameter(USER);
        try{
            model.deleteUser(user);
        }catch(Throwable t){
            success = false;
            errMsg = t.getMessage();
        }

        if (success){
            session.setAttribute("message", "<font color=\"blue\"><b>User <em>"+user+"</em> was deleted!</b></font>");
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem delete user <em>"+
                user+"</em></b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}