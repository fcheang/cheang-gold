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

public class SaveAndPutOnWaitlistAction extends Action {

    public SaveAndPutOnWaitlistAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        Referral ref = (Referral)session.getAttribute(REF);
        debug(ref.toString());
        ref.setStatus(Action.NOT_SCHEDULED);
        ref.setIsActive(true);
        ref.setCreateDate(new Date());
        ref.setRemoveDate(Model.getEndOfTime());

        boolean success = true;
        String errMsg = "";
        String retPage;

        try{
            model.insertReferral(ref);
            session.setAttribute(Action.REF_ID, String.valueOf(ref.getRefId()));
        }catch(Throwable e){
            e.printStackTrace();
            success = false;
            errMsg = e.getLocalizedMessage();
        }
        if (success){
            retPage = waitlistRefPage;
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
        return "SaveAndPutOnWaitlistAction.do";
    }
}