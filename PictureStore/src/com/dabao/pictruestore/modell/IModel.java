package com.dabao.pictruestore.modell;
/**
 *Created by dabao 2016��6��26��
 */
public interface IModel {

	public interface AsynCallback{
		void onSuccess(Object success);

		void onError(Object error);
	}
}
