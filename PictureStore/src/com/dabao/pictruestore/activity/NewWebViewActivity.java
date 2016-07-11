package com.dabao.pictruestore.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.dabao.pictruestore.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 *Created by dabao 2016��7��11��
 */
public class NewWebViewActivity extends Activity{
	@ViewInject(R.id.webView)
	private WebView webView;
	private String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_webview);
		x.view().inject(this);
		
		
		Intent intent = getIntent();
		url = intent.getStringExtra("url");
		Log.i("NewWebViewActivity", "nnnnnnnnn"+url);
		
		
		
		
		
		
		
		//���û��棬�ӷ������������µ�
				webView.clearCache(true);
//				String url = "http://176.3.16.123:8080/html4/07jd.html";
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
}
