package com.dabao.meun.fragment;

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

import com.dabao.meun.R;

public class MainActivity extends FragmentActivity implements
		OnPageChangeListener, OnCheckedChangeListener {
	private RadioGroup radioGroup;
	private RadioButton btnTabMenuDeal;
	private RadioButton btnTabMenuNearby;
	private RadioButton btnTabMenuMy;
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
		fragments.add(new JokeFragment());
		fragments.add(new PhotoFragment());
		fragments.add(new MoreFragment());
		fragments.add(new com.dabao.meun.fragment.MyFragment());
		adapter = new InnerFragmentPagerAdapter(getSupportFragmentManager());
		vp.setAdapter(adapter);
	}

	private void setListeners() {
		vp.setOnPageChangeListener(this);
		radioGroup.setOnCheckedChangeListener(this);
	}

	private void initView() {
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		btnTabMenuDeal = (RadioButton) findViewById(R.id.btn_tab_menu_deal);
		btnTabMenuNearby = (RadioButton) findViewById(R.id.btn_tab_menu_nearby);
		btnTabMenuMy = (RadioButton) findViewById(R.id.btn_tab_menu_my);
		btnTabMenuMore = (RadioButton) findViewById(R.id.btn_tab_menu_more);
		vp = (ViewPager) findViewById(R.id.vp);
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
			btnTabMenuDeal.setChecked(true);
			break;
		case 1:
			btnTabMenuNearby.setChecked(true);
			break;
		case 2:
			btnTabMenuMy.setChecked(true);
			break;
		case 3:
			btnTabMenuMore.setChecked(true);
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.btn_tab_menu_deal:
			vp.setCurrentItem(0);
			break;
		case R.id.btn_tab_menu_nearby:
			vp.setCurrentItem(1);
			break;
		case R.id.btn_tab_menu_my:
			vp.setCurrentItem(2);
			break;
		case R.id.btn_tab_menu_more:
			vp.setCurrentItem(3);
			break;
		}
	}

}
