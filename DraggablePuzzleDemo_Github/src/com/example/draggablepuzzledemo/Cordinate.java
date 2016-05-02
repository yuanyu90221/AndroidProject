package com.example.draggablepuzzledemo;

/**
 * 用來保存index的物件
 * 
 * @author yuanyu
 *
 */
public class Cordinate {
	// x軸座標
	int xindex;
	// y軸座標
	int yindex;
	
	public Cordinate(int x, int y){
		this.xindex = x;
		this.yindex = y;
	}
	
	@Override
	public String toString() {
		return String.format("(x,y) = (%d,%d)\n", xindex,yindex);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Cordinate){
			Cordinate co = (Cordinate) o;
			return (xindex == co.xindex) && (yindex == co.yindex);
		}
		return false;
	}
	
	
}
