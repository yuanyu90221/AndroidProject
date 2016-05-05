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
 * @description �ĤK���e��
 * 
 * @author Yuanyu &���a
 *
 */
public class EightthActivity extends Activity {
	// ��ܰT����Toast
	private Toast eightthTos;
	// �즲�����
	ImageView blue_left_tblock_1, green_down_tblock_2, gray_right_tblock_2, green_short_ublock, red_leftup_lblock, pink_square_2_block;
	SelfDefImgView blue_left_t_1, green_down_t_2, gray_right_t_2, green_short_u, red_leftup_l, pink_square_2;
	//��Ժ�ť��
	DragImgListener eightthImgListener;	
	//�p�ɾ� 
	public static Timer eightthTimer;	
	//�p�ɾ����
	TextView eightthTxtTimer;
	//          �ثe�֭p���        
	public static int tsec = 0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartEightthTimer = true;
	// �Ψ��x�s����m��Map
	public static HashMap<String, SelfDefImgView> eightthViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eightth);
		
		eightthTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. �]�w�ˬd�d��
		List<Cordinate> eightthCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=7 ; i++){
			for(int j=4 ; j <=8; j++){
				eightthCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. ���ͩ�Ժ�ť������
		eightthImgListener = new DragImgListener(eightthTos, eightthViewMap, 8, eightthCheckRange);
		eightthImgListener.score = 0;
		eightthImgListener.position = new PositionMatrix();
		// 2. ��n��|���ϼh�a�J,�åB�]�m��ť��
		
		blue_left_tblock_1 = (ImageView) findViewById(R.id.blue_left_tblock_1);
		blue_left_tblock_1.setOnTouchListener(eightthImgListener);
		blue_left_t_1 = new SelfDefImgView();
		blue_left_t_1.addCord(new Cordinate(0, 0));
		blue_left_t_1.addCord(new Cordinate(0, 1));
		blue_left_t_1.addCord(new Cordinate(0, 2));
		blue_left_t_1.addCord(new Cordinate(1, 1));
		eightthViewMap.put(String.valueOf(R.id.blue_left_tblock_1), blue_left_t_1);
		eightthImgListener.changeRec(0, 1, 0, 1, blue_left_t_1.occupiedSpaceList);
		
		green_down_tblock_2 = (ImageView) findViewById(R.id.green_down_tblock_2);
		green_down_tblock_2.setOnTouchListener(eightthImgListener);
		green_down_t_2 = new SelfDefImgView();
		green_down_t_2.addCord(new Cordinate(0,0));
		green_down_t_2.addCord(new Cordinate(1,0));
		green_down_t_2.addCord(new Cordinate(2,0));
		green_down_t_2.addCord(new Cordinate(1,1));
		eightthViewMap.put(String.valueOf(R.id.green_down_tblock_2), green_down_t_2);
		eightthImgListener.changeRec(3, 1, 3, 1, green_down_t_2.occupiedSpaceList);
		
		gray_right_tblock_2 = (ImageView) findViewById(R.id.gray_right_tblock_2);
		gray_right_tblock_2.setOnTouchListener(eightthImgListener);
		gray_right_t_2 = new SelfDefImgView();
		gray_right_t_2.addCord(new Cordinate(1,0));
		gray_right_t_2.addCord(new Cordinate(0,1));
		gray_right_t_2.addCord(new Cordinate(1,1));
		gray_right_t_2.addCord(new Cordinate(1,2));
		eightthViewMap.put(String.valueOf(R.id.gray_right_tblock_2), gray_right_t_2);
		eightthImgListener.changeRec(10, 1, 10, 1, gray_right_t_2.occupiedSpaceList);
		
		green_short_ublock = (ImageView) findViewById(R.id.green_short_ublock);
		green_short_ublock.setOnTouchListener(eightthImgListener);
		green_short_u = new SelfDefImgView();
		green_short_u.addCord(new Cordinate(0,0));
		green_short_u.addCord(new Cordinate(0,1));
		green_short_u.addCord(new Cordinate(1,1));
		green_short_u.addCord(new Cordinate(2,0));
		green_short_u.addCord(new Cordinate(2,1));
		eightthViewMap.put(String.valueOf(R.id.green_short_ublock), green_short_u);
		eightthImgListener.changeRec(0, 11, 0, 11, green_short_u.occupiedSpaceList);
		
		red_leftup_lblock = (ImageView) findViewById(R.id.red_leftup_lblock);
		red_leftup_lblock.setOnTouchListener(eightthImgListener);
		red_leftup_l = new SelfDefImgView();
		red_leftup_l.addCord(new Cordinate(0, 0));
		red_leftup_l.addCord(new Cordinate(1, 0));
		red_leftup_l.addCord(new Cordinate(2, 0));
		red_leftup_l.addCord(new Cordinate(0, 1));
		eightthViewMap.put(String.valueOf(R.id.red_leftup_lblock), red_leftup_l);
		eightthImgListener.changeRec(5, 11, 5, 11, red_leftup_l.occupiedSpaceList);
		
		pink_square_2_block = (ImageView) findViewById(R.id.pink_square_2_block);
		pink_square_2_block.setOnTouchListener(eightthImgListener);
		pink_square_2 = new SelfDefImgView();
		pink_square_2.addCord(new Cordinate(0, 0));
		pink_square_2.addCord(new Cordinate(0, 1));
		pink_square_2.addCord(new Cordinate(1, 0));
		pink_square_2.addCord(new Cordinate(1, 1));
		eightthViewMap.put(String.valueOf(R.id.pink_square_2_block), pink_square_2);
		eightthImgListener.changeRec(10, 11, 10, 11, pink_square_2.occupiedSpaceList);
		
		// 3. �a�J�p�ɾ�
		eightthTxtTimer = (TextView) findViewById(R.id.eightthTimer);
		eightthTimer = new Timer();
		eightthTimer.schedule(task, 0, 1000);
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		eightthTxtTimer.setText("00:00");
	    // �}��flag
		isStartEightthTimer = true;
	}

	//TimerTask�L�k�������ܤ���]���n�z�LHandler�ӷ����
	private Handler handler =  new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
					// �p��ثe�ɶ�
					eightthTxtTimer.setText(TimeUtil.getFormatStr(tsec));
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
			if(isStartEightthTimer){
				//�p�Gstartflag�Otrue�h�C��tsec+1
				tsec++;
				Message message = new Message();
				 
				//�ǰe�T��1
				message.what =1;
				handler.sendMessage(message);
			}			
		}
		
	};
	
	public void initTimer(){
		eightthTimer = new Timer();
		task = new TimerTask(){

			@Override
			public void run() {
				if(isStartEightthTimer){
					//�p�Gstartflag�Otrue�h�C��tsec+1
					tsec++;
					Message message = new Message();
					 
					//�ǰe�T��1
					message.what =1;
					handler.sendMessage(message);
				}			
			}
			
		};

	}
	
	public static void stopTimer(){
		isStartEightthTimer = false;
		if(eightthTimer != null){
			eightthTimer.cancel();
			eightthTimer = null;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.e("yuanyu", "yuanyu[timertask]");
		if(isStartEightthTimer == false){
			isStartEightthTimer = true;
			initTimer();
			eightthTimer.schedule(task, tsec, 1000);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		stopTimer();
	}
	/**
	 * �����p�ɾ� �åB�^��W�@��
	 * @param v
	 */
	public void goUpPage(View v) {
		stopTimer();
		finish();
	}
}
