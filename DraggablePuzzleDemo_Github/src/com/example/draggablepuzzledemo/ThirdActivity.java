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
/**
 * @description 第三關畫面
 * 
 * @author Yuanyu &奕軒
 *
 */
public class ThirdActivity extends Activity {
	//顯示訊息的toast
	private Toast thirdTos;
	//拖曳的方塊
	ImageView green_nblock, pink_rightdown_corner, red_long_tblock, orange_down_lblock;
	SelfDefImgView green_n, pink_rightdown_c, red_long_t, orange_down_l;
	//拖拉監聽器
	DragImgListener thirdImgListener;	
	//計時器 
	public static Timer thirdTimer;	
	//計時器顯示
	TextView thirdTxtTimer;
	//          目前累計秒數       
	public static int tsec = 0;
	// 啟動計時器flag
	public static boolean isStartThirdTimer = true;
	// 用來記住方格位置的Map
	public static HashMap<String, SelfDefImgView> thirdViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		
		thirdTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. 設定檢查範圍
		List<Cordinate> thirdCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=6 ; i++){
			for(int j=4 ; j <=7; j++){
				thirdCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. 產生拖拉監聽器物件
		thirdImgListener = new DragImgListener(thirdTos, thirdViewMap, 3, thirdCheckRange);
		thirdImgListener.score = 0;
		thirdImgListener.position = new PositionMatrix(); 
		// 2. 把要拖弋的圖層帶入,並且設置監聽器
		green_nblock = (ImageView) findViewById(R.id.green_nblock);
		green_nblock.setOnTouchListener(thirdImgListener);
		green_n = new SelfDefImgView();
		green_n.addCord(new Cordinate(0,1));
		green_n.addCord(new Cordinate(0,2));
		green_n.addCord(new Cordinate(1,0));
		green_n.addCord(new Cordinate(1,1));
		thirdViewMap.put(String.valueOf(R.id.green_nblock), green_n);
		thirdImgListener.changeRec(0, 1, 0, 1, green_n.occupiedSpaceList);
		
		pink_rightdown_corner = (ImageView) findViewById(R.id.pink_rightdown_corner);
		pink_rightdown_corner.setOnTouchListener(thirdImgListener);
		pink_rightdown_c = new SelfDefImgView();
		pink_rightdown_c.addCord(new Cordinate(0, 1));
		pink_rightdown_c.addCord(new Cordinate(1, 0));
		pink_rightdown_c.addCord(new Cordinate(1, 1));
		thirdViewMap.put(String.valueOf(R.id.pink_rightdown_corner), pink_rightdown_c);
		thirdImgListener.changeRec(8, 1, 8, 1, pink_rightdown_c.occupiedSpaceList);
		
		red_long_tblock = (ImageView) findViewById(R.id.red_long_tblock);
		red_long_tblock.setOnTouchListener(thirdImgListener);
		red_long_t = new SelfDefImgView();
		red_long_t.addCord(new Cordinate(0,0));
		red_long_t.addCord(new Cordinate(1,0));
		red_long_t.addCord(new Cordinate(2,0));
		red_long_t.addCord(new Cordinate(1,1));
		red_long_t.addCord(new Cordinate(1,2));
		thirdViewMap.put(String.valueOf(R.id.red_long_tblock), red_long_t);
		thirdImgListener.changeRec(0, 8, 0, 8, red_long_t.occupiedSpaceList);
		
		orange_down_lblock = (ImageView) findViewById(R.id.orange_down_lblock);
		orange_down_lblock.setOnTouchListener(thirdImgListener);
		orange_down_l = new SelfDefImgView();
		orange_down_l.addCord(new Cordinate(0, 0));
		orange_down_l.addCord(new Cordinate(0, 1));
		orange_down_l.addCord(new Cordinate(0, 2));
		orange_down_l.addCord(new Cordinate(1, 2));
		thirdViewMap.put(String.valueOf(R.id.orange_down_lblock), orange_down_l);
		thirdImgListener.changeRec(8, 8, 8, 8, orange_down_l.occupiedSpaceList);
		
		// 3. 帶入計時器
		thirdTxtTimer = (TextView) findViewById(R.id.thirdTimer);
		thirdTimer = new Timer();
		thirdTimer.schedule(task, 0, 1000);
		// 將計時器歸零
		tsec=0;
		//TextView 初始化
		thirdTxtTimer.setText("00:00");
	    // 開啟flag
		isStartThirdTimer = true;

	}
	
	//TimerTask無法直接改變元件因此要透過Handler來當橋樑
	private Handler handler =  new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
					// 計算目前時間
					thirdTxtTimer.setText(TimeUtil.getFormatStr(tsec));
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
			if(isStartThirdTimer){
				//如果startflag是true則每秒tsec+1
				tsec++;
				Message message = new Message();
				 
				//傳送訊息1
				message.what =1;
				handler.sendMessage(message);
			}			
		}
		
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.e("yuanyu", "yuanyu[timertask]");
		if(isStartThirdTimer == false){
			thirdTimer.schedule(task, tsec, 1000);
		}
	}

	@Override
	protected void onStop() {
		isStartThirdTimer = false;
		thirdTimer.cancel();
		super.onStop();
	}
	
	/**
	 * 關閉計時器 並且回到上一頁
	 * @param v
	 */
	public void goUpPage(View v) {
		isStartThirdTimer = false;
		thirdTimer.cancel();
		finish();

	}
}
