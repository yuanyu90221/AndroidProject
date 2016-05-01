package com.example.draggablepuzzledemo;

/**
 * @description 來儲存位置的物件
 * @author user
 *
 */
public class PositionElement {

	private boolean isOccupied = false;
	
	private int height = 0;
	
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
