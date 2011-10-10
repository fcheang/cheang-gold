package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;

public class ShowupForApptAction extends Action {

    public ShowupForApptAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "ShowupForApptAction.do";
    }

    public String perform(HttpServletRequest req){
        return completeRefPage;
    }
}
