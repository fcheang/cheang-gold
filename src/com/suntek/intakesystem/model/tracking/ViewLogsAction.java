package com.suntek.intakesystem.model.tracking;

import com.suntek.common.persistence.Action;
import javax.servlet.http.HttpServletRequest;
import com.suntek.common.persistence.Model;
import javax.servlet.http.HttpSession;
import java.util.*;
import com.suntek.common.persistence.*;

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
public class ViewLogsAction
    extends Action {

    public ViewLogsAction(Model m) {
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ViewLogsAction.do";
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
        clearSessionVariables(session);
        boolean success = true;
        String errMsg = "";
        String retPage = super.logSummaryPage;

        int lastLogId = model.getLastLogId();

        int logId = 0;
        String nextLogIdStr = req.getParameter("logId");
        if (nextLogIdStr == null || nextLogIdStr.equals("")){
            logId = lastLogId;
        }else{
            logId = Integer.parseInt(nextLogIdStr);
        }

        int hiLogId = logId;
        int loLogId = hiLogId - 9;
        //if (loLogId < 0){
        //    loLogId = 0;
        //}

        /*
        if (hiLogId <= 0){
            retPage = super.successPage;
            int pLogId = hiLogId + 10;
            String msg =
                "<font color=\"blue\"><b>No more trackings!</b></font>"+
                "<p>"+
                "<a href=\"ViewLogsAction.do?logId="+pLogId+"\">Prev</a>"+
                "</p>";

            session.setAttribute("message", msg);
            return retPage;
        }
        */

        try{
            List logs = model.getPatientLog(loLogId, hiLogId);

            String logTable =
                "<table width=\"1200\" border=\"1\">\n"+
                "<tr>\n"+
                "<td width=\"32\"><strong>Entry</strong></td>\n"+
                "<td width=\"140\"><p><strong>Client Name</strong></td>\n"+
                "<td width=\"60\"><strong>Date of initial call</strong></td>\n"+
                "<td width=\"80\"><strong>Date confirm letter send</strong></td>\n"+
                "<td width=\"60\"><strong>1st call date</strong></td>\n"+
                "<td width=\"60\"><strong>2nd call date</strong></td>\n"+
                "<td width=\"64\"><strong>contact with client</strong></td>\n"+
                "<td width=\"51\"><strong># of attempts</strong></td>\n"+
                "<td width=\"356\"><strong>misc. notes</strong></td>\n"+
                "</tr>\n";

            for (int i=0; i<logs.size(); i++){
                PatientLog log = (PatientLog)logs.get(i);
                logTable += "<tr>\n" +
                    "<td>"+log.getLogId()+"</td>\n" +
                    "<td>"+log.getClientName()+"</td>\n" +
                    "<td>"+log.getIniCallDate()+"</td>\n" +
                    "<td>"+log.getConfirmSendDate()+"</td>\n" +
                    "<td>"+log.getFirstCallDate()+"</td>\n" +
                    "<td>"+log.getSecCallDate()+"</td>\n" +
                    "<td>"+log.getContactClient()+"</td>\n" +
                    "<td>"+log.getNumAttempts()+"</td>\n" +
                    "<td>"+log.getNotes()+"</td>\n" +
                    "</tr>\n";
            }
            logTable += "</table>\n";

            int nextLogId = loLogId - 1;
            //if (nextLogId < 0){
            //    nextLogId = 0;
            //}

            int prevLogId = hiLogId + 10 ;

            String nextAction = "<p>";

            if (prevLogId - 10 < lastLogId){
                nextAction += "<a href=\"ViewLogsAction.do?logId="+prevLogId+"\">Prev</a>";
            }
            nextAction += "&nbsp;&nbsp;&nbsp;";
            if (nextLogId > 0){
                nextAction += "<a href=\"ViewLogsAction.do?logId="+nextLogId+"\">Next</a>";
            }
            nextAction += "</p>";

            session.setAttribute("logTable", logTable);
            session.setAttribute("nextAction", nextAction);

        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            e.printStackTrace();
        }
        if (!success){
            retPage = errorPage;
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying patient tracking!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

    private void clearSessionVariables(HttpSession session){
        session.setAttribute("logTable", null);
        session.setAttribute("logAction", null);
    }

}
