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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SixthActivity extends Activity {

	private Toast sixthTos;
	//�즲�����
	ImageView lightblue_leftdown_lblock, green_laydown_leftup_lblock, brown_square_2_block, yellow_n_block,green_rightup_lblock,lightgreen_leftup_lblock;
	SelfDefImgView lightblue_leftdown_l, green_laydown_leftup_l, brown_square_2, yellow_n, green_rightup_l, lightgreen_leftup_l;
	//��Ժ�ť��
	DragImgListener sixthImgListener;
	
	//�p�ɾ� 
	Timer sixthTimer;
	
	//�p�ɾ����
	TextView sixthTxtTimer;
	//          �ثe�֭p���         ��ܬ��                ��ܤ�����
	private int tsec = 0,  csec =0 , cmin =0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartSixthTimer = true;
	
	public static HashMap<String, SelfDefImgView> sixthViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sixth);
		
		sixthTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. �]�w�ˬd�d��
		List<Cordinate> sixthCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=7 ; i++){
			for(int j=4 ; j <=8; j++){
				sixthCheckRange.add(new Cordinate(i,j));
			}
		}
		
		// 1. ���ͩ�Ժ�ť������
		sixthImgListener = new DragImgListener(sixthTos, sixthViewMap, this, sixthCheckRange);
		sixthImgListener.score = 0;
		sixthImgListener.position = new PositionMatrix();
		
		// 2. ��n��|���ϼh�a�J,�åB�]�m��ť��
		lightblue_leftdown_lblock = (ImageView) findViewById(R.id.lightblue_leftdown_lblock);
		lightblue_leftdown_lblock.setOnTouchListener(sixthImgListener);
		lightblue_leftdown_l = new SelfDefImgView();
		lightblue_leftdown_l.addCord(new Cordinate(0,0));
		lightblue_leftdown_l.addCord(new Cordinate(0,1));
		lightblue_leftdown_l.addCord(new Cordinate(0,2));
		lightblue_leftdown_l.addCord(new Cordinate(1,2));
		lightblue_leftdown_l.addCord(new Cordinate(2,2));
		sixthViewMap.put(String.valueOf(R.id.lightblue_leftdown_lblock), lightblue_leftdown_l);
		
		
		green_laydown_leftup_lblock = (ImageView) findViewById(R.id.green_laydown_leftup_lblock);
		green_laydown_leftup_lblock.setOnTouchListener(sixthImgListener);
		green_laydown_leftup_l = new SelfDefImgView();
		green_laydown_leftup_l.addCord(new Cordinate(0,0));
		green_laydown_leftup_l.addCord(new Cordinate(1,0));
		green_laydown_leftup_l.addCord(new Cordinate(2,0));
		green_laydown_leftup_l.addCord(new Cordinate(0,1));
		sixthViewMap.put(String.valueOf(R.id.green_laydown_leftup_lblock), green_laydown_leftup_l);
		
		
		brown_square_2_block = (ImageView) findViewById(R.id.brown_square_2_block);
		brown_square_2_block.setOnTouchListener(sixthImgListener);
		brown_square_2 = new SelfDefImgView();
		brown_square_2.addCord(new Cordinate(0,0));
		brown_square_2.addCord(new Cordinate(0,1));
		brown_square_2.addCord(new Cordinate(1,0));
		brown_square_2.addCord(new Cordinate(1,1));
		sixthViewMap.put(String.valueOf(R.id.brown_square_2_block), brown_square_2);
	
		
		yellow_n_block = (ImageView) findViewById(R.id.yellow_n_block);
		yellow_n_block.setOnTouchListener(sixthImgListener);
		yellow_n = new SelfDefImgView();
		yellow_n.addCord(new Cordinate(1, 0));
		yellow_n.addCord(new Cordinate(0, 1));
		yellow_n.addCord(new Cordinate(1, 1));
		yellow_n.addCord(new Cordinate(0, 2));
		sixthViewMap.put(String.valueOf(R.id.yellow_n_block), yellow_n);
		
		
		green_rightup_lblock = (ImageView) findViewById(R.id.green_rightup_lblock);
		green_rightup_lblock.setOnTouchListener(sixthImgListener);
		green_rightup_l = new SelfDefImgView();
		green_rightup_l.addCord(new Cordinate(0,0));
		green_rightup_l.addCord(new Cordinate(1,0));
		green_rightup_l.addCord(new Cordinate(1,1));
		green_rightup_l.addCord(new Cordinate(1,2));
		sixthViewMap.put(String.valueOf(R.id.green_rightup_lblock), green_rightup_l);
		
		
		lightgreen_leftup_lblock = (ImageView) findViewById(R.id.lightgreen_leftup_lblock);
		lightgreen_leftup_lblock.setOnTouchListener(sixthImgListener);
		lightgreen_leftup_l = new SelfDefImgView();
		lightgreen_leftup_l.addCord(new Cordinate(0,0));
		lightgreen_leftup_l.addCord(new Cordinate(0,1));
		lightgreen_leftup_l.addCord(new Cordinate(0,2));
		lightgreen_leftup_l.addCord(new Cordinate(1,0));
		sixthViewMap.put(String.valueOf(R.id.lightgreen_leftup_lblock), lightgreen_leftup_l);
		
		
		// 3. �a�J�p�ɾ�
		sixthTxtTimer = (TextView) findViewById(R.id.sixthTimer);
		sixthTimer = new Timer();
		sixthTimer.schedule(task, 0, 1000);
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		sixthTxtTimer.setText("00:00");
	    // �}��flag
		isStartSixthTimer = true;
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
					sixthTxtTimer.setText(resultTime);
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
			if(isStartSixthTimer){
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
	 * stop fifthTimer
	 * @param v
	 */
	public void stopSixthTimer(View v){
		
		isStartSixthTimer = false;
	}
	
	/**
	 * restart fifthTimer
	 * @param v
	 */
	public void restartSixthTimer(View v){
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		sixthTxtTimer.setText("00:00");
	    // �}��flag
		isStartSixthTimer = true;
	}
}
