package com.example.testhttp;

import java.io.File;

import org.apache.http.Header;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

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
		
		final AsyncHttpClient client = new AsyncHttpClient();
		
		button1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v)
			{
				client.get("http://218.29.72.146:8090/app/FoodAndDrug.apk", new FileAsyncHttpResponseHandler(MainActivity.this) {
					@Override
					public void onFailure(int arg0, Header[] arg1, Throwable arg2,
							File arg3)
					{
						// TODO Auto-generated method stub
					System.out.println("onFailure");	
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, File arg2)
					{
						System.out.println("onSuccess");
						System.out.println(arg2.getAbsolutePath());
					}

					@Override
					protected File getTargetFile()
					{
						System.out.println("getTargetFile");
						File f = new File(Environment.getExternalStorageDirectory().getPath() + "/FoodAndDrug.apk");
						return f;
					}

					@Override
					public void onProgress(int bytesWritten, int totalSize)
					{
						super.onProgress(bytesWritten, totalSize);
						System.out.println(bytesWritten + "/" + totalSize);
						textView1.setText(bytesWritten + "%");
						progressBar1.setMax(totalSize);
						progressBar1.setProgress(bytesWritten);
					} 
				});
			}});
		
		
	}
}
