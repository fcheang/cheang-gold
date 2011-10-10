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

public class SelectClinicAndApptDateAction extends Action {

    public SelectClinicAndApptDateAction(Model m) {
        super(m);
    }

    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
      	String retPage = super.selectClinicAndApptDatePage;

        List clinics = model.getClinicName();
        String clinicList = "";
        for (int i=0; i<clinics.size(); i++){
            clinicList = clinicList + "<option>"+clinics.get(i)+"</option>"+"\n";
        }
        session.setAttribute(csaClinicList, clinicList);

        Calendar cal = new GregorianCalendar();
        session.setAttribute(csaMonth, new Integer(cal.get(Calendar.MONTH)+1).toString() );
        session.setAttribute(csaDate, new Integer(cal.get(Calendar.DATE)).toString() );
        session.setAttribute(csaYear, new Integer(cal.get(Calendar.YEAR)).toString());

        return retPage;
    }

    public String getName() {
        return "SelectClinicAndApptDateAction.do";
    }
}