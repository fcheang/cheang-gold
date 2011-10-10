package com.suntek.intakesystem.model.productivity;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.Model;
import com.suntek.common.persistence.Timesheet;

public class ViewHoursBilledAction extends Action {
    
	public ViewHoursBilledAction(Model m) {
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ViewHoursBilledAction.do";
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
        
        boolean success = true;
        String errMsg = "";
        String retPage = "view_hours_billed.jsp";        
        
        try{
        	int billMonth = Integer.parseInt(req.getParameter("billMonth"));
        	int billDay = Integer.parseInt(req.getParameter("billDay"));
        	int billYear = Integer.parseInt(req.getParameter("billYear"));
        	Date billDate = Model.getDateForDB(billMonth, billDay, billYear);
        	
        	List sheets = model.getTimesheet(billDate, Action.BILLED);
        	        	
            String hoursBilledTable =
                "<table border=\"1\">\n"+
                "<tr>\n"+
                "<td width=10%><strong>Action</strong></td>\n"+
                "<td width=25%><p><strong>Date</strong></td>\n"+
                "<td width=25%><strong>Clinic</strong></td>\n"+
                "<td width=30%><strong>Staff</strong></td>\n"+
                "<td width=10%><strong>Total Hours</strong></td>\n"+
                "</tr>\n";

            for (int i=0; i<sheets.size(); i++){
                Timesheet entry = (Timesheet)sheets.get(i);
                	hoursBilledTable += "<tr>\n" +
                    "<td width=10%><a href=\"DeleteTimesheetAction.do?timesheetId="+entry.getId()+"\">Delete</td>\n"+
                    "<td width=25%>"+entry.getDateStr()+"</td>\n" +
                    "<td width=25%>"+entry.getClinic()+"</td>\n" +
                    "<td width=30%>"+entry.getStaff()+"</td>\n" +
                    "<td width=10%>"+entry.getTotalHoursStr()+"</td>\n" +
                    "</tr>\n";
            }
            hoursBilledTable += "</table>\n";

        	session.setAttribute("billMonth", billMonth);
        	session.setAttribute("billDay", billDay);
        	session.setAttribute("billYear", billYear);
            
            session.setAttribute("hoursBilledTable", hoursBilledTable);
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            e.printStackTrace();
        }
        if (!success){
            retPage = errorPage;
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying hours billed for staff!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

}
