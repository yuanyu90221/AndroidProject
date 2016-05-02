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
 * @description ���ѿ�ܵ��ŵe�� 
 * 
 * @author Jane
 *
 */
public class MainIndexActivity extends Activity {
	//�إ߸�Ʈw
	public static SQLiteDatabase db;
	Cursor cur;
	ImageView imgv1,imgv2,imgv3;
	//�Ψ��x�s�C��level���ɶ�
	public static HashMap<Integer,Integer> levelTimes = new HashMap<Integer,Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//�]�w�ù����H�������
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//�]�w�ù����V���
		setContentView(R.layout.activity_main_index);
		imgv1 = (ImageView) findViewById(R.id.imageView1);
		imgv2 = (ImageView) findViewById(R.id.imageView2);
		// �}�ҩΫإ߸�Ʈw
        db = openOrCreateDatabase(DBUtil.DB_NAME,  Context.MODE_PRIVATE, null);	
        // �إ߸�ƪ�
        db.execSQL(DBUtil.createTable);
        // ���d�߸��
        cur = db.rawQuery(DBUtil.queryTable, null);
        // �Y�L���
        if(cur.getCount() == 0){
        	// ��l�Ƹ�ƪ�;
        	DBUtil.initDB(db);
        }
        // �]�w�ثe�C�����d���ɶ���0
        for(int i = 1; i <= 10; i++){
        	levelTimes.put(i, 0);
        }
	}

	public void click_easy(View v) {
		Intent it = new Intent(this, EasyLevelActivity.class); //�إ� Intent �ó]�w�ؼ� Activity
		startActivity(it); // �Ұ� Intent �����ؼ�

	}
	
	public void click_medium(View v) {
		Intent it = new Intent(this, MediumLevelActivity.class); //�إ� Intent �ó]�w�ؼ� Activity
		startActivity(it); // �Ұ� Intent �����ؼ�

	}


}
