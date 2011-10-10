package com.suntek.intakesystem.model.productivity;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.Model;
import com.suntek.common.persistence.Provider;

public class ShowEnterHoursBilledAction extends Action {

    public ShowEnterHoursBilledAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ShowEnterHoursBilledAction.do";
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
        String billMonth = req.getParameter("billMonth");
        if (billMonth != null && !billMonth.equals("")){
        	session.setAttribute("currMonth", req.getParameter("billMonth") );
        	session.setAttribute("currDay", req.getParameter("billDay") );
        	session.setAttribute("currYear", req.getParameter("billYear") );
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

        return "enter_hours_billed.jsp";
    }
}
