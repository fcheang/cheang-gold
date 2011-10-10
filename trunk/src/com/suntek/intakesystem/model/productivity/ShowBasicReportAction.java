package com.suntek.intakesystem.model.productivity;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.Model;

public class ShowBasicReportAction extends Action {

    public ShowBasicReportAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ShowBasicReportAction.do";
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
        
        // generate current date
        Date today = new Date();
        session.setAttribute("reportMonth", Model.getMonth(today));
        session.setAttribute("reportDay", Model.getDay(today));
        session.setAttribute("reportYear", Model.getYear(today));
        
        return "view_basic_report.jsp";
    }
}
