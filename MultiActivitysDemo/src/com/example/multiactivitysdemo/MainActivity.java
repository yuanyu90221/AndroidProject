package com.example.multiactivitysdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Toast tos, tosCheckNet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 取得連線管理員
		// 但須在AndroidManifest.xml裡面加入 讀取網路的權限
		// <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 讀取其他Activity發過來的Context
		Intent it = this.getIntent();
		if(it != null){
			String context = it.getStringExtra("context");
			tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);
			if(!(context == null || context.equals(""))){
				tos.setText(context);
				tos.show();
			}
		}
		tosCheckNet = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		NetworkInfo info = CheckNetworkStatusUtil.getNetworkInfo(cm);
		String result;
		if( info != null){
			result = String.format("TypeName: %s\nState: %s\nisAvailable: %s\n", 
					                      info.getTypeName(),
					                      info.getState(),
					                      String.valueOf(info.isAvailable())
					                      );
		}
		else{
			result = "網路未連接";
		}
		tosCheckNet.setText(result);
		tosCheckNet.show();
	}

	/**
	 * 切換頁面
	 * 
	 * @param v
	 */
	public void navFrom1to2(View v){
		// 準備Intent
		Intent it = new Intent(this, SecondActivity.class);
		it.putExtra("context", "切換頁面從1到2");
		CustomObject co = new CustomObject("LiangYuanyu", JobTypes.ENG, 28);
		Bundle bundle = new Bundle();
		bundle.putSerializable("customObj", co);
		it.putExtras(bundle);
		startActivity(it);
	}
	
	/**
	 * 切換頁面
	 * 
	 * @param v
	 */
	public void navFrom1to3(View v){
		// 準備Intent
		Intent it = new Intent(this, ThirdActivity.class);
		it.putExtra("context", "切換頁面從1到3");
		startActivity(it);
	}
}
