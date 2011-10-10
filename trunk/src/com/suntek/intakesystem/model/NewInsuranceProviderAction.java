package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;

public class NewInsuranceProviderAction extends Action {

    public NewInsuranceProviderAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "NewInsuranceProviderAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        String name = req.getParameter("name");
        String streetAddress = req.getParameter("streetaddress");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zipcode = req.getParameter("zipcode");
        String pn1 = null;
        String pn1first = req.getParameter("pn1_first");
        String pn1second = req.getParameter("pn1_second");
        String pn1third = req.getParameter("pn1_third");
        if (pn1second != null && pn1third != null){
            if (pn1first != null){
                pn1 = pn1first + pn1second + pn1third;
            }else{
                pn1 = pn1second + pn1third;
            }
        }
        String pn2 = null;
        String pn2first = req.getParameter("pn2_first");
        String pn2second = req.getParameter("pn2_second");
        String pn2third = req.getParameter("pn2_third");
        if (pn2second != null && pn2third != null){
            if (pn2first != null){
                pn2 = pn2first + pn2second + pn2third;
            }else{
                pn2 = pn2second + pn2third;
            }
        }
        String fax = null;
        String faxFirst = req.getParameter("fax_first");
        String faxSecond = req.getParameter("fax_second");
        String faxThird = req.getParameter("fax_third");
        if (faxSecond != null && faxThird != null){
            if (faxFirst != null){
                fax = faxFirst + faxSecond + faxThird;
            }else{
                fax = faxSecond + faxThird;
            }
        }
        String notes = req.getParameter("notes");

        boolean success = true;
        String errMsg = "";
        String retPage;
        try{
            model.insertInsuranceProvider(name, streetAddress, state, city, zipcode,
                pn1, pn2, fax, notes);
            retPage = successPage;
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message",
                "<font color=\"blue\"><b>Insurance provider <em>"+name+"</em> was created successfully!</b></font>");
        }else{
            session.setAttribute("message",
                "<font color=\"red\"><b>Problem create Insurance provider <em>"+name+"</em>!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}
