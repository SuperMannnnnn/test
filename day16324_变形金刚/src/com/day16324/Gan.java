package com.day16324;

import android.widget.TextView;

public class Gan implements Weapon{

	@Override
	public String getName() {
		return "���ǹ";
	}


	@Override
	public void gongji(TextView tv) {
		tv.setText("��������������");
	}

}
