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
		//ע��㲥������
		receiver = new SmsStateReceive();
		IntentFilter filter = new IntentFilter();
		filter.addAction("ACTION_SMS_SEND_OK_OR_NOT");
		filter.addAction("ACTION_SMS_RECEIVE_OK_OR_NOT");
		this.registerReceiver(receiver, filter);
		
		//ע��㲥������
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
	 * ���Ͷ���
	 */
	private void sendMessage() {
		//1.  SmsManager
		SmsManager manager = SmsManager.getDefault();
		//2.  sendXXX()  
		String number = etNumber.getText().toString();
		String body = etText.getText().toString();
		//��ʾ�����Ƿ�ɹ�����
		Intent i1 = new Intent("ACaTION_SMS_SEND_OK_OR_NOT");
		PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
		//��ʾ�Է��ɹ�����
		Intent i2 = new Intent("ACTION_SMS_RECEIVE_OK_OR_NOT");
		PendingIntent deliveryIntent = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
		//�����ı����ţ�
		ArrayList<String> bodies = manager.divideMessage(body);
		for(String b : bodies){
			manager.sendTextMessage(number, null, b, sentIntent,  deliveryIntent);
		}
	}

	
	/**
	 * ���ڽ��ն��ŵĹ㲥������
	 */
	class ReceiveSmsReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			//��ȡ���ŵĺ���������
			Object[] objs = (Object[])intent.getExtras().get("pdus");
			for(Object obj  : objs){
				// obj����һ������Ϣ   
				byte[] data = (byte[])obj;
				//ͨ��data ������һ�����Ŷ���
				SmsMessage message=SmsMessage.createFromPdu(data);
				String body = message.getDisplayMessageBody();
				String number = message.getDisplayOriginatingAddress();
				Log.i("info", "�绰:"+number);
				Log.i("info", "����:"+body);
				if(number.equals("15555555555") || body.contains("fapiao")){ //ɧ�Ŷ���
					//��ֹ����㲥������������
					//���ض���
					abortBroadcast();
				}
			}
		}
	}
	
	
	
	/**
	 * ���ն��ŷ��ͽ���Ĺ㲥������
	 */
	class SmsStateReceive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("ACTION_SMS_RECEIVE_OK_OR_NOT")) {
				// �ж϶Է��Ƿ�ɹ����ն���
				int code = getResultCode();// ��ȡ�����
				switch (code) {
				case RESULT_OK:
					Toast.makeText(context, "��ϲ���Է��ѳɹ����ն���", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				case SmsManager.RESULT_ERROR_NO_SERVICE:
				case SmsManager.RESULT_ERROR_NULL_PDU:
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(context, "��ϲ���Է����ն���ʧ��", Toast.LENGTH_SHORT)
							.show();
					break;
				}
			} else if (action.equals("ACTION_SMS_SEND_OK_OR_NOT")) {
				// �ж϶����Ƿ�ɹ�����
				int code = getResultCode();// ��ȡ�����
				switch (code) {
				case RESULT_OK:
					Toast.makeText(context, "��ϲ�����ŷ��ͳɹ�", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				case SmsManager.RESULT_ERROR_NO_SERVICE:
				case SmsManager.RESULT_ERROR_NULL_PDU:
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(context, "��ϲ�����ŷ���ʧ��", Toast.LENGTH_SHORT)
							.show();
					break;
				}

			}

		}

	}

}
