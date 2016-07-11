package com.dabao.v4.fragments;

import com.dabao.v4.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LastMusicFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.last_fragment, null);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
