package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;
import java.util.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ModifyApptAction extends Action {

    public ModifyApptAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
      /*
        HttpSession session = req.getSession(false);

        String refId = (String)req.getSession(false).getAttribute(REF_ID);
        Appointment appt = model.getApptById(Integer.valueOf(refId).intValue());
        session.setAttribute(APPT, appt);

        String selectedProvider = appt.getProvider();

        List providers = model.getAllProvider();
        StringBuffer sb = new StringBuffer("");
        for (int i=0; i<providers.size(); i++){
            Provider p = (Provider)providers.get(i);
            if (p.getName().equals(selectedProvider)){
                sb.append("<option selected>").append(p.getName()).append("</option>").append("\n");
            }else{
                sb.append("<option>").append(p.getName()).append("</option>").append("\n");
            }
        }
        session.setAttribute("providerList", sb.toString());

        return editApptPage;
       */
      return "";
    }

    public String getName() {
        return "ModifyApptAction.do";
    }
}
