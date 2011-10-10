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

public class SelectReportAction extends Action {

    public SelectReportAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

      	String retPage = super.savedReportPage;

        String reportList =
            "<option value=\"WeeklyReportAction.do\">Weekly and Monthly Report</option>\n";

        if (super.hasPermission(session, super.AUDIT_REPORT_OBJ, super.READ)){
            reportList +=
            "<option value=\"AuditReportAction.do\">Audit Report</option>\n";
        }

        reportList +=
            "<option value=\"NoShowsReportAction.do\">NoShows Report</option>\n";

        reportList +=
            "<option value=\"CancelReportAction.do\">Canceled appointment report</option>\n";

        reportList +=
            "<option value=\"ShowRefCountReportAction.do\">Total # of child, adult, NoShow referrals breakdown by month</option>\n";

        session.setAttribute("reportList", reportList);

        debug("returing "+retPage);
        return retPage;
    }

    public String getName() {
        return "SelectReportAction.do";
    }
}
