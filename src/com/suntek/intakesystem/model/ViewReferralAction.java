package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

public class ViewReferralAction extends Action {

    public ViewReferralAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "ViewReferralAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        clearSessionVariables(session);
        boolean success = true;
        String errMsg = "";
        String retPage = super.referralDetailPage;

        String refId = req.getParameter(REF_ID);
        session.setAttribute(REF_ID, refId);

        try{
            Referral ref = model.getReferral(Integer.parseInt(refId));
            Date rd = model.getReferralDate(Integer.parseInt(refId));
            ref.setRd(rd);
            session.setAttribute(REF, ref); // setting java bean

            if (ref.getStatus().equals(Action.SCHEDULED) || ref.getStatus().equals(Action.SEEN)
                || ref.getStatus().equals(Action.NOT_SEEN)){

              List appts = model.getApptById(ref.getRefId());
              Appointment appt = null;
              String apptStr = "";

              for (int i = 0; i < appts.size(); i++) {
                appt = (Appointment) appts.get(i);

                if (appt.getClinic() == null) {
                  appt.setClinic("");
                }
                String apptDateStr = appt.getApptDateStr();
                if (appt.getProvider() == null) {
                  appt.setProvider("");
                }
                if (appt.getNotes() == null) {
                  appt.setNotes("");
                }
                int apptNum = i + 1;
                String apptTimeStr = appt.getApptTimeStr();
                if (i > 0){
                  apptStr += "<hr>";
                }
                apptStr +=
                    "<p align=\"left\" style=\"margin-top: 0; margin-bottom: 0\">" +
                    "<b><i>Appointment "+apptNum+"</i></b><br>"+
                    "<b>Status: </b>" + appt.getStatus() + "<br>";
                if (appt.getStatus().equals(Action.SEEN)){
                    apptStr += "<b>Reason code: </b>" + appt.getReasonCode() + "<br>";
                }
                apptStr +=
                    "<b>Scheduled clinic: </b>" + appt.getClinic() + "<br>" +
                    "<b>Appointment date: </b>" + apptDateStr + "<br>" +
                    "<b>Appointment time: </b>" + apptTimeStr + "<br>" +
                    "<b>Provider: </b>" + appt.getProvider() + "<br>" +
                    "<b>Need Translation: </b>" + appt.getNeedTranslationSvc() +
                    "<br>" +
                    "<b>Collateral received: </b>" + appt.getCollateralReceived() +
                    "<br>" +
                    "<b>Notes: </b>" + appt.getNotes() +
                    "</p><br>";
              }
              session.setAttribute("appointment", apptStr);
            }

            String actionStr = "";
            String actionStr2 = "";

            session.setAttribute("action", actionStr);
            req.setAttribute("action", actionStr);
            session.setAttribute("action2", actionStr2);
            req.setAttribute("action2", actionStr2);

        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            e.printStackTrace();
        }
        if (!success){
            retPage = errorPage;
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying referral detail!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

    private void clearSessionVariables(HttpSession session){
        session.setAttribute(REF_ID, null);
        session.setAttribute(REF, null);
        session.setAttribute("appointment", null);
        session.setAttribute("action", null);
        session.setAttribute("action2", null);
        session.setAttribute("message", null);
        session.setAttribute("error", null);
    }
}
