package com.dabao.contact.model;

import java.util.List;

import com.dabao.contact.entity.Conversation;

/**
 * 短消息相关业务层接口
 */
public interface ISmsModel {
	
	/**
	 * 查询所有的会话数据
	 * @return
	 */
	List<Conversation> findAllConversations();
}
