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
	 * 加载头像  使用内存缓存
	 * @param context
	 * @param photoId
	 * @return
	 */
	public static Bitmap getPhoto(Context context,int photoId){
		//判断photoid是否为null
		if(photoId == 0){
			return null;
		}
		//先去内存缓存中读取
		SoftReference<Bitmap> ref = cache.get(photoId);
		if(ref != null){
			Bitmap b = ref.get();
			if(b != null){
				return b;
			}
		}
		//内存缓存中没有
		ContentResolver r = context.getContentResolver();
		Uri uri = Data.CONTENT_URI;
		String[] projection = {Data.DATA15};
		Cursor c = r.query(uri, projection, Data._ID+"=?", new String[] {photoId+""}, null);
		Bitmap bitmap = null;
		if(c.moveToNext()){
			byte[] bytes = c.getBlob(0);//读取该字段中的byte[]
			bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			//存入缓存
			cache.put(photoId, new SoftReference<Bitmap>(bitmap));
		}
		c.close();
		return bitmap ;
	}
	
	
}
