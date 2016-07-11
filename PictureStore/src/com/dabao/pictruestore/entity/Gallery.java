package com.dabao.pictruestore.entity;

import java.util.List;

/**
 *Created by dabao 2016��7��4��
 */
public class Gallery {
	private List<Picture>list;
	private int id;
    private int  galleryclass ;//          ͼƬ���� 
    private String title     ;//          ���� 
    private String img     ;//          ͼ����� 
    private int count     ;//          ������ 
    private int rcount     ;//           �ظ��� 
    private int fcount     ;//          �ղ��� 
    private int size     ;//      ͼƬ������ 
	public Gallery() {
		super();
	}
	public Gallery(List<Picture>list,int id, int galleryclass, String title, String img,
			int count, int rcount, int fcount, int size) {
		super();
		this.list = list;
		this.id = id;
		this.galleryclass = galleryclass;
		this.title = title;
		this.img = img;
		this.count = count;
		this.rcount = rcount;
		this.fcount = fcount;
		this.size = size;
	}
	public List<Picture> getList() {
		return list;
	}
	public void setList(List<Picture> list) {
		this.list = list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGalleryclass() {
		return galleryclass;
	}
	public void setGalleryclass(int galleryclass) {
		this.galleryclass = galleryclass;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRcount() {
		return rcount;
	}
	public void setRcount(int rcount) {
		this.rcount = rcount;
	}
	public int getFcount() {
		return fcount;
	}
	public void setFcount(int fcount) {
		this.fcount = fcount;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Gallery [id=" + id + ", galleryclass=" + galleryclass
				+ ", title=" + title + ", img=" + img + ", count=" + count
				+ ", rcount=" + rcount + ", fcount=" + fcount + ", size="
				+ size + "]";
	}
    
    
    
    
}