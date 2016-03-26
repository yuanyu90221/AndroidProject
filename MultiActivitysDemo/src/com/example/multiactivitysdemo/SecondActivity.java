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
	 * 切換頁面
	 * 
	 * @param v
	 */
	public void navFrom2to1(View v){
		// 準備Intent
		Intent it = new Intent(this, MainActivity.class);
		
		startActivity(it);
	}
	
	/**
	 * 切換頁面
	 * 
	 * @param v
	 */
	public void navFrom2to3(View v){
		// 準備Intent
		Intent it = new Intent(this, ThirdActivity.class);
		
		startActivity(it);
	}
}
