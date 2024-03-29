package com.example.draggablepuzzledemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// 拖曳的方塊
	ImageView brown_rectangle, red_rectangle, brown_down_lblock, green_up_lblock;
	SelfDefImgView brown_rec, red_rec, brown_down_l, greep_up_l;
	// 拖拉監聽器
	DragImgListener imgListener;

	// 計時器
	Timer timer;

	// 計時器顯示
	TextView txtTimer;
	// 目前累計秒數 顯示秒數 顯示分鐘數
	private int tsec = 0, csec = 0, cmin = 0;
	// 啟動計時器flag
	public static boolean isStartTimer = true;

	private Toast tos;

	public static HashMap<String, SelfDefImgView> viewMap = new HashMap<String, SelfDefImgView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		// 0. 設定檢查範圍
		List<Cordinate> firstCheckRange = new ArrayList<Cordinate>();
		for (int i = 3; i <= 6; i++) {
			for (int j = 4; j <= 7; j++) {
				firstCheckRange.add(new Cordinate(i, j));
			}
		}
		// 1. 產生拖拉監聽器物件
		imgListener = new DragImgListener(tos, viewMap, this, firstCheckRange);
		imgListener.score = 0;
		imgListener.position = new PositionMatrix();
		// 2. 把要拖弋的圖層帶入,並且設置監聽器
		brown_rectangle = (ImageView) findViewById(R.id.brown_rectangle);
		brown_rectangle.setOnTouchListener(imgListener);
		brown_rec = new SelfDefImgView();
		brown_rec.addCord(new Cordinate(0, 0));
		brown_rec.addCord(new Cordinate(0, 1));
		brown_rec.addCord(new Cordinate(0, 2));
		brown_rec.addCord(new Cordinate(0, 3));
		Log.d("occupiedList", "yuanyu List1: " + brown_rec.toString());
		viewMap.put(String.valueOf(R.id.brown_rectangle), brown_rec);

		red_rectangle = (ImageView) findViewById(R.id.red_rectangle);
		red_rectangle.setOnTouchListener(imgListener);
		red_rec = new SelfDefImgView();
		red_rec.addCord(new Cordinate(0, 0));
		red_rec.addCord(new Cordinate(0, 1));
		red_rec.addCord(new Cordinate(0, 2));
		red_rec.addCord(new Cordinate(0, 3));
		viewMap.put(String.valueOf(R.id.red_rectangle), red_rec);

		brown_down_lblock = (ImageView) findViewById(R.id.brown_down_lblock);
		brown_down_lblock.setOnTouchListener(imgListener);
		brown_down_l = new SelfDefImgView();
		brown_down_l.addCord(new Cordinate(0, 0));
		brown_down_l.addCord(new Cordinate(0, 1));
		brown_down_l.addCord(new Cordinate(0, 2));
		brown_down_l.addCord(new Cordinate(1, 2));
		viewMap.put(String.valueOf(R.id.brown_down_lblock), brown_down_l);

		green_up_lblock = (ImageView) findViewById(R.id.green_up_lblock);
		green_up_lblock.setOnTouchListener(imgListener);
		greep_up_l = new SelfDefImgView();
		greep_up_l.addCord(new Cordinate(0, 0));
		greep_up_l.addCord(new Cordinate(1, 0));
		greep_up_l.addCord(new Cordinate(1, 1));
		greep_up_l.addCord(new Cordinate(1, 2));
		viewMap.put(String.valueOf(R.id.green_up_lblock), greep_up_l);
		Log.d("occupiedList", "yuanyu List1: " + red_rec.toString());
		// 3. 帶入計時器
		txtTimer = (TextView) findViewById(R.id.timer);
		timer = new Timer();
		// 設定Timer(task為執行內容，0代表立刻開始,間格1秒執行一次)
		timer.schedule(task, 0, 1000);

		// 將計時器歸零
		tsec = 0;
		// TextView 初始化
		txtTimer.setText("00:00");
		// 開啟flag
		isStartTimer = true;

	}

	// TimerTask無法直接改變元件因此要透過Handler來當橋樑
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 計算目前時間
				csec = tsec % 60;
				cmin = (tsec / 60) % 60;
				String resultTime = "", resultMin = "", resultSec = "";
				resultMin = String.valueOf(cmin).length() > 1 ? String.valueOf(cmin) : "0" + String.valueOf(cmin);
				resultSec = String.valueOf(csec).length() > 1 ? String.valueOf(csec) : "0" + String.valueOf(csec);
				resultTime = resultMin + ":" + resultSec;
				txtTimer.setText(resultTime);
				break;
			}
		}

	};
	/**
	 * TimerTask 用來做計時器功能的thread
	 */
	private TimerTask task = new TimerTask() {

		@Override
		public void run() {
			if (isStartTimer) {
				// 如果startflag是true則每秒tsec+1
				tsec++;
				Message message = new Message();

				// 傳送訊息1
				message.what = 1;
				handler.sendMessage(message);
			}
		}

	};

	/**
	 * stop計時器
	 * 
	 * @param v
	 */
	public void stopTimer(View v) {
		// 開啟flag
		isStartTimer = false;
	}

	/**
	 * stop計時器
	 * 
	 * @param v
	 */
	public void restartTimer(View v) {
		// 將計時器歸零
		tsec = 0;
		// TextView 初始化
		txtTimer.setText("00:00");
		// 開啟flag
		isStartTimer = true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e("yuanyu", "yuanyu[timertask]");
	}

	/**
	 * 跳至第二關
	 * 
	 * @param v
	 */
	public void gotoSecondLevel(View v) {
		Intent it = new Intent(this, SecondActivity.class);
		startActivity(it);
	}

}
