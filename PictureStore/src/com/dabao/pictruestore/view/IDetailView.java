package com.dabao.pictruestore.view;

import java.util.List;

import com.dabao.pictruestore.entity.New;

/**
 *Created by dabao 2016��7��10��
 */
public interface IDetailView {
	/**
	 * ����view����ʾͼƬ
	 * @param pictures
	 */
	void showDetail(List<New> news);
}