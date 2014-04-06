package com.flip.flip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




 
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
 
public class Feedsend extends Activity {
 private String jsonResult;
 private String url = "http://cominteract.com/sendpost/index.php";
 private ListView listView;
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_feedsend);
  listView = (ListView) findViewById(R.id.listView1);
  accessWebService();
 }
 

 
 // Async Task to access the web
 private class JsonReadTask extends AsyncTask<String, Void, String> {
  @Override
  protected String doInBackground(String... params) {
   HttpClient httpclient = new DefaultHttpClient();
   HttpPost httppost = new HttpPost(params[0]);
   try {
    HttpResponse response = httpclient.execute(httppost);
    jsonResult = inputStreamToString(
      response.getEntity().getContent()).toString();
   }
 
   catch (ClientProtocolException e) {
    e.printStackTrace();
   } catch (IOException e) {
    e.printStackTrace();
   }
   return null;
  }
 
  private StringBuilder inputStreamToString(InputStream is) {
   String rLine = "";
   StringBuilder answer = new StringBuilder();
   BufferedReader rd = new BufferedReader(new InputStreamReader(is));
 
   try {
    while ((rLine = rd.readLine()) != null) {
     answer.append(rLine);
    }
   }
 
   catch (IOException e) {
    // e.printStackTrace();
    Toast.makeText(getApplicationContext(),
      "Error..." + e.toString(), Toast.LENGTH_LONG).show();
   }
   return answer;
  }
 
  @Override
  protected void onPostExecute(String result) {
   ListAdder();
  }
 }// end async task
 
 public void accessWebService() {
  JsonReadTask task = new JsonReadTask();
  // passes values for the urls string array
  task.execute(new String[] { url });
 }
 
 // build hash set for list view
 public void ListAdder() {
  List<Map<String, String>> FeedbackList = new ArrayList<Map<String, String>>();
  FeedbackAdapter adapter = null;
  try {
   JSONObject jsonResponse = new JSONObject(jsonResult);
   JSONArray jsonMainNode = jsonResponse.optJSONArray("feedback_coinflip");
   FeedbackClass[] fav = new FeedbackClass[(jsonMainNode.length())];
   for (int i = 0; i < jsonMainNode.length(); i++) {
    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
    String name = jsonChildNode.optString("Uname");
    String feed = jsonChildNode.optString("Feedback");
    String title = jsonChildNode.optString("Title");
    String email = jsonChildNode.optString("Email");
    String dateposted = jsonChildNode.optString("Dateposted");
    String outPut = name + "-" + email;
    fav[i] = new FeedbackClass(name, dateposted, title, feed);
    FeedbackList.add(createFeedback("Feedbacks", outPut));
   }
  adapter = new FeedbackAdapter(this, R.layout.listview_feedback, fav);

  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  DateFormat yearFormat = new SimpleDateFormat("yyyy");
  DateFormat monthFormat = new SimpleDateFormat("MMMM");
  DateFormat dayFormat = new SimpleDateFormat("dd");
  
  //get current date time with Date()
  Date date = new Date();

  Toast.makeText(getApplicationContext(), String.valueOf(monthFormat.format(date) + " - " + dayFormat.format(date) + " - " + yearFormat.format(date)), Toast.LENGTH_LONG).show();
  } catch (JSONException e) {
   Toast.makeText(getApplicationContext(), "Error" + e.toString(),
     Toast.LENGTH_SHORT).show();
  }
  
  listView.setAdapter(adapter);
 }
 
 private HashMap<String, String> createFeedback(String name, String email) {
  HashMap<String, String> FeedbackNameEmail = new HashMap<String, String>();
  FeedbackNameEmail.put(name, email);
  return FeedbackNameEmail;
 }
}