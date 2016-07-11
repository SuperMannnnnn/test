package com.dabao.contact.presenter.impl;

import java.util.List;

import com.dabao.contact.entity.Calllog;
import com.dabao.contact.model.ICalllogModel;
import com.dabao.contact.model.impl.CalllogModelImpl;
import com.dabao.contact.presenter.IDialPresenter;
import com.dabao.contact.view.IDialView;

public class DialPresenterImpl implements IDialPresenter{
	private ICalllogModel model;
	private IDialView view;
	
	
	
	
	public DialPresenterImpl(IDialView view) {
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
