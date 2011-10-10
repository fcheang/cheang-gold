package com.suntek.intakesystem.model.admin;

import java.util.List;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;

public class NewUserAction extends Action {

    public NewUserAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "NewUserAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String description = req.getParameter("description");
        String[] roles = req.getParameterValues("role");

        boolean success = true;
        String errMsg = "";
        String retPage;

        if (super.hasPermission(session, super.USER, super.CREATE)){
            try{
                model.insertUser(userid, password, firstname, lastname, description, roles);
                retPage = successPage;
            }catch(Throwable e){
                success = false;
                errMsg = e.getLocalizedMessage();
                retPage = errorPage;
            }
            if (success){
                session.setAttribute("message",
                    "<font color=\"blue\"><b>User "+userid+" was created successfully!</b></font>");
            }else{
                session.setAttribute("message",
                    "<font color=\"red\"><b>Problem create user "+userid+"!</b></font>");
                session.setAttribute("error", errMsg);
            }
        }else{
            session.setAttribute("message",
            "<font color=\"red\"><b>User do not have permission to perform this function!</b></font>");
            session.setAttribute("error", "");
            retPage = errorPage;
        }
        return retPage;
    }

    protected void NewUserAction(String msg){
        System.out.println("[NewUserAction]: "+msg);
    }
}
