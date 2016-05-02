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

public class NinethActivity extends Activity {

	private Toast ninethTos;
	// �즲�����
	ImageView red_leftup_lblock_1, gray_right_tblock_3, green_up_lblock_1, green_short_ublock_1, brown_square_2_block_1, gray_square_2_block_1;
	SelfDefImgView red_leftup_l_1, gray_right_t_3, green_up_l_1, green_short_u_1, brown_square_2_1, gray_square_2_1;
	//��Ժ�ť��
	DragImgListener ninethImgListener;
	
	//�p�ɾ� 
	public static Timer ninethTimer;
	
	//�p�ɾ����
	TextView ninethTxtTimer;
	//          �ثe�֭p���         ��ܬ��                ��ܤ�����
	public static int tsec = 0,  csec =0 , cmin =0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartNinethTimer = true;
	
	public static HashMap<String, SelfDefImgView> ninethViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nineth);
		
		ninethTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. �]�w�ˬd�d��
		List<Cordinate> ninethCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=7 ; i++){
			for(int j=4 ; j <=8; j++){
				ninethCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. ���ͩ�Ժ�ť������
		ninethImgListener = new DragImgListener(ninethTos, ninethViewMap, 9, ninethCheckRange);
		ninethImgListener.score = 0;
		ninethImgListener.position = new PositionMatrix();
		// 2. ��n��|���ϼh�a�J,�åB�]�m��ť��
		red_leftup_lblock_1 = (ImageView) findViewById(R.id.red_leftup_lblock_1);
		red_leftup_lblock_1.setOnTouchListener(ninethImgListener);
		red_leftup_l_1 = new SelfDefImgView();
		red_leftup_l_1.addCord(new Cordinate(0, 0));
		red_leftup_l_1.addCord(new Cordinate(1, 0));
		red_leftup_l_1.addCord(new Cordinate(2, 0));
		red_leftup_l_1.addCord(new Cordinate(0, 1));
		ninethViewMap.put(String.valueOf(R.id.red_leftup_lblock_1), red_leftup_l_1);
		
		gray_right_tblock_3 = (ImageView) findViewById(R.id.gray_right_tblock_3);
		gray_right_tblock_3.setOnTouchListener(ninethImgListener);
		gray_right_t_3 = new SelfDefImgView();
		gray_right_t_3.addCord(new Cordinate(1,0));
		gray_right_t_3.addCord(new Cordinate(0,1));
		gray_right_t_3.addCord(new Cordinate(1,1));
		gray_right_t_3.addCord(new Cordinate(1,2));
		ninethViewMap.put(String.valueOf(R.id.gray_right_tblock_3), gray_right_t_3);
		
		green_up_lblock_1 = (ImageView) findViewById(R.id.green_up_lblock_1);
		green_up_lblock_1.setOnTouchListener(ninethImgListener);
		green_up_l_1 = new SelfDefImgView();
		green_up_l_1.addCord(new Cordinate(0, 0));
		green_up_l_1.addCord(new Cordinate(1, 0));
		green_up_l_1.addCord(new Cordinate(1, 1));
		green_up_l_1.addCord(new Cordinate(1, 2));
		ninethViewMap.put(String.valueOf(R.id.green_up_lblock_1), green_up_l_1);
		
		green_short_ublock_1 = (ImageView) findViewById(R.id.green_short_ublock_1);
		green_short_ublock_1.setOnTouchListener(ninethImgListener);
		green_short_u_1 = new SelfDefImgView();
		green_short_u_1.addCord(new Cordinate(0,0));
		green_short_u_1.addCord(new Cordinate(0,1));
		green_short_u_1.addCord(new Cordinate(1,1));
		green_short_u_1.addCord(new Cordinate(2,0));
		green_short_u_1.addCord(new Cordinate(2,1));
		ninethViewMap.put(String.valueOf(R.id.green_short_ublock_1), green_short_u_1);
		
		brown_square_2_block_1 = (ImageView) findViewById(R.id.brown_square_2_block_1);
		brown_square_2_block_1.setOnTouchListener(ninethImgListener);
		brown_square_2_1 = new SelfDefImgView();
		brown_square_2_1.addCord(new Cordinate(0,0));
		brown_square_2_1.addCord(new Cordinate(0,1));
		brown_square_2_1.addCord(new Cordinate(1,0));
		brown_square_2_1.addCord(new Cordinate(1,1));
		ninethViewMap.put(String.valueOf(R.id.brown_square_2_block_1), brown_square_2_1);
		
		gray_square_2_block_1 = (ImageView) findViewById(R.id.gray_square_2_block_1);
		gray_square_2_block_1.setOnTouchListener(ninethImgListener);
		gray_square_2_1 = new SelfDefImgView();
		gray_square_2_1.addCord(new Cordinate(0, 0));
		gray_square_2_1.addCord(new Cordinate(0, 1));
		gray_square_2_1.addCord(new Cordinate(1, 0));
		gray_square_2_1.addCord(new Cordinate(1, 1));
		ninethViewMap.put(String.valueOf(R.id.gray_square_2_block_1), gray_square_2_1);

		// 3. �a�J�p�ɾ�
		ninethTxtTimer = (TextView) findViewById(R.id.ninethTimer);
		ninethTimer = new Timer();
		ninethTimer.schedule(task, 0, 1000);
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		ninethTxtTimer.setText("00:00");
	    // �}��flag
		isStartNinethTimer = true;
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
					ninethTxtTimer.setText(resultTime);
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
			if(isStartNinethTimer){
				//�p�Gstartflag�Otrue�h�C��tsec+1
				tsec++;
				Message message = new Message();
				 
				//�ǰe�T��1
				message.what =1;
				handler.sendMessage(message);
			}			
		}
		
	};
	
	public void goUpPage(View v) {
		isStartNinethTimer = false;
		ninethTimer.cancel();
		finish();
	}
}
