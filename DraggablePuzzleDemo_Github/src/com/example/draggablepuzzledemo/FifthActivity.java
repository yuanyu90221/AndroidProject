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
 * @description �Ĥ����e��
 * 
 * @author Yuanyu &���a
 *
 */
public class FifthActivity extends Activity {
	//��ܰT����Toast
	private Toast fifthTos;
	//�즲�����
	ImageView purple_leftdown_lblock, lightgreen_rightdown_lblock, gray_square_2_block, red_verticallong_block;
	SelfDefImgView purple_leftdown_l, lightgreen_rightdown_l, gray_square_2, red_verticallong;
	//��Ժ�ť��
	DragImgListener fifthImgListener;	
	//�p�ɾ� 
	public static Timer fifthTimer;	
	//�p�ɾ����
	TextView fifthTxtTimer;
	//          �ثe�֭p���        
	public static int tsec = 0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartFifthTimer = true;
	// �Ψ��x�s����m��Map
	public static HashMap<String, SelfDefImgView> fifthViewMap = new HashMap<String, SelfDefImgView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth);
		
		fifthTos = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		
		// 0. �]�w�ˬd�d��
		List<Cordinate> fifthCheckRange = new ArrayList<Cordinate>();
		for(int i =3 ; i <=6 ; i++){
			for(int j=4 ; j <=7; j++){
				fifthCheckRange.add(new Cordinate(i,j));
			}
		}
		// 1. ���ͩ�Ժ�ť������
		fifthImgListener = new DragImgListener(fifthTos, fifthViewMap, 5, fifthCheckRange);
		fifthImgListener.score = 0;
		fifthImgListener.position = new PositionMatrix();
		// 2. ��n��|���ϼh�a�J,�åB�]�m��ť��
		purple_leftdown_lblock = (ImageView) findViewById(R.id.purple_leftdown_lblock);
		purple_leftdown_lblock.setOnTouchListener(fifthImgListener);
		purple_leftdown_l = new SelfDefImgView();
		purple_leftdown_l.addCord(new Cordinate(0, 0));
		purple_leftdown_l.addCord(new Cordinate(0, 1));
		purple_leftdown_l.addCord(new Cordinate(0, 2));
		purple_leftdown_l.addCord(new Cordinate(1, 2));
		fifthViewMap.put(String.valueOf(R.id.purple_leftdown_lblock), purple_leftdown_l);
		fifthImgListener.changeRec(0, 1, 0, 1, purple_leftdown_l.occupiedSpaceList);
		
		lightgreen_rightdown_lblock = (ImageView) findViewById(R.id.lightgreen_rightdown_lblock);
		lightgreen_rightdown_lblock.setOnTouchListener(fifthImgListener);
		lightgreen_rightdown_l = new SelfDefImgView();
		lightgreen_rightdown_l.addCord(new Cordinate(1,0));
		lightgreen_rightdown_l.addCord(new Cordinate(1,1));
		lightgreen_rightdown_l.addCord(new Cordinate(1,2));
		lightgreen_rightdown_l.addCord(new Cordinate(0,2));
		fifthViewMap.put(String.valueOf(R.id.lightgreen_rightdown_lblock), lightgreen_rightdown_l);
		fifthImgListener.changeRec(8, 1, 8, 1, lightgreen_rightdown_l.occupiedSpaceList);
		
		gray_square_2_block = (ImageView) findViewById(R.id.gray_square_2_block);
		gray_square_2_block.setOnTouchListener(fifthImgListener);
		gray_square_2 = new SelfDefImgView();
		gray_square_2.addCord(new Cordinate(0, 0));
		gray_square_2.addCord(new Cordinate(1, 0));
		gray_square_2.addCord(new Cordinate(0, 1));
		gray_square_2.addCord(new Cordinate(1, 1));
		fifthViewMap.put(String.valueOf(R.id.gray_square_2_block), gray_square_2);
		fifthImgListener.changeRec(0, 9, 0, 9, gray_square_2.occupiedSpaceList);
		
		red_verticallong_block = (ImageView) findViewById(R.id.red_verticallong_block);
		red_verticallong_block.setOnTouchListener(fifthImgListener);
		red_verticallong = new SelfDefImgView();
		red_verticallong.addCord(new Cordinate(0,0));
		red_verticallong.addCord(new Cordinate(1,0));
		red_verticallong.addCord(new Cordinate(2,0));
		red_verticallong.addCord(new Cordinate(3,0));
		fifthViewMap.put(String.valueOf(R.id.red_verticallong_block), red_verticallong);
		fifthImgListener.changeRec(6, 10, 6, 10, red_verticallong.occupiedSpaceList);
		
		// 3. �a�J�p�ɾ�
		fifthTxtTimer = (TextView) findViewById(R.id.fifthTimer);
		fifthTimer = new Timer();
		fifthTimer.schedule(task, 0, 1000);
		// �N�p�ɾ��k�s
		tsec=0;
		//TextView ��l��
		fifthTxtTimer.setText("00:00");
	    // �}��flag
		isStartFifthTimer = true;
	}
	
	//TimerTask�L�k�������ܤ���]���n�z�LHandler�ӷ����
	private Handler handler =  new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 1:
					// �p��ثe�ɶ�
					fifthTxtTimer.setText(TimeUtil.getFormatStr(tsec));
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
			if(isStartFifthTimer){
				//�p�Gstartflag�Otrue�h�C��tsec+1
				tsec++;
				Message message = new Message();
				 
				//�ǰe�T��1
				message.what =1;
				handler.sendMessage(message);
			}			
		}
		
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.e("yuanyu", "yuanyu[timertask]");
		if(isStartFifthTimer == false){
			fifthTimer.schedule(task, tsec, 1000);
		}
	}

	@Override
	protected void onStop() {
		isStartFifthTimer = false;
		fifthTimer.cancel();
		super.onStop();
	}
	/**
	 * �����p�ɾ� �åB�^��W�@��
	 * @param v
	 */
	public void goUpPage(View v) {
		isStartFifthTimer = false;
		fifthTimer.cancel();
		finish();
	}
}
