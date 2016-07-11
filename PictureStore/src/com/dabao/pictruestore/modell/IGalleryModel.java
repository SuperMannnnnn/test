package com.dabao.pictruestore.modell;


/**
 *Created by dabao 2016年7月4日
 */
public interface IGalleryModel extends IModel{
	/**
	 * 加载图库列表回调
	 * @param back
	 */
	void loadGallerys(AsynCallback back);
}
