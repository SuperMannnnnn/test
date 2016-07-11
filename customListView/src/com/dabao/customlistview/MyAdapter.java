package com.dabao.customlistview;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 *Created by dabao 2016Äê7ÔÂ4ÈÕ
 */
public class MyAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<String> list;
	
	
	public MyAdapter(Context context, ArrayList<String> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv = new TextView(context);
		tv.setText(list.get(position));
		return tv;
	}

}
