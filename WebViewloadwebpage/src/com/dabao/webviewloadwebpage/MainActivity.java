package com.dabao.webviewloadwebpage;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webView);
		//���û��棬�ӷ������������µ�
		webView.clearCache(true);
		String url = "http://176.3.16.123:8080/html4/07jd.html";
		//������ҳ
		webView.loadUrl(url);
		//���������ӣ�����ϵͳ�������
		//������� new webViewClient
		webView.setWebViewClient(new WebViewClient(){
			//ÿ�ε��������ӻ�ִ��
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//��ͨ�����ӣ���������Ҫ���� webview�����html
				//ִ�����⹦��
				String tag="tarena:tel/";
				if(url.contains(tag)){
					//��绰
					int taglength=tag.length();
					String mobile=url.substring(taglength);
					Uri uri=Uri.parse("tel:"+mobile);
					Intent intent=new Intent(Intent.ACTION_CALL, uri);
					startActivity(intent);
					//���������android�Ѿ������ˣ�webView���ټ���
					//return false,androidû�д���webview���ؼ������ذ�
					return true;
				}
				
				
				return super.shouldOverrideUrlLoading(view, url);
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
