package com.dabao.contact.view;

import java.util.List;

import com.dabao.contact.entity.Conversation;

/**
 * ͨ����¼view��ӿ�
 */
public interface ISmsView {
	public void setData(List<Conversation> cs);
	public 	void showData();
}
