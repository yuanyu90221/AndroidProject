package com.example.tablelayoutgame;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends Activity implements OnClickListener{
	private int startTime = 0;
	private Chronometer chronometer ;
	Button btnStart, btnStop, btnReset;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		chronometer  = (Chronometer) findViewById(R.id.chronometer);
		btnStart = (Button) findViewById(R.id.btnStart);
		btnReset = (Button) findViewById(R.id.btnReset);
		btnStop = (Button) findViewById(R.id.btnStop);
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnReset.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Log.e("Yuanyu", String.valueOf(v.getId()== R.id.btnStop));
		Log.e("Yuanyu", String.valueOf(v.getId()== R.id.btnStart));
		Log.e("Yuanyu", String.valueOf(v.getId()== R.id.btnReset));
		switch(v.getId()){
			case R.id.btnStart:
				Log.e("Yuanyu", "start timer");
				chronometer.setBase(SystemClock.elapsedRealtime());
				chronometer.start();
				break;
			case R.id.btnStop:
				Log.e("Yuanyu", "stop timer");
				chronometer.stop();
				break;
			case R.id.btnReset:
				Log.e("Yuanyu", "reset timer");
				chronometer.setBase(SystemClock.elapsedRealtime());
				break;
		}
		
	}

	
}
