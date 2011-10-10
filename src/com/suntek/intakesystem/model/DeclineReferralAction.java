package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;

public class DeclineReferralAction extends Action {

    public DeclineReferralAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "DeclineReferralAction.do";
    }

    public String perform(HttpServletRequest req){
        return declineRefPage;
    }
}
