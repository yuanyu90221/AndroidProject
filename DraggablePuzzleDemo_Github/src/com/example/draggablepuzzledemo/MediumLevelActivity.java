package com.example.draggablepuzzledemo;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/**
 * @description �������d�e�� 
 * 
 * @author Jane
 *
 */
public class MediumLevelActivity extends Activity {

	SQLiteDatabase db;
	TextView[] timerTxt = new TextView[5];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medium_level);
		// �}�ҩΫإ߸�Ʈw
        db = openOrCreateDatabase(DBUtil.DB_NAME,  Context.MODE_PRIVATE, null);	
        int[] levels = {6,7,8,9,10};
        timerTxt[0] = (TextView) findViewById(R.id.level6time);
        timerTxt[1] = (TextView) findViewById(R.id.level7time);
        timerTxt[2] = (TextView) findViewById(R.id.level8time);
        timerTxt[3] = (TextView) findViewById(R.id.level9time);
        timerTxt[4] = (TextView) findViewById(R.id.level10time);
        HashMap<Integer, Integer> resultMap;
        resultMap = DBUtil.queryRangeDate(db, levels);
        if(resultMap!=null && resultMap.size() == 5){
        	for(int i =0; i < 5; i++){
        		int tsec = resultMap.get(i+6);
        		MainIndexActivity.levelTimes.put(i+6,tsec);
        		timerTxt[i].setText(TimeUtil.getFormatStr(tsec));
        	}
        }
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		int[] levels = {6,7,8,9,10};
        timerTxt[0] = (TextView) findViewById(R.id.level6time);
        timerTxt[1] = (TextView) findViewById(R.id.level7time);
        timerTxt[2] = (TextView) findViewById(R.id.level8time);
        timerTxt[3] = (TextView) findViewById(R.id.level9time);
        timerTxt[4] = (TextView) findViewById(R.id.level10time);
        HashMap<Integer, Integer> resultMap;
        resultMap = DBUtil.queryRangeDate(db, levels);
        if(resultMap!=null && resultMap.size() == 5){
        	for(int i =0; i < 5; i++){
        		int tsec = resultMap.get(i+6);
        		MainIndexActivity.levelTimes.put(i+6,tsec);
        		timerTxt[i].setText(TimeUtil.getFormatStr(tsec));
        	}
        }
	}

	public void goUpPage(View v) {
		finish();
	}

	public void goMediumLevel1(View v) {

		Intent it = new Intent(this, SixthActivity.class); 
		startActivity(it); // �Ұ� Intent �����ؼ�
	}
	public void goMediumLevel2(View v) {

		Intent it = new Intent(this, SeventhActivity.class); 
		startActivity(it); // �Ұ� Intent �����ؼ�
	}
	public void goMediumLevel3(View v) {

		Intent it = new Intent(this, EightthActivity.class); 
		startActivity(it); // �Ұ� Intent �����ؼ�
	}
	
	public void goMediumLevel4(View v) {

		Intent it = new Intent(this, NinethActivity.class); 
		startActivity(it); // �Ұ� Intent �����ؼ�
	}
	public void goMediumLevel5(View v) {

		Intent it = new Intent(this, TenthActivity.class); 
		startActivity(it); // �Ұ� Intent �����ؼ�
	}
}
