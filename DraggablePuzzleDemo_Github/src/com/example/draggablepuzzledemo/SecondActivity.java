package com.example.draggablepuzzledemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {
	//顯示訊息的toast
	private Toast secondTos;
	//拖曳的方塊
	ImageView green_down_tblock, gray_right_tblock, blue_left_tblock, red_up_tblock;
	SelfDefImgView green_down_t, gray_right_t, blue_left_t, red_up_t;
	//拖拉監聽器
	DragImgListener secondImgListener;	
	//計時器 
	public static Timer secondTimer;	
	//計時器顯示
	TextView secondTxtTimer;
	//          目前累計秒數        
	public static int tsec = 0;
	// 啟動計時器flag
	public static boolean isStartSecondTimer = true;
	// 用來記住方格位置的Map
	public static HashMap<String, SelfDefImgView> secondViewMap = new HashMap<String, SelfDefImgView>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		secondTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. 設定檢查範圍
		List<Cordinate> secondCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=6 ; i++){
			for(int j=4 ; j <=7; j++){
				secondCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. 產生拖拉監聽器物件
		secondImgListener = new DragImgListener(secondTos, secondViewMap, 2, secondCheckRange);
		secondImgListener.score = 0;
		secondImgListener.position = new PositionMatrix();
		// 2. 把要拖弋的圖層帶入,並且設置監聽器
		green_down_tblock = (ImageView) findViewById(R.id.green_down_tblock);
		green_down_tblock.setOnTouchListener(secondImgListener);
		green_down_t = new SelfDefImgView();
		green_down_t.addCord(new Cordinate(0, 0));
		green_down_t.addCord(new Cordinate(1, 0));
		green_down_t.addCord(new Cordinate(1, 1));
		green_down_t.addCord(new Cordinate(2, 0));
		secondViewMap.put(String.valueOf(R.id.green_down_tblock), green_down_t);
		
		gray_right_tblock = (ImageView) findViewById(R.id.gray_right_tblock);
		gray_right_tblock.setOnTouchListener(secondImgListener);
		gray_right_t = new SelfDefImgView();
		gray_right_t.addCord(new Cordinate(1, 0));
		gray_right_t.addCord(new Cordinate(0, 1));
		gray_right_t.addCord(new Cordinate(1, 1));
		gray_right_t.addCord(new Cordinate(1, 2));
		secondViewMap.put(String.valueOf(R.id.gray_right_tblock), gray_right_t);

		blue_left_tblock = (ImageView) findViewById(R.id.blue_left_tblock);
		blue_left_tblock.setOnTouchListener(secondImgListener);
		blue_left_t = new SelfDefImgView();
		blue_left_t.addCord(new Cordinate(0, 0));
		blue_left_t.addCord(new Cordinate(0, 1));
		blue_left_t.addCord(new Cordinate(0, 2));
		blue_left_t.addCord(new Cordinate(1, 1));
		secondViewMap.put(String.valueOf(R.id.blue_left_tblock), blue_left_t);
		
		red_up_tblock = (ImageView) findViewById(R.id.red_up_tblock);
		red_up_tblock.setOnTouchListener(secondImgListener);
		red_up_t = new SelfDefImgView();
		red_up_t.addCord(new Cordinate(0, 1));
		red_up_t.addCord(new Cordinate(1, 0));
		red_up_t.addCord(new Cordinate(1, 1));
		red_up_t.addCord(new Cordinate(2, 1));
		secondViewMap.put(String.valueOf(R.id.red_up_tblock), red_up_t);
		
		// 3. 帶入計時器
		secondTxtTimer = (TextView) findViewById(R.id.secondTimer);
		secondTimer = new Timer();
		secondTimer.schedule(task, 0, 1000);
		// 將計時器歸零
		tsec=0;
		//TextView 初始化
		secondTxtTimer.setText("00:00");
	    // 開啟flag
		isStartSecondTimer = true;
	}

	//TimerTask無法直接改變元件因此要透過Handler來當橋樑
	private Handler handler =  new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
					// 計算目前時間
					secondTxtTimer.setText(TimeUtil.getFormatStr(tsec));
					break;
			}
		}
		
	};
	/**
	 * TimerTask 用來做計時器功能的thread
	 */
	private TimerTask task = new TimerTask(){

		@Override
		public void run() {
			if(isStartSecondTimer){
				//如果startflag是true則每秒tsec+1
				tsec++;
				Message message = new Message();
				 
				//傳送訊息1
				message.what =1;
				handler.sendMessage(message);
			}			
		}
		
	};
	
	public void goUpPage(View v) {
		isStartSecondTimer = false;
		secondTimer.cancel();
		finish();
	}
}
