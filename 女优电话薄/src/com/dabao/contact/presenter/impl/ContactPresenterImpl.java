 package com.dabao.contact.presenter.impl;

import java.util.List;

import com.dabao.contact.entity.Contact;
import com.dabao.contact.model.CommonCallback;
import com.dabao.contact.model.IContactModel;
import com.dabao.contact.model.impl.ContactModelImpl;
import com.dabao.contact.presenter.IContactPresenter;
import com.dabao.contact.view.IContactView;

public class ContactPresenterImpl implements IContactPresenter {
	
	private IContactModel model;
	private IContactView view;
	

	public ContactPresenterImpl(IContactView view) {
		model = new ContactModelImpl();
		this.view = view;
	}


	@Override
	public void loadAllContacts() {
		model.findAll(new CommonCallback() {
			@Override
			public void onDataLoaded(Object data) {
				@SuppressWarnings("unchecked")
				List<Contact> cs = (List<Contact>) data;
				view.setData(cs);
				view.showData();
			}
		});
	}
	
}
