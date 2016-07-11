package com.dabao.popupwindow;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends Activity {

	private PopupWindow popupWindow;
	private Button btnMore;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnMore = (Button) findViewById(R.id.buttonMore);
		btnMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (popupWindow==null)
				{
				// ��ʾmore.xml
				// ��more.xml���view
				View view = View
						.inflate(MainActivity.this, R.layout.more, null);
				popupWindow = new PopupWindow(view,
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);

				int btnHeight = btnMore.getHeight();
				// viewû����ʾ����
				// 1,����
				view.measure(0, 0);
				// 2,�ò����߶�
				int viewHeight = view.getMeasuredHeight();
				int yOffset = btnHeight + viewHeight;
				// popupwindos����btnMore����ʾ����ʾ������
				popupWindow.showAsDropDown(btnMore, 0, -yOffset);
Log.i("popupWindow", "show "+popupWindow.toString());
				// ����more.xml�е�btn
				Button btnAddFriend = (Button) view
						.findViewById(R.id.btn_addFriend);
				btnAddFriend.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Log.i("popupWindow", "close "+popupWindow.toString());

						popupWindow.dismiss();
						popupWindow=null;
					}
				});
				
			}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}