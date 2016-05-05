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
 * @description �Ĥ@���e��
 * 
 * @author Yuanyu &���a
 *
 */
public class FirstActivity extends Activity {

	//��ܰT����toast
	private Toast tos;
	// �즲�����
	ImageView brown_rectangle, red_rectangle, brown_down_lblock, green_up_lblock;
	SelfDefImgView brown_rec, red_rec, brown_down_l, greep_up_l;
	// ��Ժ�ť��
	DragImgListener imgListener;
	// �p�ɾ�
	public static Timer timer;
	// �p�ɾ����
	TextView txtTimer;
	// 				      �ثe�֭p���      
	public static int tsec = 0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartTimer = true;
	// �ΨӰO�����m��Map
	public static HashMap<String, SelfDefImgView> viewMap = new HashMap<String, SelfDefImgView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);

		tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		// 0. �]�w�ˬd�d��
		List<Cordinate> firstCheckRange = new ArrayList<Cordinate>();
		for (int i = 3; i <= 6; i++) {
			for (int j = 4; j <= 7; j++) {
				firstCheckRange.add(new Cordinate(i, j));
			}
		}
		// 1. ���ͩ�Ժ�ť������
		imgListener = new DragImgListener(tos, viewMap, 1, firstCheckRange);
		imgListener.score = 0;
		imgListener.position = new PositionMatrix();
		// 2. ��n��|���ϼh�a�J,�åB�]�m��ť��
		brown_rectangle = (ImageView) findViewById(R.id.brown_rectangle);
		brown_rectangle.setOnTouchListener(imgListener);
		brown_rec = new SelfDefImgView();
		brown_rec.addCord(new Cordinate(0, 0));
		brown_rec.addCord(new Cordinate(0, 1));
		brown_rec.addCord(new Cordinate(0, 2));
		brown_rec.addCord(new Cordinate(0, 3));
		Log.d("occupiedList", "yuanyu List1: " + brown_rec.toString());
		viewMap.put(String.valueOf(R.id.brown_rectangle), brown_rec);
		imgListener.changeRec(0, 1, 0, 1, brown_rec.occupiedSpaceList);
		
		red_rectangle = (ImageView) findViewById(R.id.red_rectangle);
		red_rectangle.setOnTouchListener(imgListener);
		red_rec = new SelfDefImgView();
		red_rec.addCord(new Cordinate(0, 0));
		red_rec.addCord(new Cordinate(0, 1));
		red_rec.addCord(new Cordinate(0, 2));
		red_rec.addCord(new Cordinate(0, 3));
		viewMap.put(String.valueOf(R.id.red_rectangle), red_rec);
		imgListener.changeRec(9, 1, 9, 1, red_rec.occupiedSpaceList);
		
		brown_down_lblock = (ImageView) findViewById(R.id.brown_down_lblock);
		brown_down_lblock.setOnTouchListener(imgListener);
		brown_down_l = new SelfDefImgView();
		brown_down_l.addCord(new Cordinate(0, 0));
		brown_down_l.addCord(new Cordinate(0, 1));
		brown_down_l.addCord(new Cordinate(0, 2));
		brown_down_l.addCord(new Cordinate(1, 2));
		viewMap.put(String.valueOf(R.id.brown_down_lblock), brown_down_l);
		imgListener.changeRec(0, 8, 0, 8, brown_down_l.occupiedSpaceList);

		green_up_lblock = (ImageView) findViewById(R.id.green_up_lblock);
		green_up_lblock.setOnTouchListener(imgListener);
		greep_up_l = new SelfDefImgView();
		greep_up_l.addCord(new Cordinate(0, 0));
		greep_up_l.addCord(new Cordinate(1, 0));
		greep_up_l.addCord(new Cordinate(1, 1));
		greep_up_l.addCord(new Cordinate(1, 2));
		viewMap.put(String.valueOf(R.id.green_up_lblock), greep_up_l);
		imgListener.changeRec(8, 8, 8, 8, greep_up_l.occupiedSpaceList);
		Log.d("occupiedList", "yuanyu List1: " + greep_up_l.toString());
		// 3. �a�J�p�ɾ�
		txtTimer = (TextView) findViewById(R.id.timer);
		timer = new Timer();
		// �]�wTimer(task�����椺�e�A0�N��ߨ�}�l,����1�����@��)
		timer.schedule(task, 0, 1000);

		// �N�p�ɾ��k�s
		tsec = 0;
		// TextView ��l��
		txtTimer.setText("00:00");
		// �}��flag
		isStartTimer = true;

	}

	// TimerTask�L�k�������ܤ���]���n�z�LHandler�ӷ����
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// �p��ثe�ɶ�
				txtTimer.setText(TimeUtil.getFormatStr(tsec));
				break;
			}
		}

	};
	/**
	 * TimerTask �ΨӰ��p�ɾ��\�઺thread
	 */
	private TimerTask task = new TimerTask() {

		@Override
		public void run() {
			if (isStartTimer) {
				// �p�Gstartflag�Otrue�h�C��tsec+1
				tsec++;
				Message message = new Message();

				// �ǰe�T��1
				message.what = 1;
				handler.sendMessage(message);
			}
		}

	};
	
	public void initTimer(){
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				if (isStartTimer) {
					// �p�Gstartflag�Otrue�h�C��tsec+1
					tsec++;
					Message message = new Message();

					// �ǰe�T��1
					message.what = 1;
					handler.sendMessage(message);
				}
			}

		};
	}

	public static void stopTimer(){
		isStartTimer = false;
		if(timer != null){
			timer.cancel();
			timer = null;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.e("yuanyu", "yuanyu[timertask]");
		if(isStartTimer==false){
			isStartTimer=true;			
			initTimer();
			timer.schedule(task, tsec, 1000);
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
