package com.dabao.pictruestore.modell;
/**
 *Created by dabao 2016Äê6ÔÂ26ÈÕ
 */
public interface IModel {

	public interface AsynCallback{
		void onSuccess(Object success);

		void onError(Object error);
	}
}
