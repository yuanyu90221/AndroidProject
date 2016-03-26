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
	 * 切換頁面
	 * 
	 * @param v
	 */
	public void navFrom3to2(View v){
		// Intent
		Intent it = new Intent(this, SecondActivity.class);
		
		startActivity(it);
	}
	
	/**
	 * 切換頁面
	 * 
	 * @param v
	 */
	public void navFrom3to1(View v){
		
		 Intent it = new Intent(this, MainActivity.class);
		 
		 startActivity(it);
	}
	
}
