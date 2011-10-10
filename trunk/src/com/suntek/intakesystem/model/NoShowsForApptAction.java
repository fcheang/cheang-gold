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

public class NoShowsForApptAction extends Action {

    public NoShowsForApptAction(Model model) {
        super(model);
    }

    public String perform(HttpServletRequest req) {
        return noshowsApptPage;
    }

    public String getName() {
        return "NoShowsForApptAction.do";
    }
}