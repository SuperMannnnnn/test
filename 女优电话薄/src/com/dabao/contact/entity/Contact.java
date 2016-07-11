package com.dabao.contact.entity;

public class Contact {
	private int id;
	private int photoId;
	private String name;
	private String email;
	private String phone;
	private String address;
	
	
	
	public Contact() {
		super();
	}
	public Contact(int id, int photoId, String name, String email,
			String phone, String address) {
		super();
		this.id = id;
		this.photoId = photoId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", photoId=" + photoId + ", name=" + name
				+ ", email=" + email + ", phone=" + phone + ", address="
				+ address + "]";
	}
	
}
