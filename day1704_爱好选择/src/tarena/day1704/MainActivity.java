package tarena.day1704;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;
	private ArrayList<String> list =
			new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView) findViewById(R.id.tv1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * View android控件父类
	 *  |- TextView
	 *  |- EditText
	 *  |- Button
	 *  |- ImageView
	 *  |- ImageButton
	 *  |- RelativeLayout
	 *  |- LinearLayout
	 *  ....
	 *  |- CheckBox
	 */
	public void doClick(View view) {
		CheckBox cb = (CheckBox) view;
		String s = cb.getText().toString();
		
		//判断是否勾选
		if(cb.isChecked()) {
			list.add(s);
		} else {
			list.remove(s);
		}
		
		tv.setText("");
		
		for(int i=0;i<list.size();i++) {
			if(i!=0) {
				tv.append(", ");
			}
				
			tv.append(list.get(i));
		}
	}

	
	
}





