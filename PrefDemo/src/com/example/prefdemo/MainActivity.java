package com.example.prefdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private final String PREF_SETTING = "SETTING";
	private SharedPreferences pref = null;
	
	private EditText txtInput = null;
	private TextView txtResult = null;
	private Button saveBtn = null, loadBtn = null, exitBtn = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		restorePrefs();
	}
	
	/**
	 * 
	 */
	private void initView() {
		txtInput = (EditText) findViewById(R.id.inputText);
		txtResult = (TextView) findViewById(R.id.resultText);
		saveBtn = (Button) findViewById(R.id.btnSave);
		loadBtn = (Button) findViewById(R.id.btnLoad);
		exitBtn = (Button) findViewById(R.id.btnExit);
		
		saveBtn.setOnClickListener(this);
		exitBtn.setOnClickListener(this);
		loadBtn.setOnClickListener(this);
		
		pref = getSharedPreferences(PREF_SETTING, 0);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btnSave:
				pref.edit().putString("info", txtInput.getText().toString()).commit();
				break;
			case R.id.btnLoad:
				String info = pref.getString("info","default value");
				txtResult.setText(info);
				break;
			case R.id.btnExit:
				finish();
				break;
			default:
				break;
		}
	}

	/**
	 * 
	 */
	private void restorePrefs(){
		SharedPreferences settings = getSharedPreferences(PREF_SETTING, 0);
		String pref_name = settings.getString("info", "");
		if(!pref_name.equals("")){
			txtResult.setText(pref_name);
		}
	}
	
	/**
	 *  store value to shareds
	 */
	private void storePrefs(){
		SharedPreferences settings = getSharedPreferences(PREF_SETTING, 0);
		settings.edit().putString("info", txtInput.getText().toString()).commit();
	}

//	@Override
//	protected void onPause() {
//		super.onPause();
//		storePrefs();
//	}
	
	
}
