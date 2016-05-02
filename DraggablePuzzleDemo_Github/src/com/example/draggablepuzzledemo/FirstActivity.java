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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends Activity {

	// �즲�����
	ImageView brown_rectangle, red_rectangle, brown_down_lblock, green_up_lblock;
	SelfDefImgView brown_rec, red_rec, brown_down_l, greep_up_l;
	// ��Ժ�ť��
	DragImgListener imgListener;

	// �p�ɾ�
	public static Timer timer;

	// �p�ɾ����
	TextView txtTimer;
	// 				      �ثe�֭p���        ��ܬ��         ��ܤ�����
	public static int tsec = 0, csec = 0, cmin = 0;
	// �Ұʭp�ɾ�flag
	public static boolean isStartTimer = true;

	private Toast tos;

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
		
		red_rectangle = (ImageView) findViewById(R.id.red_rectangle);
		red_rectangle.setOnTouchListener(imgListener);
		red_rec = new SelfDefImgView();
		red_rec.addCord(new Cordinate(0, 0));
		red_rec.addCord(new Cordinate(0, 1));
		red_rec.addCord(new Cordinate(0, 2));
		red_rec.addCord(new Cordinate(0, 3));
		viewMap.put(String.valueOf(R.id.red_rectangle), red_rec);
		
		brown_down_lblock = (ImageView) findViewById(R.id.brown_down_lblock);
		brown_down_lblock.setOnTouchListener(imgListener);
		brown_down_l = new SelfDefImgView();
		brown_down_l.addCord(new Cordinate(0, 0));
		brown_down_l.addCord(new Cordinate(0, 1));
		brown_down_l.addCord(new Cordinate(0, 2));
		brown_down_l.addCord(new Cordinate(1, 2));
		viewMap.put(String.valueOf(R.id.brown_down_lblock), brown_down_l);

		green_up_lblock = (ImageView) findViewById(R.id.green_up_lblock);
		green_up_lblock.setOnTouchListener(imgListener);
		greep_up_l = new SelfDefImgView();
		greep_up_l.addCord(new Cordinate(0, 0));
		greep_up_l.addCord(new Cordinate(1, 0));
		greep_up_l.addCord(new Cordinate(1, 1));
		greep_up_l.addCord(new Cordinate(1, 2));
		viewMap.put(String.valueOf(R.id.green_up_lblock), greep_up_l);
		Log.d("occupiedList", "yuanyu List1: " + red_rec.toString());
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
				csec = tsec % 60;
				cmin = (tsec / 60) % 60;
				String resultTime = "", resultMin = "", resultSec = "";
				resultMin = String.valueOf(cmin).length() > 1 ? String.valueOf(cmin) : "0" + String.valueOf(cmin);
				resultSec = String.valueOf(csec).length() > 1 ? String.valueOf(csec) : "0" + String.valueOf(csec);
				resultTime = resultMin + ":" + resultSec;
				txtTimer.setText(resultTime);
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

	@Override
	protected void onResume() {
		super.onResume();
		Log.e("yuanyu", "yuanyu[timertask]");
	}

	public void goUpPage(View v) {
		isStartTimer = false;
		timer.cancel();
		finish();
	}
	
	
}
