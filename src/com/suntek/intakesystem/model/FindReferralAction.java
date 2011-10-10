package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import com.suntek.common.utils.SecurityUtil;

import java.util.*;
import javax.servlet.http.*;

public class FindReferralAction extends Action {

    public FindReferralAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "FindReferralAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.referralSummaryPage;
        String message = "";

        // outer join, when appt not used
        String sql = "select distinct r.referralId, rs.status, r.firstName, r.middleInitial, "+
            "r.lastName, r.clinic, r.isUrgent, r.needMedicalMgntSvc, r.needTherapy "+
            "from referralStatus rs, insurance i, referral r "+
            "where r.referralId = rs.referralId and rs.createDate <= now() and rs.removeDate > now() "+
            "and r.referralId = i.referralId ";

        boolean apptUsed = isApptUsed(req);
        if (apptUsed){
            // equal join, when appt used
            sql =  "select distinct r.referralId, rs.status, r.firstName, r.middleInitial, "+
            "r.lastName, r.clinic, r.isUrgent, r.needMedicalMgntSvc, r.needTherapy "+
            "from referralStatus rs, insurance i, referral r, appointment a "+
            "where r.referralId = rs.referralId and rs.createDate <= now() and rs.removeDate > now() "+
            "and r.referralId = i.referralId and rs.apptId = a.apptId ";
        }

        String status = req.getParameter("status");
        if (!status.equalsIgnoreCase("null")){
            sql += "and rs.status = '"+status+"' ";
        }

        String clinic = req.getParameter("clinic");
        if (!clinic.equalsIgnoreCase("null")){
            sql += "and r.clinic = '"+clinic+"' ";
        }

        String ins = req.getParameter("insurance");
        if (!ins.equalsIgnoreCase("null")){
            sql += "and i.insuranceCompany = '"+ins+"' ";
        }

        String ageGroup = req.getParameter("ageGroup");
        if (ageGroup.equalsIgnoreCase("Child")){
            sql += "and to_days(now()) - to_days(r.birthdate) < 6570 ";
        }else if (ageGroup.equalsIgnoreCase("Adult")){
            sql += "and to_days(now()) - to_days(r.birthdate) >= 6570 ";
        }

        String attr1 = req.getParameter("attr1");
        String pred1 = req.getParameter("pred1");
        String val1 = req.getParameter("val1");
        if (!attr1.equalsIgnoreCase("null") && val1 != null && !val1.equalsIgnoreCase("")){
            sql += getClause(attr1, pred1, val1);
        }

        String attr2 = req.getParameter("attr2");
        String pred2 = req.getParameter("pred2");
        String val2 = req.getParameter("val2");
        if (!attr2.equalsIgnoreCase("null") && val2 != null && !val2.equalsIgnoreCase("")){
            sql += getClause(attr2, pred2, val2);
        }

        String attr3 = req.getParameter("attr3");
        String pred3 = req.getParameter("pred3");
        String val3 = req.getParameter("val3");
        if (!attr3.equalsIgnoreCase("null") && val3 != null && !val3.equalsIgnoreCase("")){
            sql += getClause(attr3, pred3, val3);
        }

        String attr4 = req.getParameter("attr4");
        String pred4 = req.getParameter("pred4");
        String val4 = req.getParameter("val4");
        if (!attr4.equalsIgnoreCase("null") && val4 != null && !val4.equalsIgnoreCase("")){
            sql += getClause(attr4, pred4, val4);
        }

        String attr5 = req.getParameter("attr5");
        String pred5 = req.getParameter("pred5");
        String val5 = req.getParameter("val5");
        if (!attr5.equalsIgnoreCase("null") && val5 != null && !val5.equalsIgnoreCase("")){
            sql += getClause(attr5, pred5, val5);
        }

        String sort1 = req.getParameter("sort1");
        String order1 = req.getParameter("order1");
        String sort2 = req.getParameter("sort2");
        String order2 = req.getParameter("order2");

        if ( !sort1.equalsIgnoreCase("null") ){
            sql += "order by "+sort1+" "+order1+" ";
            if ( !sort2.equalsIgnoreCase("null") ){
                sql += ", "+sort2+" "+order2+" ";
            }
        }else{
            if (!sort2.equalsIgnoreCase("null")){
                sql += "order by "+sort2+" "+order2+" ";
            }else{
                sql += "order by r.referralId ";
            }
        }

