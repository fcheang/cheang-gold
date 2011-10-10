package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class EnterApptReportAction extends Action {

    public EnterApptReportAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "EnterApptReportAction.do";
    }

    /**
     * perform
     *
     * @param req HttpServletRequest
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        StringBuffer apptClinic = new StringBuffer();
        StringBuffer apptProvider = new StringBuffer();


        Calendar cal = new GregorianCalendar();
        session.setAttribute("adMonth", new Integer(cal.get(Calendar.MONTH)+1).toString() );
        session.setAttribute("adDate", new Integer(cal.get(Calendar.DATE)).toString() );
        session.setAttribute("adYear", new Integer(cal.get(Calendar.YEAR)).toString());

        List clinics = model.getClinicName();
        String clinic = "";
        for (int i=0; i<clinics.size(); i++){
            clinic = (String)clinics.get(i);
            apptClinic.append("<option value=\"").append(clinic).append(
                "\" >").append(clinic).append("</option>\n");
        }
        session.setAttribute("apptClinic", apptClinic.toString());

        List providers = model.getAllProvider();
        Provider provider = null;
        for (int i=0; i<providers.size(); i++){
            provider = (Provider)providers.get(i);
            apptProvider.append("<option value=\"").append(provider.getName()).append(
                "\" >").append(provider.getName()).append("</option>\n");
        }
        session.setAttribute("apptProvider", apptProvider.toString());

        String insStr = "";
        List insuranceProviders = model.getAllInsuranceProvider();
        for (int i=0; i<insuranceProviders.size(); i++){
        	String name = ((InsuranceProvider)insuranceProviders.get(i)).name;
        	insStr = insStr + "<option value=\"" + name + "\">" + name + "</option>\n";
        }
        session.setAttribute("insuranceList", insStr);
        
        return "appt_report.jsp";
    }
}
