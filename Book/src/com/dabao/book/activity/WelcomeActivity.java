package com.dabao.book.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dabao.book.R;
import com.dabao.book.fragment.MyFragment;

public class WelcomeActivity extends FragmentActivity {
	private ViewPager vp;
	private List<Fragment> list = new ArrayList<Fragment>();
	private int [] res = {R.drawable.yaoming,};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		
		initView();
		
		
	}

	private void initView() {
		vp = (ViewPager) findViewById(R.id.vp);
		for (int i = 0; i < 1; i++) {
			
			MyFragment f = new MyFragment(res[i]);
			list.add(f);
		}
		
		MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());
		vp.setAdapter(adapter);
		vp.setCurrentItem(0);
		
	}
	
	
	class MyFragmentAdapter extends FragmentPagerAdapter{

		public MyFragmentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			return list == null? 0:list.size();
		}
		
	}
	

}
