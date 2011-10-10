package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

public class ViewAllWaitlistedReferralAction extends Action {

    public ViewAllWaitlistedReferralAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "ViewAllWaitlistedReferralAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.referralSummaryPage;
        String message = "";
        try{
            List list = model.getAllWaitlistedReferral();
            if (list.size() == 0){
                message = "<b>No Referral information found!</b>";
            }else{
                if (list.size() > 1){
                    message = "<h3>"+list.size()+" referrals found:</h3><p>";
                }else{
                    message = "<h3>"+list.size()+" referral found:</h3><p>";
                }

                boolean firstTable = true;
                String currClinic = "";
                for (int i=0; i<list.size(); i++){
                    Referral ref = (Referral)list.get(i);

                    if (!ref.getClinic().equals(currClinic)){
                        currClinic = ref.getClinic();
                        if (!firstTable){
                            // close tag for previous table
                            message += "</table><p>";
                        }else{
                            firstTable = false;
                        }
                        // header
                        message += "<table border=1>"+
                        "<tr><th width=5%><strong>ID</strong></th>"+
                        "<th width=15%><strong>Clinic</strong></th>"+
                        "<th width=15%><strong>Refer date</strong></th>"+
                        "<th width=20%><strong>Patient name</strong></th>"+
                        "<th width=10%><strong>Is urgent?</strong></th>"+
                        "<th width=10%><strong>Need medical service?</strong></th>"+
                        "<th width=10%><strong>Need therapy?</strong></th>"+
                        "</tr>";
                    }
                    String patientName = ref.getFullName();

                    message +=
                    "<tr><td width=5%><a href=\"ViewReferralAction.do?refId="+ref.getRefId()+"\">"+ref.getRefId()+"</a></td>"+
                    "<td width=15%>"+ref.getClinic()+"</td>"+
                    "<td width=15%>"+ref.getCreateDate()+"</td>"+
                    "<td width=20%>"+patientName+"</td>"+
                    "<td width=10%>"+ref.getUrgent()+"</td>"+
                    "<td width=10%>"+ref.getMmNeeded()+"</td>"+
                    "<td width=10%>"+ref.getTpNeeded()+"</td></tr>";
                }
                message += "</table><p>";
            }
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message", message);
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying referral summary!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
}
