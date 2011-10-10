package com.suntek.intakesystem.model.productivity;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.*;


public class ShowEnterHoursWorkedAction extends Action {

    public ShowEnterHoursWorkedAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ShowEnterHoursWorkedAction.do";
    }

    public String perform(HttpServletRequest req) {
    	return perform(req, true);
    }
    
    /**
     * perform
     *
     * @param req HttpServletRequest
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String perform(HttpServletRequest req, boolean clearSession) {
        HttpSession session = req.getSession();
        
        if (clearSession){
        	session.setAttribute("message", null);
        }
        
        // generate current date
        Date today = new Date();        
        String workMonth = req.getParameter("workMonth");
        if (workMonth != null && !workMonth.equals("")){
        	session.setAttribute("currMonth", req.getParameter("workMonth") );
        	session.setAttribute("currDay", req.getParameter("workDay") );
        	session.setAttribute("currYear", req.getParameter("workYear") );
        }else{
        	session.setAttribute("currMonth", Model.getMonth(today));
        	session.setAttribute("currDay", Model.getDay(today));
        	session.setAttribute("currYear", Model.getYear(today));
        }
        
        // generate clinicList
        String clinic = req.getParameter("clinic");        
        String clinicList = "";
        List list = model.getClinicName();
        for (int i=0; i<list.size(); i++){
            String name = (String)list.get(i);
            if (clinic != null && !clinic.equals("")){
            	if (name.equals(clinic)){
            		clinicList += "<option value=\""+name+"\" selected>"+name+"</option>";
            	}else{
            		clinicList += "<option value=\""+name+"\" >"+name+"</option>";
            	}
            }else{
            	if (i == 0){
            		clinicList += "<option value=\""+name+"\" selected>"+name+"</option>";
            	}else{
            		clinicList += "<option value=\""+name+"\" >"+name+"</option>";
            	}
            }
        }
        session.setAttribute("clinicList", clinicList);
        
        // generate providerList        
        String staff = req.getParameter("staff");
        String providerList = "";
        list = model.getAllProvider();
        for (int i=0; i<list.size(); i++){
        	String name = ((Provider)list.get(i)).getName();
        	if (staff != null && !staff.equals("")){
        		if (name.equals(staff)){
        			providerList += "<option value=\""+name+"\" selected>"+name+"</option>";
        		}else{
        			providerList += "<option value=\""+name+"\" >"+name+"</option>";
        		}
        	}else{
	        	if (i == 0){
	        		providerList += "<option value=\""+name+"\" selected>"+name+"</option>";        		
	        	}else{
	        		providerList += "<option value=\""+name+"\" >"+name+"</option>";        		
	        	}
        	}
        }
        session.setAttribute("providerList", providerList);

        return "enter_hours_worked.jsp";
    }
}
