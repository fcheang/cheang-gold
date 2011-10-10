package com.suntek.intakesystem.model.productivity;

import java.text.DateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.suntek.common.persistence.*;
import com.suntek.common.utils.*;

public class ViewBasicReportAction extends Action {
    
	public ViewBasicReportAction(Model m) {
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ViewBasicReportAction.do";
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
        String retPage = super.reportResultPage;        
        String message = "";
        try{
        	int reportMonth = Integer.parseInt(req.getParameter("reportMonth"));
        	int reportDay = Integer.parseInt(req.getParameter("reportDay"));
        	int reportYear = Integer.parseInt(req.getParameter("reportYear"));
        	Date reportDate = Model.getDateForDB(reportMonth, reportDay, reportYear);

        	List reportEntries = model.getProductivityReportEntries(reportDate);
        	
        	String prodDate = DateFormat.getDateInstance(DateFormat.LONG).format(reportDate);
            message = "<p align=\"center\" class=\"subtitle\">Daily Productivity Report for Date: "+
            	prodDate+"</p>";
            
            message += 
            	"<p align=\"center\">"+
            	"<table border=1>"+
            	"<tr>"+
            	"<th width=26%><strong>Staff</strong></th>\n"+
            	"<th width=10%><strong>Clinic</String></th>\n"+
            	"<th width=8%><strong># Hours worked</strong></th>\n"+
            	"<th width=8%><strong># Hours billed</strong></th>\n"+
            	"<th width=8%><strong># Hours scheduled</strong></th>\n"+
            	"<th width=8%><strong># Scheduled</strong></th>\n"+
            	"<th width=8%><strong># Seen</strong></th>\n"+
            	"<th width=8%><strong>Daily Productivity</strong></th>\n"+
            	"<th width=8%><strong>No-Show rate</strong></th>\n"+
            	"<th width=8%><strong>% Scheduled</strong></th>\n"+
            	"</tr>\n";
            
            ProductivityReportEntry entry = null;
            for (int i=0; i<reportEntries.size(); i++){
            	entry = (ProductivityReportEntry)reportEntries.get(i);
            	message +=
            		"<tr>"+
            		"<td width=26%>"+entry.getStaff()+"</td>\n"+
            		"<td width=10%>"+entry.getClinic()+"</td>\n"+
            		"<td width=8%>"+entry.getHoursWorkedStr()+"</td>\n"+
            		"<td width=8%>"+entry.getHoursBilledStr()+"</td>\n"+            		
            		"<td width=8%>"+entry.getHoursScheduledStr()+"</td>\n"+
            		"<td width=8%>"+entry.getNumScheduled()+"</td>\n"+
            		"<td width=8%>"+entry.getNumSeen()+"</td>\n"+
            		"<td width=8%>"+entry.getDailyProductivity()+"</td>\n"+
            		"<td width=8%>"+entry.getNoShowRate()+"</td>\n"+
            		"<td width=8%>"+entry.getPercentScheduled()+"</td>\n"+
            		"</tr>\n";
            }
            message += "</table></p>";
            message += getLegand(DateFormat.getDateInstance(DateFormat.SHORT).format(reportDate));
        }catch(Throwable e){
        	success = false;
        	errMsg = e.getLocalizedMessage();
        	retPage = errorPage;
        	e.printStackTrace();
        }
        if (success){
        	session.setAttribute("message", message);
        	String currentTime = "<b>"+super.getCurrentTime()+"</b>";
        	session.setAttribute("currentTime", currentTime);
        }else{
        	session.setAttribute("message", "<font color=\"red\"><b>Problem generating Basic Productivity report!</b></font>");
        	session.setAttribute("error", errMsg);
        }
        return retPage;
    }
    
    public String getLegand(String dateStr){
    	StringBuffer sb = new StringBuffer();
    	sb.append("# Hours worked = Hours worked for staff on ").append(dateStr).append("<br>\n");
    	sb.append("# Hours billed = Hours billed for staff on ").append(dateStr).append("<br>\n");   	
    	sb.append("# Hours scheduled = Number of hours scheduled for staff on ").append(dateStr).append("<br>\n");
    	sb.append("# Scheduled = Number of patients scheduled for staff on ").append(dateStr).append("<br>\n");
    	sb.append("# Seen = Number of patients seen for staff on ").append(dateStr).append("<br>\n");
    	sb.append("Daily Productivity = (# Hours billed) / (# Hours worked)").append("<br>\n");
    	sb.append("No-show Rate = (# Scheduled - # Seen) / (# Scheduled)").append("<br>\n");
    	sb.append("% Scheduled = (# Hours Scheduled) / (# Hours worked)").append("<br>\n");;
    	return sb.toString();
    }
    
}
