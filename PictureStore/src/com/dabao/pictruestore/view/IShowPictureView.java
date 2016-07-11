package com.dabao.pictruestore.view;

import java.util.List;

import com.dabao.pictruestore.entity.Picture;

/**
 *Created by dabao 2016年7月4日
 */
public interface IShowPictureView {
	/**
	 * 图库展开后显示图片到控件上
	 * @param pictures
	 */
	void showGalleryList(List<Picture> Pictures);
}
