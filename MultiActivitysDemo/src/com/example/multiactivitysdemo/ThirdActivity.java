package com.example.multiactivitysdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Yuanyu
 *
 */
public class ThirdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
	}
	
	/**
	 * 從第3個活動頁換到第2個活動頁
	 * 
	 * @param v
	 */
	public void navFrom3to2(View v){
		// 注入intent 把兩個要切換的Activity放入
		Intent it = new Intent(this, SecondActivity.class);
		
		startActivity(it);
	}
	
	/**
	 * 從第3個活動頁換到第1個活動頁
	 * 
	 * @param v
	 */
	public void navFrom3to1(View v){
		
		 Intent it = new Intent(this, MainActivity.class);
		 
		 startActivity(it);
	}
	
}
