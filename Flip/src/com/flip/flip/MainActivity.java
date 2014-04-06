package com.flip.flip;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;






import android.R.color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.ViewGroup;


import android.view.View;

import android.view.View.OnClickListener;

import android.view.animation.Animation;

import android.view.animation.Animation.AnimationListener;

import android.view.animation.AnimationUtils;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import android.graphics.Color;
import android.graphics.Point;

@SuppressLint({ "HandlerLeak", "NewApi" })
public class MainActivity extends Activity implements AnimationListener {
	
	
	private boolean roundflag;
	private Timer new_timer;
	private int iteration,roundstart;
	private MyHandler handler;
	
	TextView txtchoice1,txtchoice2,toincoss,txtover;
	Button hitme,addchoice,tryagain, addrounds, btnSubmit;
	Spinner spinner1;
	private int maxite = 0; 
	String inittxt1,inittxt2,roundnum,choice1txt,choice2txt,winmessage;
	static final int CUSTOM_DIALOG_ID = 0;
	
	int x = 0;
	int cntithasbegun = 0;
	
	
	int scorechoice1,scorechoice2;
	
	static final int CUSTOM_DIALOG_ID2 = 1;
	
	static final int CUSTOM_DIALOG_ID3 = 2;
	
	static final int CUSTOM_DIALOG_ID4 = 3;
       private Animation animation1;
       
       MediaPlayer mp;
       
       int width,height;

       private boolean isBackOfCoin = true;
       View setting,mmenu;
       TextView dialogText;
       EditText choice1edit,choice2edit;
       Button dialogAddchoice, dialogDismiss;

   
	@SuppressWarnings("deprecation")
	@Override

       protected void onCreate(Bundle savedInstanceState) {

             super.onCreate(savedInstanceState);
             

             
             setContentView(R.layout.activity_main);
             animation1 = AnimationUtils.loadAnimation(this, R.anim.to_middle);

             animation1.setAnimationListener(this);
             
         	if(getIntent().getExtras()!=null)
    		{
    			Bundle getCoin = getIntent().getExtras();
    			int coinfront = getCoin.getInt("coinfront");    
    			((ImageView)findViewById(R.id.imageView1)).setImageResource(coinfront);
    		}
             setting = (View) findViewById(R.id.settingmenu);
             mmenu = (View) findViewById(R.id.clickmenu);
             winmessage = "Time to decide";
             roundflag = false;
             txtchoice1 =  (TextView) findViewById(R.id.txtchoice1);
             txtchoice2 =  (TextView) findViewById(R.id.txtchoice2);
             toincoss =  (TextView) findViewById(R.id.textView1);
             addchoice =  (Button) findViewById(R.id.addchoice);
             hitme =  (Button) findViewById(R.id.flip);
             addrounds =  (Button) findViewById(R.id.addrounds);
             Display display = getWindowManager().getDefaultDisplay();
             
             
             inittxt1 = txtchoice1.getText().toString();
             inittxt2 = txtchoice2.getText().toString();
             int currentapiVersion = android.os.Build.VERSION.SDK_INT;
             if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
            	 Point size = new Point();
            	 display.getSize(size);
            	 width = size.x;
            	 height = size.y;
             } else{
                 width =  display.getWidth();
                 height = display.getHeight();
             }
    
             
             if(height > 1400)
             {
            	 hitme.setTextSize((float) (45));
            	 txtchoice1.setTextSize((float) (45));
            	 txtchoice2.setTextSize((float) (45));
            	 toincoss.setTextSize((float) (45));
            	 addchoice.setTextSize((float) (45));
             }
             else if(height > 1200 && height <= 1400)
             {
                 hitme.setTextSize((float) (37));
                 txtchoice1.setTextSize((float) (37));
                 txtchoice2.setTextSize((float) (37));
                 toincoss.setTextSize((float) (37));
                 addchoice.setTextSize((float) (37));
            	 
             }
             else if(height >= 800 && height <= 1200)
             {
                 hitme.setTextSize((float) (28));
                 txtchoice1.setTextSize((float) (28));
                 txtchoice2.setTextSize((float) (28));
                 toincoss.setTextSize((float) (28));
                 addchoice.setTextSize((float) (28));
            	 
             }
             
             else if(height > 400 && height < 800)
             {
                 hitme.setTextSize((float) (25));
                 txtchoice1.setTextSize((float) (25));
                 txtchoice2.setTextSize((float) (25));
                 toincoss.setTextSize((float) (25));
                 addchoice.setTextSize((float) (25));
             }
             else
             {
                 hitme.setTextSize((float) (22));
                 txtchoice1.setTextSize((float) (22));
                 txtchoice2.setTextSize((float) (22));
                 toincoss.setTextSize((float) (22));
                 addchoice.setTextSize((float) (22));
             }
            
