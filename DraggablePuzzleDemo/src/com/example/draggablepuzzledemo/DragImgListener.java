package com.example.draggablepuzzledemo;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;
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
    private static final int RANGE = 40;
    private static int fx0 = 0;
    private static int fy0 = 0;
    private static int fx1 = 0;
    private static int fy1 = 0;
	private Toast result;
	
	public Toast getResult() {
		return result;
	}

	public void setResult(Toast result) {
		this.result = result;
	}
	
	public DragImgListener(Toast tos){
		this.result = tos;
	}

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
			// ��X��Ʈ沾��
			fx0 = (mx/RANGE);
			fy0 = (my/RANGE);
			fx1 = (mx + v.getWidth())/RANGE;
			fy1 = (my+ v.getHeight())/RANGE;
			
			String cod = String.format("(fx0,fy0) = (%d, %d),\n (fx1, fy1) = (%d, %d)",fx0 , fy0, fx1, fy1);
			
			result.setText(cod);
			result.show();
			fx0 *= RANGE;
			fy0 *= RANGE;
			//v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
			v.layout(fx0, fy0, fx0 + v.getWidth(), fy0 + v.getHeight());
			break;
		case MotionEvent.ACTION_CANCEL:
		}
		Log.e("view", String.valueOf(v.getX()) + "~~" + String.valueOf(v.getY()));
		Log.e("address", String.valueOf(mx) + "~~" + String.valueOf(my)); // �O���ثe��m
		Log.e("getx", String.valueOf(x) + "~~" + String.valueOf(y)); // �O���ثe��m
		return true;
	}

}
