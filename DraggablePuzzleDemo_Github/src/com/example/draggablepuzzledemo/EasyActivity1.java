package com.example.draggablepuzzledemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class EasyActivity1 extends Activity {

	ImageView imgv1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_easy_activity1);
		
		imgv1 = (ImageView) findViewById(R.id.imageView1);
	}

	public void goUpPage(View v) {
		Intent it = new Intent(this, EasyLevelActivity.class); //建立 Intent 並設定目標 Activity
		startActivity(it); // 啟動 Intent 中的目標

	}
	
}
