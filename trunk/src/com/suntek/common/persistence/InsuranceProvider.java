package com.suntek.common.persistence;

public class InsuranceProvider {
	public int id;
    public String name;
    public String street;
    public String city;
    public String state;
    public String zipcode;
    public String pn1;
    public String pn2;
    public String fax;
    public String notes;

    public InsuranceProvider(){    	
    }
    
    public InsuranceProvider(String name, String street, String city, String state,
        String zipcode, String pn1, String pn2, String fax, String notes) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.pn1 = pn1;
        this.pn2 = pn2;
        this.fax = fax;
        this.notes = notes;
    }
    
    public boolean equals(Object obj){
    	if (this == obj)
    		return true;
    	if (obj instanceof InsuranceProvider){
    		InsuranceProvider ip = (InsuranceProvider)obj;
    		if (id == ip.id)
    			return true;    
    	}
    	return false;
    }
}