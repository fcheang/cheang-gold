package com.suntek.intakesystem.model.productivity;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.Model;

public class ShowViewHoursBilledAction extends Action {

    public ShowViewHoursBilledAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ShowViewHoursBilledAction.do";
    }

    /**
     * perform
     *
     * @param req HttpServletRequest
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession();
        // clear session variable
        session.setAttribute("hoursBilledTable", null);
        
        // generate current date
        Date today = new Date();
        session.setAttribute("billMonth", Model.getMonth(today));
        session.setAttribute("billDay", Model.getDay(today));
        session.setAttribute("billYear", Model.getYear(today));
        
        return "view_hours_billed.jsp";
    }
}
