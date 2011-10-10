package com.suntek.intakesystem.model.productivity;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.Model;

public class EnterHoursBilledAction extends Action {

	  public EnterHoursBilledAction(Model m) {
	      super(m);
	  }

	  public String getName() {
	      return "EnterHoursBilledAction.do";
	  }

	  public String perform(HttpServletRequest req) {
	      HttpSession session = req.getSession(false);
	      String message = "";

	      try{
	    	  int workMonth = Integer.parseInt(req.getParameter("billMonth"));
	    	  int workDay = Integer.parseInt(req.getParameter("billDay"));
	    	  int workYear = Integer.parseInt(req.getParameter("billYear"));
	    	  Date workDate = Model.getDateForDB(workMonth, workDay, workYear);
	          String clinic = req.getParameter("clinic");
	          String staff = req.getParameter("staff");
	          BigDecimal hrsWorked = new BigDecimal(req.getParameter("hoursBilled")).setScale(2, BigDecimal.ROUND_HALF_UP);
	          String type = super.BILLED;
	          
	          model.insertTimesheet(workDate, clinic, staff, type, hrsWorked);
	          message = "<font color=\"blue\"><b>Hours billed for "+staff+" was added successfully!</b></font>";
	      }catch(Throwable e){
	          e.printStackTrace();
	          message = "<font color=\"red\"><b>Failed to save! "+e.getLocalizedMessage()+"</b></font>";
	      }
	      session.setAttribute("message", message);
	      return new ShowEnterHoursBilledAction(model).perform(req, false);
	  }
}
