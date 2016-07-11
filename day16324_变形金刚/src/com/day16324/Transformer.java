package com.day16324;

import android.widget.TextView;

public class Transformer {

	private Weapon w;
	public void setWeapon(Weapon w){
		this.w =w;
		
	}
	void attack(TextView tv){
		if(w == null){
			tv.setText("”√—¿“ß");
			return;
		}
		tv.setText("”√"+w.getName()+tv.getText());
		w.gongji(tv);
	}
		
	
	
	
	
	
	
	
}
