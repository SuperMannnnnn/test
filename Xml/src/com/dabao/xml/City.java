package com.dabao.xml;

public class City {
	private int id;
	private int pid;
	private String name;
	private String zipCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public City() {
		super();
	}
	public City(int id, int pid, String name, String zipCode) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.zipCode = zipCode;
	}
	
}
