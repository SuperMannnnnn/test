package com.day16324;

import android.widget.TextView;

public class Lyb implements Weapon{

	@Override
	public String getName() {
		return "������";
	}


	@Override
	public void gongji(TextView tv) {
		tv.setText("������������������");
	}
	

}
