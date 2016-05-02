package com.example.draggablepuzzledemo;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @description 提供關卡畫面
 * 
 * @author Jane 
 */
public class EasyLevelActivity extends Activity {
	ImageView imgv1;
	SQLiteDatabase db;
	TextView[] timerTxt = new TextView[5];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_easy_level);
		
		imgv1 = (ImageView) findViewById(R.id.imageView1);
		// 開啟或建立資料庫
        db = openOrCreateDatabase(DBUtil.DB_NAME,  Context.MODE_PRIVATE, null);	
        int[] levels = {1,2,3,4,5};
        timerTxt[0] = (TextView) findViewById(R.id.level1time);
        timerTxt[1] = (TextView) findViewById(R.id.level2time);
        timerTxt[2] = (TextView) findViewById(R.id.level3time);
        timerTxt[3] = (TextView) findViewById(R.id.level4time);
        timerTxt[4] = (TextView) findViewById(R.id.level5time);
        HashMap<Integer, Integer> resultMap;
        // 查詢1~5關的累計分數
        resultMap = DBUtil.queryRangeDate(db, levels);
        if(resultMap!=null && resultMap.size() == 5){
        	for(int i =0; i < 5; i++){
        		int tsec = resultMap.get(i+1);
        		MainIndexActivity.levelTimes.put(i+1,tsec);
        		timerTxt[i].setText(TimeUtil.getFormatStr(tsec));
        	}
        }
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		int[] levels = {1,2,3,4,5};
        timerTxt[0] = (TextView) findViewById(R.id.level1time);
        timerTxt[1] = (TextView) findViewById(R.id.level2time);
        timerTxt[2] = (TextView) findViewById(R.id.level3time);
        timerTxt[3] = (TextView) findViewById(R.id.level4time);
        timerTxt[4] = (TextView) findViewById(R.id.level5time);
        HashMap<Integer, Integer> resultMap;
        resultMap = DBUtil.queryRangeDate(db, levels);
        if(resultMap!=null && resultMap.size() == 5){
        	for(int i =0; i < 5; i++){
        		int tsec = resultMap.get(i+1);
        		MainIndexActivity.levelTimes.put(i+1,tsec);
        		timerTxt[i].setText(TimeUtil.getFormatStr(tsec));
        	}
        }
	}


	public void goUpPage(View v) {
		finish();
	}

	public void goEasyLevel1(View v) {

		Intent it = new Intent(this, FirstActivity.class); 
		startActivity(it); // 啟動 Intent 中的目標
	}
	public void goEasyLevel2(View v) {

		Intent it = new Intent(this, SecondActivity.class); 
		startActivity(it); // 啟動 Intent 中的目標
	}
	public void goEasyLevel3(View v) {

		Intent it = new Intent(this, ThirdActivity.class); 
		startActivity(it); // 啟動 Intent 中的目標
	}
	
	public void goEasyLevel4(View v) {

		Intent it = new Intent(this, FourthActivity.class); 
		startActivity(it); // 啟動 Intent 中的目標
	}
	public void goEasyLevel5(View v) {

		Intent it = new Intent(this, FifthActivity.class); 
		startActivity(it); // 啟動 Intent 中的目標
	}
}
