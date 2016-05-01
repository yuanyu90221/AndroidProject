package com.example.draggablepuzzledemo;

public class TimeUtil {
	public static String getFormatStr(int tsec){
		String resultMin, resultSec, resultTime;
		int cmin = (tsec/ 60) % 60;
		int csec = (tsec % 60);
		resultMin = String.valueOf(cmin).length() > 1 ? String.valueOf(cmin) : "0" + String.valueOf(cmin);
		resultSec = String.valueOf(csec).length() > 1 ? String.valueOf(csec) : "0" + String.valueOf(csec);
		resultTime = resultMin + ":" + resultSec;
		return resultTime;
	}
}
