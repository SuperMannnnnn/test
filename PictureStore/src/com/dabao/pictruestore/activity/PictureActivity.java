package com.dabao.pictruestore.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.dabao.pictruestore.R;
import com.dabao.pictruestore.fragment.NewFragment;
import com.dabao.pictruestore.fragment.GirlFragment;
import com.dabao.pictruestore.fragment.JokeFragment;
import com.dabao.pictruestore.fragment.MoreFragment;

/**
 *Created by dabao 2016Äê6ÔÂ26ÈÕ
 */
public class PictureActivity extends FragmentActivity implements
OnPageChangeListener, OnCheckedChangeListener{

	private RadioGroup radioGroup;
	private RadioButton btnTabMenuGril;
	private RadioButton btnTabMenuJoke;
	private RadioButton btnTabMenuGrilAndJoke;
	private RadioButton btnTabMenuMore;

	private List<Fragment> fragments;
	private ViewPager vp;
	private FragmentPagerAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
		initView();

		setListeners();

		setAdapter();
		
			}
	private void setAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new GirlFragment());
		fragments.add(new JokeFragment());
		fragments.add(new NewFragment());
		fragments.add(new MoreFragment());
		adapter = new InnerFragmentPagerAdapter(getSupportFragmentManager());
		vp.setAdapter(adapter);
	}
	private void setListeners() {
		vp.setOnPageChangeListener(this);
		radioGroup.setOnCheckedChangeListener(this);
	}
	private void initView() {
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		btnTabMenuGril = (RadioButton) findViewById(R.id.btn_tab_menu_gril);
		btnTabMenuJoke = (RadioButton) findViewById(R.id.btn_tab_menu_joke);
		btnTabMenuGrilAndJoke = (RadioButton) findViewById(R.id.btn_tab_menu_grilandjoke);
		btnTabMenuMore = (RadioButton) findViewById(R.id.btn_tab_menu_more);
		vp = (ViewPager) findViewById(R.id.vp);
	}
	
	
	
	
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.btn_tab_menu_gril:
			vp.setCurrentItem(0);
			break;
		case R.id.btn_tab_menu_joke:
			vp.setCurrentItem(1);
			break;
		case R.id.btn_tab_menu_grilandjoke:
			vp.setCurrentItem(2);
			break;
		case R.id.btn_tab_menu_more:
			vp.setCurrentItem(3);
			break;
		}
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}
	@Override
	public void onPageSelected(int position) {
		switch (position) {
		case 0:
			btnTabMenuGril.setChecked(true);
			break;
		case 1:
			btnTabMenuJoke.setChecked(true);
			break;
		case 2:
			btnTabMenuGrilAndJoke.setChecked(true);
			break;
		case 3:
			btnTabMenuMore.setChecked(true);
			break;
		}
	}
	
	
	

	class InnerFragmentPagerAdapter extends FragmentPagerAdapter {

		public InnerFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}
	}
	
	
}
