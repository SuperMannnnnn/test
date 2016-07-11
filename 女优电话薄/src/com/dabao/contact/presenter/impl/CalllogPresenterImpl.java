package com.dabao.contact.presenter.impl;

import java.util.List;

import com.dabao.contact.entity.Calllog;
import com.dabao.contact.model.ICalllogModel;
import com.dabao.contact.model.impl.CalllogModelImpl;
import com.dabao.contact.presenter.ICalllogPresenter;
import com.dabao.contact.view.ICalllogView;

public class CalllogPresenterImpl implements ICalllogPresenter{
	private ICalllogModel model;
	private ICalllogView view;
	
	
	
	public CalllogPresenterImpl(ICalllogView view) {
		this.view = view;
		this.model = new CalllogModelImpl();
	}



	@Override
	public void loadAllCalllogs() {
		List<Calllog>logs = model.findAll();
		view.setData(logs);
		view.showData();
	}

}