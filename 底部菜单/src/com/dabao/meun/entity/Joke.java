package com.dabao.meun.entity;

import java.util.List;

/**
 * 笑话的属性
 * @author 大宝
 *
 */
public class Joke {

	 private String id;
     private String title;
     private String text;
     private String type;
     private String ct;
     
     
     
     
	public Joke( String title, String text) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.type = type;
		this.ct = ct;
	}
	public Joke() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	@Override
	public String toString() {
		return "Joke [id=" + id + ", title=" + title + ", text=" + text
				+ ", type=" + type + ", ct=" + ct + "]";
	}
	public static void setUrls(List<Joke> jokes) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
