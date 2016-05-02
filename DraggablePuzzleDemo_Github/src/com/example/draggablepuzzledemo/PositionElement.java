package com.example.draggablepuzzledemo;

/**
 * @description ���x�s��m������
 * 
 * @author Yuanyu
 *
 */
public class PositionElement {
	// �x�s�o��O�_���Q�������
	private boolean isOccupied = false;
	// �x�s�檺y�y��
	private int height = 0;
	// �x�s�檺x�y��
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
