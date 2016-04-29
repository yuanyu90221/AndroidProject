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

public class TenActivity extends Activity {

	private Toast tenthTos;
	// �즲�����
	ImageView blue_left_tblock_2, red_long_tblock_2, pink_rightdown_corner_1, purple_laydown_lblock_1, green_short_ublock_2, lightgreen_rightdown_lblock_1;
	SelfDefImgView blue_left_t_2, red_long_t_2, pink_rightdown_c_1, purple_laydown_l_1, green_short_u_2, lightgreen_rightdown_l_1;
	//��Ժ�ť��
	DragImgListener tenthImgListener;
	
	//�p�ɾ� 
	Timer tenthTimer;
	
	//�p�ɾ����
	TextView tenthTxtTimer;
	//          �ثe�֭p���         ��ܬ��                ��ܤ�����
	private int tsec = 0,  csec =0 , cmin =0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartTenthTimer = true;
	
	public static HashMap<String, SelfDefImgView> tenthViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ten);
		
		tenthTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. �]�w�ˬd�d��
		List<Cordinate> tenthCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=7 ; i++){
			for(int j=4 ; j <=8; j++){
				tenthCheckRange.add(new Cordinate(i,j));
			}
		}
		
		// 1. ���ͩ�Ժ�ť������
		tenthImgListener = new DragImgListener(tenthTos, tenthViewMap, this, tenthCheckRange);
		tenthImgListener.score = 0;
		tenthImgListener.position = new PositionMatrix();
		// 2. ��n��|���ϼh�a�J,�åB�]�m��ť��
		blue_left_tblock_2 = (ImageView) findViewById(R.id.blue_left_tblock_2);
		blue_left_tblock_2.setOnTouchListener(tenthImgListener);
		blue_left_t_2 = new SelfDefImgView();
		blue_left_t_2.addCord(new Cordinate(0, 0));
		blue_left_t_2.addCord(new Cordinate(0, 1));
		blue_left_t_2.addCord(new Cordinate(0, 2));
		blue_left_t_2.addCord(new Cordinate(1, 1));
		tenthViewMap.put(String.valueOf(R.id.blue_left_tblock_2), blue_left_t_2);
		
		red_long_tblock_2 = (ImageView) findViewById(R.id.red_long_tblock_2);
		red_long_tblock_2.setOnTouchListener(tenthImgListener);
		red_long_t_2 = new SelfDefImgView();
		red_long_t_2.addCord(new Cordinate(0, 0));
		red_long_t_2.addCord(new Cordinate(1, 0));
		red_long_t_2.addCord(new Cordinate(2, 0));
		red_long_t_2.addCord(new Cordinate(1, 1));
		red_long_t_2.addCord(new Cordinate(1, 2));
		tenthViewMap.put(String.valueOf(R.id.red_long_tblock_2), red_long_t_2);
		
		pink_rightdown_corner_1 = (ImageView) findViewById(R.id.pink_rightdown_corner_1);
		pink_rightdown_corner_1.setOnTouchListener(tenthImgListener);
		pink_rightdown_c_1 = new SelfDefImgView();
		pink_rightdown_c_1.addCord(new Cordinate(1, 0));
		pink_rightdown_c_1.addCord(new Cordinate(0, 1));
		pink_rightdown_c_1.addCord(new Cordinate(1, 1));
		tenthViewMap.put(String.valueOf(R.id.pink_rightdown_corner_1), pink_rightdown_c_1);
		
		purple_laydown_lblock_1 = (ImageView) findViewById(R.id.purple_laydown_lblock_1);
		purple_laydown_lblock_1.setOnTouchListener(tenthImgListener);
		purple_laydown_l_1 = new SelfDefImgView();
		purple_laydown_l_1.addCord(new Cordinate(0,0));
		purple_laydown_l_1.addCord(new Cordinate(0,1));
		purple_laydown_l_1.addCord(new Cordinate(1,1));
		purple_laydown_l_1.addCord(new Cordinate(2,1));
		tenthViewMap.put(String.valueOf(R.id.purple_laydown_lblock_1), purple_laydown_l_1);
		
		green_short_ublock_2 = (ImageView) findViewById(R.id.green_short_ublock_2);
		green_short_ublock_2.setOnTouchListener(tenthImgListener);
		green_short_u_2 = new SelfDefImgView();
		green_short_u_2.addCord(new Cordinate(0,0));
		green_short_u_2.addCord(new Cordinate(0,1));
		green_short_u_2.addCord(new Cordinate(1,1));
		green_short_u_2.addCord(new Cordinate(2,0));
		green_short_u_2.addCord(new Cordinate(2,1));
		tenthViewMap.put(String.valueOf(R.id.green_short_ublock_2), green_short_u_2);
		
		lightgreen_rightdown_lblock_1 = (ImageView) findViewById(R.id.lightgreen_rightdown_lblock_1);
		lightgreen_rightdown_lblock_1.setOnTouchListener(tenthImgListener);
		lightgreen_rightdown_l_1 = new SelfDefImgView();
		lightgreen_rightdown_l_1.addCord(new Cordinate(1, 0));
		lightgreen_rightdown_l_1.addCord(new Cordinate(1, 1));
		lightgreen_rightdown_l_1.addCord(new Cordinate(1, 2));
		lightgreen_rightdown_l_1.addCord(new Cordinate(0, 2));
		tenthViewMap.put(String.valueOf(R.id.lightgreen_rightdown_lblock_1), lightgreen_rightdown_l_1);
		
 		// 3. �a�J�p�ɾ�
		tenthTxtTimer = (TextView) findViewById(R.id.tenthTimer);
		tenthTimer = new Timer();
		tenthTimer.schedule(task, 0, 1000);
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		tenthTxtTimer.setText("00:00");
	    // �}��flag
		isStartTenthTimer = true;
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
					tenthTxtTimer.setText(resultTime);
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
			if(isStartTenthTimer){
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
	 * stop tenthTimer
	 * @param v
	 */
	public void stopTenthTimer(View v){
		
		isStartTenthTimer = false;
	}
	
	/**
	 * restart tenthTimer
	 * @param v
	 */
	public void restartTenthTimer(View v){
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		tenthTxtTimer.setText("00:00");
	    // �}��flag
		isStartTenthTimer = true;
	}
}
