package com.day16324;

import android.widget.TextView;

public class Lyb implements Weapon{

	@Override
	public String getName() {
		return "狼牙棒";
	}


	@Override
	public void gongji(TextView tv) {
		tv.setText("哇我挖我挖我我哇哇");
	}
	

}
