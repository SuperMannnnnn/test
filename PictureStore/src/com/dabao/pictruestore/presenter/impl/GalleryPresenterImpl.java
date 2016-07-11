package com.dabao.pictruestore.presenter.impl;

import java.util.List;

import android.util.Log;

import com.dabao.pictruestore.entity.Gallery;
import com.dabao.pictruestore.modell.IGalleryModel;
import com.dabao.pictruestore.modell.IModel.AsynCallback;
import com.dabao.pictruestore.modell.impl.GalleryModelImpl;
import com.dabao.pictruestore.presenter.IGalleryPresenter;
import com.dabao.pictruestore.view.IGalleryView;

/**
 *Created by dabao 2016Äê7ÔÂ4ÈÕ
 */
public class GalleryPresenterImpl implements IGalleryPresenter{

	private IGalleryModel model;
	private IGalleryView view;
	
	
	
	
	
	public GalleryPresenterImpl(IGalleryView view) {
		this.model = new GalleryModelImpl();
		this.view = view;
	}





	@Override
	public void loadGallerys() {
		model.loadGallerys(new AsynCallback() {
			
			@Override
			public void onSuccess(Object success) {
				List<Gallery>gallerys = (List<Gallery>) success;
//				Log.i("dabao", "1111111111111111"+gallerys);
				view.showGalleryList(gallerys);
			}
			
			@Override
			public void onError(Object error) {
			}
		});
		
	}

}
