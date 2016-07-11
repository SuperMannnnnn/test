package com.dabao.contact.fragment;

import java.util.List;

import com.dabao.contact.R;
import com.dabao.contact.adapter.CalllogAdapter;
import com.dabao.contact.entity.Calllog;
import com.dabao.contact.presenter.IDialPresenter;
import com.dabao.contact.presenter.impl.DialPresenterImpl;
import com.dabao.contact.view.IDialView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DialFragment extends Fragment implements IDialView,OnClickListener{
	private List<Calllog> logs;
	private IDialPresenter presenter;
	private ListView lvCalllog;
	private CalllogAdapter adapter; 
	private LinearLayout keyboard;
	private TextView tvTitle;
	private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
	
	
	
	public DialFragment() {
		this.presenter = new DialPresenterImpl(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dial, null);
		setView(view);
		setListener();
		//调用persenter的方法，执行加载联系人的流程
		presenter.loadAllCalllogs();
		return view;
	}

	private void setListener() {
		//给每一个按钮添加监听
				b1.setOnClickListener(this);
				b2.setOnClickListener(this);
				b3.setOnClickListener(this);
				b4.setOnClickListener(this);
				b5.setOnClickListener(this);
				b6.setOnClickListener(this);
				b7.setOnClickListener(this);
				b8.setOnClickListener(this);
				b9.setOnClickListener(this);
				b10.setOnClickListener(this);
				b11.setOnClickListener(this);
				b12.setOnClickListener(this);
				
				//当触摸tvTitle时 显示软键盘
				tvTitle.setOnTouchListener(new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if(keyboard.getVisibility() == View.VISIBLE){
							return true;
						}
						keyboard.setVisibility(View.VISIBLE);
						TranslateAnimation anim = new TranslateAnimation(0, 0, keyboard.getHeight(), 0);
						anim.setDuration(400);
						keyboard.startAnimation(anim);
						return false;
					}
				});
				//当lsitView滚动时  使用动画 让keyboard消失
				lvCalllog.setOnScrollListener(new OnScrollListener() {
					public void onScrollStateChanged(AbsListView view, int scrollState) {
						if(scrollState != OnScrollListener.SCROLL_STATE_IDLE){
							//如果不是空闲状态
							if(keyboard.getVisibility()==View.INVISIBLE){
								return;
							}
							keyboard.setVisibility(View.INVISIBLE);
							TranslateAnimation anim = new TranslateAnimation(0, 0, 0, keyboard.getHeight());
							anim.setDuration(400);
							keyboard.startAnimation(anim);
						}
					}
					public void onScroll(AbsListView view, int firstVisibleItem,
							int visibleItemCount, int totalItemCount) {
					}
				});
				
	}

	private void setView(View view) {
		this.lvCalllog = (ListView)view.findViewById(R.id.lvCalllog);
		this.keyboard = (LinearLayout) view.findViewById(R.id.lvKeyboard);
		tvTitle = (TextView) view.findViewById(R.id.textView1);
		b1 = (Button) view.findViewById(R.id.button01);
		b2 = (Button) view.findViewById(R.id.button02);
		b3 = (Button) view.findViewById(R.id.button03);
		b4 = (Button) view.findViewById(R.id.button04);
		b5 = (Button) view.findViewById(R.id.button05);
		b6 = (Button) view.findViewById(R.id.button06);
		b7 = (Button) view.findViewById(R.id.button07);
		b8 = (Button) view.findViewById(R.id.button08);
		b9 = (Button) view.findViewById(R.id.button09);
		b10 = (Button) view.findViewById(R.id.button10);
		b11 = (Button) view.findViewById(R.id.button11);
		b12 = (Button) view.findViewById(R.id.button12);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button01:
			Log.i("info", "1");
			break;
		}
	}

	@Override
	public void setData(List<Calllog> logs) {
		this.logs = logs;
	}

	@Override
	public void showData() {
		//设置自定义Adapter
				adapter = new CalllogAdapter(getActivity(), logs);
				lvCalllog.setAdapter(adapter);
	}
}
