package com.suntek.intakesystem.model;

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

public class ShowReferralHistoryAction extends Action {

    public ShowReferralHistoryAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        Referral ref = (Referral)req.getSession(false).getAttribute(REF);
        String historyTable = model.getRefHistoryTable(ref.getRefId());
        session.setAttribute("message", historyTable);
        return refHistoryPage;
    }

    public String getName() {
        return "ShowReferralHistoryAction.do";
    }
}