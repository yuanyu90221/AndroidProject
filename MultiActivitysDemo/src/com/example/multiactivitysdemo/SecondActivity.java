package com.example.multiactivitysdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}
	
	/**
	 * 從第2個活動頁換到第1個活動頁
	 * 
	 * @param v
	 */
	public void navFrom2to1(View v){
		// 注入intent 把兩個要切換的Activity放入
		Intent it = new Intent(this, MainActivity.class);
		
		startActivity(it);
	}
	
	/**
	 * 從第2個活動頁換到第3個活動頁
	 * 
	 * @param v
	 */
	public void navFrom2to3(View v){
		// 注入intent 把兩個要切換的Activity放入
		Intent it = new Intent(this, ThirdActivity.class);
		
		startActivity(it);
	}
}
