package com.example.draggablepuzzledemo;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

/**
 * @description 用來儲存位置資訊的舉證
 * 
 * @author Yuanyu
 *
 */
public class PositionMatrix {
	
	public static int size = 20;
	
	public PositionElement[][] position= new PositionElement[size][size];
	
    public Map<String, PositionElement> hasValue = new HashMap<String, PositionElement>();
    
    public PositionMatrix(){
    	for(int i = 0; i < size; i++){
    		for(int j = 0; j < size; j++){
    			position[i][j] = new PositionElement();
    		}
    	}
    	
    }
	public PositionElement[][] getPosition() {
		return position;
	}

	public void setPosition(PositionElement[][] position) {
		this.position = position;
	}
	
	/**
	 * 用來改變矩陣 true false的函式
	 * @param w
	 * @param h
	 * @param changeValue
	 */
	public void changeValue(int w, int h, boolean changeValue){
		// 先找取出searchKey 
		String searchKey = String.valueOf(w) + String.valueOf(h);
		Log.e("changeValue", "changeValue " + searchKey);
		PositionElement pe = hasValue.get(searchKey);
		// find no
		if (pe == null) {
			if(changeValue){				
				hasValue.put(searchKey,position[w][h]);
			}
		} else {
			if (pe.isOccupied() && !changeValue) {
				hasValue.remove(searchKey);
			}
		}
		// Change Value
		position[w][h].setOccupied(changeValue);
		position[w][h].setHeight(h);
		position[w][h].setWidth(w);
	}

	@Override
	public String toString() {
		String result = "";
		int mapSize = hasValue.size();
		String []keys = hasValue.keySet().toArray(new String[mapSize]);
		result +="key size = " + String.valueOf(mapSize);
		for(String key: keys){
			result += hasValue.get(key).toString();
		}
		return result;
	}
	
	
	
}
