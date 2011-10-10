package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import java.util.*;
import javax.servlet.http.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ViewAllProviderAction extends Action {

    public ViewAllProviderAction(Model m) {
        super(m);
    }

    public String getName(){
        return "ViewAllProviderAction.do";
    }

    public String perform(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        boolean success = true;
        String errMsg = "";
        String retPage = super.providerInfoPage;
        String message = "";
        try{
            List<Provider> list = model.getAllProvider();
            if (list.size() == 0){
                message = "<b>No Provider found!</b>";
            }else{
                message = "<h3>"+list.size()+" Provider(s) found:</h3><p>";
                message = message +
                           "<table border=1><tr>\n"+
                           "<th width=5%><strong>Id</strong></th>\n"+
                           "<th width=15%><strong>Name</strong></th>\n"+
                           "<th width=15%><strong>Title</strong></th>\n"+
                           "</tr>\n";

                for (int i=0; i<list.size(); i++){
                    Provider provider = (Provider)list.get(i);
                    message += "<tr>\n"+
                    "<td width=5%><a href=\"ViewProviderDetailAction.do?"+Action.PROVIDER_ID+"="+provider.getProviderId()+"\">"+provider.getProviderId()+"</td>\n"+
                    "<td width=15%>"+provider.getName()+"</td>\n"+
                    "<td width=15%>"+provider.getTitle()+"</td>\n</tr>\n";
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
            session.setAttribute("message", "<font color=\"red\"><b>Problem displaying Provider!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

}