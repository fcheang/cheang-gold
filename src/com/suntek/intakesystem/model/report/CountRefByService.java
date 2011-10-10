package com.suntek.intakesystem.model.report;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

public class CountRefByService extends Action {

    public CountRefByService(Model model){
    	super(model);
    }

    public String getName(){
    	return "CountRefByService.do";
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

            int numMedSvc = model.getNeedMedicalSvcCount(esd, eed);
            int numTherapy = model.getNeedTherapyCount(esd, eed);

            // header
            message += "<table border=1 width=50%>"+
            "<tr><th width=20%><strong>Service type</strong></th>"+
            "<th width=5%><strong>Count</strong></th>"+
            "</tr>";

            message +=
                "<tr><td width=20%>Need medical management service</td><td width=5%>"+numMedSvc+"</td></tr>"+
                "<tr><td width=20%>Need therapy</td><td width=5%>"+numTherapy+"</td></tr>"+
                "</table><p>";
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message", message);
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying referral by service count!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}
