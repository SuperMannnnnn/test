package com.dabao.sqlite;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.view.Menu;

public class MainActivity extends Activity {
	private SQLiteDatabase db = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//�������ݿ�
		//createDataBase();
		//�������ݱ�
		//createTable();
		//��������
		insertData();
		//ɾ������
		
		//�޸�����
		
		//�ͷ�����
		
	}


	public void createDataBase(){
		 String name = "dabao.db";
		 int mode = MODE_PRIVATE;
		 CursorFactory factory = null;
		db= openOrCreateDatabase(name, mode, factory);
	}
	

	private void createTable() {
		String sql = "CREATE TABLE students("
				+"name VARCHAR(16) NOT NULL UNIQUE,"
				+"tel VARCHAR(16),"
				+"email VARCHAR(50)"
				+")";
		db.execSQL(sql);
		
	}
	
	private void insertData(){
		String sql =  "insert into teachers "
				+ "(name, tel) "
				+ "values "
				+ "('�Ź�', '13800138888')";
		db.execSQL(sql);
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
