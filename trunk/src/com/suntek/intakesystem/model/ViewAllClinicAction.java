package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

public class ViewAllClinicAction extends Action {

    public ViewAllClinicAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "ViewAllClinicAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.clinicInfoPage;
        String message = "";
        try{
            List list = model.getAllClinic();
            if (list.size() == 0){
                message = "<b>No Clinic information found!</b>";
            }else{
                message = "<h3>"+list.size()+" Clinic found:</h3><p>";
                message = message +
                           "<table border=1><tr>"+
                           "<th width=5%><strong>Action</strong></th>"+
                           "<th width=15%><strong>Clinic name</strong></th>"+
                           "<th width=15%><strong>Phone number</strong></th>"+
                           "<th width=15%><strong>Fax number</strong></th>"+
                           "<th width=25%><strong>Address</strong></th>"+
                           "<th width=25%><strong>Notes</strong></th>"+
                           "</tr>";

                for (int i=0; i<list.size(); i++){
                    Clinic clinic = (Clinic)list.get(i);
                    message += "<tr>"+
                    "<td width=5%><a href=\"DeleteClinicAction.do?clinic="+
                    clinic.getName()+"\">Delete</td>"+
                    "<td width=15%>"+
                    clinic.getName()+"</td>";

                    message += "<td width=15%>";
                    if (clinic.getPn1() != null){
                        message += clinic.getPn1()+"<br>";
                    }
                    if (clinic.getPn2() != null){
                        message += clinic.getPn2();
                    }
                    message += "&nbsp";
                    message += "</td>";

                    message += "<td width=15%>";
                    if (clinic.getFax() != null){
                        message += clinic.getFax();
                    }
                    message += "&nbsp";
                    message += "</td>";

                    message += "<td width=25%>";
                    message = message + clinic.getFullAddress();
                    message += "</td>";

                    message += "<td width=25%>";
                    if (clinic.getNotes() != null){
                        message += clinic.getNotes();
                    }else{
                        message += "&nbsp";
                    }
                    message += "</td></tr>";
                }
                message += "</table>";

            }
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message", message);
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying clinic!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}
