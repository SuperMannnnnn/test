package com.dabao.pictruestore.entity;

import java.util.List;

/**
 *Created by dabao 2016Äê7ÔÂ10ÈÕ
 */
public class Detail {
	private int status;
	private int desc;
	private List<New> detail;
	public Detail() {
		super();
	}
	public Detail(int status, int desc, List<New> detail) {
		super();
		this.status = status;
		this.desc = desc;
		this.detail = detail;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDesc() {
		return desc;
	}
	public void setDesc(int desc) {
		this.desc = desc;
	}
	public List<New> getDetail() {
		return detail;
	}
	public void setDetail(List<New> detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "Detail [status=" + status + ", desc=" + desc + ", detail="
				+ detail + "]";
	}
	
	
	
	
}
