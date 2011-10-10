package com.suntek.intakesystem.model.report;


import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.*;

import javax.servlet.http.*;
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
public class ShowRefCountReportAction
    extends Action {

    private static final String monthList =
        "<option value=\"0\">Jan</option>\n"+
        "<option value=\"1\">Feb</option>\n"+
        "<option value=\"2\">Mar</option>\n"+
        "<option value=\"3\">Apr</option>\n"+
        "<option value=\"4\">May</option>\n"+
        "<option value=\"5\">Jun</option>\n"+
        "<option value=\"6\">July</option>\n"+
        "<option value=\"7\">Aug</option>\n"+
        "<option value=\"8\">Sept</option>\n"+
        "<option value=\"9\">Oct</option>\n"+
        "<option value=\"10\">Nov</option>\n"+
        "<option value=\"11\">Dec</option>\n";


    public ShowRefCountReportAction(Model m){
        super(m);
    }

    /**
     * getName
     *
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String getName() {
        return "ShowRefCountReportAction.do";
    }

    /**
     * perform
     *
     * @param req HttpServletRequest
     * @return String
     * @todo Implement this com.suntek.common.persistence.Action method
     */
    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession();
        GregorianCalendar cal = new GregorianCalendar();

        // generate fromMonthList
        int m = cal.get(GregorianCalendar.MONTH);
        String fromMonthList = new String(monthList);
        fromMonthList = fromMonthList.replace("\""+m+"\"", "\""+m+"\" selected");
        session.setAttribute("fromMonthList", fromMonthList);

        // generate fromYearList
        String fromYearList = "";
        int y = cal.get(GregorianCalendar.YEAR);
        for (int i=2000; i<=y; i++){
            if (i!=y){
                fromYearList += "<option value=\"" + i + "\" >" + i + "</option>\n";
            }else{
                fromYearList += "<option value=\"" + i + "\" selected>" + i + "</option>\n";
            }
        }
        session.setAttribute("fromYearList", fromYearList);

        // generate toMonthList
        session.setAttribute("toMonthList", fromMonthList);

        // generate toYearList
        session.setAttribute("toYearList", fromYearList);

        // generate insProviderList
        String insProviders = "";
        List list = model.getAllInsuranceProvider();
        for (int i=0; i<list.size(); i++){
            String name = ((InsuranceProvider)list.get(i)).name;
            if (i == 0){
                insProviders += "<option value=\""+name+"\" selected>"+name+"</option>";
            }else{
                insProviders += "<option value=\""+name+"\" >"+name+"</option>";
            }
        }
        session.setAttribute("insProviderList", insProviders);

        return super.refCountReportInputPage;
    }
}
