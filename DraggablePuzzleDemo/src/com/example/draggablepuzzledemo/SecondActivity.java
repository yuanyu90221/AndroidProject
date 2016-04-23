package com.example.draggablepuzzledemo;

import java.util.HashMap;
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
	
	private Toast secondTos;
	//�즲�����
	ImageView green_down_tblock, gray_right_tblock, blue_left_tblock, red_up_tblock;
	SelfDefImgView green_down_t, gray_right_t, blue_left_t, red_up_t;
	//��Ժ�ť��
	DragImgListener secondImgListener;
	
	//�p�ɾ� 
	Timer secondTimer;
	
	//�p�ɾ����
	TextView secondTxtTimer;
	//          �ثe�֭p����         ��ܬ���                ��ܤ�����
	private int tsec = 0,  csec =0 , cmin =0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartSecondTimer = true;
	
	public static HashMap<String, SelfDefImgView> secondViewMap = new HashMap<String, SelfDefImgView>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		secondTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 1. ���ͩ�Ժ�ť������
		secondImgListener = new DragImgListener(secondTos, secondViewMap, this);
		DragImgListener.score = 0;
		secondImgListener.position = new PositionMatrix();
		// 2. ��n��|���ϼh�a�J,�åB�]�m��ť��
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
		
		// 3. �a�J�p�ɾ�
		secondTxtTimer = (TextView) findViewById(R.id.secondTimer);
		secondTimer = new Timer();
		secondTimer.schedule(task, 0, 1000);
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		secondTxtTimer.setText("00:00");
	    // �}��flag
		isStartSecondTimer = true;
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
					secondTxtTimer.setText(resultTime);
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
			if(isStartSecondTimer){
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
	 * stop the timer
	 * @param v
	 */
	public void stopSecondTimer(View v){
		isStartSecondTimer = false;
	}
	
	/**
	 * start the timer
	 * @param v
	 */
	public void restartSecondTimer(View v){
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		secondTxtTimer.setText("00:00");
	    // �}��flag
		isStartSecondTimer = true;
	}
}