package com.example.multiactivitysdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * �q��1�Ӭ��ʭ������2�Ӭ��ʭ�
	 * 
	 * @param v
	 */
	public void navFrom1to2(View v){
		// �`�Jintent ���ӭn������Activity��J
		Intent it = new Intent(this, SecondActivity.class);
		
		startActivity(it);
	}
	
	/**
	 * �q��1�Ӭ��ʭ������3�Ӭ��ʭ�
	 * 
	 * @param v
	 */
	public void navFrom1to3(View v){
		// �`�Jintent ���ӭn������Activity��J
		Intent it = new Intent(this, ThirdActivity.class);
		
		startActivity(it);
	}
}
