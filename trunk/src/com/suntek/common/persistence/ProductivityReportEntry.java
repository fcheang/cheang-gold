package com.suntek.common.persistence;

import java.util.*;
import java.math.*;

public class ProductivityReportEntry implements Comparable {

	private String staff;
	private Date date;
	private BigDecimal hoursBilled;
	private BigDecimal hoursWorked;
	private BigDecimal hoursScheduled;	
	private int numScheduled;
	private int numSeen;
	private String clinic;
	
	public int compareTo(Object o){
		ProductivityReportEntry entry = (ProductivityReportEntry)o;
		if (this.staff.compareTo(entry.staff) < 0){
			return -1;
		}else if (this.staff.compareTo(entry.staff) == 0){
			return this.clinic.compareTo(entry.clinic);
		}else{ // > 0
			return 1;
		}
	}
	
	public boolean equals(Object o){
		if (o instanceof ProductivityReportEntry){
			ProductivityReportEntry entry = (ProductivityReportEntry)o;			
			if (this.staff.equals(entry.staff) && (this.clinic.equals(entry.clinic))){
				return true;
			}
		}
		return false;
	}
	
	public String getStaff(){
		return staff;
	}
	
	public void setStaff(String s){
		if (s != null){
			staff = s;
		}else{
			staff = "Unknown";
		}
	}
	
	public Date getDate(){
		return date;
	}
	
	public String getDateStr(){
		return Model.df.format(date);
	}
	
	public void setDate(Date d){
		date = d;
	}
	
	public BigDecimal getHoursBilled(){
		return hoursBilled;
	}
	
	public String getHoursBilledStr(){
		if (hoursBilled != null){
			return hoursBilled.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		}else{
			return "Unknown";
		}
	}
	
	public void addHoursBilled(BigDecimal bd){
		BigDecimal bdScaled = null;
		if (bd != null){
			bdScaled = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		}else{
			return;
		}
		if (hoursBilled == null){
			hoursBilled = bdScaled; 
		}else{
			hoursBilled = hoursBilled.add(bdScaled);
		}
	}
	
	public BigDecimal getHoursWorked(){		
		return hoursWorked;
	}
	
	public String getHoursWorkedStr(){
		if (hoursWorked != null){
			return hoursWorked.toString();
		}else{
			return "Unknown";
		}
	}
	
	public void addHoursWorked(BigDecimal bd){
		BigDecimal bdScaled = null;
		if (bd != null){
			bdScaled = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		}else{
			return;
		}
		if (hoursWorked == null){
			hoursWorked = bdScaled; 
		}else{
			hoursWorked = hoursWorked.add(bdScaled);
		}
	}

	public BigDecimal getHoursScheduled(){
		return hoursScheduled;
	}
	
	public String getHoursScheduledStr(){
		if (hoursScheduled != null){
			return hoursScheduled.toString();
		}else{
			return "0";
		}
	}
	
	public void addHoursScheduled(BigDecimal bd){
		BigDecimal bdScaled = null;
		if (bd != null){
			bdScaled = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		}else{
			return;
		}
		if (hoursScheduled == null){
			hoursScheduled = bdScaled; 
		}else{
			hoursScheduled = hoursScheduled.add(bdScaled);
		}
		
	}
	
	public int getNumScheduled(){
		return numScheduled;
	}
	
	public void addNumScheduled(int ns){		
		numScheduled += ns;
	}
	
	public int getNumSeen(){
		return numSeen;
	}
	
	public void addNumSeen(int ns){
		numSeen += ns;
	}
	
	public String getDailyProductivity(){
		if (hoursBilled != null && hoursWorked != null){
			if (!hoursWorked.equals(BigDecimal.ZERO)){
				double hrsBilled = hoursBilled.doubleValue();
				double hrsWorked = hoursWorked.doubleValue();
				double pd = (hrsBilled / hrsWorked) * 100;
				long pdLong = (long)pd;
				if ( (pd - pdLong) >= 0.5){
					pdLong += 1;
				}
				BigDecimal pdBD = new BigDecimal(pdLong);
				return pdBD.toString() + "%";
			}else{
				return "Unknown"; // Divide by zero
			}
		}else{
			return "Unknown";
		}
	}
	
	public String getNoShowRate(){
		if (numScheduled > 0){
			double pctNoShow = (((double)(numScheduled - numSeen)) / ((double)numScheduled)) * 100;
			long pctNoShowLong = (long)pctNoShow;
			if ( (pctNoShow - pctNoShowLong) >= 0.5 ){
				pctNoShowLong += 1;
			}
			return new BigDecimal(pctNoShowLong).toString() + "%";
		}else{
			// numScheduled is zero, then NoShow rate is not interesting 
			return "0%";
		}
	}
	
	public String getPercentScheduled(){
		if (hoursScheduled != null && hoursWorked != null){
			if (!hoursWorked.equals(BigDecimal.ZERO)){
				double hrsScheduled = hoursScheduled.doubleValue();
				double hrsWorked = hoursWorked.doubleValue();
				double ps = (hrsScheduled / hrsWorked) * 100;
				long psLong = (long)ps;
				if ( (ps - psLong) >= 0.5 ){
					psLong += 1;
				}
				return new BigDecimal(psLong).toString()+"%";
			}else{
				return "Unknown"; // Divide by zero
			}
		}else{
			return "Unknown";
		}
	}
	
	public String getClinic(){
		return clinic;
	}
	
	public void setClinic(String c){
		if (c != null){
			clinic = c;
		}else{
			clinic = "Unknown";
		}
	}
}
