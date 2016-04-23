package com.example.draggablepuzzledemo;

import java.util.ArrayList;
import java.util.List;
/**
 * �s�W�Ψ��x�s�e�ΪŮ檺����
 * 
 * @author user
 *
 */
public class SelfDefImgView{
	// �x�s�ثe�ϧΩҥe���W����m
	public Cordinate position = new Cordinate(0,0);

	public Cordinate getPosition() {
		return position;
	}

	public void setPosition(Cordinate position) {
		this.position = position;
	}
	// �x�s�۹索�W�����ȱooffset
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
