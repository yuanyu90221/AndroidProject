package com.example.multiactivitysdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

public class SecondActivity extends Activity implements OnClickListener{
    
	Toast tos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		// 讀取其他Activity發過來的Context
		Intent it = this.getIntent();
		if(it != null){
			String context = it.getStringExtra("context");
			tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);
			if(!(context == null || context.equals(""))){
				tos.setText(context);
				tos.show();
			}
			CustomObject co = null;
			co = (CustomObject) this.getIntent().getSerializableExtra("customObj");
			if(co != null) {
				String jobTypes = co.getJobType().getTypeName();
				String result = String.format("Name: %s\nJob: %s\nAge: %d years old\n",
											   co.getName(),
											   co.getJobType().getTypeName(),
											   co.getAges());
				new AlertDialog.Builder(this)
					.setTitle("Test")
					.setMessage(result)
					.setCancelable(true)
					.setNeutralButton("關閉視窗", this)
					.show();
			}
		}
	}
	
	/**
	 * 切換頁面
	 * 
	 * @param v
	 */
	public void navFrom2to1(View v){
		// 準備Intent
		Intent it = new Intent(this, MainActivity.class);
		it.putExtra("context", "切換頁面從2到1");
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
		it.putExtra("context", "切換頁面從2到3");
		startActivity(it);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_NEUTRAL){
			dialog.cancel();
		}
		
	}

	
	
}
