package com.dabao.contact.adapter;

import java.util.List;

import com.dabao.contact.R;
import com.dabao.contact.entity.Contact;
import com.dabao.contact.util.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter{
	private Context context;
	private List<Contact> contacts;
	private LayoutInflater inflater;
	private Bitmap defaultPhoto;
	
	public ContactAdapter(Context context, List<Contact> contacts) {
		super();
		this.context = context;
		this.contacts = contacts;
		this.inflater = LayoutInflater.from(context);
		defaultPhoto = BitmapFactory.decodeResource(context.getResources(), R.drawable.img01g_15);
	}

	@Override
	public int getCount() {
		return contacts.size();
	}

	@Override
	public Contact getItem(int position) {
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("null")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
		convertView = inflater.inflate(R.layout.item_gv_contact, null);
		holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
		holder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
		convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		//设置内容
		Contact c = getItem(position);
		holder.tvName.setText(c.getName());
		//设置头像
		Bitmap bitmap = BitmapUtils.getPhoto(context, c.getPhotoId());
		if(bitmap != null){
			holder.ivPhoto.setImageBitmap(bitmap);
		}else{
			holder.ivPhoto.setImageBitmap(defaultPhoto);
		}
		return convertView;
	}

	class ViewHolder{
		TextView tvName;
		ImageView ivPhoto;
	}
}
