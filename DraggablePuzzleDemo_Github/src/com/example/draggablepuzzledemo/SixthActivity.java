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
 * @description �Ĥ����e��
 * 
 * @author Yuanyu &���a
 *
 */
public class SixthActivity extends Activity {
	//��ܰT����toast
	private Toast sixthTos;
	//�즲�����
	ImageView lightblue_leftdown_lblock, green_laydown_leftup_lblock, brown_square_2_block, yellow_n_block,green_rightup_lblock,lightgreen_leftup_lblock;
	SelfDefImgView lightblue_leftdown_l, green_laydown_leftup_l, brown_square_2, yellow_n, green_rightup_l, lightgreen_leftup_l;
	//��Ժ�ť��
	DragImgListener sixthImgListener;	
	//�p�ɾ� 
	public static Timer sixthTimer;	
	//�p�ɾ����
	TextView sixthTxtTimer;
	//          �ثe�֭p���         
	public static int tsec = 0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartSixthTimer = true;
	// �ΨӰO�����m��Map
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
		sixthImgListener = new DragImgListener(sixthTos, sixthViewMap, 6, sixthCheckRange);
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
		sixthImgListener.changeRec(0, 1, 0, 1, lightblue_leftdown_l.occupiedSpaceList);
		
		green_laydown_leftup_lblock = (ImageView) findViewById(R.id.green_laydown_leftup_lblock);
		green_laydown_leftup_lblock.setOnTouchListener(sixthImgListener);
		green_laydown_leftup_l = new SelfDefImgView();
		green_laydown_leftup_l.addCord(new Cordinate(0,0));
		green_laydown_leftup_l.addCord(new Cordinate(1,0));
		green_laydown_leftup_l.addCord(new Cordinate(2,0));
		green_laydown_leftup_l.addCord(new Cordinate(0,1));
		sixthViewMap.put(String.valueOf(R.id.green_laydown_leftup_lblock), green_laydown_leftup_l);
		sixthImgListener.changeRec(4, 1, 4, 1, green_laydown_leftup_l.occupiedSpaceList);
		
		brown_square_2_block = (ImageView) findViewById(R.id.brown_square_2_block);
		brown_square_2_block.setOnTouchListener(sixthImgListener);
		brown_square_2 = new SelfDefImgView();
		brown_square_2.addCord(new Cordinate(0,0));
		brown_square_2.addCord(new Cordinate(0,1));
		brown_square_2.addCord(new Cordinate(1,0));
		brown_square_2.addCord(new Cordinate(1,1));
		sixthViewMap.put(String.valueOf(R.id.brown_square_2_block), brown_square_2);
		sixthImgListener.changeRec(10, 1, 10, 1, brown_square_2.occupiedSpaceList);
		
		yellow_n_block = (ImageView) findViewById(R.id.yellow_n_block);
		yellow_n_block.setOnTouchListener(sixthImgListener);
		yellow_n = new SelfDefImgView();
		yellow_n.addCord(new Cordinate(1, 0));
		yellow_n.addCord(new Cordinate(0, 1));
		yellow_n.addCord(new Cordinate(1, 1));
		yellow_n.addCord(new Cordinate(0, 2));
		sixthViewMap.put(String.valueOf(R.id.yellow_n_block), yellow_n);
		sixthImgListener.changeRec(0, 10, 0, 10, yellow_n.occupiedSpaceList);
		
		green_rightup_lblock = (ImageView) findViewById(R.id.green_rightup_lblock);
		green_rightup_lblock.setOnTouchListener(sixthImgListener);
		green_rightup_l = new SelfDefImgView();
		green_rightup_l.addCord(new Cordinate(0,0));
		green_rightup_l.addCord(new Cordinate(1,0));
		green_rightup_l.addCord(new Cordinate(1,1));
		green_rightup_l.addCord(new Cordinate(1,2));
		sixthViewMap.put(String.valueOf(R.id.green_rightup_lblock), green_rightup_l);
		sixthImgListener.changeRec(5, 10, 5, 10, green_rightup_l.occupiedSpaceList);
		
		lightgreen_leftup_lblock = (ImageView) findViewById(R.id.lightgreen_leftup_lblock);
		lightgreen_leftup_lblock.setOnTouchListener(sixthImgListener);
		lightgreen_leftup_l = new SelfDefImgView();
		lightgreen_leftup_l.addCord(new Cordinate(0,0));
		lightgreen_leftup_l.addCord(new Cordinate(0,1));
		lightgreen_leftup_l.addCord(new Cordinate(0,2));
		lightgreen_leftup_l.addCord(new Cordinate(1,0));
		sixthViewMap.put(String.valueOf(R.id.lightgreen_leftup_lblock), lightgreen_leftup_l);
		sixthImgListener.changeRec(10, 10, 10, 10, lightgreen_leftup_l.occupiedSpaceList);
		
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
					sixthTxtTimer.setText(TimeUtil.getFormatStr(tsec));
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
	 * �����p�ɾ� �åB�^��W�@��
	 * @param v
	 */
	public void goUpPage(View v) {
		isStartSixthTimer = false;
		sixthTimer.cancel();
		finish();
	}
}
