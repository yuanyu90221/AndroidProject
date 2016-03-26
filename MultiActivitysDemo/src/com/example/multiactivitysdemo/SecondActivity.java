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
	 * �q��2�Ӭ��ʭ������1�Ӭ��ʭ�
	 * 
	 * @param v
	 */
	public void navFrom2to1(View v){
		// �`�Jintent ���ӭn������Activity��J
		Intent it = new Intent(this, MainActivity.class);
		
		startActivity(it);
	}
	
	/**
	 * �q��2�Ӭ��ʭ������3�Ӭ��ʭ�
	 * 
	 * @param v
	 */
	public void navFrom2to3(View v){
		// �`�Jintent ���ӭn������Activity��J
		Intent it = new Intent(this, ThirdActivity.class);
		
		startActivity(it);
	}
}
