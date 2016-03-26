package com.example.quizdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{
     
	ListView quizList;
	String[] quizArray = {"fools Day"," fathers Day","Mon's Day"};
	String[] quizAns = {"4/1", "6/6", "5/12"};
	Toast tos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView quizList = (ListView)findViewById(R.id.quizList);
		ArrayAdapter<String> ard = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quizArray);
		quizList.setAdapter(ard);
		quizList.setOnItemClickListener(this);
		tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	   String result = quizAns[position];
	   tos.setText(result);
	   tos.show();
	}

	
}
