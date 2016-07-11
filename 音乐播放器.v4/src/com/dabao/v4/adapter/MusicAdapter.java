package com.dabao.v4.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dabao.v4.R;
import com.dabao.v4.entiy.Music;
import com.dabao.v4.util.ImageLoader;
                                                                                                                                                                 
public class MusicAdapter extends BaseAdapter {

	public int selectIndex;
	private Context context;
	private List<Music> musics;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;

	public MusicAdapter(Context context, List<Music> musics, ListView listView) {
		this.context = context;
		this.musics = musics;
		this.inflater = LayoutInflater.from(context);
		this.imageLoader = new ImageLoader(context, listView);
	}


	@Override
	public int getCount() {
		return musics.size();
	}

	@Override
	public Music getItem(int position) {
		return musics.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_lv_music, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvSinger = (TextView) convertView
					.findViewById(R.id.tvSinger);
			holder.ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
			holder.tvDurtion = (TextView) convertView.findViewById(R.id.tvTime);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		Music music = getItem(position);
		holder.tvName.setText(music.getTitle());
		holder.tvSinger.setText(music.getArtist_name());
		holder.tvDurtion.setText(music.getPublishtime());

		// œ‘ æÕº∆¨
		imageLoader.displayImage(holder.ivPic, music.getPic_small());
		return convertView;
	}

	/**
	 * Õ£÷πœﬂ≥Ã
	 */
	public void stopThread() {
		imageLoader.stopThread();
	}

	class ViewHolder {
		ImageView ivPic;
		TextView tvName;
		TextView tvSinger;
		TextView tvDurtion;
	}

}
