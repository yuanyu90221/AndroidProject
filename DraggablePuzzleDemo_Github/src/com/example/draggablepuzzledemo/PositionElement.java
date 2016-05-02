package com.example.draggablepuzzledemo;

/**
 * @description 來儲存位置的物件
 * 
 * @author Yuanyu
 *
 */
public class PositionElement {
	// 儲存這格是否有被方塊佔住
	private boolean isOccupied = false;
	// 儲存格的y座標
	private int height = 0;
	// 儲存格的x座標
	private int width = 0;

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "position("+String.valueOf(width)+","+ String.valueOf(height)+") = (" + String.valueOf(isOccupied)+")";
	}
	
	
}
