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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FifthActivity extends Activity {

	private Toast fifthTos;
	//拖曳的方塊
	ImageView purple_leftdown_lblock, lightgreen_rightdown_lblock, gray_square_2_block, red_verticallong_block;
	SelfDefImgView purple_leftdown_l, lightgreen_rightdown_l, gray_square_2, red_verticallong;
	//拖拉監聽器
	DragImgListener fifthImgListener;
	
	//計時器 
	Timer fifthTimer;
	
	//計時器顯示
	TextView fifthTxtTimer;
	//          目前累計秒數         顯示秒數                顯示分鐘數
	public static int tsec = 0,  csec =0 , cmin =0;
	// 啟動計時器flag
	public static boolean isStartFifthTimer = true;
	
	public static HashMap<String, SelfDefImgView> fifthViewMap = new HashMap<String, SelfDefImgView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth);
		
		fifthTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		
		// 0. 設定檢查範圍
		List<Cordinate> fifthCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=6 ; i++){
			for(int j=4 ; j <=7; j++){
				fifthCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. 產生拖拉監聽器物件
		fifthImgListener = new DragImgListener(fifthTos, fifthViewMap, 5, fifthCheckRange);
		fifthImgListener.score = 0;
		fifthImgListener.position = new PositionMatrix();
		// 2. 把要拖弋的圖層帶入,並且設置監聽器
		purple_leftdown_lblock = (ImageView) findViewById(R.id.purple_leftdown_lblock);
		purple_leftdown_lblock.setOnTouchListener(fifthImgListener);
		purple_leftdown_l = new SelfDefImgView();
		purple_leftdown_l.addCord(new Cordinate(0, 0));
		purple_leftdown_l.addCord(new Cordinate(0, 1));
		purple_leftdown_l.addCord(new Cordinate(0, 2));
		purple_leftdown_l.addCord(new Cordinate(1, 2));
		fifthViewMap.put(String.valueOf(R.id.purple_leftdown_lblock), purple_leftdown_l);
		
		lightgreen_rightdown_lblock = (ImageView) findViewById(R.id.lightgreen_rightdown_lblock);
		lightgreen_rightdown_lblock.setOnTouchListener(fifthImgListener);
		lightgreen_rightdown_l = new SelfDefImgView();
		lightgreen_rightdown_l.addCord(new Cordinate(1,0));
		lightgreen_rightdown_l.addCord(new Cordinate(1,1));
		lightgreen_rightdown_l.addCord(new Cordinate(1,2));
		lightgreen_rightdown_l.addCord(new Cordinate(0,2));
		fifthViewMap.put(String.valueOf(R.id.lightgreen_rightdown_lblock), lightgreen_rightdown_l);
		
		gray_square_2_block = (ImageView) findViewById(R.id.gray_square_2_block);
		gray_square_2_block.setOnTouchListener(fifthImgListener);
		gray_square_2 = new SelfDefImgView();
		gray_square_2.addCord(new Cordinate(0, 0));
		gray_square_2.addCord(new Cordinate(1, 0));
		gray_square_2.addCord(new Cordinate(0, 1));
		gray_square_2.addCord(new Cordinate(1, 1));
		fifthViewMap.put(String.valueOf(R.id.gray_square_2_block), gray_square_2);
		
		red_verticallong_block = (ImageView) findViewById(R.id.red_verticallong_block);
		red_verticallong_block.setOnTouchListener(fifthImgListener);
		red_verticallong = new SelfDefImgView();
		red_verticallong.addCord(new Cordinate(0,0));
		red_verticallong.addCord(new Cordinate(1,0));
		red_verticallong.addCord(new Cordinate(2,0));
		red_verticallong.addCord(new Cordinate(3,0));
		fifthViewMap.put(String.valueOf(R.id.red_verticallong_block), red_verticallong);
		
		// 3. 帶入計時器
		fifthTxtTimer = (TextView) findViewById(R.id.fifthTimer);
		fifthTimer = new Timer();
		fifthTimer.schedule(task, 0, 1000);
		// 將計時器歸零
		tsec=0;
		//TextView 初始化
		fifthTxtTimer.setText("00:00");
	    // 開啟flag
		isStartFifthTimer = true;
	}
	
	//TimerTask無法直接改變元件因此要透過Handler來當橋樑
	private Handler handler =  new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
					// 計算目前時間
					csec = tsec %60;
					cmin = (tsec /60)%60;
					String resultTime = "",resultMin="", resultSec="";
					resultMin = String.valueOf(cmin).length() > 1 ?
							    String.valueOf(cmin):"0"+String.valueOf(cmin);
					resultSec = String.valueOf(csec).length() > 1 ?
						    	String.valueOf(csec):"0"+String.valueOf(csec);
					resultTime = resultMin + ":"+ resultSec;
					fifthTxtTimer.setText(resultTime);
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
			if(isStartFifthTimer){
				//如果startflag是true則每秒tsec+1
				tsec++;
				Message message = new Message();
				 
				//傳送訊息1
				message.what =1;
				handler.sendMessage(message);
			}			
		}
		
	};
	/**
	 * stop fifthTimer
	 * @param v
	 */
	public void stopFifthTimer(View v){
		
		isStartFifthTimer = false;
	}
	
	/**
	 * restart fifthTimer
	 * @param v
	 */
	public void restartFifthTimer(View v){
		// 將計時器歸零
		tsec=0;
		//TextView 初始化
		fifthTxtTimer.setText("00:00");
	    // 開啟flag
		isStartFifthTimer = true;
	}
	
	
	/**
	 * goto 6th level
	 * @param v
	 */
	public void gotoSixfthLevel(View v){
		Intent it = new Intent(this, SixthActivity.class);
		startActivity(it);
	}
	
	public void goUpPage(View v) {
		finish();
	}
}
