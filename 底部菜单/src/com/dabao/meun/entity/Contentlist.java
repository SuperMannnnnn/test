package com.dabao.meun.entity;
/**
 *Created by dabao 2016��6��26��
 */
public class Contentlist {
	private String id;
	private String title;
	private String text;
	private String type;
	private String ct;

	public Contentlist(String id, String title, String text, String type,
			String ct) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.type = type;
		this.ct = ct;
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
		return "Contentlist [id=" + id + ", title=" + title + ", text="
				+ text + ", type=" + type + ", ct=" + ct + "]";
	}

}

