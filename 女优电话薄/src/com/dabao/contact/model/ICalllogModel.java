package com.dabao.contact.model;

import java.util.List;

import com.dabao.contact.entity.Calllog;

public interface ICalllogModel {
	/**
	 * 查询所有通话记录
	 * @return
	 */
	public List<Calllog> findAll();
}
