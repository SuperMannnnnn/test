package tarena.day0801;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	
	RelativeLayout rl;
	ToggleButton tb;
	//FlashLight fl = new FlashLight();
	FlashLight fl = 
		new FlashLight(Color.YELLOW);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	
	
	public void doClick(View view) {
		rl=(RelativeLayout)
		 findViewById(R.id.rl);
		tb=(ToggleButton)
		 findViewById(R.id.toggleButton1);
		
		switch(view.getId()) {
		case R.id.button1: bai();  break;
		case R.id.button2: hong(); break;
		case R.id.button3: lan();  break;
		case R.id.toggleButton1: kaiGuan(); break;
		}
	}

	private void bai() {
		//修改数据模型对象
		fl.color = Color.WHITE;
		//根据数据模型，设置界面显示
		show();
	}
	private void hong() {
		fl.color = Color.RED;
		show();
	}
	private void lan() {
		fl.color = Color.BLUE;
		show();
	}

	private void kaiGuan() {
		//打开状态
		if(tb.isChecked()) {
			fl.turnOn();
		} else {//关闭状态
			fl.turnOff();
		}
		
		show();
	}

	private void show() {
		if(fl.on) {
			rl.setBackgroundColor(fl.color);
		} else {
			rl.setBackgroundColor(Color.BLACK);
		}
	}

	
	
	

}
