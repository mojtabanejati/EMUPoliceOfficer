package com.Mojtaba.emupoliceofficer;


import java.util.ArrayList;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

	public class requestforWS extends Activity {
		private final String NAMESPACE = "http://tempuri.org/";
		private final String URL = "http://mojtabanejati-001-site1.smarterasp.net/WebService.asmx";
		private final String SOAP_ACTION = "http://tempuri.org/RequestForReading";
		private final String METHOD_NAME = "RequestForReading";
		private ListView List;
		ArrayList<String> list = new ArrayList<String>();
		public Cursor cursor, cursor1;
		ArrayAdapter<String> adapter;
		ArrayAdapter<String> ListAdapter;
		SQLiteDatabase database;
		private Button Show;
		String[] columns = { "name", "phone", "organization", "location",
				"summary", "Theft", "Assault", "Vandalism", "Drug", "Murder",
				"Other" };
		String[] responseArray;
		String[] columns2 = { "email", "comment" };

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.reguest);
			//final String query2 = "select * from " + sqllite.Tablename2;
			//final String query = "select * from " + sqllite.Tablename1;
			List=(ListView) findViewById(R.id.listView1);
			ListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
			Show = (Button) findViewById(R.id.btnshow);
			//SQL = new sqllite(getApplicationContext());
			//database = SQL.getReadableDatabase();

			Show.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

				/*	cursor = database.rawQuery(query, null);
					if (cursor.moveToFirst()) {
						do {
							
							for (int i = 0; i < columns.length; i++) {
								results.add(cursor.getString(cursor
										.getColumnIndex(columns[i])));
							}
							// results.add(cursor.getString(cursor.getColumnIndex("name")));
							// results.add(cursor.getString(cursor.getColumnIndex("phone")));
							// results.add(cursor.getString(cursor.getColumnIndex("organization")));
							// results.add(cursor.getString(cursor.getColumnIndex("location")));
							// results.add(cursor.getString(cursor.getColumnIndex("summary")));
							// results.add(cursor.getString(cursor.getColumnIndex("Theft")));
							// results.add(cursor.getString(cursor.getColumnIndex("Assault")));
							// results.add(cursor.getString(cursor.getColumnIndex("Vandalism")));
							// results.add(cursor.getString(cursor.getColumnIndex("Drug")));
							// results.add(cursor.getString(cursor.getColumnIndex("Murder")));
							// results.add(cursor.getString(cursor.getColumnIndex("Other")));
						} while (cursor.moveToNext());
					}
					
					cursor1 = database.rawQuery(query2, null);
					if (cursor1.moveToFirst()) {
						do {
							for (int i = 0; i < columns2.length; i++) {
								results1.add(cursor1.getString(cursor1
										.getColumnIndex(columns2[i])));
							}
						} while (cursor1.moveToNext());
					}
					cursor1.close();
					cursor.close();
					// Toast.makeText(getApplicationContext(), results.toString(),
					// Toast.LENGTH_LONG).show();
					System.out.println(results);
					Log.i("results : ", results.toString());
					Log.i("results 1 : ", results1.toString());
					
					
					 adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, results);
					 List.setAdapter(adapter);
				*/
						ConnectingWS connectingWS = new ConnectingWS();
						connectingWS.execute();
					//TextView txtView = (TextView) findViewById(R.id.textView3);
					//txtView.setText(results.toString());
				   	 //Toast.makeText(getApplicationContext(), results.toString(),
						// Toast.LENGTH_LONG).show();

				}
				
				 
			   

			}

			);
		
		
			
			
				
				////if using editText////
				
			  
			  
			  
			 
		//	}
			
		}
		
		private void RequestForReading(){
			SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
			
			/*PropertyInfo f_usernameInfo = new PropertyInfo();
			f_usernameInfo.setName("f_username");
			f_usernameInfo.setValue(f_username);
			
			PropertyInfo f_passwordInfo = new PropertyInfo();
			f_passwordInfo.setName("f_password");
			f_passwordInfo.setValue(f_password);
			
			request.addProperty(f_usernameInfo);
			request.addProperty(f_passwordInfo);*/
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE httpTransportSE = new HttpTransportSE(URL,30000);
			try {
				httpTransportSE.call(SOAP_ACTION, envelope);
				SoapObject response = (SoapObject) envelope.getResponse();
				int count =  response.getPropertyCount();
				responseArray = new String [count];
				for(int i = 0 ; i < response.getPropertyCount();i++)
				{
					if(response.getProperty(i).toString().equals("anyType{}"))
						responseArray[i] = "No Info";
					else
						responseArray[i] = response.getProperty(i).toString();
				}
			}
			catch (Exception e) {
				Log.e("error", e.getMessage());
				}
			}
		
		private class ConnectingWS extends AsyncTask<String, String, String>{

			private final ProgressDialog dialog = new ProgressDialog(requestforWS.this);
			@Override
			protected void onPreExecute() {
				this.dialog.setMessage("Please Waiting...");
				this.dialog.show();
				super.onPreExecute();
			}
			@Override
			protected String doInBackground(String... params) {
				
				RequestForReading();
				return null;
			}
			@Override
			protected void onPostExecute(String result) {
				if (this.dialog.isShowing()) {
					this.dialog.dismiss();
				}
				String oneRow="";
				for(int i = 0; i < responseArray.length; ++i)
				{

					oneRow += responseArray[i].trim() + ',';
				}
				list.add(oneRow);
				List.setAdapter(ListAdapter);
				super.onPostExecute(result);
			}
		}
		@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			finish();
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			super.onBackPressed();
		}


}
