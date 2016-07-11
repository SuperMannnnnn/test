package com.dabao.pictruestore.adapter;

import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dabao.pictruestore.R;
import com.dabao.pictruestore.adapter.ShowPictureAdapter.ViewHolder;
import com.dabao.pictruestore.entity.New;
import com.dabao.pictruestore.entity.Picture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *Created by dabao 2016年7月10日
 */
public class DetailAdapter extends BaseAdapter{
	private Context context;
	private List<New>news;
	private LayoutInflater inflater;
	private RequestQueue queue;
	
	
	
	
	public DetailAdapter(Context context, List<New> news) {
		super();
		this.context = context;
		this.news = news;
		this.inflater = LayoutInflater.from(context);
		queue = Volley.newRequestQueue(context);
	}

	@Override
	public int getCount() {
		return news.size();
	}

	@Override
	public New getItem(int position) {
		return news.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_new, null);
			holder = new ViewHolder();
			holder.tvDiggCount = (TextView) convertView.findViewById(R.id.tv_digg_count);
			holder.tvRepinCount = (TextView) convertView.findViewById(R.id.tv_repin_count);
			holder.tvSource = (TextView) convertView.findViewById(R.id.tv_source);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();

		New neww = getItem(position);
		holder.tvDiggCount.setText("赞  "+neww.getDigg_count());
		holder.tvRepinCount.setText("收藏  "+neww.getRepin_count());
		holder.tvSource.setText(neww.getSource());
		holder.tvTime.setText(neww.getPublish_time());
		holder.tvTitle.setText(neww.getTitle());
		
		return convertView;
	}

	
	class ViewHolder {
		private TextView tvTitle;
		private TextView tvSource;
		private TextView tvTime;
		private TextView tvDiggCount;
		private TextView tvRepinCount;

	}
}
