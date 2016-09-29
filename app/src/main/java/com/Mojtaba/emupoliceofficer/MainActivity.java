package com.Mojtaba.emupoliceofficer;




//import com.Mojtaba.emupoliceofficer.MainActivity;
//import com.Mojtaba.emupoliceofficer.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MainActivity extends Activity {
	
	private ImageButton login;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		login = (ImageButton) findViewById(R.id.imageButton1);
		
		
	login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(MainActivity.this, login.class);
			    startActivity(intent);
					
	}
		

});
}
	
}