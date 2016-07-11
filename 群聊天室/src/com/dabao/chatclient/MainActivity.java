package com.dabao.chatclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button btnConnect;
	private Button btnSend;
	private List<String>messages = new ArrayList<String>();
	private EditText etIp;
	private EditText etMessage;
	private ListView lvMessages;
	private ArrayAdapter<String> adapter;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Socket socket;

	public static final int HANDLER_CONNECT_SUCCESS = 1;
	public static final int HANDLER_RECEIVE_MESSAGE=2;
	
	// 声明Handler对象 处理消息
		private Handler handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case HANDLER_RECEIVE_MESSAGE:
					//接收到了服务端发送过来的消息
					String message=(String) msg.obj;
					//给listView更新Adapter
					messages.add(message);
					Log.i("dabao", "message中按钮被false"+message);
					adapter.notifyDataSetChanged();
					lvMessages.setSelection(messages.size()-1);
					etMessage.setText("");
					break;
				case HANDLER_CONNECT_SUCCESS: // 连接成功
					etIp.setEnabled(false);
					btnConnect.setEnabled(false);
					Log.i("dabao", "handler中按钮被false");
					break;
				}
			}
		};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		
		setAdapter();
		setOnClickLiseteners();
		
	}


	private void setAdapter() {
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,messages);
		lvMessages.setAdapter(adapter);
	}


	private void setOnClickLiseteners() {
		btnConnect.setOnClickListener(this);
		btnSend.setOnClickListener(this);
	}


	private void initView() {
		btnConnect = (Button) findViewById(R.id.btn_ip);
		btnSend = (Button) findViewById(R.id.btn_send);
		etIp = (EditText) findViewById(R.id.et_ip);
		etMessage = (EditText) findViewById(R.id.et_message);
		lvMessages = (ListView) findViewById(R.id.lv_message);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ip:
			new Thread(){
				public void run() {
					try {
						connect();
						Log.i("dabao", "btn_connect");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			break;
		case R.id.btn_send:
			new Thread(){
				public void run() {
					try {
						sendMessage();
						Log.i("dabao", "btn_send");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			break;
		}
	}


	private void sendMessage() throws IOException {
		String text = etMessage.getText().toString();
		dos.writeUTF("你哥临死前说："+text);
		dos.flush();
		
	}



	// 建立连接 在工作线程中执行
	private void connect() throws IOException {
		String ip = etIp.getText().toString();
		socket = new Socket(ip, 3426);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		// 连接建立成功 把etIp与btnConnect不可用
		handler.sendEmptyMessage(HANDLER_CONNECT_SUCCESS);
		Log.i("dabao", "handler在connect方法中发送成功"+handler);
		// 启动工作线程 用于读取服务端写给客户端的消息
		new ReadThread().start();
	}

	/**
	 * 不断的读取消息的工作线程
	 */
	class ReadThread extends Thread{
		@Override
		public void run() {
			try {
			while(true){
					String message = dis.readUTF();
					Log.i("dabao", "ReadThread在handler方法中接受的message"+message);
					Message msg = new Message();
					msg.what = HANDLER_RECEIVE_MESSAGE;
					msg.obj =  message;
					handler.sendMessage(msg);
					Log.i("dabao", "ReadThread在handler方法中发送的msg"+msg);
			}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}


}
