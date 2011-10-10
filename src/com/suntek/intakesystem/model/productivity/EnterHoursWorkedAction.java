package com.suntek.intakesystem.model.productivity;

import java.util.Date;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.Model;

public class EnterHoursWorkedAction extends Action {

	  public EnterHoursWorkedAction(Model m) {
	      super(m);
	  }

	  public String getName() {
	      return "EnterHoursWorkedAction.do";
	  }

	  public String perform(HttpServletRequest req) {
	      HttpSession session = req.getSession(false);
	      String message = "";

	      try{
	    	  int workMonth = Integer.parseInt(req.getParameter("workMonth"));
	    	  int workDay = Integer.parseInt(req.getParameter("workDay"));
	    	  int workYear = Integer.parseInt(req.getParameter("workYear"));
	    	  Date workDate = Model.getDateForDB(workMonth, workDay, workYear);
	          String clinic = req.getParameter("clinic");
	          String staff = req.getParameter("staff");
	          BigDecimal hrsWorked = new BigDecimal(req.getParameter("hoursWorked")).setScale(2, BigDecimal.ROUND_HALF_UP);
	          String type = super.WORKED;
	          
	          model.insertTimesheet(workDate, clinic, staff, type, hrsWorked);
	          message = "<font color=\"blue\"><b>Hours worked for "+staff+" was added successfully!</b></font>";
	      }catch(Throwable e){
	          e.printStackTrace();
	          message = "<font color=\"red\"><b>Failed to save! "+e.getLocalizedMessage()+"</b></font>";
	      }
	      session.setAttribute("message", message);
	      return new ShowEnterHoursWorkedAction(model).perform(req, false);
	  }
}
