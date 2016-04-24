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

public class ThirdActivity extends Activity {

	private Toast thirdTos;
	//�즲�����
	ImageView green_nblock, pink_rightdown_corner, red_long_tblock, orange_down_lblock;
	SelfDefImgView green_n, pink_rightdown_c, red_long_t, orange_down_l;
	//��Ժ�ť��
	DragImgListener thirdImgListener;
	
	//�p�ɾ� 
	Timer thirdTimer;
	
	//�p�ɾ����
	TextView thirdTxtTimer;
	//          �ثe�֭p���         ��ܬ��                ��ܤ�����
	private int tsec = 0,  csec =0 , cmin =0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartThirdTimer = true;
	
	public static HashMap<String, SelfDefImgView> thirdViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		
		thirdTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. �]�w�ˬd�d��
		List<Cordinate> thirdCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=6 ; i++){
			for(int j=4 ; j <=7; j++){
				thirdCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. ���ͩ�Ժ�ť������
		thirdImgListener = new DragImgListener(thirdTos, thirdViewMap, this, thirdCheckRange);
		DragImgListener.score = 0;
		thirdImgListener.position = new PositionMatrix(); 
		// 2. ��n��|���ϼh�a�J,�åB�]�m��ť��
		green_nblock = (ImageView) findViewById(R.id.green_nblock);
		green_nblock.setOnTouchListener(thirdImgListener);
		green_n = new SelfDefImgView();
		green_n.addCord(new Cordinate(0,1));
		green_n.addCord(new Cordinate(0,2));
		green_n.addCord(new Cordinate(1,0));
		green_n.addCord(new Cordinate(1,1));
		thirdViewMap.put(String.valueOf(R.id.green_nblock), green_n);
		
		pink_rightdown_corner = (ImageView) findViewById(R.id.pink_rightdown_corner);
		pink_rightdown_corner.setOnTouchListener(thirdImgListener);
		pink_rightdown_c = new SelfDefImgView();
		pink_rightdown_c.addCord(new Cordinate(0, 1));
		pink_rightdown_c.addCord(new Cordinate(1, 0));
		pink_rightdown_c.addCord(new Cordinate(1, 1));
		thirdViewMap.put(String.valueOf(R.id.pink_rightdown_corner), pink_rightdown_c);
		
		red_long_tblock = (ImageView) findViewById(R.id.red_long_tblock);
		red_long_tblock.setOnTouchListener(thirdImgListener);
		red_long_t = new SelfDefImgView();
		red_long_t.addCord(new Cordinate(0,0));
		red_long_t.addCord(new Cordinate(1,0));
		red_long_t.addCord(new Cordinate(2,0));
		red_long_t.addCord(new Cordinate(1,1));
		red_long_t.addCord(new Cordinate(1,2));
		thirdViewMap.put(String.valueOf(R.id.red_long_tblock), red_long_t);
		
		orange_down_lblock = (ImageView) findViewById(R.id.orange_down_lblock);
		orange_down_lblock.setOnTouchListener(thirdImgListener);
		orange_down_l = new SelfDefImgView();
		orange_down_l.addCord(new Cordinate(0, 0));
		orange_down_l.addCord(new Cordinate(0, 1));
		orange_down_l.addCord(new Cordinate(0, 2));
		orange_down_l.addCord(new Cordinate(1, 2));
		thirdViewMap.put(String.valueOf(R.id.orange_down_lblock), orange_down_l);
		// 3. �a�J�p�ɾ�
		thirdTxtTimer = (TextView) findViewById(R.id.thirdTimer);
		thirdTimer = new Timer();
		thirdTimer.schedule(task, 0, 1000);
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		thirdTxtTimer.setText("00:00");
	    // �}��flag
		isStartThirdTimer = true;

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
						thirdTxtTimer.setText(resultTime);
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
				if(isStartThirdTimer){
					//�p�Gstartflag�Otrue�h�C��tsec+1
					tsec++;
					Message message = new Message();
					 
					//�ǰe�T��1
					message.what =1;
					handler.sendMessage(message);
				}			
			}
			
		};

	public void stopThirdTimer(View v){
		isStartThirdTimer = false;
	}
	
	public void restartThirdTimer(View v){
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		thirdTxtTimer.setText("00:00");
	    // �}��flag
		isStartThirdTimer = true;
	}
}
