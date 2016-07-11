package com.dabao.book.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dabao.book.R;
import com.dabao.book.activity.MainActivity;

@SuppressLint("ValidFragment")
public class MyFragment extends Fragment{
	
	private int resID;

	@SuppressLint("ValidFragment")
	public MyFragment(int resID) {
		super();
		this.resID = resID;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = View.inflate(getActivity(), R.layout.fragment_layout, null);
		ImageView iv = (ImageView) view.findViewById(R.id.iv);
		iv.setImageResource(resID);
		Button btnWelcome = (Button) view.findViewById(R.id.bt);
		btnWelcome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),MainActivity.class));
				
			}
		});
		
		return view;
	}

}
