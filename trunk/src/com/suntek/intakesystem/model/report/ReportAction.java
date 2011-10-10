package com.suntek.intakesystem.model.report;

import com.suntek.common.persistence.*;

import javax.servlet.http.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ReportAction extends Action {

    public ReportAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        String reportType = req.getParameter("reportType");
        Action action = null;
        if (reportType.equalsIgnoreCase("CountRefByStatus.do")){
            action = new CountRefByStatus(model);
        }else if (reportType.equalsIgnoreCase("CountRefByService.do")){
            action = new CountRefByService(model);
        }else if (reportType.equalsIgnoreCase("CountRefByInsurance.do")){
            action = new CountRefByInsurance(model);
        }else if (reportType.equalsIgnoreCase("AvgWaitTime.do")){
            action = new AvgWaitTime(model);
        }else if (reportType.equalsIgnoreCase("CountRefByChildAdult.do")){
            action = new CountRefByChildAdult(model);
        }else if (reportType.equalsIgnoreCase("WeeklyReportAction.do")){
            action = new WeeklyReportAction(model);
        }else if (reportType.equalsIgnoreCase("AuditReportAction.do")){
            action = new AuditReportAction(model);
        }else if (reportType.equalsIgnoreCase("NoShowsReportAction.do")){
            action = new NoShowsReportAction(model);
        }else if (reportType.equalsIgnoreCase("CancelReportAction.do")){
            action = new CancelReportAction(model);
        }else if (reportType.equalsIgnoreCase("ShowRefCountReportAction.do")){
            action = new ShowRefCountReportAction(model);
        }
        return action.perform(req);
    }

    public String getName() {
        return "ReportAction.do";
    }
}
