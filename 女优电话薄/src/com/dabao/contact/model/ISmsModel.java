package com.dabao.contact.model;

import java.util.List;

import com.dabao.contact.entity.Conversation;

/**
 * ����Ϣ���ҵ���ӿ�
 */
public interface ISmsModel {
	
	/**
	 * ��ѯ���еĻỰ����
	 * @return
	 */
	List<Conversation> findAllConversations();
}
