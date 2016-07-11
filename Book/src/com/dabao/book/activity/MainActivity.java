package com.dabao.book.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dabao.book.R;
import com.dabao.book.fragment.FragmentCart;
import com.dabao.book.fragment.FragmentMine;
import com.dabao.book.fragment.FragmentStore;



public class MainActivity extends FragmentActivity {
	@ViewInject(R.id.viewPager)
	private ViewPager mViewPager;
	@ViewInject(R.id.rg_meun)
	private RadioGroup rg;
	@ViewInject(R.id.radioStore)
	private RadioButton rbStore;
	@ViewInject(R.id.radioCart)
	private RadioButton rbCart;
	@ViewInject(R.id.radioMine)
	private RadioButton rbMine;
	private List<Fragment> fragments;
	private MyAdapter myAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化控件
		x.view().inject(this);
		//设置adapter
		setAdapter();
		//设置监听器
		setListeners();
	}

	private void setListeners() {
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radioStore:
					rbStore.setTextColor(Color.WHITE);
					mViewPager.setCurrentItem(0);
					break;
				case R.id.radioCart:
					rbStore.setTextColor(Color.WHITE);
					mViewPager.setCurrentItem(1);
					break;
				case R.id.radioMine:
					rbStore.setTextColor(Color.WHITE);
					mViewPager.setCurrentItem(2);
					break;

				}
			}
		});
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int i) {
				switch (i) {
				case 0:
					rbStore.setChecked(true);
					break;

				case 1:
					rbCart.setChecked(true);
					break;
				case 2:
					rbMine.setChecked(true);
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int i, float v, int i2) {
				if(v!=0){
					//设置第三个fragment header的透明度
				//	MineFragment fragment = (MineFragment) fragments.get(2);
				//	fragment.slide(v);

				}
				
			}
			
			@Override
			public void onPageScrollStateChanged(int i) {
				
			}
		});
		
		
	}

	private void setAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new FragmentStore());
		fragments.add(new FragmentCart());
		fragments.add(new FragmentMine());
		
		myAdapter = new MyAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(myAdapter);
		//缓冲三页
		mViewPager.setOffscreenPageLimit(3);
		
	}

	public class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
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
