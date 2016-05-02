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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * @description 第四關畫面
 * 
 * @author Yuanyu &奕軒
 *
 */
public class FourthActivity extends Activity {
	// 用來顯示訊息的Toast
	private Toast fourthTos;
	//拖曳的方塊
	ImageView green_down_tblock_1, gray_right_tblock_1, purple_laydown_lblock, blue_laydown_lblock;
	SelfDefImgView green_down_t_1, gray_right_t_1, purple_laydown_l, blue_laydown_l;
	//拖拉監聽器
	DragImgListener fourthImgListener;	
	//計時器 
	public static Timer fourthTimer;	
	//計時器顯示
	TextView fourthTxtTimer;
	//             目前累計秒數        
	public static int tsec = 0;
	// 啟動計時器flag
	public static boolean isStartFourthTimer = true;
	// 用來儲存方格位置的Map
	public static HashMap<String, SelfDefImgView> fourthViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);
		
		fourthTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		
		// 0. 設定檢查範圍
		List<Cordinate> fourthCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=6 ; i++){
			for(int j=4 ; j <=7; j++){
				fourthCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. 產生拖拉監聽器物件
		fourthImgListener = new DragImgListener(fourthTos, fourthViewMap, 4, fourthCheckRange);
		fourthImgListener.score = 0;
		fourthImgListener.position = new PositionMatrix();
		
		// 2. 把要拖弋的圖層帶入,並且設置監聽器
		green_down_tblock_1 = (ImageView) findViewById(R.id.green_down_tblock_1);
		green_down_tblock_1.setOnTouchListener(fourthImgListener);
		green_down_t_1 = new SelfDefImgView();
		green_down_t_1.addCord(new Cordinate(0,0));
		green_down_t_1.addCord(new Cordinate(1,1));
		green_down_t_1.addCord(new Cordinate(1,0));
		green_down_t_1.addCord(new Cordinate(2,0));
		fourthViewMap.put(String.valueOf(R.id.green_down_tblock_1), green_down_t_1);
		fourthImgListener.changeRec(0, 1, 0, 1, green_down_t_1.occupiedSpaceList);
		
		gray_right_tblock_1 = (ImageView) findViewById(R.id.gray_right_tblock_1);
		gray_right_tblock_1.setOnTouchListener(fourthImgListener);
		gray_right_t_1 = new SelfDefImgView();
		gray_right_t_1.addCord(new Cordinate(0,1));
		gray_right_t_1.addCord(new Cordinate(1,0));
		gray_right_t_1.addCord(new Cordinate(1,1));
		gray_right_t_1.addCord(new Cordinate(1,2));
		fourthViewMap.put(String.valueOf(R.id.gray_right_tblock_1), gray_right_t_1);
		fourthImgListener.changeRec(8, 1, 8, 1, gray_right_t_1.occupiedSpaceList);
		
		purple_laydown_lblock = (ImageView) findViewById(R.id.purple_laydown_lblock);
		purple_laydown_lblock.setOnTouchListener(fourthImgListener);
		purple_laydown_l = new SelfDefImgView();
		purple_laydown_l.addCord(new Cordinate(0,0));
		purple_laydown_l.addCord(new Cordinate(0,1));
		purple_laydown_l.addCord(new Cordinate(1,1));
		purple_laydown_l.addCord(new Cordinate(2,1));
		fourthViewMap.put(String.valueOf(R.id.purple_laydown_lblock), purple_laydown_l);
		fourthImgListener.changeRec(0, 9, 0, 9, purple_laydown_l.occupiedSpaceList);
		
		blue_laydown_lblock = (ImageView) findViewById(R.id.blue_laydown_lblock);
		blue_laydown_lblock.setOnTouchListener(fourthImgListener);
		blue_laydown_l = new SelfDefImgView();
		blue_laydown_l.addCord(new Cordinate(0,0));
		blue_laydown_l.addCord(new Cordinate(1,0));
		blue_laydown_l.addCord(new Cordinate(2,0));
		blue_laydown_l.addCord(new Cordinate(3,0));
		fourthViewMap.put(String.valueOf(R.id.blue_laydown_lblock), blue_laydown_l);
		fourthImgListener.changeRec(6, 10, 6, 10, blue_laydown_l.occupiedSpaceList);
		
		// 3. 帶入計時器
		fourthTxtTimer = (TextView) findViewById(R.id.fourthTimer);
		fourthTimer = new Timer();
		fourthTimer.schedule(task, 0, 1000);
		// 將計時器歸零
		tsec=0;
		//TextView 初始化
		fourthTxtTimer.setText("00:00");
	    // 開啟flag
		isStartFourthTimer = true;
	}

	//TimerTask無法直接改變元件因此要透過Handler來當橋樑
	private Handler handler =  new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
					// 計算目前時間
					fourthTxtTimer.setText(TimeUtil.getFormatStr(tsec));
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
			if(isStartFourthTimer){
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
		if(isStartFourthTimer == false){
			fourthTimer.schedule(task, tsec, 1000);
		}
	}

	@Override
	protected void onStop() {
		isStartFourthTimer = false;
		fourthTimer.cancel();
		super.onStop();
	}
	/**
	 * 關閉計時器 並且回到上一頁
	 * @param v
	 */
	public void goUpPage(View v) {
		isStartFourthTimer = false;
		fourthTimer.cancel();
		finish();
	}
}
