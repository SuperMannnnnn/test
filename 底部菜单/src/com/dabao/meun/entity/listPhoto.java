package com.dabao.meun.entity;

public class listPhoto {
	private int gallery;
    private int id;
    private String src;
	public listPhoto() {
		super();
	}
	public listPhoto(int gallery, int id, String src) {
		super();
		this.gallery = gallery;
		this.id = id;
		this.src = src;
	}
	public int getGallery() {
		return gallery;
	}
	public void setGallery(int gallery) {
		this.gallery = gallery;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
    
    
}
