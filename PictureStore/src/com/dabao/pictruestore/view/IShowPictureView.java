package com.dabao.pictruestore.view;

import java.util.List;

import com.dabao.pictruestore.entity.Picture;

/**
 *Created by dabao 2016��7��4��
 */
public interface IShowPictureView {
	/**
	 * ͼ��չ������ʾͼƬ���ؼ���
	 * @param pictures
	 */
	void showGalleryList(List<Picture> Pictures);
}