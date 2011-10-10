package com.suntek.checkinsystem.model;

import com.suntek.common.persistence.*;
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

public class ViewReadOnlyReferralAction extends Action {

    public ViewReadOnlyReferralAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.csaReferralDetailPage;

        String refId = req.getParameter(REF_ID);
        session.setAttribute(REF_ID, refId);

        try{
            Referral ref = model.getReferral(Integer.parseInt(refId));
            Date rd = model.getReferralDate(Integer.parseInt(refId));
            ref.setRd(rd);
            session.setAttribute(REF, ref); // setting java bean

            String actionStr = "";
            String actionStr2 = "";

            if (ref.getStatus().equals(Action.SCHEDULED) || ref.getStatus().equals(Action.SEEN)){
              List appts = model.getApptById(ref.getRefId());
              Appointment appt = null;
              for (int i = 0; i < appts.size(); i++) {
                appt = (Appointment) appts.get(i);

                if (appt != null) {
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
                  String apptTimeStr = appt.getApptTimeStr();
                  String appointmentStr =
                      "<p align=\"left\" style=\"margin-top: 0; margin-bottom: 0\">" +
                      "<b>Scheduled clinic: </b>" + appt.getClinic() + "<br>" +
                      "<b>Appointment date: </b>" + apptDateStr + "<br>" +
                      "<b>Appointment time: </b>" + apptTimeStr + "<br>" +
                      "<b>Provider: </b>" + appt.getProvider() + "<br>" +
                      "<b>Need Translation: </b>" + appt.getNeedTranslationSvc() + "<br>" +
                      "<b>Collateral received: </b>" + appt.getCollateralReceived() +
                      "<br>" +
                      "<b>Notes: </b>" + appt.getNotes() +
                      "</p>";

                  session.setAttribute("appointment", appointmentStr);
                }
              }
            }
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

    public String getName() {
        return "ViewReadOnlyReferralAction.do";
    }
}
