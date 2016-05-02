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
 * @description 第七關畫面
 * 
 * @author Yuanyu &奕軒
 *
 */
public class SeventhActivity extends Activity {	
	//顯示訊息的toast
	private Toast seventhTos;
	// 拖曳的方塊
	ImageView brown_vertical_block, red_long_tblock, gray_short_right_tblock, blue_horizontal_block, orange_leftdown_lblock, pink_n_block;
	SelfDefImgView brown_vertical, red_long_t, gray_short_right_t, blue_horizontal, orange_leftdown_l, pink_n;
	//拖拉監聽器
	DragImgListener seventhImgListener;
	//計時器 
	public static Timer seventhTimer;
	//計時器顯示
	TextView seventhTxtTimer;
	//          目前累計秒數        
	public static int tsec = 0;
	// 啟動計時器flag
	public static boolean isStartSeventhTimer = true;
	// 用來記住方格位置的Map
	public static HashMap<String, SelfDefImgView> seventhViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seventh);
		
		seventhTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. 設定檢查範圍
		List<Cordinate> seventhCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=7 ; i++){
			for(int j=4 ; j <=8; j++){
				seventhCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. 產生拖拉監聽器物件
		seventhImgListener = new DragImgListener(seventhTos, seventhViewMap, 7, seventhCheckRange);
		seventhImgListener.score = 0;
		seventhImgListener.position = new PositionMatrix();
		
		// 2. 把要拖弋的圖層帶入,並且設置監聽器
		brown_vertical_block = (ImageView) findViewById(R.id.brown_vertical_block);
		brown_vertical_block.setOnTouchListener(seventhImgListener);
		brown_vertical = new SelfDefImgView();
		brown_vertical.addCord(new Cordinate(0, 0));
		brown_vertical.addCord(new Cordinate(0, 1));
		brown_vertical.addCord(new Cordinate(0, 2));
		brown_vertical.addCord(new Cordinate(0, 3));
		seventhViewMap.put(String.valueOf(R.id.brown_vertical_block), brown_vertical);
		seventhImgListener.changeRec(0, 1, 0, 1, brown_vertical.occupiedSpaceList);
		
		red_long_tblock = (ImageView) findViewById(R.id.red_long_tblock_1);
		red_long_tblock.setOnTouchListener(seventhImgListener);
		red_long_t = new SelfDefImgView();
		red_long_t.addCord(new Cordinate(0, 0));
		red_long_t.addCord(new Cordinate(1, 0));
		red_long_t.addCord(new Cordinate(2, 0));
		red_long_t.addCord(new Cordinate(1, 1));
		red_long_t.addCord(new Cordinate(1, 2));
		seventhViewMap.put(String.valueOf(R.id.red_long_tblock_1),red_long_t);
		seventhImgListener.changeRec(3, 1, 3, 1, red_long_t.occupiedSpaceList);
		
		gray_short_right_tblock = (ImageView) findViewById(R.id.gray_short_right_tblock);
		gray_short_right_tblock.setOnTouchListener(seventhImgListener);
		gray_short_right_t = new SelfDefImgView();
		gray_short_right_t.addCord(new Cordinate(1,0));
		gray_short_right_t.addCord(new Cordinate(0,1));
		gray_short_right_t.addCord(new Cordinate(1,1));
		gray_short_right_t.addCord(new Cordinate(1,2));
		seventhViewMap.put(String.valueOf(R.id.gray_short_right_tblock), gray_short_right_t);
		seventhImgListener.changeRec(10, 1, 10, 1, gray_short_right_t.occupiedSpaceList);
		
		blue_horizontal_block = (ImageView) findViewById(R.id.blue_horizontal_block);
		blue_horizontal_block.setOnTouchListener(seventhImgListener);
		blue_horizontal = new SelfDefImgView();
		blue_horizontal.addCord(new Cordinate(0,0));
		blue_horizontal.addCord(new Cordinate(1,0));
		blue_horizontal.addCord(new Cordinate(2,0));
		blue_horizontal.addCord(new Cordinate(3,0));
		seventhViewMap.put(String.valueOf(R.id.blue_horizontal_block), blue_horizontal);
		seventhImgListener.changeRec(0, 12, 0, 12, blue_horizontal.occupiedSpaceList);
		
		orange_leftdown_lblock = (ImageView) findViewById(R.id.orange_leftdown_lblock);
		orange_leftdown_lblock.setOnTouchListener(seventhImgListener);
		orange_leftdown_l = new SelfDefImgView();
		orange_leftdown_l.addCord(new Cordinate(0, 0));
		orange_leftdown_l.addCord(new Cordinate(0, 1));
		orange_leftdown_l.addCord(new Cordinate(0, 2));
		orange_leftdown_l.addCord(new Cordinate(1, 2));
		seventhViewMap.put(String.valueOf(R.id.orange_leftdown_lblock), orange_leftdown_l);
		seventhImgListener.changeRec(6, 10, 6, 10, orange_leftdown_l.occupiedSpaceList);
		
		pink_n_block = (ImageView) findViewById(R.id.pink_n_block);
		pink_n_block.setOnTouchListener(seventhImgListener);
		pink_n = new SelfDefImgView();
		pink_n.addCord(new Cordinate(1, 0));
		pink_n.addCord(new Cordinate(0, 1));
		pink_n.addCord(new Cordinate(1, 1));
		pink_n.addCord(new Cordinate(0, 2));
		seventhViewMap.put(String.valueOf(R.id.pink_n_block),pink_n);
		seventhImgListener.changeRec(10, 10, 10, 10, pink_n.occupiedSpaceList);
		
		// 3. 帶入計時器
		seventhTxtTimer = (TextView) findViewById(R.id.seventhTimer);
		seventhTimer = new Timer();
		seventhTimer.schedule(task, 0, 1000);
		// 將計時器歸零
		tsec=0;
		//TextView 初始化
		seventhTxtTimer.setText("00:00");
	    // 開啟flag
		isStartSeventhTimer = true;
	}
	
	//TimerTask無法直接改變元件因此要透過Handler來當橋樑
	private Handler handler =  new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
					// 計算目前時間
					seventhTxtTimer.setText(TimeUtil.getFormatStr(tsec));
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
			if(isStartSeventhTimer){
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
		if(isStartSeventhTimer == false){
			seventhTimer.schedule(task, tsec, 1000);
		}
	}

	@Override
	protected void onStop() {
		isStartSeventhTimer = false;
		seventhTimer.cancel();
		super.onStop();
	}
	/**
	 * 關閉計時器 並且回到上一頁
	 * @param v
	 */
	public void goUpPage(View v) {
		isStartSeventhTimer = false;
		seventhTimer.cancel();
		finish();
	}
	
}
