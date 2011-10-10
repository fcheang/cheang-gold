package com.suntek.intakesystem.model.admin;

import com.suntek.common.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ManageRemoteUserAction  extends Action {

    public ManageRemoteUserAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ManageRemoteUserAction.do";
    }

    /**
     * perform
     *
     * @param req HttpServletRequest
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        String retPage = "";

        if (super.hasPermission(session, "remote user", "manage")){
            retPage = showRemoteUser(session);
        }else{
            session.setAttribute("message",
                                 "<font color=\"red\"><b>User do not have permission to perform this action!</b></font>");
            session.setAttribute("error", "");
            retPage = errorPage;
        }
        return retPage;
    }


}
