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
 * @description �ĤQ���e��
 * 
 * @author Yuanyu &���a
 *
 */
public class TenthActivity extends Activity {
	// ��ܰT����Toast
	private Toast tenthTos;
	// �즲�����
	ImageView blue_left_tblock_2, red_long_tblock_2, pink_rightdown_corner_1, purple_laydown_lblock_1, green_short_ublock_2, lightgreen_rightdown_lblock_1;
	SelfDefImgView blue_left_t_2, red_long_t_2, pink_rightdown_c_1, purple_laydown_l_1, green_short_u_2, lightgreen_rightdown_l_1;
	//��Ժ�ť��
	DragImgListener tenthImgListener;	
	//�p�ɾ� 
	public static Timer tenthTimer;	
	//�p�ɾ����
	TextView tenthTxtTimer;
	//                �ثe�֭p���       
	public static int tsec = 0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartTenthTimer = true;
	
	public static HashMap<String, SelfDefImgView> tenthViewMap = new HashMap<String, SelfDefImgView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tenth);
		
		tenthTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		// 0. �]�w�ˬd�d��
		List<Cordinate> tenthCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=7 ; i++){
			for(int j=4 ; j <=8; j++){
				tenthCheckRange.add(new Cordinate(i,j));
			}
		}
		
		// 1. ���ͩ�Ժ�ť������
		tenthImgListener = new DragImgListener(tenthTos, tenthViewMap, 10, tenthCheckRange);
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
		tenthImgListener.changeRec(0, 1, 0, 1, blue_left_t_2.occupiedSpaceList);
		
		red_long_tblock_2 = (ImageView) findViewById(R.id.red_long_tblock_2);
		red_long_tblock_2.setOnTouchListener(tenthImgListener);
		red_long_t_2 = new SelfDefImgView();
		red_long_t_2.addCord(new Cordinate(0, 0));
		red_long_t_2.addCord(new Cordinate(1, 0));
		red_long_t_2.addCord(new Cordinate(2, 0));
		red_long_t_2.addCord(new Cordinate(1, 1));
		red_long_t_2.addCord(new Cordinate(1, 2));
		tenthViewMap.put(String.valueOf(R.id.red_long_tblock_2), red_long_t_2);
		tenthImgListener.changeRec(3, 1, 3, 1, red_long_t_2.occupiedSpaceList);
		
		pink_rightdown_corner_1 = (ImageView) findViewById(R.id.pink_rightdown_corner_1);
		pink_rightdown_corner_1.setOnTouchListener(tenthImgListener);
		pink_rightdown_c_1 = new SelfDefImgView();
		pink_rightdown_c_1.addCord(new Cordinate(1, 0));
		pink_rightdown_c_1.addCord(new Cordinate(0, 1));
		pink_rightdown_c_1.addCord(new Cordinate(1, 1));
		tenthViewMap.put(String.valueOf(R.id.pink_rightdown_corner_1), pink_rightdown_c_1);
		tenthImgListener.changeRec(10, 1, 10, 1, pink_rightdown_c_1.occupiedSpaceList);
		
		purple_laydown_lblock_1 = (ImageView) findViewById(R.id.purple_laydown_lblock_1);
		purple_laydown_lblock_1.setOnTouchListener(tenthImgListener);
		purple_laydown_l_1 = new SelfDefImgView();
		purple_laydown_l_1.addCord(new Cordinate(0,0));
		purple_laydown_l_1.addCord(new Cordinate(0,1));
		purple_laydown_l_1.addCord(new Cordinate(1,1));
		purple_laydown_l_1.addCord(new Cordinate(2,1));
		tenthViewMap.put(String.valueOf(R.id.purple_laydown_lblock_1), purple_laydown_l_1);
		tenthImgListener.changeRec(0, 11, 0, 11, purple_laydown_l_1.occupiedSpaceList);
		
		green_short_ublock_2 = (ImageView) findViewById(R.id.green_short_ublock_2);
		green_short_ublock_2.setOnTouchListener(tenthImgListener);
		green_short_u_2 = new SelfDefImgView();
		green_short_u_2.addCord(new Cordinate(0,0));
		green_short_u_2.addCord(new Cordinate(0,1));
		green_short_u_2.addCord(new Cordinate(1,1));
		green_short_u_2.addCord(new Cordinate(2,0));
		green_short_u_2.addCord(new Cordinate(2,1));
		tenthViewMap.put(String.valueOf(R.id.green_short_ublock_2), green_short_u_2);
		tenthImgListener.changeRec(5, 11, 5, 11, green_short_u_2.occupiedSpaceList);
		
		lightgreen_rightdown_lblock_1 = (ImageView) findViewById(R.id.lightgreen_rightdown_lblock_1);
		lightgreen_rightdown_lblock_1.setOnTouchListener(tenthImgListener);
		lightgreen_rightdown_l_1 = new SelfDefImgView();
		lightgreen_rightdown_l_1.addCord(new Cordinate(1, 0));
		lightgreen_rightdown_l_1.addCord(new Cordinate(1, 1));
		lightgreen_rightdown_l_1.addCord(new Cordinate(1, 2));
		lightgreen_rightdown_l_1.addCord(new Cordinate(0, 2));
		tenthViewMap.put(String.valueOf(R.id.lightgreen_rightdown_lblock_1), lightgreen_rightdown_l_1);
		tenthImgListener.changeRec(10, 10, 10, 10, lightgreen_rightdown_l_1.occupiedSpaceList);
		
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
					tenthTxtTimer.setText(TimeUtil.getFormatStr(tsec));
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
	 * �����p�ɾ� �åB�^��W�@��
	 * 
	 * @param v
	 */
	public void goUpPage(View v) {
		isStartTenthTimer = false;
		tenthTimer.cancel();
		finish();
	}
	
}
