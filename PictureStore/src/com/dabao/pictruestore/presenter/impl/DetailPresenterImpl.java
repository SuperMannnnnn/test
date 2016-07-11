package com.dabao.pictruestore.presenter.impl;

import java.util.List;

import android.util.Log;

import com.dabao.pictruestore.entity.New;
import com.dabao.pictruestore.modell.IDetailModel;
import com.dabao.pictruestore.modell.IModel.AsynCallback;
import com.dabao.pictruestore.modell.impl.DetailModelImpl;
import com.dabao.pictruestore.presenter.IDetailPresenter;
import com.dabao.pictruestore.view.IDetailView;

/**
 *Created by dabao 2016Äê7ÔÂ10ÈÕ
 */
public class DetailPresenterImpl implements IDetailPresenter{
	private IDetailModel model;
	private IDetailView view;
	
	
	
	public DetailPresenterImpl(IDetailView view) {
		super();
		this.view = view;
		model = new DetailModelImpl();
	}



	@Override
	public void loadDetail() {
		model.loadDetail(new AsynCallback() {
			
			@Override
			public void onSuccess(Object success) {
				List<New>news = (List<New>) success;
				Log.i("DetailPresenterImpl", "bbbbbbbb"+news);
				view.showDetail(news);
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
