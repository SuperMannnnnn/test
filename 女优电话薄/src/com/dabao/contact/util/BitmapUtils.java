package com.dabao.contact.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Data;

public class BitmapUtils {
	private static Map<Integer, SoftReference<Bitmap>> cache = new HashMap<Integer, SoftReference<Bitmap>>();
	
	/**
	 * ����ͷ��  ʹ���ڴ滺��
	 * @param context
	 * @param photoId
	 * @return
	 */
	public static Bitmap getPhoto(Context context,int photoId){
		//�ж�photoid�Ƿ�Ϊnull
		if(photoId == 0){
			return null;
		}
		//��ȥ�ڴ滺���ж�ȡ
		SoftReference<Bitmap> ref = cache.get(photoId);
		if(ref != null){
			Bitmap b = ref.get();
			if(b != null){
				return b;
			}
		}
		//�ڴ滺����û��
		ContentResolver r = context.getContentResolver();
		Uri uri = Data.CONTENT_URI;
		String[] projection = {Data.DATA15};
		Cursor c = r.query(uri, projection, Data._ID+"=?", new String[] {photoId+""}, null);
		Bitmap bitmap = null;
		if(c.moveToNext()){
			byte[] bytes = c.getBlob(0);//��ȡ���ֶ��е�byte[]
			bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			//���뻺��
			cache.put(photoId, new SoftReference<Bitmap>(bitmap));
		}
		c.close();
		return bitmap ;
	}
	
	
}
