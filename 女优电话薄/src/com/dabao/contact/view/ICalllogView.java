package com.dabao.contact.view;

import java.util.List;

import com.dabao.contact.entity.Calllog;

/**
 * 通话记录view层接口
 */
public interface ICalllogView {
	public void setData(List<Calllog> logs);
	public 	void showData();
}
