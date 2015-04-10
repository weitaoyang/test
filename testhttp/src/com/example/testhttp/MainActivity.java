package com.example.testhttp;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity
{
	/**
	 * 获取地区列表
	 */
	public static final String areaDictionary_findAllTown = "areaDictionary/findAllTown.action";
	
	private TextView textView1;
	private ProgressBar progressBar1;
	private Button button1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView1 = (TextView) findViewById(R.id.textView1);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		button1 = (Button) findViewById(R.id.button1);
		
		button1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				AsyncHttpClient client = new AsyncHttpClient();
				
				File f1 = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/IMG_20141115_083233.jpg");
				File f2 = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/IMG_20141123_132950.jpg");
				
				RequestParams params = new RequestParams();
				params.put("username", "杨卫涛");
				params.put("password", "123456");
				try
				{
					params.put("file1", f1);
					params.put("file2", f2);
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				
				client.post("http://192.168.2.176:8080/AsbFramework/test/test.do", params, new JsonHttpResponseHandler()
				{
					@Override
					public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
					{
						System.out.println("onFailure");
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers, JSONObject response)
					{
						System.out.println(response.toString());
					}

					@Override
					public void onProgress(int bytesWritten, int totalSize)
					{
						textView1.setText(bytesWritten + "b");
						progressBar1.setMax(totalSize);
						progressBar1.setProgress(bytesWritten);
					}
				});
				
				
//				client.get("http://www.baidu.com/img/baidu_jgylogo3.gif", new FileAsyncHttpResponseHandler(MainActivity.this)
//				{
//					@Override
//					public void onFailure(int arg0, Header[] arg1, Throwable arg2, File arg3)
//					{
//						System.out.println("onFailure");
//					}
//
//					@Override
//					public void onSuccess(int arg0, Header[] arg1, File arg2)
//					{
//						System.out.println("onSuccess");
//						System.out.println(arg2.getAbsolutePath());
//					}
//
//					@Override
//					protected File getTargetFile()
//					{
//						System.out.println("getTargetFile");
//						File f = new File(Environment.getExternalStorageDirectory().getPath() + "/baidu_jgylogo3.gif");
//						return f;
//					}
//
//					@Override
//					public void onProgress(int bytesWritten, int totalSize)
//					{
//						textView1.setText(bytesWritten + "b");
//						progressBar1.setMax(totalSize);
//						progressBar1.setProgress(bytesWritten);
//					}
//				});
			}
		});
		
		
	}
}
