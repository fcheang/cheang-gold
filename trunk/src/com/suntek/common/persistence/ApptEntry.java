package com.suntek.common.persistence;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ApptEntry {

    public int referralId = -1;
    public Date apptDate = null;
    public String firstName = null;
    public String lastName = null;
    public String provider = null;
    public Date checkInTime = null;
    public boolean isCheckedIn = false;

    public ApptEntry() {
    }
}