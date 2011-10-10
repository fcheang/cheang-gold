package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

/**
 * Action for logging into the system
 */
public class EnterReferralAction extends Action {

    public EnterReferralAction(Model model){
	    super(model);
    }

    public String getName(){
	    return "EnterReferralAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        session.setAttribute(REF, null); // clean up previous record
      	String retPage = newReferralPage;
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

        Calendar cal = new GregorianCalendar();
        session.setAttribute("rdMonth", new Integer(cal.get(Calendar.MONTH)+1).toString() );
        session.setAttribute("rdDate", new Integer(cal.get(Calendar.DATE)).toString() );
        session.setAttribute("rdYear", new Integer(cal.get(Calendar.YEAR)).toString());

        debug("returing "+retPage);
        return retPage;
    }

}
