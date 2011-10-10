package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

public class ViewAllInsuranceAction extends Action {

    public ViewAllInsuranceAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "ViewAllInsuranceAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = insuranceInfoPage;
        String message = "";
        try{
            List list = model.getAllInsuranceProvider();
            if (list.size() == 0){
                message = "<b>No Insurance Provider information found!</b>";
            }else{
                message = "<h3>"+list.size()+" Insurance Provider found:</h3><p>";
                message = message +
                           "<table border=1><tr>"+
                           "<th width=5%><strong>Action</strong></th>"+
                           "<th width=15%><strong>Provider name</strong></th>"+
                           "<th width=15%><strong>Phone number</strong></th>"+
                           "<th width=15%><strong>Fax number</strong></th>"+
                           "<th width=25%><strong>Address</strong></th>"+
                           "<th width=25%><strong>Notes</strong></th>"+
                           "</tr>";

                for (int i=0; i<list.size(); i++){
                    InsuranceProvider ins = (InsuranceProvider)list.get(i);
                    message += "<tr><td width=5%><a href=\"DeleteInsProviderAction.do?insProvider="+
                        ins.name+"\">Delete</td>";

                    message += "<td width=15%>"+ins.name+"</td>";

                    message += "<td width=15%>";
                    if (ins.pn1 != null){
                        message += ins.pn1+"<br>";
                    }
                    if (ins.pn2 != null){
                        message += ins.pn2;
                    }
                    message += "</td>";

                    message += "<td width=15%>";
                    if (ins.fax != null){
                        message += ins.fax;
                    }
                    message += "</td>";

                    message += "<td width=25%>";
                    if (ins.street != null){
                        message = message + ins.street + "<br>";
                    }
                    if (ins.city != null){
                        message = message + ins.city + " ";
                    }
                    if (ins.state != null){
                        message = message + ins.state + " ";
                    }
                    if (ins.zipcode != null){
                        message = message + ins.zipcode;
                    }
                    message += "</td>";

                    message += "<td width=25%>";
                    if (ins.notes != null){
                        message += ins.notes;
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
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying insurance provider!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}
