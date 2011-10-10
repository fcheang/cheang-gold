package com.suntek.intakesystem.model.report;

import com.suntek.common.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.text.*;


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
public class ViewApptReportAction extends Action {

    private DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);

    public ViewApptReportAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ViewApptReportAction.do";
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

        // reset session
        session.setAttribute("apptDateRange", null);
        session.setAttribute("doctorAndClinic", null);
        session.setAttribute("apptDayViewTable", null);

        int adMonth = Integer.parseInt(req.getParameter("adMonth"));
        int adDate = Integer.parseInt(req.getParameter("adDate"));
        int adYear = Integer.parseInt(req.getParameter("adYear"));
        Calendar sd = new GregorianCalendar(adYear, adMonth - 1, adDate);

        Calendar ed = null;
        try{
            int adMonth2 = Integer.parseInt(req.getParameter("adMonth2"));
            int adDate2 = Integer.parseInt(req.getParameter("adDate2"));
            int adYear2 = Integer.parseInt(req.getParameter("adYear2"));
            ed = new GregorianCalendar(adYear2, adMonth2 - 1, adDate2);
            if (ed.before(sd) || ed.equals(sd)){
                ed = null;
            }
        }catch(Exception e){
            // endDate not specified
            ed = null;
        }

        String apptClinic = req.getParameter("apptClinic");
        String apptProvider = req.getParameter("apptProvider");
        String[] insList = req.getParameterValues("insuranceOption");
        

        String[] displayCols = req.getParameterValues("list");
        if (ed != null){
            session.setAttribute("apptDateRange",
                                 df.format(sd.getTime()) + " - " +
                                 df.format(ed.getTime()));
        }else{
            session.setAttribute("apptDateRange",
                                 df.format(sd.getTime()));
        }

        List dateRange = getDateRange(sd, ed);
        StringBuffer sb = new StringBuffer();
        
        boolean isInsAny = false;
        if (insList == null || insList.length == 0){
        	isInsAny = true;
        }else{
	        for (int i=0; i<insList.length; i++){
	        	String ins = insList[i];
	        	if (ins.equals("any")){
	        		isInsAny = true;
	        	}
	        }
        }
        
        if (isInsAny){
        	// insurance is any
	        if (!apptClinic.equals("any") && !apptProvider.equals("any")) {
	            session.setAttribute("doctorAndClinic", apptProvider + ", " + apptClinic + " clinic");
	            for (int i=0; i<dateRange.size(); i++){
	                Calendar cal = (Calendar)dateRange.get(i);
	                List appts = model.getApptDayViewByProviderByClinic(cal, apptProvider, apptClinic);
	                String apptTables = getApptTable(appts, cal, displayCols, false, false);
	                sb.append(apptTables);
	            }
	        }
	        else if (!apptClinic.equals("any") && apptProvider.equals("any")) {
	            session.setAttribute("doctorAndClinic", apptClinic + " clinic");
	            for (int i=0; i<dateRange.size(); i++){
	                Calendar cal = (Calendar)dateRange.get(i);
	                List appts = model.getApptDayViewByClinic(cal, apptClinic);
	                String apptTables = getApptTable(appts, cal, displayCols, false, true);
	                sb.append(apptTables);
	            }
	        }
	        else if (apptClinic.equals("any") && !apptProvider.equals("any")) {
	            session.setAttribute("doctorAndClinic", apptProvider);
	            for (int i=0; i<dateRange.size(); i++){
	                Calendar cal = (Calendar)dateRange.get(i);
	                List appts = model.getApptDayViewByProvider(cal, apptProvider);
	                String apptTables = getApptTable(appts, cal, displayCols, true, false);
	                sb.append(apptTables);
	            }
	        }
	        else {
	            // clinic = any && provider = any
	            for (int i=0; i<dateRange.size(); i++){
	                Calendar cal = (Calendar)dateRange.get(i);
	                List appts = model.getApptDayView(cal);
	                String apptTables = getApptTable(appts, cal, displayCols, true, true);
	                sb.append(apptTables);
	            }
	        }
        }else{
        	// insurace selected
        	String insListStr = "";
        	for (int i=0; i<insList.length; i++){
        		if (i+1 != insList.length){
        			insListStr += insList[i] + ", ";
        		}else{
        			insListStr += insList[i];
        		}
        	}
        	
	        if (!apptClinic.equals("any") && !apptProvider.equals("any")) {
	            session.setAttribute("doctorAndClinic", apptProvider + ", " + apptClinic + " clinic" + ", " + insListStr);
	            		
	            for (int i=0; i<dateRange.size(); i++){
	                Calendar cal = (Calendar)dateRange.get(i);
	                List appts = model.getApptDayViewByProviderByClinicByIns(cal, apptProvider, apptClinic, insList);
	                String apptTables = getApptTable(appts, cal, displayCols, false, false);
	                sb.append(apptTables);
	            }
	        }
	        else if (!apptClinic.equals("any") && apptProvider.equals("any")) {
	            session.setAttribute("doctorAndClinic", apptClinic + " clinic" + ", " + insListStr);
	            for (int i=0; i<dateRange.size(); i++){
	                Calendar cal = (Calendar)dateRange.get(i);
	                List appts = model.getApptDayViewByClinicByIns(cal, apptClinic, insList);
	                String apptTables = getApptTable(appts, cal, displayCols, false, true);
	                sb.append(apptTables);
	            }
	        }
	        else if (apptClinic.equals("any") && !apptProvider.equals("any")) {
	            session.setAttribute("doctorAndClinic", apptProvider + ", " + insListStr);
	            for (int i=0; i<dateRange.size(); i++){
	                Calendar cal = (Calendar)dateRange.get(i);
	                List appts = model.getApptDayViewByProviderByIns(cal, apptProvider, insList);
	                String apptTables = getApptTable(appts, cal, displayCols, true, false);
	                sb.append(apptTables);
	            }
	        }
	        else {
	            session.setAttribute("doctorAndClinic", insListStr);	        	
	            // clinic = any && provider = any && insurance selected
	            for (int i=0; i<dateRange.size(); i++){
	                Calendar cal = (Calendar)dateRange.get(i);
	                List appts = model.getApptDayViewByIns(cal, insList);
	                String apptTables = getApptTable(appts, cal, displayCols, true, true);
	                sb.append(apptTables);
	            }
	        }        	
        }

        session.setAttribute("apptDayViewTable", sb.toString());
        return "appt_day_view.jsp";
    }

    private List getDateRange(Calendar sd, Calendar ed){
        List dates = new ArrayList();
        if (ed == null){
            dates.add(sd);
        }else{
            while (sd.before(ed)){
                Calendar cal = new GregorianCalendar();
                cal.setTime(sd.getTime());
                dates.add(cal);
                sd.add(Calendar.DATE, 1);
            }
            Calendar cal = new GregorianCalendar();
            cal.setTime(ed.getTime());
            dates.add(cal);
        }
        return dates;
    }

    private boolean hasEndTime(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("a.endTime")){
                return true;
            }
        }
        return false;
    }

    private boolean hasSSN(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("r.ssn")){
                return true;
            }
        }
        return false;
    }

    private boolean hasDob(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("r.birthDate")){
                return true;
            }
        }
        return false;
    }

    private boolean hasIsNew(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("r.isNew")){
                return true;
            }
        }
        return false;
    }

    private boolean hasPhoneNum(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("r.phoneNumber")){
                return true;
            }
        }
        return false;
    }

    private boolean hasInsurance(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("i.insurance")){
                return true;
            }
        }
        return false;
    }

    private boolean hasMemberId(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("i.memberId")){
                return true;
            }
        }
        return false;
    }

    private boolean hasCopay(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("i.copay")){
                return true;
            }
        }
        return false;
    }
    
    private boolean hasCopayParity(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("i.copayParity")){
                return true;
            }
        }
        return false;
    }
    
    private boolean hasEligEffDate(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("i.eligEffDate")){
                return true;
            }
        }
        return false;
    }
    
    private boolean hasEligTermDate(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("i.eligTermDate")){
                return true;
            }
        }
        return false;
    }
    

    private boolean hasIsChild(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("r.isChild")){
                return true;
            }
        }
        return false;
    }

    private boolean hasLegalGardian(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("r.legalGardian")){
                return true;
            }
        }
        return false;
    }

    private boolean hasLegalGardianPhoneNum(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("r.legalGardianPhoneNumber")){
                return true;
            }
        }
        return false;
    }

    private boolean hasIsUrgent(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("a.isUrgent")){
                return true;
            }
        }
        return false;
    }

    private boolean hasNeedTranslationSvc(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("a.needTranslationSvc")){
                return true;
            }
        }
        return false;
    }

    private boolean hasCollateralReceived(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("a.collateralReceived")){
                return true;
            }
        }
        return false;
    }

    private boolean hasApptNotes(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("a.notes")){
                return true;
            }
        }
        return false;
    }

    private boolean hasApptStatus(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("rs.status")){
                return true;
            }
        }
        return false;
    }
    
    private boolean hasMedicalId(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("i.medicalId")){
                return true;
            }
        }
        return false;
    }    
    
    private boolean hasMedicalIssueDate(String[] cols){
        if (cols == null){
            return false;
        }
        for (int i=0; i<cols.length; i++){
            String col = cols[i];
            if (col.equals("i.medicalIssueDate")){
                return true;
            }
        }
        return false;
    }
    
    
    private String getApptTable(List appts, Calendar cal, String[] displayCols,
                                boolean withClinic, boolean withProvider){
    	StringBuffer sb = new StringBuffer();
        sb.append(df.format(cal.getTime())).append("<br>");
        sb.append("<table width=\"100%\" border=\"1\">\n");
        sb.append("<tr>\n");
        sb.append("<td><b>Time</b></td>\n");
        if (withClinic){
            sb.append("<td><b>Clinic</b></td>\n");
        }
        if (withProvider){
            sb.append("<td><b>Provider</b></td>\n");
        }
        sb.append("<td><b>Patient</b></td>\n");

        if (hasEndTime(displayCols)){
            sb.append("<td><b>EndTime</b></td>\n");
        }
        if (hasSSN(displayCols)){
            sb.append("<td><b>SSN</b></td>\n");
        }
        if (hasDob(displayCols)){
            sb.append("<td><b>Birth Date</b></td>\n");
        }
        if (hasIsNew(displayCols)){
            sb.append("<td><b>Is New</b></td>\n");
        }
        if (hasPhoneNum(displayCols)){
            sb.append("<td><b>Phone#</b></td>\n");
        }
        if (hasEligEffDate(displayCols)){
            sb.append("<td><b>EligEffDate</b></td>\n");
        }
        if (hasEligTermDate(displayCols)){
            sb.append("<td><b>EligTermDate</b></td>\n");
        }        
        if (hasInsurance(displayCols)){
            sb.append("<td><b>Insurance</b></td>\n");
        }
        if (hasMemberId(displayCols)){
            sb.append("<td><b>MemberId</b></td>\n");
        }
        if (hasCopayParity(displayCols)){
            sb.append("<td><b>Copay Parity</b></td>\n");
        }                
        if (hasCopay(displayCols)){
            sb.append("<td><b>Copay non-Parity</b></td>\n");
        }
        if (hasIsChild(displayCols)){
            sb.append("<td><b>Is Child</b></td>\n");
        }
        if (hasLegalGardian(displayCols)){
            sb.append("<td><b>Legal Gardian</b></td>\n");
        }
        if (hasLegalGardianPhoneNum(displayCols)){
            sb.append("<td><b>Legal Gardian Phone#</b></td>\n");
        }
        if (hasIsUrgent(displayCols)){
            sb.append("<td><b>Urgent</b></td>\n");
        }
        if (hasNeedTranslationSvc(displayCols)){
            sb.append("<td><b>Need Translation</b></td>\n");
        }
        if (hasCollateralReceived(displayCols)){
            sb.append("<td><b>Collateral Recived</b></td>\n");
        }
        if (hasApptNotes(displayCols)){
            sb.append("<td><b>Notes</b></td>\n");
        }
        if (hasApptStatus(displayCols)){
        	sb.append("<td><b>Current Status</b></td>\n");
        }
        if (hasMedicalId(displayCols)){
        	sb.append("<td><b>Medi-Cal CIN#</b></td>\n");
        }
        if (hasMedicalIssueDate(displayCols)){
        	sb.append("<td><b>Medi-Cal Issue Date</b></td>\n");
        }
        sb.append("</tr>\n");
        
        List apptIdList = new ArrayList(); 
        for (int i=0; i<appts.size(); i++){
            ApptDayViewEntry appt = (ApptDayViewEntry)appts.get(i);
            if (apptIdList.contains(appt.getApptIdInt())){
            	// ignore duplicate entry, dup entry exist because
            	// each appt can have multiple status, and in this
            	// case we want to get the latest status, the query
            	// is order by createDate desc, so we will get the 
            	// latest status
            	continue;
            }else{
            	apptIdList.add(appt.getApptIdInt());
            }
            sb.append("<tr>\n");
            sb.append("<td>"+appt.getApptTime()+"</td>\n");
            if (withClinic){
                sb.append("<td>"+appt.clinic+"</td>\n");
            }
            if (withProvider){
                sb.append("<td>"+appt.provider+"</td>\n");
            }
            sb.append("<td>"+appt.getFullName()+"</td>\n");
            if (hasEndTime(displayCols)){
                sb.append("<td>"+appt.getEndTime()+"</td>\n");
            }
            if (hasSSN(displayCols)){
                sb.append("<td>" + appt.getSSN() + "</td>\n");
            }
            if (hasDob(displayCols)){
                sb.append("<td>" + appt.getDob() + "</td>\n");
            }
            if (hasIsNew(displayCols)){
                sb.append("<td>" + appt.getIsNew() + "</td>\n");
            }
            if (hasPhoneNum(displayCols)){
                sb.append("<td>" + appt.getPhoneNum() + "</td>\n");
            }
            if (hasEligEffDate(displayCols)){
            	sb.append("<td>" + appt.getEligEffDate() + "</td>\n");
            }
            if (hasEligTermDate(displayCols)){
            	sb.append("<td>" + appt.getEligTermDate() + "</td>\n");
            }                    
            if (hasInsurance(displayCols)){
                sb.append("<td>" + appt.ins + "</td>\n");
            }
            if (hasMemberId(displayCols)){
                sb.append("<td>" + appt.getMemberId() + "</td>\n");
            }
            if (hasCopayParity(displayCols)){
                sb.append("<td>" + appt.getCopayParity() + "</td>\n");
            }            
            if (hasCopay(displayCols)){
                sb.append("<td>" + appt.getCopay() + "</td>\n");
            }
            if (hasIsChild(displayCols)){
                sb.append("<td>" + appt.getIsChild() + "</td>\n");
            }
            if (hasLegalGardian(displayCols)){
                sb.append("<td>" + appt.getLgFullName() + "</td>\n");
            }
            if (hasLegalGardianPhoneNum(displayCols)){
                sb.append("<td>"+appt.getLgPhoneNum()+"</td>\n");
            }
            if (hasIsUrgent(displayCols)){
                sb.append("<td>"+appt.getIsUrgent()+"</td>\n");
            }
            if (hasNeedTranslationSvc(displayCols)){
                sb.append("<td>"+appt.getNeedTranSvc()+"</td>\n");
            }
            if (hasCollateralReceived(displayCols)){
                sb.append("<td>"+appt.getCollRcv()+"</td>\n");
            }
            if (hasApptNotes(displayCols)){
                sb.append("<td>"+appt.getApptNotes()+"</td>\n");
            }
            if (hasApptStatus(displayCols)){
            	sb.append("<td>"+appt.getStatus()+"</td>\n");
            }
            if (hasMedicalId(displayCols)){
            	sb.append("<td>"+appt.getMedicalId()+"</td>\n");
            }
            if (hasMedicalIssueDate(displayCols)){
            	sb.append("<td>"+appt.getMedicalIssueDate()+"</td>\n");
            }
            
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
        sb.append("<br>");
        String s = "<b>"+apptIdList.size()+" records found.</b><br>";
        return s + sb.toString();
    }

}

