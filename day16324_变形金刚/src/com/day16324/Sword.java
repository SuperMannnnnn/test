package com.day16324;

import android.widget.TextView;

public class Sword implements Weapon{

	public String getName() {
		
		return "���콣";
	}

	public void gongji(TextView tv) {
		
		tv.setText("������������");
	}

}
