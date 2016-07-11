package com.dabao.pictruestore.modell;

/**
 *Created by dabao 2016年6月26日
 */
public interface IPictureModel extends IModel{
	/**
	 * 加载单张图片回调
	 * @param back
	 */
	void loadPictures(AsynCallback back);
}
