package com.demo.propertyreader.models;

public class Address {

	private String permanent;
	private String current;
	public String getPermanent() {
		return permanent;
	}
	public void setPermanent(String permanent) {
		this.permanent = permanent;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	@Override
	public String toString() {
		return "Address [permanent=" + permanent + ", current=" + current + "]";
	}
	
}
