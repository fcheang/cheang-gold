package com.suntek.intakesystem.model.report;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import com.suntek.common.persistence.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

public class WeeklyReportAction extends Action {

    public WeeklyReportAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.reportResultPage;
        String message = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        try{
            int esdMonth = Integer.valueOf(req.getParameter("esdMonth")).intValue();
            int esdDate = Integer.valueOf(req.getParameter("esdDate")).intValue();
            int esdYear = Integer.valueOf(req.getParameter("esdYear")).intValue();
            Date esd = new GregorianCalendar(esdYear, esdMonth-1, esdDate).getTime();

            int eedMonth = Integer.valueOf(req.getParameter("eedMonth")).intValue();
            int eedDate = Integer.valueOf(req.getParameter("eedDate")).intValue();
            int eedYear = Integer.valueOf(req.getParameter("eedYear")).intValue();
            Date eed = new GregorianCalendar(eedYear, eedMonth-1, eedDate).getTime();

            String reportTitle = req.getParameter("reportTitle");

            message += "<p align=\"center\"><b>";
            if (reportTitle != null && !reportTitle.equals("")){
                message += reportTitle + "<br>";
            }
            message += "Period of: "+df.format(esd)+" - "+df.format(eed);
            message += "</b></p>";

            List clinicNames = model.getClinicName();
            Collections.sort(clinicNames);
            for (int i=0; i<clinicNames.size(); i++){
                String clinicName = (String)clinicNames.get(i);
                message += "<b>"+clinicName+"</b><br>\n";
                message += model.getSummaryReportTable(clinicName, esd, eed) + "<br><br>\n";
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
            session.setAttribute("message", "<font color=\"red\"><b>Problem generating weekly/monthly report!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

    public String getName() {
        return "WeeklyReportAction.do";
    }
}
