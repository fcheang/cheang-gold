package com.suntek.intakesystem.model.report;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

public class CountRefByStatus extends Action {

    public CountRefByStatus(Model model){
    	super(model);
    }

    public String getName(){
    	return "CountRefByStatus.do";
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

            Map map = model.getRefByStatusCount(esd, eed);
            if (map.size() == 0){
                message = "<b>No referral by status count!</b>";
            }else{
                message = "<h3>"+map.size()+" referral by status count found:</h3><p>";

                // header
                message += "<table border=1 width=50%>"+
                "<tr><th width=20%><strong>Referral status</strong></th>"+
                "<th width=5%><strong>Count</strong></th>"+
                "</tr>";

                Iterator iter = map.keySet().iterator();
                while (iter.hasNext()){
                    String status = (String)iter.next();
                    Integer count = (Integer)map.get(status);
                    message +=
                    "<tr><td width=20%>"+status+"</td>"+
                    "<td width=5%>"+count.toString()+"</td></tr>";
                }
                message += "</table><p>";
            }
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message", message);
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying referral by status count!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}
