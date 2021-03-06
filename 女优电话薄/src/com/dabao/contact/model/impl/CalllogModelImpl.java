package com.dabao.contact.model.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog.Calls;

import com.dabao.contact.app.YouLuApplication;
import com.dabao.contact.entity.Calllog;
import com.dabao.contact.model.ICalllogModel;

public class CalllogModelImpl implements ICalllogModel{

	@Override
	public List<Calllog> findAll() {
		//��ѯ���ݿ�  contacts     calls��
		ContentResolver r = YouLuApplication.getApp().getContentResolver();
		Uri uri = Calls.CONTENT_URI;
		String[] projection = {
				Calls._ID,			//0 
				"photo_id", 			//1
				"name",				//2
				Calls.NUMBER, 	//3
				Calls.DATE,			//4
				Calls.TYPE			//5
		};
		Cursor c = r.query(uri, projection, null, null, Calls.DATE+" desc");
		List<Calllog>logs = new ArrayList<Calllog>();
		while(c.moveToNext()){
			Calllog log = new Calllog();
			log.setId(c.getInt(0));
			log.setPhotoId(c.getInt(1));
			log.setName(c.getString(2));
			log.setNumber(c.getString(3));
			log.setDate(c.getLong(4));
			log.setType(c.getInt(5));
			logs.add(log);
		}
		c.close();
		return logs;
	}

}
