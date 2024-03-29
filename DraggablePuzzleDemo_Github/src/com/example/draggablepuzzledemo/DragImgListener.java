package com.example.draggablepuzzledemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;
/**
 * @description 拖拉監聽器 用來監聽物件移動
 * 
 * @author Yuanyu & 奕軒
 * 
 */
public class DragImgListener implements OnTouchListener{
	float x = 0, y = 0; // 原本圖片存在的X,Y軸位置
	int mx = 0, my = 0; // 圖片被拖曳的X ,Y軸距離長度
	float rowx = 0, rowy = 0;
	// 移動單格的單位
    private static final int RANGE = 40;
    // 檢查範圍
    public int checkRange_x = 10, checkRange_y = 11;
    private static int fx0 = 0;
    private static int fy0 = 0;
    // 計算在目標方格內的格數
    public int score = 0;
    // 用來標註是否被占用的矩陣
    public PositionMatrix position = new PositionMatrix();
    // 修改
    public HashMap<String, SelfDefImgView> viewMap;
    // 用來確認的遊戲結束的分數
    public int checkValue = 0;
    // 檢查範圍格子
    public List<Cordinate> checkRange = new ArrayList<Cordinate>();
  
    // playLevel 1 ~ 10  
    public int playLevel;
    // 顯示
	private Toast result;
	
	public Toast getResult() {
		return result;
	}

	public void setResult(Toast result) {
		this.result = result;
	}
	
	/**
	 * 建構子
	 * 
	 * @param tos
	 * @param viewMap
	 * @param playLevel
	 * @param checkRange
	 */
	public DragImgListener(Toast tos, HashMap<String, SelfDefImgView> viewMap, int playLevel, List<Cordinate> checkRange){
		this.result = tos;
		this.viewMap = viewMap;
		this.playLevel = playLevel;
		this.checkRange = checkRange;
		this.checkValue = checkRange.size();
		this.checkRange_x = (playLevel >= 6)? 12:10;
		this.checkRange_y = (playLevel >= 6)? 13:11;
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
			if(fx0 != 0 || fy0 != 0){
				fx0 /= RANGE;
				fy0 /= RANGE;
			}
			else{
				fx0 = (int)(v.getX()/RANGE);
				fy0 =(v.getTop()/RANGE);
			}
			// 取得目前 所屬於 view 類型
			SelfDefImgView sv = viewMap.get(vid);
			Log.d("yuanyu", "yuanyu before change Pos sv : " + sv.toString());
			// 取得目前的座標
			Cordinate curpost = sv.getPosition();
			// 檢查拖拉範圍
			if(tempFx >= 0 && tempFx < checkRange_x && tempFy >= 1 && tempFy < checkRange_y){
				
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
					
					if(curpost.xindex == 0 && curpost.yindex == 0){
						fx0 = (int)(v.getX()/RANGE);
						fy0 =(v.getTop()/RANGE);
						curpost.xindex = fx0;
						curpost.yindex = fy0;						
					}
					else {
						int offsetx = (tempFx > curpost.xindex)? -1: 1;
						int offsety = (tempFy > curpost.yindex)? -1: 1;
						for(int tempX = tempFx;Math.abs(tempX-curpost.xindex) > 1 ; tempX += offsetx){
							for(int tempY = tempFy;Math.abs(tempY-curpost.yindex) > 1 ; tempY += offsety){
								if(checkRec(tempX,tempY,resultPosition,sv.occupiedSpaceList)){
									changeRec(curpost.xindex, curpost.yindex, tempX, tempY,sv.occupiedSpaceList);
									fx0 = tempX;
									fy0 = tempY;
									curpost.xindex = fx0;
									curpost.yindex = fy0;
									break;
								}
							}
						}
					}
				}
			}
			else {
				int modifiedTempFx = (tempFx >= checkRange_x) ? checkRange_x-1: 0;
				int modifiedTempFy = (tempFy >= checkRange_y) ? checkRange_y-1: 1;
				int offsetx = (modifiedTempFx > curpost.xindex )? -1:1;
				int offsety = (modifiedTempFy > curpost.yindex) ? -1:1;
				for(int tempX = modifiedTempFx;Math.abs(tempX-curpost.xindex) > 1 ; tempX += offsetx){
					for(int tempY = modifiedTempFy;Math.abs(tempY-curpost.yindex) > 1 ; tempY += offsety){
						if(checkRec(tempX,tempY,resultPosition,sv.occupiedSpaceList)){
							changeRec(curpost.xindex, curpost.yindex, tempX, tempY,sv.occupiedSpaceList);
							fx0 = tempX;
							fy0 = tempY;
							curpost.xindex = fx0;
							curpost.yindex = fy0;
							break;
						}
					}
				}
			}
			
