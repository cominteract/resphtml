package com.flip.flip;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Note extends Activity {
	TextView txttitle,txtdesc;
	Button choicereminder,sendfeed,viewfeed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		txtdesc = (TextView) findViewById(R.id.desctxt);
		txttitle = (TextView) findViewById(R.id.titletxt);
		choicereminder = (Button) findViewById(R.id.choicereminder);
		sendfeed = (Button) findViewById(R.id.sendfeed);
		viewfeed = (Button) findViewById(R.id.viewfeed);
		if(getIntent().getExtras()!=null)
		{
		Bundle getTitle = getIntent().getExtras();
		txttitle.setText(getTitle.getString("title"));
		txtdesc.setText(getTitle.getString("desc"));
	
		if(getTitle.getString("title").equals("Choice Reminder"))
		{
			sendfeed.setVisibility(View.GONE);
			viewfeed.setVisibility(View.GONE);
			choicereminder.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View arg0) {
				Intent i = new Intent(Note.this,FlipCoinAlarm.class);
				startActivity(i);
				
				}
			
			});
		}
		
		else if(getTitle.getString("title").equals("Feedback"))
		{
		
			choicereminder.setVisibility(View.GONE);
			
			sendfeed.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View arg0) {
					if(isNetworkConnected()==true)
					{
						Intent i = new Intent(Note.this,Postfeed.class);
						startActivity(i);
					}
					else
					{
						Toast.makeText(getApplicationContext(),"Must have internet connection to send a feedback to us", Toast.LENGTH_LONG).show();
					}
				
				}
			
			});
			viewfeed.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View arg0) {
				if(isNetworkConnected()==true)
				{
					Intent i = new Intent(Note.this,Feedsend.class);
					startActivity(i);
				}
				else
				{
					Toast.makeText(getApplicationContext(),"Must have internet connection to send a feedback to us", Toast.LENGTH_LONG).show();
				}
				
				}
			
			});
		}
		else
		{
			choicereminder.setVisibility(View.GONE);
			sendfeed.setVisibility(View.GONE);
			viewfeed.setVisibility(View.GONE);
		}
		
		
		
		}

	
	}
	private boolean isNetworkConnected() {
	    boolean isConnectedWifi = false;
	    boolean isConnectedMobile = false;

	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                isConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                isConnectedMobile = true;
	    }
	    return isConnectedWifi || isConnectedMobile;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note, menu);
		return true;
	}

}
