package com.dabao.pictruestore.modell;


/**
 *Created by dabao 2016年7月4日
 */
public interface IShowPictureModel extends IModel{
	/**
	 * 加载图片列表展开的回调
	 * @param back
	 */
	void loadGallerys(AsynCallback back,int id);
}
