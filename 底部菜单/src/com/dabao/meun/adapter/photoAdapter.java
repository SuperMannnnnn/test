package com.dabao.meun.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.dabao.meun.R;
import com.dabao.meun.entity.Joke;
import com.dabao.meun.entity.Photo;
import com.dabao.meun.entity.listPhoto;

public class photoAdapter extends BaseAdapter{
	
	private Context context;
	private List<listPhoto> photos;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	
	public photoAdapter(Context context, List<listPhoto> photos) {
		super();
		this.context = context;
		this.photos = photos;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return photos.size();
	}

	@Override
	public listPhoto getItem(int position) {
		return photos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_photo, null);
			holder = new ViewHolder();
			holder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv_photo);
			convertView.setTag(holder);
		}
			holder = (ViewHolder) convertView.getTag();
			listPhoto photo = getItem(position);
			//œ‘ æÕº∆¨
			ImageListener listener = ImageLoader.getImageListener(holder.ivPhoto, R.drawable.ic_launcher, R.drawable.ic_launcher);
			imageLoader.get(photo.getSrc(), listener, 200, 200);
			
		return convertView;
	}

	class ViewHolder{
		ImageView ivPhoto;
	}
	
}
