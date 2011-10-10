package com.suntek.intakesystem.model.admin;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ShowUpdateUserAction extends Action {

    public ShowUpdateUserAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        String userId = req.getParameter("user");
        User user = model.getUser(userId);
        session.setAttribute(super.edit_user_userId, user.userid);
        session.setAttribute(super.edit_user_password, user.password);
        session.setAttribute(super.edit_user_firstName, user.firstname);
        session.setAttribute(super.edit_user_lastName, user.lastname);
        session.setAttribute(super.edit_user_description, user.description);

        List<String> roles = model.getAllRoleNames();
        String roleList = "<select name=\"role\" id=\"role\" size="+roles.size()+" multiple>";        
        for (int i=0; i<roles.size(); i++){
            String role = (String)roles.get(i);
            if (user.roles.contains(role)){
                roleList += "<option value=\""+role+"\" selected>"+role+"</option>\n";
            }else{
                roleList += "<option value=\""+role+"\" >"+role+"</option>\n";
            }
        }
        roleList += "</select>";
        session.setAttribute(super.edit_user_roleList, roleList);
        return super.editUserPage;
    }

    public String getName() {
        return "ShowUpdateUserAction.do";
    }
}