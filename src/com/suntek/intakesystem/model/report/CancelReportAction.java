package com.suntek.intakesystem.model.report;

import com.suntek.common.persistence.ClinicAndNumEntry;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import com.suntek.common.persistence.Model;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;
import com.suntek.common.persistence.NoShowsEntry;
import com.suntek.common.persistence.Action;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class CancelReportAction extends Action {
    public CancelReportAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "CancelReportAction.do";
    }

    /**
     * perform
     *
     * @param req HttpServletRequest
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        String retPage = null;
        String message = "";
        String errMsg = "";

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
            message += "Report period range: "+df.format(esd)+" - "+df.format(eed);
            message += "</b></p>\n";

            message += "<p align=\"center\">"+
                "<table width=50% border=1>"+
                "<tr><th width=10%><strong>Clinic</strong></th>\n"+
                "<th width=5%><strong># Canceled Appointment</strong></th>\n"+
                "</tr>\n";

            List list = model.getCanceledApptSummary(esd, eed);
            ClinicAndNumEntry cns = null;
            int total = 0;
            for (int i=0; i<list.size(); i++){
                cns = (ClinicAndNumEntry)list.get(i);
                message +=
                    "<tr><td width=10%>"+cns.clinic+"</td>\n"+
                    "<td width=5%>"+cns.numEntry+"</td>\n"+
                    "</tr>\n";
                total += cns.numEntry;
            }
            message +=
                "<tr><td width=10%>Total</td>\n"+
                "<td width=5%>"+total+"</td>\n";
            message += "</table></p>";

            message += "<p align=\"center\">Canceled Appointment Detail:";
            message += "<table border=1>"+
                "<tr><th><strong>Appointment Date</strong></th>\n"+
                "<th><strong>Patient</strong></th>\n"+
                "<th><strong>Clinic</strong></th>\n"+
                "<th><string>Provider</strong></th>\n"+
                "</tr>\n";

            list = model.getCanceledApptDetail(esd, eed);
            NoShowsEntry nse = null;
            for (int i=0; i<list.size(); i++){
                nse = (NoShowsEntry)list.get(i);
                message +=
                    "<tr><td>"+nse.getApptDate()+"</td>\n"+
                    "<td>"+nse.getFullName()+"</td>\n"+
                    "<td>"+nse.getClinic()+"</td>\n"+
                    "<td>"+nse.getProvider()+"</td>\n"+
                    "</tr>\n";
            }
            message += "</table></p>";
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
            session.setAttribute("message", "<font color=\"red\"><b>Problem generating Canceled Appointment report!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;

    }
}
