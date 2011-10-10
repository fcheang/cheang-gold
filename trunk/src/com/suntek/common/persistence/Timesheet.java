package com.suntek.common.persistence;

import java.util.*;
import java.math.BigDecimal;

public class Timesheet {

	private int id;
	private Date date;
	private String clinic;
	private String staff;
	private String type;
	private BigDecimal totalHours;
	
	public int getId(){
		return id;
	}
	
	public void setId(int aId){
		id = aId;
	}
	
	public Date getDate(){
		return date;
	}
	
	public String getDateStr(){
		if (date != null){
			return Model.df.format(date);
		}else{
			return "&nbsp";
		}
	}
	
	public void setDate(Date aDate){
		date = aDate;
	}
	
	public String getClinic(){
		return clinic;
	}
	
	public void setClinic(String c){
		clinic = c;
	}
	
	public String getStaff(){
		return staff;
	}
	
	public void setStaff(String s){
		staff = s;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String t){
		type = t;
	}
	
	public BigDecimal getTotalHours(){
		return totalHours;
	}
	
	public String getTotalHoursStr(){
		return totalHours.toString();
	}
	
	public void setTotalHours(BigDecimal t){
		totalHours = t;
	}
	
}
