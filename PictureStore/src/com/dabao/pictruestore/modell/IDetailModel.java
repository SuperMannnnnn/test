package com.dabao.pictruestore.modell;


/**
 *Created by dabao 2016年7月10日
 */
public interface IDetailModel extends IModel{
	/**
	 * 加载新闻列表展开的回调
	 * @param back
	 */
	void loadDetail(AsynCallback back);
}
