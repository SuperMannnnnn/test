package com.dabao.sms;

import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private EditText etNumber;
	private EditText etText;
	private Button btnSend;
	private SmsStateReceive receiver;
	private ReceiveSmsReceiver receiver2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setView();
		setListeners();
		//注册广播接收者
		receiver = new SmsStateReceive();
		IntentFilter filter = new IntentFilter();
		filter.addAction("ACTION_SMS_SEND_OK_OR_NOT");
		filter.addAction("ACTION_SMS_RECEIVE_OK_OR_NOT");
		this.registerReceiver(receiver, filter);
		
		//注册广播接收者
		receiver2 = new ReceiveSmsReceiver();
		IntentFilter f2 = new IntentFilter();
		f2.addAction("android.provider.Telephony.SMS_RECEIVED");
		this.registerReceiver(receiver2, f2);
		
		
	}

	@Override
	protected void onDestroy() {
		this.unregisterReceiver(receiver);
		this.unregisterReceiver(receiver2);
		super.onDestroy();
	}
	
	
	private void setListeners() {
		btnSend.setOnClickListener(this);
	}

	private void setView() {
		etNumber = (EditText) findViewById(R.id.et_number);
		etText = (EditText) findViewById(R.id.et_text);
		btnSend = (Button) findViewById(R.id.button1);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			sendMessage();
			break;

		default:
			break;
		}

	}

	/**
	 * 发送短信
	 */
	private void sendMessage() {
		//1.  SmsManager
		SmsManager manager = SmsManager.getDefault();
		//2.  sendXXX()  
		String number = etNumber.getText().toString();
		String body = etText.getText().toString();
		//提示短信是否成功发送
		Intent i1 = new Intent("ACaTION_SMS_SEND_OK_OR_NOT");
		PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
		//提示对方成功接收
		Intent i2 = new Intent("ACTION_SMS_RECEIVE_OK_OR_NOT");
		PendingIntent deliveryIntent = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
		//处理长文本短信：
		ArrayList<String> bodies = manager.divideMessage(body);
		for(String b : bodies){
			manager.sendTextMessage(number, null, b, sentIntent,  deliveryIntent);
		}
	}

	
	/**
	 * 用于接收短信的广播接收器
	 */
	class ReceiveSmsReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			//获取短信的号码与内容
			Object[] objs = (Object[])intent.getExtras().get("pdus");
			for(Object obj  : objs){
				// obj就是一条短消息   
				byte[] data = (byte[])obj;
				//通过data 解析出一个短信对象
				SmsMessage message=SmsMessage.createFromPdu(data);
				String body = message.getDisplayMessageBody();
				String number = message.getDisplayOriginatingAddress();
				Log.i("info", "电话:"+number);
				Log.i("info", "内容:"+body);
				if(number.equals("15555555555") || body.contains("fapiao")){ //骚扰短信
					//阻止有序广播的向后继续传播
					//拦截短信
					abortBroadcast();
				}
			}
		}
	}
	
	
	
	/**
	 * 接收短信发送结果的广播接收器
	 */
	class SmsStateReceive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("ACTION_SMS_RECEIVE_OK_OR_NOT")) {
				// 判断对方是否成功接收短信
				int code = getResultCode();// 获取结果码
				switch (code) {
				case RESULT_OK:
					Toast.makeText(context, "恭喜，对方已成功接收短信", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				case SmsManager.RESULT_ERROR_NO_SERVICE:
				case SmsManager.RESULT_ERROR_NULL_PDU:
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(context, "恭喜，对方接收短信失败", Toast.LENGTH_SHORT)
							.show();
					break;
				}
			} else if (action.equals("ACTION_SMS_SEND_OK_OR_NOT")) {
				// 判断短信是否成功发送
				int code = getResultCode();// 获取结果码
				switch (code) {
				case RESULT_OK:
					Toast.makeText(context, "恭喜，短信发送成功", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				case SmsManager.RESULT_ERROR_NO_SERVICE:
				case SmsManager.RESULT_ERROR_NULL_PDU:
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(context, "恭喜，短信发送失败", Toast.LENGTH_SHORT)
							.show();
					break;
				}

			}

		}

	}

}
