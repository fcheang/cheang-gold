package com.suntek.intakesystem.model.report;

import com.suntek.common.persistence.*;
import java.math.*;
import java.util.*;
import javax.servlet.http.*;


public class AvgWaitTime extends Action {

    public AvgWaitTime(Model model){
    	super(model);
    }

    public String getName(){
    	return "AvgWaitTime.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.reportResultPage;
        String message = "";
        try{
            int esdMonth = Integer.valueOf(req.getParameter("esdMonth")).intValue();
            int esdDate = Integer.valueOf(req.getParameter("esdDate")).intValue();
            int esdYear = Integer.valueOf(req.getParameter("esdYear")).intValue();
            Date esd = new GregorianCalendar(esdYear, esdMonth-1, esdDate).getTime();

            int eedMonth = Integer.valueOf(req.getParameter("eedMonth")).intValue();
            int eedDate = Integer.valueOf(req.getParameter("eedDate")).intValue();
            int eedYear = Integer.valueOf(req.getParameter("eedYear")).intValue();
            Date eed = new GregorianCalendar(eedYear, eedMonth-1, eedDate).getTime();

            BigDecimal bd = model.getAvgWaitTime(esd, eed);
            if (bd == null){
                message = "<b>No wait time information!</b>";
            }else{
                BigDecimal waitTime = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                message = "<p><h3>Average wait days between referral and appointment is: <br>"+
                "<font color=\"blue\">"+waitTime.toString()+"</font> days.</h3><p>";
            }
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message", message);
            String currentTime = super.getCurrentTime();
            session.setAttribute("currentTime", currentTime);
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying average wait time!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}
