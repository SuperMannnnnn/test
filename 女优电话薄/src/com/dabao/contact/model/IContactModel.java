package com.dabao.contact.model;

/**
 * 联系人相关业务层接口
 */
public interface IContactModel {

	/**
	 * 查询所有联系人  
	 * @param callback  查询成功后将会调用该callback
	 */
	public void findAll(CommonCallback callback);
}
