package com.suntek.intakesystem.model;

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

public class ConfirmAndScheduleApptAction extends Action {

    public ConfirmAndScheduleApptAction(Model model) {
        super(model);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        Referral ref = (Referral)session.getAttribute(REF);
        debug(ref.toString());
        ref.setStatus(Action.NOT_SCHEDULED);
        ref.setIsActive(true);
        ref.setCreateDate(new Date());
        ref.setRemoveDate(Model.getEndOfTime());
        ref.setUserId((String)session.getAttribute(super.USER_ID));

        boolean success = true;
        String errMsg = "";
        String retPage;

        try{
            model.insertReferral(ref);
            List clinics = model.getClinicName();
            String clinicList = "";
            for (int i=0; i<clinics.size(); i++){
                clinicList = clinicList + "<option>"+clinics.get(i)+"</option>"+"\n";
            }
            session.setAttribute("clinicName", clinicList);
            session.setAttribute(super.REF_ID, String.valueOf(ref.getRefId()));

            List providers = model.getAllProvider();
            StringBuffer sb = new StringBuffer("");
            for (int i=0; i<providers.size(); i++){
                Provider p = (Provider)providers.get(i);
                sb.append("<option>").append(p.getName()).append("</option>").append("\n");
            }
            session.setAttribute("providerList", sb.toString());

        }catch(Throwable e){
            e.printStackTrace();
            success = false;
            errMsg = e.getLocalizedMessage();
        }
        if (success){
            retPage = newApptPage;
        }else{
            retPage = errorPage;
            session.setAttribute("message", "<font color=\"red\"><b>Problem create referral <em>"+ref.getFullName()+"</em> with the following error:</b></font>");
            session.setAttribute("error", errMsg);
        }
        resetAttr(session);
        return retPage;
    }

    private void resetAttr(HttpSession session){
        session.setAttribute(REF, null);
    }

    public String getName() {
        return "ConfirmAndScheduleApptAction.do";
    }
}