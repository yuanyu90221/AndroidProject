package com.example.draggablepuzzledemo;

import java.util.HashMap;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @description ��SQLLite�s������������
 * 
 * @author Jane
 */
public class DBUtil {
	// �]�w��DB Name�� TB_NAME
	static final String DB_NAME="TimeDB";
	static final String TB_NAME="timeRec";
	static final String LEVEL = "level";
	static final String TSEC = "tsec";
	private static SQLiteDatabase db;
	Cursor cursor;
	
	// �إ߸�ƪ�
    static final String createTable="CREATE TABLE IF NOT EXISTS " + TB_NAME +
    		           "(level INTEGER PRIMARY KEY, " + //�������
    		           "tsec INTEGER)";
    // �d�߸�ƪ�
    static final String queryTable = "select * from " + TB_NAME;
    
    // �d�߯S�w�d����
    static final String queryRangeData = "select * from " + TB_NAME + " where " + LEVEL + " in (";
    
    /**
     * ��l�Ƹ�ƪ�
     */
    public static void initDB(SQLiteDatabase db){
    	for(int i = 1; i <= 10; i++){
    		ContentValues cv = new ContentValues(2);
    		cv.put(LEVEL, i);
    		cv.put(TSEC, 0);
    		db.insert(TB_NAME, null, cv);
    	}
    }
    
    /**
     * �d�߯S�w�d�����d���ɶ�
     * @param db
     * @param levels
     * @return
     */
    public static HashMap<Integer, Integer> queryRangeDate(SQLiteDatabase db, int[] levels){
    	HashMap<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
    	
    	StringBuilder sb = new StringBuilder();
    		sb.append(queryRangeData);
    		int count = 0;
    		for(int level : levels){
    			sb.append(level);
    			if(count != levels.length -1){
    				sb.append(",");
    			}
    			else {
    				sb.append(") order by level asc");
    			}
    			count++;
    		}
    	Cursor cur = db.rawQuery(sb.toString(), null);
    	int queryCount = cur.getCount();
    	if(queryCount == 0 ){
    		return null;
    	}
    	else{
    		for(int i =0; i < queryCount; i++){
    		   cur.moveToPosition(i);
    		   resultMap.put(cur.getInt(cur.getColumnIndex(LEVEL)), cur.getInt(cur.getColumnIndex(TSEC)));
    		}
    	}
    	return resultMap;
    }
    
    /**
     * ��s�浧���
     * @param db
     * @param tsec
     * @param level
     */
    public static void updateData(SQLiteDatabase db, int tsec, int level){
    	ContentValues cv = new ContentValues(1);
    	cv.put(TSEC, tsec);
    	db.update(TB_NAME, cv, "level="+level, null);
    }
}
