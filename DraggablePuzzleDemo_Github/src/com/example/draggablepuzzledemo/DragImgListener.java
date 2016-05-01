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
 * @author Yuanyu
 * 
 */
public class DragImgListener implements OnTouchListener{
	float x = 0, y = 0; // 原本圖片存在的X,Y軸位置
	int mx = 0, my = 0; // 圖片被拖曳的X ,Y軸距離長度
	float rowx = 0, rowy = 0;
    private static final int RANGE = 40;
    
    public int checkRange_x = 10, checkRange_y = 11;
    private static int fx0 = 0;
    private static int fy0 = 0;
    // 計算在目標方格內的格數
    public int score = 0;
    // 用來標註是否被占用的矩陣
    public PositionMatrix position = new PositionMatrix();
    // 
    public HashMap<String, SelfDefImgView> viewMap;
    // 用來確認的遊戲結束的分數
    public int checkValue = 0;
    public List<Cordinate> checkRange = new ArrayList<Cordinate>();
  
    // playLevel 1 ~ 10  
    public int playLevel;
	private Toast result;
	
	public Toast getResult() {
		return result;
	}

	public void setResult(Toast result) {
		this.result = result;
	}
	
	public DragImgListener(Toast tos, HashMap<String, SelfDefImgView> viewMap, int playLevel, List<Cordinate> checkRange){
		this.result = tos;
		this.viewMap = viewMap;
		this.playLevel = playLevel;
		this.checkRange = checkRange;
		this.checkValue = checkRange.size();
		this.checkRange_x = (playLevel >= 5)? 12:10;
		this.checkRange_y = (playLevel >= 5)? 13:11;
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
					}
				}
			}
			
			String cod = String.format("(fx0,fy0) = (%d, %d),\n score = %d",fx0 , fy0, score);
			int currentTsec = 0;
			if(score == checkValue){
				
				cod = "過關!";
				switch(playLevel){
					case 1:
						FirstActivity.isStartTimer = false;
						currentTsec = FirstActivity.tsec;
						FirstActivity.timer.cancel();
						break;
					case 2:
						SecondActivity.isStartSecondTimer = false;
						currentTsec = SecondActivity.tsec;
						SecondActivity.secondTimer.cancel();
						break;
					case 3:
						ThirdActivity.isStartThirdTimer = false;
						currentTsec = ThirdActivity.tsec;
						ThirdActivity.thirdTimer.cancel();
						break;
					case 4:
						FourthActivity.isStartFourthTimer = false;
						currentTsec = FourthActivity.tsec;
						FourthActivity.fourthTimer.cancel();
						break;
					case 5:
						FifthActivity.isStartFifthTimer = false;
						currentTsec = FifthActivity.tsec;
						FifthActivity.fifthTimer.cancel();
						break;
					case 6:
						SixthActivity.isStartSixthTimer = false;
						currentTsec = SixthActivity.tsec;
						SixthActivity.sixthTimer.cancel();
						break;
					case 7:
						SeventhActivity.isStartSeventhTimer = false;
						currentTsec = SeventhActivity.tsec;
						SeventhActivity.seventhTimer.cancel();
						break;
					case 8:
						EightthActivity.isStartEightthTimer = false;
						currentTsec = EightthActivity.tsec;
						EightthActivity.eightthTimer.cancel();
						break;
					case 9:
						NinethActivity.isStartNinethTimer = false;
						currentTsec = NinethActivity.tsec;
						NinethActivity.ninethTimer.cancel();
						break;
					case 10:
						TenthActivity.isStartTenthTimer = false;
						currentTsec = TenthActivity.tsec;
						TenthActivity.tenthTimer.cancel();
						break;
				}
				int accTime = MainIndexActivity.levelTimes.get(playLevel);
				// acctTime = 0 update
				if(accTime==0){
					DBUtil.updateData(MainIndexActivity.db, currentTsec, playLevel);
				}
				else{
					if(accTime > currentTsec){
						DBUtil.updateData(MainIndexActivity.db, currentTsec, playLevel);
					}
				}
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
	
	public boolean checkRec(int orgw, int orgh,PositionElement[][] resultPosition, List<Cordinate> occpuiedList){
		for(Cordinate offset : occpuiedList){
			if((orgw + offset.xindex >= checkRange_x || orgh + offset.yindex >= checkRange_y ) || resultPosition[orgw + offset.xindex][orgh+offset.yindex].isOccupied()){
				return false;
			}
		}
		return true;
	}

}
