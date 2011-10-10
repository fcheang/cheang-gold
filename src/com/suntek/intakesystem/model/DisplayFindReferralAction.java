package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

/**
 * Action for logging into the system
 */
public class DisplayFindReferralAction extends Action {

    public DisplayFindReferralAction(Model model){
	    super(model);
    }

    public String getName(){
	    return "DisplayFindReferralAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
      	String retPage = findRefPage;

        String statusList = "<option>"+Action.NOT_SCHEDULED+"</option>\n"+
            "<option>"+Action.WAITLISTED+"</option>\n"+
            "<option>"+Action.SCHEDULED+"</option>\n"+
            "<option>"+Action.DECLINED+"</option>\n"+
            "<option>"+Action.SEEN+"</option>\n" +
            "<option>"+Action.NOT_SEEN+"</option>\n";
        session.setAttribute("status", statusList);

        List ins = model.getInsuranceProviderName();
        String insList = "";
        for (int i=0; i<ins.size(); i++){
            insList = insList + "<option>"+ins.get(i)+"</option>"+"\n";
        }
        session.setAttribute("insuranceProvider", insList);

        List clinics = model.getClinicName();
        String clinicList = "";
        for (int i=0; i<clinics.size(); i++){
            clinicList = clinicList + "<option>"+clinics.get(i)+"</option>"+"\n";
        }
        session.setAttribute("clinicName", clinicList);

        debug("returing "+retPage);
        return retPage;
    }

}
