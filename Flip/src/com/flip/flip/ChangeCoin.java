package com.flip.flip;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ChangeCoin extends Activity {
	int[] coinfront = new int[]{R.drawable.ausdollarfront,R.drawable.africanfrancfront,R.drawable.canadadollarfront,R.drawable.cubanpesofront,R.drawable.estoniankroonfront,R.drawable.frencheurofront,R.drawable.germaneurofront,R.drawable.greekeurofront,R.drawable.hongkongdollarfront,R.drawable.italianeurofront,R.drawable.japaneseyenfront,R.drawable.mexicanpesofront,R.drawable.nethercoinfront,R.drawable.phpesofront,R.drawable.spanisheurofront,R.drawable.swissfrancfront,R.drawable.usdollarfront};
	int[] coinback = new int[]{R.drawable.ausdollarback,R.drawable.africanfrancback,R.drawable.canadadollarback,R.drawable.cubanpesoback,R.drawable.estoniankroonback,R.drawable.frencheuroback,R.drawable.germaneuroback,R.drawable.greekeuroback,R.drawable.hongkongdollarback,R.drawable.italianeuroback,R.drawable.japaneseyenback,R.drawable.mexicanpesoback,R.drawable.nethercoinback,R.drawable.phpesoback,R.drawable.spanisheuroback,R.drawable.swissfrancback,R.drawable.usdollarback};
	String[] coin = new String[]{"ausdollar","africanfranc","canadadollar","cubanpeso","estoniankroon","frencheuro","germaneuro","greekeuro","hongkongdollar","italianeuro","japaneseyen","mexicanpeso","nethercoin","phpeso","spanisheuro","swissfranc","usdollar"};
	final ArrayList<String> list = new ArrayList<String>();
	private ListView lv;
	private Button close;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_coin);
        lv = (ListView)findViewById(R.id.listCoin1);
        FlipMenuClass[] fav = new FlipMenuClass[(coin.length)];
        FlipMenuAdapter adapter = null;
        
    	for (int i = 0; i < coin.length; i++) {
   	     fav[i] = new FlipMenuClass(coinfront[i], coin[i], coin[i]);
   	     list.add(coin[i]);

   	      	}
        
        close = (Button)findViewById(R.id.closecoin_button);
        close.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new FlipMenuAdapter(this, R.layout.listview_item_row, fav);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
      		public void onItemClick(AdapterView<?> parent, final View view, int position,
    				long id) {
  				Intent intent = new Intent(ChangeCoin.this,MainActivity.class);
  				intent.putExtra("coin", coin[position]);
  				intent.putExtra("coinfront", coinfront[position]);
  				intent.putExtra("coinback", coinback[position]);
  				startActivity(intent);
      		}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_coin, menu);
		return true;
	}

}
