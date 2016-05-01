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

public class SeventhActivity extends Activity {

	private Toast seventhTos;
	// �즲�����
	ImageView brown_vertical_block, red_long_tblock, gray_short_right_tblock, blue_horizontal_block, orange_leftdown_lblock, pink_n_block;
	SelfDefImgView brown_vertical, red_long_t, gray_short_right_t, blue_horizontal, orange_leftdown_l, pink_n;
	//��Ժ�ť��
	DragImgListener seventhImgListener;
	
	//�p�ɾ� 
	Timer seventhTimer;
	
	//�p�ɾ����
	TextView seventhTxtTimer;
	//          �ثe�֭p���         ��ܬ��                ��ܤ�����
	public static int tsec = 0,  csec =0 , cmin =0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartSeventhTimer = true;
	
	public static HashMap<String, SelfDefImgView> seventhViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seventh);
		
		seventhTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. �]�w�ˬd�d��
		List<Cordinate> seventhCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=7 ; i++){
			for(int j=4 ; j <=8; j++){
				seventhCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. ���ͩ�Ժ�ť������
		seventhImgListener = new DragImgListener(seventhTos, seventhViewMap, 7, seventhCheckRange);
		seventhImgListener.score = 0;
		seventhImgListener.position = new PositionMatrix();
		
		// 2. ��n��|���ϼh�a�J,�åB�]�m��ť��
		brown_vertical_block = (ImageView) findViewById(R.id.brown_vertical_block);
		brown_vertical_block.setOnTouchListener(seventhImgListener);
		brown_vertical = new SelfDefImgView();
		brown_vertical.addCord(new Cordinate(0, 0));
		brown_vertical.addCord(new Cordinate(0, 1));
		brown_vertical.addCord(new Cordinate(0, 2));
		brown_vertical.addCord(new Cordinate(0, 3));
		seventhViewMap.put(String.valueOf(R.id.brown_vertical_block), brown_vertical);
		
		red_long_tblock = (ImageView) findViewById(R.id.red_long_tblock_1);
		red_long_tblock.setOnTouchListener(seventhImgListener);
		red_long_t = new SelfDefImgView();
		red_long_t.addCord(new Cordinate(0, 0));
		red_long_t.addCord(new Cordinate(1, 0));
		red_long_t.addCord(new Cordinate(2, 0));
		red_long_t.addCord(new Cordinate(1, 1));
		red_long_t.addCord(new Cordinate(1, 2));
		seventhViewMap.put(String.valueOf(R.id.red_long_tblock_1),red_long_t);
		
		gray_short_right_tblock = (ImageView) findViewById(R.id.gray_short_right_tblock);
		gray_short_right_tblock.setOnTouchListener(seventhImgListener);
		gray_short_right_t = new SelfDefImgView();
		gray_short_right_t.addCord(new Cordinate(1,0));
		gray_short_right_t.addCord(new Cordinate(0,1));
		gray_short_right_t.addCord(new Cordinate(1,1));
		gray_short_right_t.addCord(new Cordinate(1,2));
		seventhViewMap.put(String.valueOf(R.id.gray_short_right_tblock), gray_short_right_t);
		
		blue_horizontal_block = (ImageView) findViewById(R.id.blue_horizontal_block);
		blue_horizontal_block.setOnTouchListener(seventhImgListener);
		blue_horizontal = new SelfDefImgView();
		blue_horizontal.addCord(new Cordinate(0,0));
		blue_horizontal.addCord(new Cordinate(1,0));
		blue_horizontal.addCord(new Cordinate(2,0));
		blue_horizontal.addCord(new Cordinate(3,0));
		seventhViewMap.put(String.valueOf(R.id.blue_horizontal_block), blue_horizontal);
		
		orange_leftdown_lblock = (ImageView) findViewById(R.id.orange_leftdown_lblock);
		orange_leftdown_lblock.setOnTouchListener(seventhImgListener);
		orange_leftdown_l = new SelfDefImgView();
		orange_leftdown_l.addCord(new Cordinate(0, 0));
		orange_leftdown_l.addCord(new Cordinate(0, 1));
		orange_leftdown_l.addCord(new Cordinate(0, 2));
		orange_leftdown_l.addCord(new Cordinate(1, 2));
		seventhViewMap.put(String.valueOf(R.id.orange_leftdown_lblock), orange_leftdown_l);
		
		pink_n_block = (ImageView) findViewById(R.id.pink_n_block);
		pink_n_block.setOnTouchListener(seventhImgListener);
		pink_n = new SelfDefImgView();
		pink_n.addCord(new Cordinate(1, 0));
		pink_n.addCord(new Cordinate(0, 1));
		pink_n.addCord(new Cordinate(1, 1));
		pink_n.addCord(new Cordinate(0, 2));
		seventhViewMap.put(String.valueOf(R.id.pink_n_block),pink_n);
		
		// 3. �a�J�p�ɾ�
		seventhTxtTimer = (TextView) findViewById(R.id.seventhTimer);
		seventhTimer = new Timer();
		seventhTimer.schedule(task, 0, 1000);
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		seventhTxtTimer.setText("00:00");
	    // �}��flag
		isStartSeventhTimer = true;
	}
	
	//TimerTask�L�k�������ܤ���]���n�z�LHandler�ӷ����
	private Handler handler =  new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
					// �p��ثe�ɶ�
					csec = tsec %60;
					cmin = (tsec /60)%60;
					String resultTime = "",resultMin="", resultSec="";
					resultMin = String.valueOf(cmin).length() > 1 ?
							    String.valueOf(cmin):"0"+String.valueOf(cmin);
					resultSec = String.valueOf(csec).length() > 1 ?
						    	String.valueOf(csec):"0"+String.valueOf(csec);
					resultTime = resultMin + ":"+ resultSec;
					seventhTxtTimer.setText(resultTime);
					break;
			}
		}
		
	};
	/**
	 * TimerTask �ΨӰ��p�ɾ��\�઺thread
	 */
	private TimerTask task = new TimerTask(){

		@Override
		public void run() {
			if(isStartSeventhTimer){
				//�p�Gstartflag�Otrue�h�C��tsec+1
				tsec++;
				Message message = new Message();
				 
				//�ǰe�T��1
				message.what =1;
				handler.sendMessage(message);
			}			
		}
		
	};
	/**
	 * stop seventhTimer
	 * @param v
	 */
	public void stopSeventhTimer(View v){
		
		isStartSeventhTimer = false;
	}
	
	/**
	 * restart seventhTimer
	 * @param v
	 */
	public void restartSeventhTimer(View v){
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		seventhTxtTimer.setText("00:00");
	    // �}��flag
		isStartSeventhTimer = true;
	}
	
	/**
	 * goto 8th level
	 * @param v
	 */
	public void gotoEightthLevel(View v){
		Intent it = new Intent(this, EightthActivity.class);
		startActivity(it);
	}
	
	public void goUpPage(View v) {
		finish();
	}
	
}
