package com.example.draggablepuzzledemo;

import java.util.ArrayList;
import java.util.List;
/**
 * 新增用來儲存占用空格的物件
 * 
 * @author user
 *
 */
public class SelfDefImgView{
	// 儲存目前圖形所占左上角位置
	public Cordinate position = new Cordinate(0,0);

	public Cordinate getPosition() {
		return position;
	}

	public void setPosition(Cordinate position) {
		this.position = position;
	}
	// 儲存相對左上角有值得offset
	public List<Cordinate> occupiedSpaceList  = new ArrayList<Cordinate>();
	
	
	public List<Cordinate> getOccupiedSpaceList() {
		return occupiedSpaceList;
	}

	public void setOccupiedSpaceList(List<Cordinate> occupiedSpaceList) {
		this.occupiedSpaceList = occupiedSpaceList;
	}
	
	public void addCord(Cordinate cord){
		occupiedSpaceList.add(cord);
	}
	
	public void removeCord(Cordinate cord){
		int index = occupiedSpaceList.indexOf(cord);
		occupiedSpaceList.remove(index);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("position:"+position.toString());
		for(Cordinate cord : occupiedSpaceList){
			sb.append(cord.toString());
		}
		return sb.toString();
	}	
}
