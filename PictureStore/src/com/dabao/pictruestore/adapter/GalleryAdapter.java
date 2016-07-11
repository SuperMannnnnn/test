package com.dabao.pictruestore.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.dabao.pictruestore.R;
import com.dabao.pictruestore.entity.Gallery;
import com.dabao.pictruestore.modell.IModel.AsynCallback;
import com.dabao.pictruestore.modell.impl.GalleryModelImpl;
import com.dabao.pictruestore.ui.UrlConsts;

public class GalleryAdapter extends BaseAdapter {
	private Context context;
	private List<Gallery> gallerys;
	private LayoutInflater inflater;
	private RequestQueue queue;
	
	
	
	
	
	public GalleryAdapter(Context context, List<Gallery> gallerys) {
		super();
		this.context = context;
		this.gallerys = gallerys;
		this.inflater = LayoutInflater.from(context);
		queue = Volley.newRequestQueue(context);

	}

	@Override
	public int getCount() {
		return gallerys.size();
	}

	@Override
	public Gallery getItem(int position) {
		return gallerys.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_picture_girl, null);
			holder = new ViewHolder();
			holder.ivPhoto = (ImageView) convertView
					.findViewById(R.id.iv_picture);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvSize = (TextView) convertView.findViewById(R.id.tv_size);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();

		Gallery gallery = getItem(position);
		holder.tvName.setText(gallery.getTitle());
		holder.tvSize.setText(gallery.getSize()+"уе");
		
		ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());
		
		ImageListener listener = ImageLoader.getImageListener(holder.ivPhoto,
				R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get(UrlConsts.URL_GALLERY_COVER+gallery.getImg(), listener);
		return convertView;
	}

	class ViewHolder {
		private ImageView ivPhoto;
		private TextView tvName;
		private TextView tvSize;

	}

	private class BitmapCache implements ImageCache{
		int max = 10*1024*1024;
		LruCache<String, Bitmap> bmps = new LruCache<String, Bitmap>(max){
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