             mmenu.setOnClickListener(clickMenu);
             setting.setOnClickListener(setMenu);
             
             
             hitme.setOnClickListener(new OnClickListener() {     
             public void onClick(View v) {  	   
            	 
            	 
            	 if(String.valueOf(txtchoice1.getText())!= inittxt1 || String.valueOf(txtchoice2.getText())!= inittxt2)
            	 {
            		 v.setEnabled(false);        
                 	maxite = (int) (8 + Math.random() * 4);
                 	mp = MediaPlayer.create(getApplicationContext(),  R.raw.spincoin);
                 	mp.start();
                 	iteration=0;
                 	handler= new MyHandler();
                 	new_timer= new Timer();
                 	new_timer.schedule(new TickClass(), 500, 620);
                 	findViewById(R.id.flip).setEnabled(true);
            	 }
            	 else
            	 {
            		 Toast.makeText(getApplicationContext(), "Must add choices first", Toast.LENGTH_LONG).show();
            	 }
           
             	}
             });

             
             findViewById(R.id.addchoice).setOnClickListener(new OnClickListener() {
                 public void onClick(View v) {
                     
                
                	 showDialog(CUSTOM_DIALOG_ID);
                	 
                	 
                 }
             });
             
             addrounds.setOnClickListener(new OnClickListener() {
                 public void onClick(View v) {
                     
                	 showDialog(CUSTOM_DIALOG_ID3);
                	 
                 }
             });
       }
	
	


       
       @Override
       protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        Dialog dialog = null;;
           switch(id) {
           case CUSTOM_DIALOG_ID:
            dialog = new Dialog(MainActivity.this,R.style.PauseDialog);
            
            dialog.setContentView(R.layout.customdialog);
            dialog.setTitle("Adding choices...");
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_box_bg);
            choice1edit = (EditText)dialog.findViewById(R.id.choice1edit);
            choice2edit = (EditText)dialog.findViewById(R.id.choice2edit);
            
            dialogText = (TextView)dialog.findViewById(R.id.dialogtextview);
            
            if(height > 1400)
            {
            	dialogText.setTextSize((float) (45));
            }
            else if(height > 1200 && height < 1400)
            {
            	dialogText.setTextSize((float) (37));

           	 
            }
            else if(height >= 800 && height <= 1200)
            {
            	dialogText.setTextSize((float) (28));
            }
            else if(height > 400 && height < 800)
            {
            	dialogText.setTextSize((float) (25));           	 
            }
            else
            {
            	dialogText.setTextSize((float) (22));
            }
            

            dialogAddchoice = (Button)dialog.findViewById(R.id.dialogupdate);
            dialogDismiss = (Button)dialog.findViewById(R.id.dialogdismiss);
            

            
            dialogAddchoice.setOnClickListener(clickAddchoice);
            dialogDismiss.setOnClickListener(clickDismiss);
            
               break;
               
           case CUSTOM_DIALOG_ID2: 
        	   dialog = new Dialog(MainActivity.this,R.style.PauseDialog);
        	   dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_box_bg);
               dialog.setContentView(R.layout.finished);
               dialog.setTitle("Try again?");
               
               
               
             
               txtover = (TextView)dialog.findViewById(R.id.txtover);
               txtover.setText(String.valueOf(winmessage));
               
               tryagain = (Button)dialog.findViewById(R.id.tryagain);
               tryagain.setOnClickListener(tryAgainClick);
               
        	   break;
           case CUSTOM_DIALOG_ID3:
        	   
        	   dialog = new Dialog(MainActivity.this,R.style.PauseDialog);
           	   dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_box_bg);
               dialog.setContentView(R.layout.addround);
               dialog.setTitle("Adding Rounds..");
               
       		spinner1 = (Spinner) dialog.findViewById(R.id.spinner1);
       		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
   			btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
   			btnSubmit.setOnClickListener(addRoundClick);
       	
    		
               
        	   break;

           }
           return dialog;
       }  

       
