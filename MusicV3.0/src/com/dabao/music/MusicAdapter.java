package com.dabao.music;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter{
private Context context;
private List<Music> musics;

	public MusicAdapter(Context context, List<Music> musics) {
	super();
	this.context = context;
	this.musics = musics;
}

	@Override
	public int getCount() {
		
		return musics.size();
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Music music = musics.get(position);
		
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.music_item, null);
		}
		TextView tvName = (TextView) convertView.findViewById(R.id.tv_music_item_name);
		TextView tvPath = (TextView) convertView.findViewById(R.id.tv_music_item_path);
		tvName.setText(music.getName());
		tvPath.setText(music.getPath());
		return convertView;
	}
	
	
	
	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}


}
