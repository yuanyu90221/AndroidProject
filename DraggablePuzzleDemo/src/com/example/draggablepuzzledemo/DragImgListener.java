package com.example.draggablepuzzledemo;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
/**
 * @description 拖拉監聽器 用來監聽物件移動
 * 
 * @author Yuanyu
 * 
 */
public class DragImgListener implements OnTouchListener{
	float x = 0, y = 0; // 原本圖片存在的X,Y軸位置
	int mx = 0, my = 0; // 圖片被拖曳的X ,Y軸距離長度
	float rowx = 0, rowy = 0;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// 判斷觸控事件
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:// 按下圖片時
			// rowx, rowy為游標與畫面相對左上角座標
			rowx = event.getRawX();
			rowy = event.getRawY();
			// v.getTop()是被拖拉圖層頂端與畫面最上層的距離
			// event.getX()該被拖拉圖形的x軸位置
			x = event.getX();
			y = rowy - v.getTop(); // event.getY();

		case MotionEvent.ACTION_MOVE:// 移動圖片時
			// 把游標所在的座標減去 圖形被拖拉的座標 即是移動的位置
			mx = (int) (event.getRawX() - x);
			my = (int) (event.getRawY() - y);			
			
			v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());

			break;
		case MotionEvent.ACTION_CANCEL:
		}
		Log.e("view", String.valueOf(v.getX()) + "~~" + String.valueOf(v.getY()));
		Log.e("address", String.valueOf(mx) + "~~" + String.valueOf(my)); // 記錄目前位置
		Log.e("getx", String.valueOf(x) + "~~" + String.valueOf(y)); // 記錄目前位置
		return true;
	}

}
