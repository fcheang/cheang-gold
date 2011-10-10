package com.suntek.intakesystem.model.admin;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

public class ViewAllUserAction extends Action {

    public ViewAllUserAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "ViewAllUserAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = "";
        String message = "";

        if (super.hasPermission(session, super.USER, super.CREATE)){
            try{
                List<User> users = model.getAllUser();
                if (users.size() == 0){
                    message = "<b>No user information found!</b>";
                }else{
                    message = "<h3>"+users.size()+" users found</h3><p>";
                    message = message +
                               "<table border=1><tr>"+
                               "<th width=7%><strong>Action</strong></th>"+
                               "<th width=8%><strong>Action</strong></th>"+
                               "<th width=10%><strong>User Id</strong></th>"+
                               "<th width=10%><strong>Password</strong></th>"+
                               "<th width=15%><strong>Role</strong></th>"+
                               "<th width=15%><strong>First name</strong></th>"+
                               "<th width=15%><strong>Last name</strong></th>"+
                               "<th width=20%><strong>Description</strong></th>"+
                               "</tr>";
                    for (User u : users){
                    	StringBuilder sb = new StringBuilder();
                    	for (String r : u.roles){
                    		sb.append(r).append("<br>");
                    	}
                        message = message + "<tr>"+
                            "<td width=7%><a href=\"DeleteUserAction.do?user="+
                            u.userid+"\">Delete</td>"+
                            "<td width=8%><a href=\"ShowUpdateUserAction.do?user="+
                            u.userid+"\">Update</td>"+
                            "<td width=10%>"+u.userid+"</td>"+
                            "<td width=10%>"+u.password+"</td>"+
                            "<td width=15%>"+sb.toString()+"</td>";
                        if (u.firstname == null || u.firstname.equalsIgnoreCase("")){
                            message += "<td width=15%>&nbsp</td>";
                        }else{
                            message += "<td width=15%>"+u.firstname+"</td>";
                        }
                        if (u.lastname == null || u.lastname.equalsIgnoreCase("")){
                            message += "<td width=15%>&nbsp</td>";
                        }else{
                            message += "<td width=15%>"+u.lastname+"</td>";
                        }
                        if (u.description == null || u.description.equalsIgnoreCase("")){
                            message += "<td width=20%>&nbsp</td>";
                        }else{
                            message += "<td width=20%>"+u.description+"</td>";
                        }
                        message += "</tr>";
                    }
                }
                retPage = userInfoPage;
            }catch(Throwable e){
                success = false;
                errMsg = e.getLocalizedMessage();
                retPage = errorPage;
            }
            if (success){
                session.setAttribute("message", message);
            }else{
                session.setAttribute("message", "<font color=\"red\"><b>Problem displaying user!</b></font>");
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

}
