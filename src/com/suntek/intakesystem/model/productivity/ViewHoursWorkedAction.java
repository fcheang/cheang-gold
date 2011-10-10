package com.suntek.intakesystem.model.productivity;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.*;

public class ViewHoursWorkedAction extends Action {
    
	public ViewHoursWorkedAction(Model m) {
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ViewHoursWorkedAction.do";
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
        String retPage = "view_hours_worked.jsp";        
        
        try{
        	int workMonth = Integer.parseInt(req.getParameter("workMonth"));
        	int workDay = Integer.parseInt(req.getParameter("workDay"));
        	int workYear = Integer.parseInt(req.getParameter("workYear"));
        	Date workDate = Model.getDateForDB(workMonth, workDay, workYear);
        	
        	List sheets = model.getTimesheet(workDate, Action.WORKED);
        	        	
            String hoursWorkedTable =
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
                	hoursWorkedTable += "<tr>\n" +
                    "<td width=10%><a href=\"DeleteTimesheetAction.do?timesheetId="+entry.getId()+"\">Delete</td>\n"+
                    "<td width=25%>"+entry.getDateStr()+"</td>\n" +
                    "<td width=25%>"+entry.getClinic()+"</td>\n" +
                    "<td width=30%>"+entry.getStaff()+"</td>\n" +
                    "<td width=10%>"+entry.getTotalHoursStr()+"</td>\n" +
                    "</tr>\n";
            }
            hoursWorkedTable += "</table>\n";

        	session.setAttribute("workMonth", workMonth);
        	session.setAttribute("workDay", workDay);
        	session.setAttribute("workYear", workYear);
            
            session.setAttribute("hoursWorkedTable", hoursWorkedTable);
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            e.printStackTrace();
        }
        if (!success){
            retPage = errorPage;
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying hours worked for staff!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

}
