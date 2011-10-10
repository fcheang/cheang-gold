package com.suntek.intakesystem.model.productivity;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.Model;
import com.suntek.common.persistence.Provider;

public class ShowViewHoursWorkedAction extends Action {

    public ShowViewHoursWorkedAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ShowViewHoursWorkedAction.do";
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
        session.setAttribute("hoursWorkedTable", null);
        
        // generate current date
        Date today = new Date();
        session.setAttribute("workMonth", Model.getMonth(today));
        session.setAttribute("workDay", Model.getDay(today));
        session.setAttribute("workYear", Model.getYear(today));
        
        return "view_hours_worked.jsp";
    }
}
