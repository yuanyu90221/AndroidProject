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
	
	public static int size = 40;
	
	public PositionElement[][] position= new PositionElement[size][size];
	
    public Map<String, PositionElement> hasValue = new HashMap<String, PositionElement>();
    
    public PositionMatrix(){
    	for(int i = 0; i < size; i++){
    		for(int j = 0; j < size; j++){
    			position[i][j] = new PositionElement();
    		}
    	}
    	position[0][1].setOccupied(true);
    	position[0][2].setOccupied(true);
    	position[0][3].setOccupied(true);
    	position[0][4].setOccupied(true);
    	position[19][1].setOccupied(true);
    	position[19][2].setOccupied(true);
    	position[19][3].setOccupied(true);
    	position[19][4].setOccupied(true);
    }
	public PositionElement[][] getPosition() {
		return position;
	}

	public void setPosition(PositionElement[][] position) {
		this.position = position;
	}
	
	public void changeValue(int w, int h, boolean changeValue){
		
		String searchKey = String.valueOf(h) + String.valueOf(w);
		Log.e("changeValue", "changeValue " + searchKey);
		PositionElement pe = hasValue.get(searchKey);
		
		if (pe == null) {
			if(changeValue){
				hasValue.put(searchKey,position[w][h]);
			}
		} else {
			if (pe.isOccupied() && !changeValue) {
				hasValue.remove(searchKey);
			}
			else{
				
			}
		}
		position[h][w].setOccupied(changeValue);
		position[h][w].setHeight(h);
		position[h][w].setWidth(w);
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
