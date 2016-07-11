package com.dabao.v4.fragments;

import com.dabao.v4.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HotMusicFragment extends Fragment {
	private ListView listView ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_music_list, null);
		setViews(view);
		
		return view;
	}
	private void setViews(View view) {
		listView = (ListView) view.findViewById(R.id.listView);
	}
}
