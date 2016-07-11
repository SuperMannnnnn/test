package com.dabao.contact.model.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;

import com.dabao.contact.app.YouLuApplication;
import com.dabao.contact.entity.Contact;
import com.dabao.contact.model.CommonCallback;
import com.dabao.contact.model.IContactModel;

public class ContactModelImpl  implements IContactModel{

	@Override
	public void findAll(final CommonCallback callback) {
		new AsyncTask<String, String, List<Contact>>() {
			@Override
			protected List<Contact> doInBackground(String... params) {
				List<Contact>cs = loadAllContacts();
				return cs;
			}
			protected void onPostExecute(List<Contact> result) {
				callback.onDataLoaded(result);
			};
			
		}.execute();
	}

	
	/**
	 * 查询所有联系人
	 *  先查contacts表 获取所有人的id
	 *  根据每个人的id查询data表获取这个人的所有数据
	 */
	protected List<Contact> loadAllContacts() {
		ContentResolver r = YouLuApplication.getApp().getContentResolver();
		// contactUri:  content://com.android.contacts/contacts
		Uri contactUri = Contacts.CONTENT_URI;
		String []columns = {
				Contacts._ID,    //0
				Contacts.PHOTO_ID//1
		};
		Cursor c1 = r.query(contactUri, columns, null, null, null);
		List<Contact>contacts = new ArrayList<Contact>();
		while(c1.moveToNext()){
			Contact c = new Contact();
			int id = c1.getInt(0);
			int photoId = c1.getInt(1);
			c.setId(id);
			c.setPhotoId(photoId);
			//dataUri: content://com.android.contacts/data
			Uri dataUri = Data.CONTENT_URI;
			String[] projection = {
					Data.RAW_CONTACT_ID,
					Data.CONTENT_TYPE,
					Data.DATA1
			};
			Cursor c2 = r.query(dataUri, projection, Data.RAW_CONTACT_ID+"=?", new String[]{id+""}, null);
			while(c2.moveToNext()){
				String mm = c2.getString(1);
				String data = c2.getString(2);
				if(mm.equals(Email.CONTENT_ITEM_TYPE)){
					//data描述的是这个人的email属性
					c.setEmail(data);
				}else if(mm.equals(Phone.CONTENT_ITEM_TYPE)){
					//data描述的这个人的phone属性
					c.setPhone(data);
				}else if(mm.equals("vnd.android.cursor.item/postal-address_v2")){
					//data描述的这个人的address属性
					c.setAddress(data);
				}else if(mm.equals("vnd.android.cursor.item/name")){
					//data描述的这个人的name属性
					c.setName(data);
				}
			}
			c2.close();
			contacts.add(c);
		}
		c1.close();
		return contacts;
	}

}
