package com.example.draggablepuzzledemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
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
    // �p��b�ؼФ�椺�����
    public int score = 0;
  
    public PositionMatrix position = new PositionMatrix();
    public HashMap<String, SelfDefImgView> viewMap;
    // �ΨӽT�{���C������������
    public static int checkValue = 0;
    public static List<Cordinate> checkRange = new ArrayList<Cordinate>();
    
    //�ΨӰO��ثe�Ҧb��Activity
    public Activity renderActivity;
	private Toast result;
	
	public Toast getResult() {
		return result;
	}

	public void setResult(Toast result) {
		this.result = result;
	}
	
	public DragImgListener(Toast tos, HashMap<String, SelfDefImgView> viewMap, Activity renderAct, List<Cordinate> checkRange){
		this.result = tos;
		this.viewMap = viewMap;
		this.renderActivity = renderAct;
		this.checkRange = checkRange;
		this.checkValue = checkRange.size();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int tempFx = 0, tempFy = 0;
		// �ΨӧP�_�ݩ����view
		String vid = String.valueOf(v.getId());
		// ���o����m�x�}
		PositionElement[][] resultPosition = position.getPosition();
		// �P�_Ĳ���ƥ�
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:// ���U�Ϥ���
			// rowx, rowy����лP�e���۹索�W���y��
			rowx = event.getRawX();
			rowy = event.getRawY();
			// v.getTop()�O�Q��Թϼh���ݻP�e���̤W�h
			x = event.getX();
			y = rowy - v.getTop(); // event.getY();
			
		case MotionEvent.ACTION_MOVE:// ���ʹϤ���
			// ���ЩҦb���y�д�h �ϧγQ��Ԫ��y�� �Y�O���ʪ���m
			mx = (int) (event.getRawX() - x);
			my = (int) (event.getRawY() - y);	
			// ��X��Ʈ沾��
			tempFx = (mx/RANGE);
			tempFy = (my/RANGE);
			// �O�s���e����
			fx0 /= RANGE;
			fy0 /= RANGE;
			// ���o�ثe ���ݩ� view ����
			SelfDefImgView sv = viewMap.get(vid);
			Log.d("yuanyu", "yuanyu before change Pos sv : " + sv.toString());
			// ���o�ثe���y��
			Cordinate curpost = sv.getPosition();
			// �ˬd��Խd��
			if(tempFx >= 0 && tempFx < 36 && tempFy >= 0 && tempFy < 36){
				
				// �ˬd�Q��Ԫ��ؼЬO�_�w�g�Q�e��F
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
				
			String cod = String.format("(fx0,fy0) = (%d, %d),\n score = %d",fx0 , fy0, score);
			if(score == checkValue){
				
				cod+= String.format("(fx0,fy0) = (%d, %d),\n score = %d, you win! time ",fx0 , fy0, score);
				if (renderActivity instanceof MainActivity) {
					MainActivity.isStartTimer = false;
				} else if(renderActivity instanceof SecondActivity) {
					SecondActivity.isStartSecondTimer = false;
				} else if(renderActivity instanceof ThirdActivity){
					ThirdActivity.isStartThirdTimer = false;
				} else if(renderActivity instanceof FourthActivity){
					FourthActivity.isStartFourthTimer = false;
				} else if(renderActivity instanceof FifthActivity){
					FifthActivity.isStartFifthTimer = false;
				} else if(renderActivity instanceof SixthActivity){
					SixthActivity.isStartSixthTimer = false;
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
		Log.e("address", String.valueOf(mx) + "~~" + String.valueOf(my)); // �O���ثe��m
		Log.e("getx", String.valueOf(x) + "~~" + String.valueOf(y)); // �O���ثe��m
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
			if(resultPosition[orgw + offset.xindex][orgh+offset.yindex].isOccupied()){
				return false;
			}
		}
		return true;
	}

}
