package com.suntek.intakesystem.model.report;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

public class CountRefByChildAdult extends Action {

    public CountRefByChildAdult(Model model){
    	super(model);
    }

    public String getName(){
    	return "CountRefByChildAdult.do";
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

            int numChild = model.getChildRefCount(esd, eed);
            int numAdult = model.getAdultRefCount(esd, eed);

            // header
            message += "<table border=1 width=50%>"+
            "<tr><th width=20%><strong>Age group</strong></th>"+
            "<th width=5%><strong>Count</strong></th>"+
            "</tr>";

            message +=
                "<tr><td width=20%>Child</td><td width=5%>"+numChild+"</td></tr>"+
                "<tr><td width=20%>Adult</td><td width=5%>"+numAdult+"</td></tr>"+
                "</table><p>";
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message", message);
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying referral by child/adult count!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}
