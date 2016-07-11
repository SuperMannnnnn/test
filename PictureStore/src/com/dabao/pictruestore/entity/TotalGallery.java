package com.dabao.pictruestore.entity;

import java.util.List;

/**
 *Created by dabao 2016Äê7ÔÂ5ÈÕ
 */
public class TotalGallery {

	 private boolean  status;
	 private int  total;
	  private List<Gallery> tngou;
	public TotalGallery() {
		super();
	}
	public TotalGallery(boolean status, int total, List<Gallery> tngou) {
		super();
		this.status = status;
		this.total = total;
		this.tngou = tngou;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Gallery> getTngou() {
		return tngou;
	}
	public void setTngou(List<Gallery> tngou) {
		this.tngou = tngou;
	}
	  
	  
	  
	  
}
