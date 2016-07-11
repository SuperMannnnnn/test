package com.dabao.pictruestore.view;

import java.util.List;

import com.dabao.pictruestore.entity.New;

/**
 *Created by dabao 2016年7月10日
 */
public interface IDetailView {
	/**
	 * 新闻view层显示图片
	 * @param pictures
	 */
	void showDetail(List<New> news);
}
