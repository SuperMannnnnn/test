package com.dabao.contact.view;

import java.util.List;

import com.dabao.contact.entity.Contact;

/**
 * 联系人界面view层接口
 */
public interface IContactView {
	/**
	 * 设置数据源
	 * @param contacts  联系人列表
	 */
	public void setData(List<Contact> contacts);
	
	/**
	 * 在界面中显示所有联系人
	 * 调用该方法前必须先设置数据源(setData())
	 */
	public void showData();
}
