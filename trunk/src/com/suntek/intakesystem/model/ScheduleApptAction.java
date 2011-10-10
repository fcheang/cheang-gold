package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

public class ScheduleApptAction extends Action {

    public ScheduleApptAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "ScheduleApptAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        String retPage = newApptPage;

        List clinics = model.getClinicName();
        String clinicList = "";
        for (int i=0; i<clinics.size(); i++){
            clinicList = clinicList + "<option>"+clinics.get(i)+"</option>"+"\n";
        }
        session.setAttribute("clinicName", clinicList);

        List providers = model.getAllProvider();
        StringBuffer sb = new StringBuffer("");
        for (int i=0; i<providers.size(); i++){
            Provider p = (Provider)providers.get(i);
            sb.append("<option>").append(p.getName()).append("</option>").append("\n");
        }
        session.setAttribute("providerList", sb.toString());

        debug("returing "+retPage);
        return retPage;
    }

}
