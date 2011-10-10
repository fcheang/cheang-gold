package com.suntek.intakesystem.model.productivity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.Model;

public class DeleteTimesheetAction extends Action {
    
	public DeleteTimesheetAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "DeleteTimesheetAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.successPage;

        String idStr = (String)req.getParameter("timesheetId");
        int id = Integer.parseInt(idStr);
        try{
            model.deleteTimesheet(id);
        }catch(Throwable t){
            success = false;
            errMsg = t.getMessage();
        }

        if (success){
            session.setAttribute("message", "<font color=\"blue\"><b>Entry <em>"+id+"</em> was deleted!</b></font>");
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem delete entry <em>"+
                id+"</em></b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}
