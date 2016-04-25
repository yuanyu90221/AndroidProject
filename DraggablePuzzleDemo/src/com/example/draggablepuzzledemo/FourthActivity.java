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

public class FourthActivity extends Activity {

	private Toast fourthTos;
	//�즲�����
	ImageView green_down_tblock_1, gray_right_tblock_1, purple_laydown_lblock, blue_laydown_lblock;
	SelfDefImgView green_down_t_1, gray_right_t_1, purple_laydown_l, blue_laydown_l;
	//��Ժ�ť��
	DragImgListener fourthImgListener;
	
	//�p�ɾ� 
	Timer fourthTimer;
	
	//�p�ɾ����
	TextView fourthTxtTimer;
	//          �ثe�֭p����         ��ܬ���                ��ܤ�����
	private int tsec = 0,  csec =0 , cmin =0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartFourthTimer = true;
	
	public static HashMap<String, SelfDefImgView> fourthViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);
		
		fourthTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		
		// 0. �]�w�ˬd�d��
		List<Cordinate> fourthCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=6 ; i++){
			for(int j=4 ; j <=7; j++){
				fourthCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. ���ͩ�Ժ�ť������
		fourthImgListener = new DragImgListener(fourthTos, fourthViewMap, this, fourthCheckRange);
		fourthImgListener.score = 0;
		fourthImgListener.position = new PositionMatrix();
		
		// 2. ��n��|���ϼh�a�J,�åB�]�m��ť��
		green_down_tblock_1 = (ImageView) findViewById(R.id.green_down_tblock_1);
		green_down_tblock_1.setOnTouchListener(fourthImgListener);
		green_down_t_1 = new SelfDefImgView();
		green_down_t_1.addCord(new Cordinate(0,0));
		green_down_t_1.addCord(new Cordinate(1,1));
		green_down_t_1.addCord(new Cordinate(1,0));
		green_down_t_1.addCord(new Cordinate(2,0));
		fourthViewMap.put(String.valueOf(R.id.green_down_tblock_1), green_down_t_1);
		
		gray_right_tblock_1 = (ImageView) findViewById(R.id.gray_right_tblock_1);
		gray_right_tblock_1.setOnTouchListener(fourthImgListener);
		gray_right_t_1 = new SelfDefImgView();
		gray_right_t_1.addCord(new Cordinate(0,1));
		gray_right_t_1.addCord(new Cordinate(1,0));
		gray_right_t_1.addCord(new Cordinate(1,1));
		gray_right_t_1.addCord(new Cordinate(1,2));
		fourthViewMap.put(String.valueOf(R.id.gray_right_tblock_1), gray_right_t_1);
		
		purple_laydown_lblock = (ImageView) findViewById(R.id.purple_laydown_lblock);
		purple_laydown_lblock.setOnTouchListener(fourthImgListener);
		purple_laydown_l = new SelfDefImgView();
		purple_laydown_l.addCord(new Cordinate(0,0));
		purple_laydown_l.addCord(new Cordinate(0,1));
		purple_laydown_l.addCord(new Cordinate(1,1));
		purple_laydown_l.addCord(new Cordinate(2,1));
		fourthViewMap.put(String.valueOf(R.id.purple_laydown_lblock), purple_laydown_l);
		
		blue_laydown_lblock = (ImageView) findViewById(R.id.blue_laydown_lblock);
		blue_laydown_lblock.setOnTouchListener(fourthImgListener);
		blue_laydown_l = new SelfDefImgView();
		blue_laydown_l.addCord(new Cordinate(0,0));
		blue_laydown_l.addCord(new Cordinate(1,0));
		blue_laydown_l.addCord(new Cordinate(2,0));
		blue_laydown_l.addCord(new Cordinate(3,0));
		fourthViewMap.put(String.valueOf(R.id.blue_laydown_lblock), blue_laydown_l);
		// 3. �a�J�p�ɾ�
		fourthTxtTimer = (TextView) findViewById(R.id.fourthTimer);
		fourthTimer = new Timer();
		fourthTimer.schedule(task, 0, 1000);
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		fourthTxtTimer.setText("00:00");
	    // �}��flag
		isStartFourthTimer = true;
	}

	//TimerTask�L�k�������ܤ���]���n�z�LHandler�ӷ�����
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
					fourthTxtTimer.setText(resultTime);
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
			if(isStartFourthTimer){
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
	 * stop thirdTimer
	 * @param v
	 */
	public void stopFourthTimer(View v){
		
		isStartFourthTimer = false;
	}
	
	/**
	 * restart thirdTimer
	 * @param v
	 */
	public void restartFourthTimer(View v){
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		fourthTxtTimer.setText("00:00");
	    // �}��flag
		isStartFourthTimer = true;
	}
	
	
	/**
	 * goto 5th Level 
	 * @param v
	 */
	public void gotoFivethLevel(View v){
		
	}
}