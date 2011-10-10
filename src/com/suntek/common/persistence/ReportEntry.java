package com.suntek.common.persistence;

import java.math.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ReportEntry {

    public String insurance;

    public int numReferral;
    public int numChild;
    public int numAdult;
    public int numUrgentEval;
    public Date nextAvailApptDate;
    public int numComplaints;

    public int totalWaitDays;
    //public int totalChildWaitDays;
    //public int totalAdultWaitDays;
    public int numScheduled;

    public static String getHeader(){
        StringBuffer sb = new StringBuffer();
        sb.append("<tr>\n");
        sb.append("<th width=15%><strong>&nbsp;</strong></th>\n");
        sb.append("<th width=15%><strong>Number of Referrals</strong></th>\n");
        sb.append("<th width=10%><strong>Child</strong></th>\n");
        sb.append("<th width=10%><strong>Adult</strong></th>\n");
        sb.append("<th width=15%><strong>Number of Urgent Evaluation</strong></th>\n");
        sb.append("<th width=10%><strong>Next available appt. date</strong></th>\n");
        sb.append("<th width=10%><strong>Number of compliants</strong></th>\n");
        sb.append("<th width=10%><strong>Waiting days until next appt.</strong></th>\n");

        //sb.append("<th width=10%><strong>Waiting days until next appt. (Child)</strong></th>\n");
        //sb.append("<th width=10%><strong>Waiting days until next appt. (Adult)</strong></th>\n");

        sb.append("</tr>\n");
        return sb.toString();
    }

    public String getRow(){
        String waitDaysStr = null;
        if (numScheduled == 0){
            waitDaysStr = "None available";
        }else{
            double waitingDays = ((double)totalWaitDays) / ((double)numScheduled);
            BigDecimal bd = new BigDecimal(waitingDays);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            waitDaysStr = bd.toString();
        }

        //String childWaitDayStr = null;


        //String adultWaitDayStr = null;


        StringBuffer sb = new StringBuffer();
        sb.append("<tr>\n");
        sb.append("<td width=15%>").append(insurance).append("</td>\n");
        sb.append("<td width=15%>").append(numReferral).append("</td>\n");
        sb.append("<td width=10%>").append(numChild).append("</td>\n");
        sb.append("<td width=10%>").append(numAdult).append("</td>\n");
        sb.append("<td width=15%>").append(numUrgentEval).append("</td>\n");
        sb.append("<td width=10%>").append("&nbsp;").append("</td>\n");
        sb.append("<td width=15%>").append("&nbsp;").append("</td>\n");
        sb.append("<td width=15%>").append(waitDaysStr).append("</td>\n");


        sb.append("</tr>\n");
        return sb.toString();
    }
}
