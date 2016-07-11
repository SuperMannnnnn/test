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
		// 1. ��ȡ��Ҫ��ʾ������
		Music music = musics.get(position);
		// 2. �ж���û�п���ֱ��ʹ�õ�convertView
		if(convertView!=null){
			// 2.1 ���û�У���ͨ��XML���صõ�
			LayoutInflater inflater = (LayoutInflater)context.getSystemService
				      (Context.LAYOUT_INFLATER_SERVICE);
		}
		// 2.2 ����У���ֱ��ʹ��

		// 3. ��װ������ģ��
		TextView tvName = (TextView) convertView.findViewById(R.id.tv_music_item_name);
		tvName.setText(music.getName());
		TextView tvPath= (TextView) convertView.findViewById(R.id.tv_music_item_path);
		tvPath.setText(music.getPath());
		// 4. ����convertView
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
