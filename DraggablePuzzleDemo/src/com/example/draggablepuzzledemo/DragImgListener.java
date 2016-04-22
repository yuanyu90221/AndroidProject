package com.example.draggablepuzzledemo;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;
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
    private static final int RANGE = 40;
    private static int fx0 = 0;
    private static int fy0 = 0;
    private static int fx1 = 0;
    private static int fy1 = 0;
    
    public static PositionMatrix position = new PositionMatrix();
    
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
		int tempFx = 0, tempFy = 0;
		boolean changeFlag = true;
		PositionElement[][] resultPosition = position.getPosition();
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
			// 算出整數格移動
			tempFx = (mx/RANGE);
			tempFy = (my/RANGE);
			fx0 /= RANGE;
			fy0 /= RANGE;
			if(tempFx != fx0 || tempFy != fy0){
				if((fx0 >= 0 && fx0 < 39) && (fy0 >= 0 && fy0 < 36) &&
					(tempFx >= 0 && tempFx < 36) && (tempFy >= 0 && tempFy < 36)){
					if(resultPosition[fy0][fx0].isOccupied()){
						
//						position.changeValue(fx0, fy0, false);
//						position.changeValue(tempFx, tempFy, true);
						for(int targetx = tempFx,targety = tempFy; targety < tempFy+4 ; targety++){
							if(resultPosition[targety][targetx].isOccupied()){
								changeFlag = false;
								break;
							}
						}
						if(changeFlag){
							ChangeRec(fx0, fy0, tempFx, tempFy);
						}
					}
					else{
						for(int targetx = tempFx,targety = tempFy; targety < tempFy+4 ; targety++){
							if(resultPosition[targety][targetx].isOccupied()){
								changeFlag = false;
								break;
							}
						}
//						ChangeRec(fx0, fy0, tempFx, tempFy);
//						position.changeValue(tempFx, tempFy, true);
						if(changeFlag){
							ChangeRec(fx0, fy0, tempFx, tempFy);
						}
					}
				}
				if(changeFlag){
					fx0 = tempFx;
					fy0 = tempFy;
				}
			}
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
		Log.e("address", String.valueOf(mx) + "~~" + String.valueOf(my)); // 記錄目前位置
		Log.e("getx", String.valueOf(x) + "~~" + String.valueOf(y)); // 記錄目前位置
		Log.e("position:", position.toString());
		return true;
	}
	
	private void ChangeRec(int orgw, int orgh, int tagw, int tagh){
		for(int i = 0 ; i <4 ;i++){
			position.changeValue(orgw, orgh+i, false);
			position.changeValue(tagw, tagh+i, true);
		}
	}

}
