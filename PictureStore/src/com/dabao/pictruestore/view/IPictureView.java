package com.dabao.pictruestore.view;

import java.util.List;

import com.dabao.pictruestore.entity.Picture;

/**
 *Created by dabao 2016年6月26日
 */
public interface IPictureView {
	/**
	 * 单张图片显示图片到控件上
	 * @param pictures
	 */
	void showPictureList(List<Picture> pictures);
}
