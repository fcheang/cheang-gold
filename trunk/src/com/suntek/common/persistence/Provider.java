package com.suntek.common.persistence;

import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Steve Cheang
 * @version 1.0
 */

public class Provider {

    private int providerId;
    private String name;
    private String title;
    private List<InsuranceProvider> allCredentials;
    private List<InsuranceProvider> credentials;
    private boolean canEditCredential = false;

    public Provider() {
    }

    public Provider(int id, String n, String p){
    	providerId = id;
        name = n;
        title = p;
    }
    
    public void setCanEditCredential(boolean flag){
    	canEditCredential = flag;
    }

    public List<InsuranceProvider> getCredentials() {
		return credentials;
	}

    public String getCredentialsAsHTML(){
    	String editableFlag = "";
    	if (!canEditCredential){
    		editableFlag = "disabled";
    	}    	
    	StringBuilder sb = new StringBuilder();  
    	for (InsuranceProvider c : allCredentials){
    		int myC = credentials.indexOf(c);
    		if (myC < 0){
    			sb.append("<input type=\"checkbox\" name=\""+c.name+"\" value=\"true\" "+editableFlag+">"+c.name+"<br>");
    		}else{
    			sb.append("<input type=\"checkbox\" name=\""+c.name+"\" value=\"true\" checked=\"true\" "+editableFlag+">"+c.name+"<br>");    			
    		}
    	}
    	return sb.toString();
    }
    
	public void setCredentials(List<InsuranceProvider> credentials) {
		this.credentials = credentials;
	}

	public void setName(String n){
        name = n;
    }

    public void setProviderId(int pid){
        providerId = pid;
    }

    public String getName(){
        if (name == null){
            return "&nbsp;";
        }else{
            return name;
        }
    }

    public int getProviderId(){
    	return providerId;
    }

	public String getTitle() {
		if (title == null){
			return "&nbsp;";
		}else{
			return title;
		}
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAllCredentials(List<InsuranceProvider> allCredentials) {
		this.allCredentials = allCredentials;
	}
}