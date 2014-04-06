package com.flip.flip;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Postfeed extends Activity {
	ProgressDialog dialog;
	EditText edittitle,editdesc,editname,editemail;
	Button send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_postfeed);
		edittitle = (EditText) findViewById(R.id.apptitle);
		editdesc = (EditText) findViewById(R.id.appfeed);
		editname = (EditText) findViewById(R.id.appname);
		editemail = (EditText) findViewById(R.id.appemail);
		send = (Button) findViewById(R.id.sendfeed);
		
		send.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(edittitle.getText().length() < 6 && editdesc.getText().length() < 6 && editemail.getText().length()  < 6 && editname.getText().length() < 6)
				{
					Toast.makeText(getApplicationContext(), "Complete details and input length must be greater than 5", Toast.LENGTH_LONG).show();
				}
				else
				{
					String message = "";
					if((editemail.getText().toString().contains("@") && editemail.getText().toString().contains("com") && isValidEmail(editemail.getText().toString()) == true))
					{
							message += "";
						  Toast.makeText(getApplicationContext(), "Submitted feedback", Toast.LENGTH_LONG).show();
						  Submitdata task = new Submitdata();
					      task.execute();
					
						
					}
					else
						message += "Email is not valid ";
					

				}
			}
			
		});
	}

	public final static boolean isValidEmail(CharSequence target) {
	    if (target == null) {
	        return false;
	    } else {
	        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	    }
	}
	

	 private class Submitdata extends AsyncTask<String, Void, String>
     {

         @Override
         protected String doInBackground(String... arg0) {


             HttpClient httpclient = new DefaultHttpClient();
             HttpPost httppost = new HttpPost("http://cominteract.com/sendpost/sendfeed/index.php");
             try {
                 // Add your data
                 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                 nameValuePairs.add(new BasicNameValuePair("feed", editdesc.getText().toString()));
                 nameValuePairs.add(new BasicNameValuePair("title", edittitle.getText().toString()));
                 nameValuePairs.add(new BasicNameValuePair("name", editname.getText().toString()));
                 nameValuePairs.add(new BasicNameValuePair("email", editemail.getText().toString()));


                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                 // Execute HTTP Post Request
                //Toast.makeText(this, resId, duration)
                 HttpResponse response = httpclient.execute(httppost);
//               Intent intent = new Intent(Register.this,Emergency.class);
//               Register.this.startActivity(intent);        



             } catch (ClientProtocolException e) {
                 // TODO Auto-generated catch block
             } catch (IOException e) {
                 // TODO Auto-generated catch block
             }

             // TODO Auto-generated method stub
             return null;
         }

         protected void onPreExecute() {
             dialog = ProgressDialog.show(Postfeed.this, null, "Loading", false);



         }
         @Override
         protected void onPostExecute(String result) {
          dialog.dismiss();
         }

     }

 //define function for doing in background like submit data

  

}
