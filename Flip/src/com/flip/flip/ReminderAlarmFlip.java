package com.flip.flip;



import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.widget.Toast;


public class ReminderAlarmFlip  extends BroadcastReceiver{
private NotificationManager mNotificationManager;
private Notification notification;   

@SuppressLint("NewApi")
@Override
public void onReceive(Context context, Intent intent) {
// TODO Auto-generated method stub     
      mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
      CharSequence choice1 = intent.getStringExtra("Choice1");
      CharSequence choice2 = intent.getStringExtra("Choice2");
      int randchoice = (int) (Math.random() * 4);
      String choicemade = "";
      PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(), 0);

      if(randchoice%2 == 0)
    	choicemade = choice1.toString();
      else
    	choicemade = choice2.toString(); 

      


      	final MediaPlayer mp;
  	

  		mp = MediaPlayer.create(context,  R.raw.notificationcoin);
  		mp.start();
  	    mp.setOnCompletionListener(new OnCompletionListener(){
 
		@Override
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			 mp.stop();
		  	 mp.release();
		    
		}}); 	   
      Toast.makeText(context, String.valueOf(choice1), Toast.LENGTH_LONG).show();
      
      
      
      

      PendingIntent pIntent = PendingIntent.getActivity(context, 0, new Intent(), 0);

      // Build notification
      // Actions are just fake
      Notification noti = new Notification.Builder(context)
          .setContentTitle("A Choice came up")
          .setContentText(choicemade).setSmallIcon(R.drawable.flipcoinm)
          .setContentIntent(pIntent).build();

      // hide the notification after its selected
      noti.flags |= Notification.FLAG_AUTO_CANCEL;

      mNotificationManager.notify(Integer.parseInt(intent.getExtras().get("NotifyCount").toString()), noti);
      

}
}

