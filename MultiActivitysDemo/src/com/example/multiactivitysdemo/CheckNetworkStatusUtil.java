package com.example.multiactivitysdemo;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 用來檢查  Android 手機的網路連線狀態
 * @author Yuanyu
 *
 */
public class CheckNetworkStatusUtil {
	public static boolean isNetworkConnection(ConnectivityManager cm){
		boolean isConnected = false;
	    NetworkInfo info = cm.getActiveNetworkInfo();
	    if( info != null){
	    	return info.isConnected();
	    }
		return isConnected;
	}
	
	public static NetworkInfo getNetworkInfo(ConnectivityManager cm){
		NetworkInfo networkInfo = null;
		if(isNetworkConnection(cm)){
			networkInfo = cm.getActiveNetworkInfo();
		}
		return networkInfo;
	}
}
