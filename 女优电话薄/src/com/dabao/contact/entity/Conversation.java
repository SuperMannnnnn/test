package com.dabao.contact.entity;
/**
 * 描述一个会话
 */
public class Conversation {
	private int id;
	private int photoId;
	private String name;
	private String number;
	private String body;
	private long date;
	public Conversation() {
		super();
	}
	public Conversation(int id, int photoId, String name, String number,
			String body, long date) {
		super();
		this.id = id;
		this.photoId = photoId;
		this.name = name;
		this.number = number;
		this.body = body;
		this.date = date;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Conversation [id=" + id + ", photoId=" + photoId + ", name="
				+ name + ", number=" + number + ", body=" + body + ", date="
				+ date + "]";
	}
	
	
}
