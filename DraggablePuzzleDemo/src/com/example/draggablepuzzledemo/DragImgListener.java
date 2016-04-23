package com.example.draggablepuzzledemo;

import java.util.List;

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
		// 用來判斷屬於哪個view
		String vid = String.valueOf(v.getId());
		// 取得佔位置矩陣
		PositionElement[][] resultPosition = position.getPosition();
		// 判斷觸控事件
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:// 按下圖片時
			// rowx, rowy為游標與畫面相對左上角座標
			rowx = event.getRawX();
			rowy = event.getRawY();
			// v.getTop()是被拖拉圖層頂端與畫面最上層
			x = event.getX();
			y = rowy - v.getTop(); // event.getY();
			
		case MotionEvent.ACTION_MOVE:// 移動圖片時
			// 把游標所在的座標減去 圖形被拖拉的座標 即是移動的位置
			mx = (int) (event.getRawX() - x);
			my = (int) (event.getRawY() - y);	
			// 算出整數格移動
			tempFx = (mx/RANGE);
			tempFy = (my/RANGE);
			// 保存之前的值
			fx0 /= RANGE;
			fy0 /= RANGE;
			// 取得目前 所屬於 view 類型
			SelfDefImgView sv = MainActivity.viewMap.get(vid);
			Log.d("yuanyu", "yuanyu before change Pos sv : " + sv.toString());
			// 取得目前的座標
			Cordinate curpost = sv.getPosition();
			// 檢查拖拉範圍
			if(tempFx >= 0 && tempFx < 36 && tempFy >= 0 && tempFy < 36){
				// 檢查被拖拉的目標是否已經被占住了
				if(checkRec(tempFx,tempFy,resultPosition, sv.occupiedSpaceList)){
					changeRec(curpost.xindex, curpost.yindex, tempFx, tempFy,sv.occupiedSpaceList);
					sv.setPosition(new Cordinate(tempFx, tempFy));
					Log.d("yuanyu", "yuanyu sv:"+ sv.toString());
					fx0 = tempFx;
					fy0 = tempFy;
				}
				else{
					fx0 = curpost.xindex;
					fy0 = curpost.yindex;
				}
			}
				
			String cod = String.format("(fx0,fy0) = (%d, %d),\n ",fx0 , fy0);
			
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
	
	private void changeRec(int orgw, int orgh, int tagw, int tagh,List<Cordinate> occpuiedList){
		for(Cordinate offset : occpuiedList){
			position.changeValue(orgw + offset.xindex, orgh+ offset.yindex, false);
			position.changeValue(tagw + offset.xindex, tagh+ offset.yindex, true);
		}
	}
	
	private boolean checkRec(int orgw, int orgh,PositionElement[][] resultPosition, List<Cordinate> occpuiedList){
		for(Cordinate offset : occpuiedList){
			if(resultPosition[orgw + offset.xindex][orgh+offset.yindex].isOccupied()){
				return false;
			}
		}
		return true;
	}

}
