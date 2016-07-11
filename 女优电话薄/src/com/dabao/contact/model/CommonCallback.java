package com.dabao.contact.model;

/**
 * 通用的回调接口
 */
public interface CommonCallback {
	/**
	 * 获取数据完毕后 调用该回调方法
	 * @param data
	 */
	public void onDataLoaded(Object data); 
}
