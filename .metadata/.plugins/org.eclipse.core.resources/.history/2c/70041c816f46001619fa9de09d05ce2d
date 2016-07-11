package com.dabao.pictruestore.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.dabao.pictruestore.R;
import com.dabao.pictruestore.entity.Picture;
import com.dabao.pictruestore.ui.UrlConsts;

/**
 *Created by dabao 2016Äê7ÔÂ9ÈÕ
 */
public class DisplayPictureAdapter extends BaseAdapter{
	private Context context;
	private List<Picture> pictures;
	private LayoutInflater inflater;
	private RequestQueue queue;


	public DisplayPictureAdapter(Context context, List<Picture> pictures) {
		super();
		this.context = context;
		this.pictures = pictures;
		this.inflater = LayoutInflater.from(context);
		queue = Volley.newRequestQueue(context);

	}

	@Override
	public int getCount() {
		return pictures.size();
	}

	@Override
	public Picture getItem(int position) {
		return pictures.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_show_picture, null);
			holder = new ViewHolder();
			holder.ivPhoto = (ImageView) convertView
					.findViewById(R.id.iv_show_picture);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();

		Picture picture = getItem(position);

		ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());

		ImageListener listener = ImageLoader.getImageListener(holder.ivPhoto,
				R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get(UrlConsts.URL_PICTURE + picture.getSrc(), listener);
		return convertView;
	}

	class ViewHolder {
		private ImageView ivPhoto;

	}

	private class BitmapCache implements ImageCache {
		int max = 10 * 1024 * 1024;
		LruCache<String, Bitmap> bmps = new LruCache<String, Bitmap>(max) {
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount();
			};
		};

		@Override
		public Bitmap getBitmap(String url) {
			return bmps.get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			bmps.put(url, bitmap);

		}

	}

}
