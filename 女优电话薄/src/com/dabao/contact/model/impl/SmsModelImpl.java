package com.dabao.contact.model.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.dabao.contact.app.YouLuApplication;
import com.dabao.contact.entity.Conversation;
import com.dabao.contact.model.ISmsModel;

public class SmsModelImpl implements ISmsModel {

	@Override
	public List<Conversation> findAllConversations() {
		// ��ѯ���ݿ� ��ȡ���еĻỰ��Ϣ
		ContentResolver r = YouLuApplication.getApp().getContentResolver();
		Uri uri = Uri.parse("content://mms-sms/conversations");
		// ��uri���صĽ�����е���ϢҪ��threads�����������
		// ��Ϣ��Ķ࣬����������Ҫ֪��ĳ��uri��ѯ����õ��Ľ����
		String[] projection = {
				"thread_id",		//0
				"address",			//1
				"body",					//2
				"date"					//3
		};
		Cursor c = r.query(uri, projection, null, null, "date desc");
		//���Դ�ӡ cursor���ؽ�����������м��е�ֵ
		List<Conversation> cs = new ArrayList<Conversation>();
		while(c.moveToNext()){
			Conversation con = new Conversation();
			con.setId(c.getInt(0));
			con.setNumber(c.getString(1));
			con.setBody(c.getString(2));
			con.setDate(c.getLong(3));
			cs.add(con);
		}
		c.close();
		return cs;
		/* ���Է��صĽ����
		c.moveToFirst();
		for(int i=0; i<c.getColumnCount(); i++){
			String name=c.getColumnName(i);
			String value=c.getString(i);
			Log.i("info", "name:"+name+"-------value:"+value);
		}
		 */
	}

}
