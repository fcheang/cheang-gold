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

public class SelectClinicAction extends Action {

    public SelectClinicAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
      	String retPage = super.selectClinicPage;

        List clinics = model.getClinicName();
        String clinicList = "";
        for (int i=0; i<clinics.size(); i++){
            clinicList = clinicList + "<option>"+clinics.get(i)+"</option>"+"\n";
        }
        session.setAttribute(csClinicList, clinicList);
        return retPage;
    }

    public String getName() {
        return "SelectClinicAction.do";
    }
}