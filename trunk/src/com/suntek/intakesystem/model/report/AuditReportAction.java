package com.suntek.intakesystem.model.report;

import com.suntek.common.persistence.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class AuditReportAction extends Action {

    public AuditReportAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        String retPage = null;
        String message = "";
        String errMsg = "";

        if (!super.hasPermission(session, super.AUDIT_REPORT_OBJ, super.READ)){
            session.setAttribute("message",
            "<font color=\"red\"><b>User do not have permission to perform this action!</b></font>");
            session.setAttribute("error", "");
            retPage = errorPage;
            return retPage;
        }

        boolean success = true;
        retPage = super.reportResultPage;
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
            message += "</b></p>\n";

            message += "<table border=1>"+
                "<tr><th width=5%><strong>UserId</strong></th>\n"+
                    "<th width=5%><strong># Referral Entered</strong></th>\n"+
                    "<th width=5%><strong># Appt. Entered</strong></th>\n"+
                "</tr>\n";

            List list = model.getAllReceptionistNames();
            Map refCount = model.getReferralCountByUserId(esd, eed);
            Map apptCount = model.getApptCountByUserId(esd, eed);

            for (int i=0; i<list.size(); i++){
                String user = (String)list.get(i);
                String numRefStr = "0";
                Integer numRef = (Integer)refCount.get(user);
                if (numRef != null){
                    numRefStr = numRef.toString();
                }
                String numApptStr = "0";
                Integer numAppt = (Integer)apptCount.get(user);
                if (numAppt != null){
                    numApptStr = numAppt.toString();
                }

                message +=
                "<tr><td width=5%>"+user+"</td>\n"+
                "<td width=5%>"+numRefStr+"</td>\n"+
                "<td width=5%>"+numApptStr+"</td></tr>\n";
            }
            message += "</table><p>";

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
            session.setAttribute("message", "<font color=\"red\"><b>Problem generating audit report!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

    public String getName() {
        return "AuditReportAction.do";
    }
}
