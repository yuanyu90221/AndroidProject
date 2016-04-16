package com.example.draggablepuzzledemo;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
/**
 * @description ��Ժ�ť�� �ΨӺ�ť���󲾰�
 * 
 * @author Yuanyu
 * 
 */
public class DragImgListener implements OnTouchListener{
	float x = 0, y = 0; // �쥻�Ϥ��s�b��X,Y�b��m
	int mx = 0, my = 0; // �Ϥ��Q�즲��X ,Y�b�Z������
	float rowx = 0, rowy = 0;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// �P�_Ĳ���ƥ�
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:// ���U�Ϥ���
			// rowx, rowy����лP�e���۹索�W���y��
			rowx = event.getRawX();
			rowy = event.getRawY();
			// v.getTop()�O�Q��Թϼh���ݻP�e���̤W�h���Z��
			// event.getX()�ӳQ��ԹϧΪ�x�b��m
			x = event.getX();
			y = rowy - v.getTop(); // event.getY();

		case MotionEvent.ACTION_MOVE:// ���ʹϤ���
			// ���ЩҦb���y�д�h �ϧγQ��Ԫ��y�� �Y�O���ʪ���m
			mx = (int) (event.getRawX() - x);
			my = (int) (event.getRawY() - y);			
			
			v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());

			break;
		case MotionEvent.ACTION_CANCEL:
		}
		Log.e("view", String.valueOf(v.getX()) + "~~" + String.valueOf(v.getY()));
		Log.e("address", String.valueOf(mx) + "~~" + String.valueOf(my)); // �O���ثe��m
		Log.e("getx", String.valueOf(x) + "~~" + String.valueOf(y)); // �O���ثe��m
		return true;
	}

}
