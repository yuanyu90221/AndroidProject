package com.example.draggablepuzzledemo;

/**
 * 用來保存index的物件
 * 
 * @author yuanyu
 *
 */
public class Cordinate {
	int xindex;
	int yindex;
	
	public Cordinate(int x, int y){
		this.xindex = x;
		this.yindex = y;
	}
	
	@Override
	public String toString() {
		return String.format("(x,y) = (%d,%d)\n", xindex,yindex);
	}
}
