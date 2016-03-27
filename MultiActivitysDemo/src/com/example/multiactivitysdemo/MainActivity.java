package com.example.multiactivitysdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	Toast tos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 讀取其他Activity發過來的Context
		Intent it = this.getIntent();
		if(it != null){
			String context = it.getStringExtra("context");
			tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);
			if(!(context == null || context.equals(""))){
				tos.setText(context);
				tos.show();
			}
		}
	}

	/**
	 * 切換頁面
	 * 
	 * @param v
	 */
	public void navFrom1to2(View v){
		// 準備Intent
		Intent it = new Intent(this, SecondActivity.class);
		it.putExtra("context", "切換頁面從1到2");
		startActivity(it);
	}
	
	/**
	 * 切換頁面
	 * 
	 * @param v
	 */
	public void navFrom1to3(View v){
		// 準備Intent
		Intent it = new Intent(this, ThirdActivity.class);
		it.putExtra("context", "切換頁面從1到3");
		startActivity(it);
	}
}
