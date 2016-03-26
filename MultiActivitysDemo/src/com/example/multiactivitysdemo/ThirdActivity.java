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
	 * �q��3�Ӭ��ʭ������2�Ӭ��ʭ�
	 * 
	 * @param v
	 */
	public void navFrom3to2(View v){
		// �`�Jintent ���ӭn������Activity��J
		Intent it = new Intent(this, SecondActivity.class);
		
		startActivity(it);
	}
	
	/**
	 * �q��3�Ӭ��ʭ������1�Ӭ��ʭ�
	 * 
	 * @param v
	 */
	public void navFrom3to1(View v){
		
		 Intent it = new Intent(this, MainActivity.class);
		 
		 startActivity(it);
	}
	
}
