package com.suntek.common.persistence;

public class Clinic {
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String pn1First;
    private String pn1Second;
    private String pn1Third;
    private String pn2First;
    private String pn2Second;
    private String pn2Third;
    private String faxFirst;
    private String faxSecond;
    private String faxThird;
    private String notes;

    public Clinic(){
    }

    public Clinic(String name, String street, String city, String state,
        String zipcode, String pn1, String pn2, String fax, String notes) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        if (pn1 != null){
            this.pn1First = getFirst(pn1);
            this.pn1Second = getSecond(pn1);
            this.pn1Third = getThird(pn1);
        }
        if (pn2 != null){
            this.pn2First = getFirst(pn2);
            this.pn2Second = getSecond(pn2);
            this.pn2Third = getThird(pn2);
        }
        if (fax != null){
            this.faxFirst = getFirst(fax);
            this.faxSecond = getSecond(fax);
            this.faxThird = getThird(fax);
        }
        this.notes = notes;
    }

    public String getName(){
        return name;
    }
    public void setName(String n){
        name = n;
    }
    public String getStreet(){
        return street;
    }
    public void setStreet(String s){
        street = s;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String c){
        city = c;
    }
    public String getState(){
        return state;
    }
    public void setState(String s){
        state = s;
    }
    public String getZipcode(){
        return zipcode;
    }
    public void setZipcode(String z){
        zipcode = z;
    }
    public String getPn1(){
        String pn1 = null;
        if (pn1First != null){
            pn1 = pn1First + "-";
        }
        if (pn1Second != null){
            if (pn1 == null)
                pn1 = pn1Second + "-";
            else
                pn1 += pn1Second + "-";
        }
        if (pn1Third != null){
            if (pn1 == null)
                pn1 = pn1Third;
            else
                pn1 += pn1Third;
        }
        return pn1;

    }
    public String getPn1ForDB(){
        String retVal = null;
        if (pn1First != null){
            retVal = pn1First;
        }
        if (pn1Second != null){
            if (retVal == null)
                retVal = pn1Second;
            else
                retVal += pn1Second;
        }
        if (pn1Third != null){
            if (retVal == null)
                retVal = pn1Third;
            else
                retVal += pn1Third;
        }
        return retVal;
    }
    public void setPn1(String p){
        if (p != null){
            pn1First = getFirst(p);
            pn1Second = getSecond(p);
            pn1Third = getThird(p);
        }
    }

    public String getPn2(){
        String pn2 = null;
        if (pn2First != null){
            pn2 = pn2First + "-";
        }
        if (pn2Second != null){
            if (pn2 == null)
                pn2 = pn2Second + "-";
            else
                pn2 += pn2Second + "-";
        }
        if (pn2Third != null){
            if (pn2 == null)
                pn2 = pn2Third;
            else
                pn2 += pn2Third;
        }
        return pn2;

    }
    public String getPn2ForDB(){
        String retVal = null;
        if (pn2First != null){
            retVal = pn2First;
        }
        if (pn2Second != null){
            if (retVal == null)
                retVal = pn2Second;
            else
                retVal += pn2Second;
        }
        if (pn2Third != null){
            if (retVal == null)
                retVal = pn2Third;
            else
                retVal += pn2Third;
        }
        return retVal;
    }
    public void setPn2(String p){
        if (p != null){
            pn2First = getFirst(p);
            pn2Second = getSecond(p);
            pn2Third = getThird(p);
        }
    }

    public String getFax(){
        String fax = null;
        if (faxFirst != null){
            fax = faxFirst + "-";
        }
        if (faxSecond != null){
            if (fax == null)
                fax = faxSecond + "-";
            else
                fax += faxSecond + "-";
        }
        if (faxThird != null){
            if (fax == null)
                fax = faxThird;
            else
                fax += faxThird;
        }
        return fax;
    }
    public String getFaxForDB(){
        String retVal = null;
        if (faxFirst != null){
            retVal = faxFirst;
        }
        if (faxSecond != null){
            if (retVal == null)
                retVal = faxSecond;
            else
                retVal += faxSecond;
        }
        if (faxThird != null){
            if (retVal == null)
                retVal = faxThird;
            else
                retVal += faxThird;
        }
        return retVal;
    }
    public void setFax(String p){
        if (p != null){
            faxFirst = getFirst(p);
            faxSecond = getSecond(p);
            faxThird = getThird(p);
        }
    }
    public String getNotes(){
        return notes;
    }
    public void setNotes(String n){
        notes = n;
    }
    public String getFullAddress(){
        String fullAddress = null;
        if (street != null){
            fullAddress = street + "<br>";
        }
        if (city != null){
            if (fullAddress == null)
                fullAddress = city + " ";
            else
                fullAddress += city + " ";
        }
        if (state != null){
            if (fullAddress == null)
                fullAddress = state + " ";
            else
                fullAddress += state + " ";
        }
        if (zipcode != null){
            if (fullAddress == null)
                fullAddress = zipcode + "<br>";
            else
                fullAddress += zipcode + "<br>";
        }
        return fullAddress;
    }

    private String getFirst(String pn){
        if (pn.length() == 10){
            return pn.substring(0, 3);
        }else{
            return null;
        }
    }

    private String getSecond(String pn){
        if (pn.length() == 10){
            return pn.substring(3, 6);
        }else if (pn.length() == 7){
            return pn.substring(0, 3);
        }else{
            return null;
        }
    }

    private String getThird(String pn){
        if (pn.length() == 10){
            return pn.substring(6, 10);
        }else if (pn.length() == 7){
            return pn.substring(3, 7);
        }else{
            return pn;
        }
    }

}