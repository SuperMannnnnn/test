package com.dabao.contact.presenter.impl;

import java.util.List;

import com.dabao.contact.entity.Conversation;
import com.dabao.contact.model.ISmsModel;
import com.dabao.contact.model.impl.SmsModelImpl;
import com.dabao.contact.presenter.ISmsPresenter;
import com.dabao.contact.view.ISmsView;

public class SmsPresenterImpl implements ISmsPresenter{
	private ISmsModel model;
	private ISmsView view ;
	
	
	
	public SmsPresenterImpl(ISmsView view) {
		this.view = view;
		this.model = new SmsModelImpl();
	}



	@Override
	public void loadAllConversations() {
		List<Conversation> cs = model.findAllConversations();
		view.setData(cs);
		view.showData();
	}
	
	
}
