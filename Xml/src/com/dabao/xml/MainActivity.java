package com.dabao.xml;

import java.util.List;

import org.dom4j.io.SAXReader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private List<Province> provinces;
	private ArrayAdapter<Province> provinceAdapter;
	private ListView lvProvince;
	private ListView lvCity;
	private List<City> cities;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		parseXML();
	}

	private void parseXML() {
		
		parseBookXML_DOM4J();
	}

	private void parseBookXML_DOM4J() {
		
		SAXReader reader = new SAXReader();
		
		
	}

	

}

     