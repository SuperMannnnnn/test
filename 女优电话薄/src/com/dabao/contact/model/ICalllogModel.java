package com.dabao.contact.model;

import java.util.List;

import com.dabao.contact.entity.Calllog;

public interface ICalllogModel {
	/**
	 * ��ѯ����ͨ����¼
	 * @return
	 */
	public List<Calllog> findAll();
}