/* start onclicks */      

       
  	 private Button.OnClickListener clickMenu
     = new Button.OnClickListener(){
  	@Override
  	 public void onClick(View arg0) {
  		Intent intent = new Intent(getApplicationContext(), FlipMenuList.class);
		startActivity(intent);
	}
 };
       
	 private Button.OnClickListener setMenu
     = new Button.OnClickListener(){
  	@Override
  	 public void onClick(View arg0) {
  		Intent intent = new Intent(getApplicationContext(), Settings.class);
		startActivity(intent);
	}
 };      
 
       private Button.OnClickListener addRoundClick
       = new Button.OnClickListener(){
     
     @SuppressWarnings("deprecation")
	@Override
     public void onClick(View arg0) {
      // TODO Auto-generated method stub
    	 
    	 roundflag = true;
    	roundnum = String.valueOf(spinner1.getSelectedItem());
      dismissDialog(CUSTOM_DIALOG_ID3);
     }
        
};
     
  	

        
   
  private Button.OnClickListener clickAddchoice
         = new Button.OnClickListener(){
       
       @Override
       public void onClick(View arg0) {
        // TODO Auto-generated method stub
        
        if(choice1edit.getText()!=null && choice2edit.getText()!=null && String.valueOf(choice1edit.getText())!="" && String.valueOf(choice1edit.getText())!="" && choice1edit.getText().length() > 5 && choice2edit.getText().length() > 5)
        {
      	  txtchoice1.setText(choice1edit.getText());
      	  txtchoice2.setText(choice2edit.getText());
      	  
      	  choice1txt = String.valueOf(choice1edit.getText());
      	  choice2txt = String.valueOf(choice2edit.getText());
      	  
     	 choice1edit.setText("");
    	 choice2edit.setText("");
      	  dialogText.setText("Choices Added");
        }
        else
        {
      	  dialogText.setText("Must add choices description");
        }
       }
          
         };
         
  private Button.OnClickListener clickDismiss
         = new Button.OnClickListener(){
       
       @SuppressWarnings("deprecation")
  	@Override
       public void onClick(View arg0) {
        // TODO Auto-generated method stub
    	   dialogText.setText("Adding Choices");
        dismissDialog(CUSTOM_DIALOG_ID);
       }
          
  };
         
         
  private Button.OnClickListener tryAgainClick
         = new Button.OnClickListener(){
       
   @SuppressWarnings("deprecation")
  	@Override
       public void onClick(View arg0) {
      	 
      	txtchoice1.setText(inittxt1);
      	txtchoice2.setText(inittxt2);
   
        // TODO Auto-generated method stub
        removeDialog(CUSTOM_DIALOG_ID2);
       }
          
  };
  /* end onclick */
       
       
        @Override
        public void onAnimationEnd(Animation animation) {
            if (isBackOfCoin) {
        		if(getIntent().getExtras()!=null)
        		{
        			Bundle getCoin = getIntent().getExtras();
        			int coinfront = getCoin.getInt("coinfront");    
        			((ImageView)findViewById(R.id.imageView1)).setImageResource(coinfront);
        		}
        		else
        		{
        				((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.smileyfront);
        		}

               } else {
            	if(getIntent().getExtras()!=null)
           		{
            		Bundle getCoin = getIntent().getExtras();
            		int coinback = getCoin.getInt("coinback");
            		((ImageView)findViewById(R.id.imageView1)).setImageResource(coinback);
        		}
            	else
            	{
            			((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.smileyback);
            	}

               }

 
            	isBackOfCoin = !isBackOfCoin;
   
        }

        @Override

        public void onAnimationRepeat(Animation animation) {

               // TODO Auto-generated method stub

        }

        @Override

        public void onAnimationStart(Animation animation) {

        // TODO Auto-generated method stub

        }
        
        private class TickClass extends TimerTask
        {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                handler.sendEmptyMessage(iteration);
                iteration++;
            }
        }
        private class MyHandler extends Handler
        {
            @SuppressWarnings("deprecation")
			@Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);

            if(roundflag == false)
               {
                if(iteration < maxite)
                  	
                {
                	((ImageView)findViewById(R.id.imageView1)).clearAnimation();

                	((ImageView)findViewById(R.id.imageView1)).setAnimation(animation1);

                	((ImageView)findViewById(R.id.imageView1)).startAnimation(animation1);
                	
                	
                	
                  
                }
                else if(iteration>=maxite && iteration <= maxite+3)
                {
                	
                	  mp.stop();
                	  mp = MediaPlayer.create(getApplicationContext(),  R.raw.lastcoinsound);
                	  mp.start();
                	  try {
						mp.setDataSource(getAssets().openFd("raw/lastcoinsound.mp3").getFileDescriptor());
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	  try {
						mp.prepare();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                
                	  
                	  if(maxite%2==0)
                	  {
                		  txtchoice1.setText("choice 1 wins");
                		  txtchoice2.setText("choice 2 loses");
                		  
                		  winmessage = "FlipCoin decides " + choice1txt;
                		
                	  }
                	  else
                	  {
                		  txtchoice1.setText("choice 1 loses");
                		  txtchoice2.setText("choice 2 wins");
                		  
                		  winmessage = "FlipCoin decides " + choice2txt;
    
                	  }
                	  cntithasbegun++;
                	
                }
                else
                {
                	  choice2txt = "";
                  	  choice1txt = "";
                	  new_timer.cancel();
                	  showDialog(CUSTOM_DIALOG_ID2);
                }
               }
            /*end if roundflag false */
            else
            {
            	if(roundstart < Integer.valueOf(roundnum))
            	{
            	 if(iteration < maxite)
                   	
                 {
                 	((ImageView)findViewById(R.id.imageView1)).clearAnimation();

                 	((ImageView)findViewById(R.id.imageView1)).setAnimation(animation1);

                 	((ImageView)findViewById(R.id.imageView1)).startAnimation(animation1);
                 	
                   
                 }
                 else if(iteration>=maxite && iteration <= maxite+3)
                 {
                 	  mp.stop();
                 	  mp = MediaPlayer.create(getApplicationContext(),  R.raw.lastcoinsound);
                 	  mp.start();
                 	  try {
 						mp.setDataSource(getAssets().openFd("raw/lastcoinsound.mp3").getFileDescriptor());
 					} catch (IllegalArgumentException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (IllegalStateException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
                 	  try {
 						mp.prepare();
 					} catch (IllegalStateException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
                 
           
               
                 	
                 }
                 else
                 {
                 	  
                 	  
                	  mp.stop();
                 	  new_timer.cancel();    
                 	  if(iteration > maxite)
                 	  {
                      	  
                     	  if(maxite%2==0)
                     	  {
                     		  scorechoice1 += 1;
                     		  txtchoice1.setText(choice1txt + " : " + String.valueOf(scorechoice1));
                     		  txtchoice2.setText(choice2txt + " : " + String.valueOf(scorechoice2));
                     		  
                     	  }
                     	  else
                     	  {
                     		  scorechoice2 += 1;
                     		  txtchoice1.setText(choice1txt + " : " + String.valueOf(scorechoice1));
                     		  txtchoice2.setText(choice2txt + " : " + String.valueOf(scorechoice2));
                     		  
                     	  }
                     		mp = MediaPlayer.create(getApplicationContext(),  R.raw.spincoin);
                         	mp.start();
                         	
                         	  try {
           						mp.setDataSource(getAssets().openFd("raw/spincoin.mp3").getFileDescriptor());
           					} catch (IllegalArgumentException e) {
           						// TODO Auto-generated catch block
           						e.printStackTrace();
           					} catch (IllegalStateException e) {
           						// TODO Auto-generated catch block
           						e.printStackTrace();
           					} catch (IOException e) {
           						// TODO Auto-generated catch block
           						e.printStackTrace();
           					}
                           	  try {
           						mp.prepare();
           					} catch (IllegalStateException e) {
           						// TODO Auto-generated catch block
           						e.printStackTrace();
           					} catch (IOException e) {
           						// TODO Auto-generated catch block
           						e.printStackTrace();
           					}
                 	  iteration = 0;
                 	roundstart++;
                 	maxite = (int) (8 + Math.random() * 4);
          	    	handler= new MyHandler();
                 	new_timer= new Timer();
                 	new_timer.schedule(new TickClass(), 500, 620);
                 	  }
                 }
         
            	 
            	}/*if roundstart not equal to roundnum */
            	else
            	{
            		iteration = 50;
            		roundflag = false;
            		
            		
              	  if(scorechoice1 > scorechoice2)
            	  {
              		  
            		  txtchoice1.setText("choice 1 wins");
            		  txtchoice2.setText("choice 2 loses");
            		  
            		  winmessage = "FlipCoin decides " + choice1txt;

            	  }
            	  else
            	  {
            		  
            		  txtchoice1.setText("choice 1 loses");
            		  txtchoice2.setText("choice 2 wins");
            		  
            		  winmessage = "FlipCoin decides " + choice2txt;
            		  
            	  }
              	cntithasbegun++;
              	  choice2txt = "";
              	  choice1txt = "";
              	  showDialog(CUSTOM_DIALOG_ID2);
            		
            	}
            }
            
            
            }
        }

}