			String cod = "";
			String cordInfo = String.format("(fx0,fy0) = (%d, %d),\n score = %d",fx0 , fy0, score);
			int currentTsec = 0;
			// 當遊戲完成
			if(score == checkValue){
				// 更改顯示訊息
				cod = String.format("過關!");
				switch(playLevel){
					case 1:
						currentTsec = FirstActivity.tsec;
						FirstActivity.stopTimer();
						break;
					case 2:						
						currentTsec = SecondActivity.tsec;
						SecondActivity.stopTimer();
						break;
					case 3:
						currentTsec = ThirdActivity.tsec;
						ThirdActivity.stopTimer();
						break;
					case 4:
						currentTsec = FourthActivity.tsec;
						FourthActivity.stopTimer();
						break;
					case 5:
						currentTsec = FifthActivity.tsec;
						FifthActivity.stopTimer();
						break;
					case 6:
						currentTsec = SixthActivity.tsec;
						SixthActivity.stopTimer();
						break;
					case 7:
						currentTsec = SeventhActivity.tsec;
						SeventhActivity.stopTimer();
						break;
					case 8:
						currentTsec = EightthActivity.tsec;
						EightthActivity.stopTimer();
						break;
					case 9:
						currentTsec = NinethActivity.tsec;
						NinethActivity.stopTimer();
						break;
					case 10:
						currentTsec = TenthActivity.tsec;
						TenthActivity.stopTimer();
						break;
				}
				// 從原本在MainIndexActivity裡面的HashMap取得該level的時間
				int accTime = MainIndexActivity.levelTimes.get(playLevel);
				// 如果累計時間 == 0 直接更新
				if(accTime==0){
					DBUtil.updateData(MainIndexActivity.db, currentTsec, playLevel);
				}
				else{
					// 如果累計時間!=0 且 新的闖關時間 < 舊的累計時間 更新
					if(accTime > currentTsec){
						DBUtil.updateData(MainIndexActivity.db, currentTsec, playLevel);
					}
				}
			}
			else {
				cod = cordInfo;
			}
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
	
	/**
	 * 改變矩陣內容,並且把檢查是否在目標範圍內
	 * 
	 * @param orgw
	 * @param orgh
	 * @param tagw
	 * @param tagh
	 * @param occpuiedList
	 */
	public void changeRec(int orgw, int orgh, int tagw, int tagh,List<Cordinate> occpuiedList){
		for(Cordinate offset : occpuiedList){
			position.changeValue(orgw + offset.xindex, orgh+ offset.yindex, false);
			position.changeValue(tagw + offset.xindex, tagh+ offset.yindex, true);
			Cordinate removeCord = new Cordinate(orgw + offset.xindex, orgh+ offset.yindex);
			Cordinate addedCord = new Cordinate(tagw + offset.xindex, tagh+ offset.yindex);
			if(checkRange.contains(removeCord)){
				score -= 1;
			}
			if(checkRange.contains(addedCord)){
				score += 1;
			}

		}
	}
	
	/**
	 * 檢查範圍
	 * 
	 * @param orgw
	 * @param orgh
	 * @param resultPosition
	 * @param occpuiedList
	 * @return
	 */
	public boolean checkRec(int orgw, int orgh,PositionElement[][] resultPosition, List<Cordinate> occpuiedList){
		for(Cordinate offset : occpuiedList){
			if((orgw + offset.xindex >= checkRange_x || orgh + offset.yindex >= checkRange_y ) || resultPosition[orgw + offset.xindex][orgh+offset.yindex].isOccupied()){
				return false;
			}
		}
		return true;
	}

	
}
