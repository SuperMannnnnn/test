package com.dabao.pictruestore.view;

import java.util.List;

import com.dabao.pictruestore.entity.Picture;

/**
 *Created by dabao 2016��6��26��
 */
public interface IPictureView {
	/**
	 * ����ͼƬ��ʾͼƬ���ؼ���
	 * @param pictures
	 */
	void showPictureList(List<Picture> pictures);
}
