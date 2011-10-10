package com.suntek.intakesystem.model.report;

import com.suntek.common.persistence.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.math.*;


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
public class GenRefCountReportAction
    extends Action {

    public GenRefCountReportAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "GenRefCountReportAction.do";
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
        String retPage = super.reportResultPage;
        String message = "";
        String errMsg = "";

        boolean success = true; 
        try{
            String fromMonthStr = req.getParameter("fromMonth");
            int fromMonth = Integer.valueOf(fromMonthStr).intValue();
            
            String fromYearStr = req.getParameter("fromYear");
            int fromYear = Integer.valueOf(fromYearStr).intValue();
            
            String toMonthStr = req.getParameter("toMonth");
            int toMonth = Integer.valueOf(toMonthStr).intValue();
            
            String toYearStr = req.getParameter("toYear");
            int toYear = Integer.valueOf(toYearStr).intValue();
            
            String insProvider = req.getParameter("insProvider");
            String reportTitle = req.getParameter("reportTitle");
            
            GregorianCalendar esd = new GregorianCalendar(fromYear, fromMonth, 1);
            GregorianCalendar eed = new GregorianCalendar(toYear, toMonth+1, 1);
        	
            message += "<p align=\"center\"><b>";
            if (reportTitle != null && !reportTitle.equals("")){
                message += reportTitle + "<br>";
            }
            message += "</b></p>\n";
            
            message += "<p align=\"center\"><b>"+insProvider+"</b></p>\n";
            	
            message += "<p align=\"center\">"+
                "<table width=100% border=1>\n"+
                "<tr>\n"+
                "<th width=15%><strong>Month</strong></th>\n"+
                "<th width=15%><strong># referral</strong></th>\n"+
                "<th width=15%><string># child</string></th>\n"+
                "<th width=15%><string># adult</string></th>\n"+
                "<th width=15%><string># no shows</string></th>\n"+
                "<th width=15%><string>% no shows</string></th>\n"+ 
                "</tr>\n";

            while (esd.before(eed)){
            	String whichMonth = getMonthYear(esd);            	

            	Date sdForMonth = esd.getTime();
            	esd.add(Calendar.MONTH, 1); // increment counter
            	Date edForMonth = esd.getTime();
            	
            	List refList = model.getReferralForRefCountReport(sdForMonth, edForMonth, insProvider);
            	int numRef = 0;
            	int numChild = 0;
            	int numAdult = 0;
            	int numNoShow = 0;
            	BigDecimal pctNoShow = new BigDecimal(0);
            	
	            for (int i=0; i<refList.size(); i++){
	            	numRef += 1;
	            	Referral ref = (Referral)refList.get(i);
		            Date createDate = ref.getCreateDateForDB();
		            boolean isChild = ref.isChild();
		            if (isChild){
		            	numChild += 1;
		            }else{
		            	numAdult += 1;
		            }
		            int refId = ref.getRefId();
		            boolean hasNoShow = model.hasNoShowAfter(refId, createDate);
		            if (hasNoShow){
		            	numNoShow += 1;
		            }
	            }
	            double pct = 0;
	            if (numRef != 0){
	            	pct = ((double)numNoShow / (double)numRef) * 100;
	            }
	            pctNoShow = new BigDecimal(pct);
	            
                message +=
                    "<tr><td width=25%>"+whichMonth+"</td>\n"+
                    "<td width=15%>"+numRef+"</td>\n"+
                    "<td width=15%>"+numChild+"</td>\n"+
                    "<td width=15%>"+numAdult+"</td>\n"+
                    "<td width=15%>"+numNoShow+"</td>\n"+
                    "<td width=15%>"+pctNoShow.setScale(2, BigDecimal.ROUND_HALF_EVEN)+"%</td>\n"+                    
                    "</tr>\n";

            }
            message += "</table></p>";
            
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message", message);
            String currentTime = super.getCurrentTime();
            session.setAttribute("currentTime", currentTime);
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem generating NoShows report!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }
    
    private String getMonthYear(GregorianCalendar esd){
    	String retVal = String.valueOf(esd.get(Calendar.YEAR));
    	String month = "";
    	switch (esd.get(Calendar.MONTH)){
    	case 0: month = "Jan"; break;    	
    	case 1: month = "Feb"; break;
    	case 2: month = "Mar"; break;
    	case 3: month = "Apr"; break;
    	case 4: month = "May"; break;
    	case 5: month = "Jun"; break;
    	case 6: month = "July"; break;
    	case 7: month = "Aug"; break;
    	case 8: month = "Sept"; break;
    	case 9: month = "Oct"; break;
    	case 10: month = "Nov"; break;
    	case 11: month = "Dec"; break;
    	}
    	return retVal += " " + month;    	 
    }
    
}
