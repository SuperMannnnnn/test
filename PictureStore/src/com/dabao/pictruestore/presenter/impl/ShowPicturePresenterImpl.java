package com.dabao.pictruestore.presenter.impl;

import java.util.List;

import android.util.Log;

import com.dabao.pictruestore.entity.Picture;
import com.dabao.pictruestore.modell.IModel.AsynCallback;
import com.dabao.pictruestore.modell.IShowPictureModel;
import com.dabao.pictruestore.modell.impl.ShowPictureModleImpl;
import com.dabao.pictruestore.presenter.IShowPicturePresenter;
import com.dabao.pictruestore.view.IShowPictureView;

/**
 *Created by dabao 2016Äê7ÔÂ4ÈÕ
 */
public class ShowPicturePresenterImpl implements IShowPicturePresenter{

	private IShowPictureModel model;
	private IShowPictureView view;
	
	
	
	
	
	public ShowPicturePresenterImpl(IShowPictureView view) {
		this.model = new ShowPictureModleImpl();
		this.view = view;
	}









	@Override
	public void loadGallerys(int id) {
		model.loadGallerys(new AsynCallback() {
			
			@Override
			public void onSuccess(Object success) {
				List<Picture>pictures = (List<Picture>) success;
//				Log.i("dabao", "1111111111111111"+pictures);
				view.showGalleryList(pictures);
			}
			
			@Override
			public void onError(Object error) {
			}
		}, id);
	}

}
