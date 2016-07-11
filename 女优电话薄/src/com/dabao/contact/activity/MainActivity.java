package com.dabao.contact.activity;

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

import com.dabao.contact.R;
import com.dabao.contact.fragment.ContactFragment;

public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;
	private RadioGroup radioGroup;
	private RadioButton rb0;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private List<Fragment> fragments;
	private MyPagerAdapter pagerAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//�ؼ���ʼ��
				setViews();
				//��viewpager����������
				setAdapter();
				//����
				setListeners();
			}

			//����
			private void setListeners() {
				//viewpager���Ƶײ�����
				viewPager.setOnPageChangeListener(new OnPageChangeListener() {
					public void onPageSelected(int position) {
						switch (position) {
						case 0:
							rb0.setChecked(true);
							break;
						case 1:
							rb1.setChecked(true);
							break;
						case 2:
							rb2.setChecked(true);
							break;
						case 3:
							rb3.setChecked(true);
							break;
						}
					}
					public void onPageScrolled(int arg0, float arg1, int arg2) {
					}
					public void onPageScrollStateChanged(int arg0) {
					}
				});
				
				//�ײ���������viewpager
				radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.radio0:
							viewPager.setCurrentItem(0);
							break;
						case R.id.radio1:
							viewPager.setCurrentItem(1);
							break;
						case R.id.radio2:
							viewPager.setCurrentItem(2);
							break;
						case R.id.radio3:
							viewPager.setCurrentItem(3);
							break;
						}
					}
				});
			}

			//��viewpager����������
			private void setAdapter() {
				fragments = new ArrayList<Fragment>();
				//��fragments���������Fragment����
				fragments.add(new ContactFragment());
				fragments.add(new ContactFragment());
				fragments.add(new ContactFragment());
				fragments.add(new ContactFragment());
				
				pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
				viewPager.setAdapter(pagerAdapter);
			}

			/**
			 * �ؼ���ʼ��
			 *   
			 *   
			 */
			private void setViews() {
				viewPager = (ViewPager) findViewById(R.id.viewPager);
				radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
				rb0 = (RadioButton) findViewById(R.id.radio0);
				rb1 = (RadioButton) findViewById(R.id.radio1);
				rb2 = (RadioButton) findViewById(R.id.radio2);
				rb3 = (RadioButton) findViewById(R.id.radio3);
			}

			class MyPagerAdapter extends FragmentPagerAdapter{
				public MyPagerAdapter(FragmentManager fm) {
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
