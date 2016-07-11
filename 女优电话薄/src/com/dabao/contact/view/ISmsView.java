package com.dabao.contact.view;

import java.util.List;

import com.dabao.contact.entity.Conversation;

/**
 * 通话记录view层接口
 */
public interface ISmsView {
	public void setData(List<Conversation> cs);
	public 	void showData();
}
