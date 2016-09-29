package com.Mojtaba.emupoliceofficer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity {
	
	String u, p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		Button Login = (Button) findViewById(R.id.login);

		Login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent intent = new Intent(login.this,
						requestforWS.class);
				EditText username = (EditText) findViewById(R.id.editText1);
				EditText pass = (EditText) findViewById(R.id.editText2);
				u = username.getText().toString();
				p = pass.getText().toString();

				if (username.getText().length() == 0
						|| pass.getText().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Please fill in all the blanks", Toast.LENGTH_LONG)
							.show();
				} else if (u.equals("root") && p.equals("mojtaba")) {
					finish();
					startActivity(intent);
				} else {
					
				}
			}
		});

	}

	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		Intent intent=new Intent(this, MainActivity.class);
		startActivity(intent);
		super.onBackPressed();
	}
}


