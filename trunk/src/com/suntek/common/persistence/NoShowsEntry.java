package com.suntek.common.persistence;

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
public class NoShowsEntry {

    public Date apptDate;
    public String firstName;
    public String lastName;
    public String clinic;
    public String provider;

    public String getApptDate(){
        if (apptDate != null){
            return Model.dtf.format(apptDate);
        }else{
            return "&nbsp;";
        }
    }

    public String getFullName(){
        if (lastName != null && firstName != null){
            return lastName+", "+firstName;
        }else if (lastName != null){
            return lastName;
        }else if (firstName != null){
            return firstName;
        }else{
            return "&nbsp;";
        }
    }

    public String getClinic(){
        if (clinic != null){
            return clinic;
        }else{
            return "&nbsp;";
        }
    }

    public String getProvider(){
        if (provider != null){
            return provider;
        }else{
            return "&nbsp;";
        }
    }

}
