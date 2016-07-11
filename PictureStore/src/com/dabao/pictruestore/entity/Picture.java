package com.dabao.pictruestore.entity;

import android.graphics.Bitmap;

/**
 *Created by dabao 2016年6月26日
 */
public class Picture {
    private int id;
    private int gallery; //图片库
    private String src; //图片地址
    
    
    
    
    
    
    
    private int width;
	private int height;
	private Bitmap bitmap;
	private Bitmap bigBitmap;
	

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public Bitmap getBigBitmap() {
		return bigBitmap;
	}

	public void setBigBitmap(Bitmap bigBitmap) {
		this.bigBitmap = bigBitmap;
	}

	
	
	
	
	
	
	
    
    
    
    
    
	public Picture() {
		super();
	}
	public Picture(int id, int gallery, String src) {
		super();
		this.id = id;
		this.gallery = gallery;
		this.src = src;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGallery() {
		return gallery;
	}
	public void setGallery(int gallery) {
		this.gallery = gallery;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	@Override
	public String toString() {
		return "Picture [id=" + id + ", gallery=" + gallery + ", src=" + src
				+ "]";
	}
    
    
	
}
