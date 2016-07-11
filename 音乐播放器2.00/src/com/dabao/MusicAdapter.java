package com.dabao;

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
	public View getView(int position, View convertView, ViewGroup parent) {
		// 1. 获取需要显示的数据
		Music music = musics.get(position);
		// 2. 判断有没有可以直接使用的convertView
		if(convertView!=null){
			// 2.1 如果没有，则通过XML加载得到
			LayoutInflater inflater = (LayoutInflater)context.getSystemService
				      (Context.LAYOUT_INFLATER_SERVICE);
		}
		// 2.2 如果有，则直接使用

		// 3. 组装数据与模板
		TextView tvName = (TextView) convertView.findViewById(R.id.tv_music_item_name);
		tvName.setText(music.getName());
		TextView tvPath= (TextView) convertView.findViewById(R.id.tv_music_item_path);
		tvPath.setText(music.getPath());
		// 4. 返回convertView
		return convertView;
	}

	
	
	
	@Override
	public int getCount() {
		return musics.size();
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
