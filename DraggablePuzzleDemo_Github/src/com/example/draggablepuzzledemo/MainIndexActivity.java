package com.example.draggablepuzzledemo;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * @description 提供選擇等級畫面 
 * 
 * @author Jane
 *
 */
public class MainIndexActivity extends Activity {
	//建立資料庫
	public static SQLiteDatabase db;
	Cursor cur;
	ImageView imgv1,imgv2,imgv3;
	//用來儲存每個level的時間
	public static HashMap<Integer,Integer> levelTimes = new HashMap<Integer,Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//設定螢幕不隨手機旋轉
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//設定螢幕直向顯示
		setContentView(R.layout.activity_main_index);
		imgv1 = (ImageView) findViewById(R.id.imageView1);
		imgv2 = (ImageView) findViewById(R.id.imageView2);
		// 開啟或建立資料庫
        db = openOrCreateDatabase(DBUtil.DB_NAME,  Context.MODE_PRIVATE, null);	
        // 建立資料表
        db.execSQL(DBUtil.createTable);
        // 先查詢資料
        cur = db.rawQuery(DBUtil.queryTable, null);
        // 若無資料
        if(cur.getCount() == 0){
        	// 初始化資料表;
        	DBUtil.initDB(db);
        }
        // 設定目前每個關卡的時間為0
        for(int i = 1; i <= 10; i++){
        	levelTimes.put(i, 0);
        }
	}

	public void click_easy(View v) {
		Intent it = new Intent(this, EasyLevelActivity.class); //建立 Intent 並設定目標 Activity
		startActivity(it); // 啟動 Intent 中的目標

	}
	
	public void click_medium(View v) {
		Intent it = new Intent(this, MediumLevelActivity.class); //建立 Intent 並設定目標 Activity
		startActivity(it); // 啟動 Intent 中的目標

	}


}
