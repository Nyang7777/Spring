package com.ict.ajax.exam;

public class JSON_VO {
	private String city; 
	private long totalcount, first, second;
	private double fristcount, secondcount;
	
	public JSON_VO(String city, long totalcount, long first, long second, double fristcount, double secondcount) {
		this.city = city;
		this.totalcount = totalcount;
		this.first = first;
		this.second = second;
		this.fristcount = fristcount;
		this.secondcount = secondcount;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(long totalcount) {
		this.totalcount = totalcount;
	}
	public long getFirst() {
		return first;
	}
	public void setFirst(long first) {
		this.first = first;
	}
	public long getSecond() {
		return second;
	}
	public void setSecond(long second) {
		this.second = second;
	}
	public double getFristcount() {
		return fristcount;
	}
	public void setFristcount(double fristcount) {
		this.fristcount = fristcount;
	}
	public double getSecondcount() {
		return secondcount;
	}
	public void setSecondcount(double secondcount) {
		this.secondcount = secondcount;
	}
	
	
	
}