        try{
            List<Referral> refs = model.getReferralUsingSql(sql);
            List<Referral> uniqueRefs = new ArrayList<Referral>();

            HashMap<Integer, Integer> uniqueRefId = new HashMap<Integer, Integer>();            
            for (int i=0; i<refs.size(); i++){
                Referral ref = refs.get(i);
                Integer refId = new Integer(ref.getRefId());
                if (uniqueRefId.get(refId) == null){                	
                	uniqueRefs.add(ref);
                	uniqueRefId.put(refId, refId);
                }
            }
            if (uniqueRefs.size() == 0){
                message = "<b>No Referral information found!</b>";
            }else{
                if (uniqueRefs.size() > 1){
                    message = "<h3>"+uniqueRefs.size()+" referrals found:</h3><p>";
                }else{
                    message = "<h3>"+uniqueRefs.size()+" referral found:</h3><p>";
                }

                // header
                message += "<table border=1>"+
                "<tr><th width=5%><strong>ID</strong></th>"+
                    "<th width=15%><strong>Clinic</strong></th>"+
                    "<th width=15%><strong>Status</strong></th>"+
                    "<th width=20%><strong>Patient name</strong></th>"+
                    "<th width=5%><strong>Is urgent?</strong></th>"+
                    "<th width=5%><strong>Need medical service?</strong></th>"+
                    "<th width=5%><strong>Need therapy?</strong></th>"+
                "</tr>";

                for (int i=0; i<uniqueRefs.size(); i++){
                    Referral ref = (Referral)uniqueRefs.get(i);
                    message +=
                    "<tr><td width=5%><a href=\"ViewReferralAction.do?refId="+ref.getRefId()+"\" >"+ref.getRefId()+"</a></td>"+
                    "<td width=15%>"+ref.getClinic()+"</td>"+
                    "<td width=15%>"+ref.getStatus()+"</td>"+
                    "<td width=20%>"+ref.getFullName()+"</td>"+
                    "<td width=5%>"+ref.getUrgent()+"</td>"+
                    "<td width=5%>"+ref.getMmNeeded()+"</td>"+
                    "<td width=5%>"+ref.getTpNeeded()+"</td></tr>";
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

    private boolean isApptUsed(HttpServletRequest req){
        for (int i=1; i<=5; i++){
            String attr = req.getParameter("attr"+i);
            StringTokenizer stk = new StringTokenizer(attr, ".");
            if (stk.hasMoreTokens()){
                String prefix = stk.nextToken();
                if (prefix.equalsIgnoreCase("a")){
                    return true;
                }
            }
        }
        return false;
    }

    private String getClause(String attr, String pred, String val){
        String sql = "";
        if (attr.equalsIgnoreCase("r.firstName") || attr.equalsIgnoreCase("r.ssn")){
        	//sql += " and " + attr + " = '" + SecurityUtil.encrypt(val) + "' ";
        	sql += " and " + attr + " = '" + val + "' ";
        }else if (attr.equalsIgnoreCase("r.lastName") ||
        	attr.equalsIgnoreCase("a.clinicName") ||
        	attr.equalsIgnoreCase("a.provider") ||
        	attr.equalsIgnoreCase("i.medicalId")){

            if (pred.equals("=") || pred.equals("like")){
                sql += " and " + attr + " like '" + val + "%' ";
            }else if (pred.equals("!=")){
                sql += " and " + attr + " not like '" + val + "' ";
            }else if (pred.equals("<=") || pred.equals(">=")){
                sql += " and " + attr + " " + pred + " '" + val + "' ";
            }

        }else if (attr.equalsIgnoreCase("a.appointmentDate")){

            if (pred.equals("=") || pred.equals("like")){
                if (hasTimeSpecified(val)){
                    // generate a.appointmentDate = 1-1-1999 14:12:40
                    sql += " and "+attr+" = "+getDateTimeForDB(val)+" ";
                }else{
                    // generate a.appointmentDate >= 1/1/1999 00:00:00 AND a.appointmentDate < 1/2/1999 00:00:00
                    sql += " and "+attr+" >= "+getDateForDB(val)+" and "+attr+" <= "+getNextDateForDB(val)+" ";
                }
            }else{
                if (hasTimeSpecified(val)){
                    sql += " and "+attr+" "+pred+" "+getDateTimeForDB(val)+" ";
                }else{
                    sql += " and "+attr+" "+pred+" "+getDateForDB(val)+" ";
                }
            }

        }else if (attr.equalsIgnoreCase("r.birthDate")){
            sql += " and "+attr+" = "+getDateForDB(val)+" ";
        }else{
            sql += " and "+attr+" "+pred+" ";
            sql += getValue(attr, val);
            sql += " ";
        }
        return sql;
    }

    private boolean hasTimeSpecified(String val){
        return val.indexOf(":") > 0;
    }

    private String getDateTimeForDB(String val){
        if (val.indexOf("/") > 0){
            // in the format mm/dd/yyyy
            StringTokenizer stk = new StringTokenizer(val, " ");
            String datePortion = stk.nextToken();
            String timePortion = stk.nextToken();

            stk = new StringTokenizer(datePortion, "/");
            String month = stk.nextToken();
            String date = stk.nextToken();
            String year = stk.nextToken();

            return "'"+year+"-"+month+"-"+date+" "+timePortion+"'";
        }else if (val.indexOf("-") > 0){
            // in the format mm-dd-yyyy
            StringTokenizer stk = new StringTokenizer(val, " ");
            String datePortion = stk.nextToken();
            String timePortion = stk.nextToken();

            stk = new StringTokenizer(datePortion, "-");
            String month = stk.nextToken();
            String date = stk.nextToken();
            String year = stk.nextToken();
            return "'"+year+"-"+month+"-"+date+" "+timePortion+"'";
        }else{
            return "'"+val+"'";
        }
    }

    private String getNextDateForDB(String val){
        if (val.indexOf("/") > 0){
            // in the format mm/dd/yyyy
            StringTokenizer stk = new StringTokenizer(val, "/");
            String month = stk.nextToken();
            String date = stk.nextToken();
            String year = stk.nextToken();
            return "'"+year+"-"+month+"-"+date+" 23:59:59'";
        }else if (val.indexOf("-") > 0){
            // in the format mm-dd-yyyy
            StringTokenizer stk = new StringTokenizer(val, "-");
            String month = stk.nextToken();
            String date = stk.nextToken();
            String year = stk.nextToken();
            return "'"+year+"-"+month+"-"+date+" 23:59:59'";
        }else{
            return "'"+val+" 23:59:59'";
        }
    }

    private String getDateForDB(String val){
        if (val.indexOf("/") > 0){
            // in the format mm/dd/yyyy
            StringTokenizer stk = new StringTokenizer(val, "/");
            String month = stk.nextToken();
            String date = stk.nextToken();
            String year = stk.nextToken();
            return "'"+year+"-"+month+"-"+date+"'";
        }else if (val.indexOf("-") > 0){
            // in the format mm-dd-yyyy
            StringTokenizer stk = new StringTokenizer(val, "-");
            String month = stk.nextToken();
            String date = stk.nextToken();
            String year = stk.nextToken();
            return "'"+year+"-"+month+"-"+date+"'";
        }else{
            return "'"+val+"'";
        }
    }

    private String getValue(String attr, String val){
        String retVal = null;

        if (attr.equals("r.referralId")){
            retVal = val;
        }else if (attr.equals("r.gender")){
            if (val.equalsIgnoreCase("m") || attr.equalsIgnoreCase("male")){
                retVal = "'m'";
            }else{
                retVal = "'f'";
            }
        }else if (attr.equals("r.needMedicalMgntSvc") ||
            attr.equals("r.needTherapy") ||
            attr.equals("r.isUrgent") ||
            attr.equals("a.translationSvcNeeded") ||
            attr.equals("a.collateralReceived")){
            if (val.equalsIgnoreCase("1") || val.equalsIgnoreCase("true") ||
                val.equalsIgnoreCase("yes")){
                retVal = "1";
            }else{
                retVal = "0";
            }
        }else{
            retVal = "'"+val+"'";
        }
        return retVal;
    }

}
