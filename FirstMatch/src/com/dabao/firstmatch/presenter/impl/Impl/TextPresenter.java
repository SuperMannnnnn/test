package com.dabao.firstmatch.presenter.impl.Impl;

import java.util.List;

import android.util.Log;

import com.dabao.firstmatch.entity.Result;
import com.dabao.firstmatch.model.IModel.AsynCallback;
import com.dabao.firstmatch.model.ITextModel;
import com.dabao.firstmatch.model.impl.TextModel;
import com.dabao.firstmatch.presenter.impl.ITextPresenter;
import com.dabao.firstmatch.view.IGithubView;

/**
 *Created by dabao 2016��7��6��
 */
public class TextPresenter implements ITextPresenter{
	private ITextModel model;
	private IGithubView view;
	
	
	
	
	
	public TextPresenter(IGithubView view) {
		this.model = new TextModel();
		this.view = view;
	}





	@Override
	public void loadText() {
		model.loadText(new AsynCallback() {
			
			@Override
			public void onSuccess(Object success) {
				List<Result>results = (List<Result>) success;
				Log.i("TextPresenter", "1111111111111111"+results);
				view.loadText(results);
			}
			
			@Override
			public void onError(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}