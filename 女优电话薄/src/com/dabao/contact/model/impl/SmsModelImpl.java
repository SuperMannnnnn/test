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
		// 查询数据库 获取所有的会话信息
		ContentResolver r = YouLuApplication.getApp().getContentResolver();
		Uri uri = Uri.parse("content://mms-sms/conversations");
		// 该uri返回的结果集中的信息要比threads表中所保存的
		// 信息多的多，所以我们需要知道某个uri查询过后得到的结果集
		String[] projection = {
				"thread_id",		//0
				"address",			//1
				"body",					//2
				"date"					//3
		};
		Cursor c = r.query(uri, projection, null, null, "date desc");
		//测试打印 cursor返回结果集的所有列及列的值
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
		/* 测试返回的结果集
		c.moveToFirst();
		for(int i=0; i<c.getColumnCount(); i++){
			String name=c.getColumnName(i);
			String value=c.getString(i);
			Log.i("info", "name:"+name+"-------value:"+value);
		}
		 */
	}

}
