package com.suntek.common.persistence;

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
public class PatientLog {

    public static String EMPTY = "&nbsp;";

    public int logId = -1;
    public String clientName = null;
    public String iniCallDate = null;
    public String confirmSendDate = null;
    public String firstCallDate = null;
    public String secCallDate = null;
    public boolean contactClient = false;
    public String numAttempts = null;
    public String notes = null;

    public int getLogId(){
        return logId;
    }

    public String getClientName(){
        if (clientName != null && !clientName.equals("")){
            return clientName;
        }else{
            return EMPTY;
        }
    }

    public String getIniCallDate(){
        if (iniCallDate != null && !iniCallDate.equals("")){
            return iniCallDate;
        }else{
            return EMPTY;
        }
    }

    public String getConfirmSendDate(){
        if (confirmSendDate != null && !confirmSendDate.equals("")){
            return confirmSendDate;
        }else{
            return EMPTY;
        }
    }

    public String getFirstCallDate(){
        if (firstCallDate != null && !firstCallDate.equals("")){
            return firstCallDate;
        }else{
            return EMPTY;
        }
    }

    public String getSecCallDate(){
        if (secCallDate != null && !secCallDate.equals("")){
            return secCallDate;
        }else{
            return EMPTY;
        }
    }

    public String getContactClient(){
        if (contactClient){
            return "yes";
        }else{
            return "no";
        }
    }

    public String getNumAttempts(){
        if (numAttempts != null && !numAttempts.equals("")){
            return numAttempts;
        }else{
            return EMPTY;
        }
    }

    public String getNotes(){
        if (notes != null && !notes.equals("")){
            return notes;
        }else{
            return EMPTY;
        }
    }

}
