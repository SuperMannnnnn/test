package com.dabao.pictruestore.fragment;

import com.dabao.pictruestore.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *Created by dabao 2016Äê6ÔÂ26ÈÕ
 */
public class JokeFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_joke, null);
		return view;
	}
}
