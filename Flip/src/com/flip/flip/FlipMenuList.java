package com.flip.flip;

import java.util.ArrayList;

import com.flip.flip.R.string;



import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class FlipMenuList extends Activity {
	final String[] criteria = new String[] {"About","How to use","Choice Reminder","Widget","Feedback"};
	final String[] criteriadesc = new String[] {"About CoinFlip","How to use CoinFlip","Use Choice Reminder","Widgetize CoinFlip","Send us a Feedback"};
	int[] criteriaimage = new int[]{R.drawable.about,R.drawable.instruction,R.drawable.bitcoinhow,R.drawable.widgetize,R.drawable.sendfeed};
	int[] desc= new int[]{R.string.about_FlipCoin,R.string.howto_FlipCoin,R.string.reminder_FlipCoin,R.string.widget_FlipCoin,R.string.sendfeed_FlipCoin};
	final ArrayList<String> list = new ArrayList<String>();
	private ListView listView1;
	private Button closebutton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flip_menu_list);
		FlipMenuAdapter adapter = null;
		FlipMenuClass[] fav = new FlipMenuClass[(criteria.length)];
		
		for (int i = 0; i < criteria.length; i++) {
	     fav[i] = new FlipMenuClass(criteriaimage[i], criteria[i], criteriadesc[i]);
	     list.add(criteria[i]);

	      	}
	        adapter = new FlipMenuAdapter(this, R.layout.listview_item_row, fav);
	        listView1 = (ListView)findViewById(R.id.listView1);
	        closebutton = (Button)findViewById(R.id.close_button);
	        closebutton.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                finish();
	            }
	        });
	        listView1.setAdapter(adapter);
            listView1.setOnItemClickListener(new OnItemClickListener() {
          		public void onItemClick(AdapterView<?> parent, final View view, int position,
        				long id) {
          				Intent intent = new Intent(FlipMenuList.this,Note.class);
          				intent.putExtra("title", criteria[position]);
          				intent.putExtra("desc", getResources().getString(desc[position]));
          				startActivity(intent);
          		}
            });
            
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.flip_menu_list, menu);
		return true;
	}

}
