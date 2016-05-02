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
/**
 * @description 第六關畫面
 * 
 * @author Yuanyu &奕軒
 *
 */
public class SixthActivity extends Activity {
	//顯示訊息的toast
	private Toast sixthTos;
	//拖曳的方塊
	ImageView lightblue_leftdown_lblock, green_laydown_leftup_lblock, brown_square_2_block, yellow_n_block,green_rightup_lblock,lightgreen_leftup_lblock;
	SelfDefImgView lightblue_leftdown_l, green_laydown_leftup_l, brown_square_2, yellow_n, green_rightup_l, lightgreen_leftup_l;
	//拖拉監聽器
	DragImgListener sixthImgListener;	
	//計時器 
	public static Timer sixthTimer;	
	//計時器顯示
	TextView sixthTxtTimer;
	//          目前累計秒數         
	public static int tsec = 0;
	// 啟動計時器flag
	public static boolean isStartSixthTimer = true;
	// 用來記住方格位置的Map
	public static HashMap<String, SelfDefImgView> sixthViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sixth);
		
		sixthTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. 設定檢查範圍
		List<Cordinate> sixthCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=7 ; i++){
			for(int j=4 ; j <=8; j++){
				sixthCheckRange.add(new Cordinate(i,j));
			}
		}
		
		// 1. 產生拖拉監聽器物件
		sixthImgListener = new DragImgListener(sixthTos, sixthViewMap, 6, sixthCheckRange);
		sixthImgListener.score = 0;
		sixthImgListener.position = new PositionMatrix();
		
		// 2. 把要拖弋的圖層帶入,並且設置監聽器
		lightblue_leftdown_lblock = (ImageView) findViewById(R.id.lightblue_leftdown_lblock);
		lightblue_leftdown_lblock.setOnTouchListener(sixthImgListener);
		lightblue_leftdown_l = new SelfDefImgView();
		lightblue_leftdown_l.addCord(new Cordinate(0,0));
		lightblue_leftdown_l.addCord(new Cordinate(0,1));
		lightblue_leftdown_l.addCord(new Cordinate(0,2));
		lightblue_leftdown_l.addCord(new Cordinate(1,2));
		lightblue_leftdown_l.addCord(new Cordinate(2,2));
		sixthViewMap.put(String.valueOf(R.id.lightblue_leftdown_lblock), lightblue_leftdown_l);
		sixthImgListener.changeRec(0, 1, 0, 1, lightblue_leftdown_l.occupiedSpaceList);
		
		green_laydown_leftup_lblock = (ImageView) findViewById(R.id.green_laydown_leftup_lblock);
		green_laydown_leftup_lblock.setOnTouchListener(sixthImgListener);
		green_laydown_leftup_l = new SelfDefImgView();
		green_laydown_leftup_l.addCord(new Cordinate(0,0));
		green_laydown_leftup_l.addCord(new Cordinate(1,0));
		green_laydown_leftup_l.addCord(new Cordinate(2,0));
		green_laydown_leftup_l.addCord(new Cordinate(0,1));
		sixthViewMap.put(String.valueOf(R.id.green_laydown_leftup_lblock), green_laydown_leftup_l);
		sixthImgListener.changeRec(4, 1, 4, 1, green_laydown_leftup_l.occupiedSpaceList);
		
		brown_square_2_block = (ImageView) findViewById(R.id.brown_square_2_block);
		brown_square_2_block.setOnTouchListener(sixthImgListener);
		brown_square_2 = new SelfDefImgView();
		brown_square_2.addCord(new Cordinate(0,0));
		brown_square_2.addCord(new Cordinate(0,1));
		brown_square_2.addCord(new Cordinate(1,0));
		brown_square_2.addCord(new Cordinate(1,1));
		sixthViewMap.put(String.valueOf(R.id.brown_square_2_block), brown_square_2);
		sixthImgListener.changeRec(10, 1, 10, 1, brown_square_2.occupiedSpaceList);
		
		yellow_n_block = (ImageView) findViewById(R.id.yellow_n_block);
		yellow_n_block.setOnTouchListener(sixthImgListener);
		yellow_n = new SelfDefImgView();
		yellow_n.addCord(new Cordinate(1, 0));
		yellow_n.addCord(new Cordinate(0, 1));
		yellow_n.addCord(new Cordinate(1, 1));
		yellow_n.addCord(new Cordinate(0, 2));
		sixthViewMap.put(String.valueOf(R.id.yellow_n_block), yellow_n);
		sixthImgListener.changeRec(0, 10, 0, 10, yellow_n.occupiedSpaceList);
		
		green_rightup_lblock = (ImageView) findViewById(R.id.green_rightup_lblock);
		green_rightup_lblock.setOnTouchListener(sixthImgListener);
		green_rightup_l = new SelfDefImgView();
		green_rightup_l.addCord(new Cordinate(0,0));
		green_rightup_l.addCord(new Cordinate(1,0));
		green_rightup_l.addCord(new Cordinate(1,1));
		green_rightup_l.addCord(new Cordinate(1,2));
		sixthViewMap.put(String.valueOf(R.id.green_rightup_lblock), green_rightup_l);
		sixthImgListener.changeRec(5, 10, 5, 10, green_rightup_l.occupiedSpaceList);
		
		lightgreen_leftup_lblock = (ImageView) findViewById(R.id.lightgreen_leftup_lblock);
		lightgreen_leftup_lblock.setOnTouchListener(sixthImgListener);
		lightgreen_leftup_l = new SelfDefImgView();
		lightgreen_leftup_l.addCord(new Cordinate(0,0));
		lightgreen_leftup_l.addCord(new Cordinate(0,1));
		lightgreen_leftup_l.addCord(new Cordinate(0,2));
		lightgreen_leftup_l.addCord(new Cordinate(1,0));
		sixthViewMap.put(String.valueOf(R.id.lightgreen_leftup_lblock), lightgreen_leftup_l);
		sixthImgListener.changeRec(10, 10, 10, 10, lightgreen_leftup_l.occupiedSpaceList);
		
		// 3. 帶入計時器
		sixthTxtTimer = (TextView) findViewById(R.id.sixthTimer);
		sixthTimer = new Timer();
		sixthTimer.schedule(task, 0, 1000);
		// 將計時器歸零
		tsec=0;
		//TextView 初始化
		sixthTxtTimer.setText("00:00");
	    // 開啟flag
		isStartSixthTimer = true;
	}

	//TimerTask無法直接改變元件因此要透過Handler來當橋樑
	private Handler handler =  new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
					// 計算目前時間
					sixthTxtTimer.setText(TimeUtil.getFormatStr(tsec));
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
			if(isStartSixthTimer){
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
	 * 關閉計時器 並且回到上一頁
	 * @param v
	 */
	public void goUpPage(View v) {
		isStartSixthTimer = false;
		sixthTimer.cancel();
		finish();
	}
}
