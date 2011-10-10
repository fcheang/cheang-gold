package com.suntek.intakesystem.model;

import com.suntek.common.persistence.*;
import javax.servlet.http.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ModifyReferralAction extends Action {

    public ModifyReferralAction(Model model){
    	super(model);
    }

    public String getName(){
    	return "ModifyReferralAction.do";
    }

    public String perform(HttpServletRequest req){
        return editReferralPage;
    }
}