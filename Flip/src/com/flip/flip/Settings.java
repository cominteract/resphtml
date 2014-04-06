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

public class Settings extends Activity {
	ListView lv;
	Button closeset;
	final String[] criteria = new String[] {"Change Coin","View Ratio","Set Chance","Set Alarm Tune","Change Icon"};
	final String[] criteriadesc = new String[] {"Set Coin Image","View Chance ratio. Heads/Tails","Change probability of Heads/Tails","Change Alarm tune","Set Icon of alarm"};
	int[] criteriaimage = new int[]{R.drawable.changecoin,R.drawable.ratio,R.drawable.chancecoins,R.drawable.alarmclockreminder,R.drawable.flipcoin};
	final ArrayList<String> list = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
        lv = (ListView)findViewById(R.id.listsetting);
        closeset = (Button)findViewById(R.id.closesetting_button);
		FlipMenuAdapter adapter = null;
		FlipMenuClass[] fav = new FlipMenuClass[(criteria.length)];
		
		for (int i = 0; i < criteria.length; i++) {
		     fav[i] = new FlipMenuClass(criteriaimage[i], criteria[i], criteriadesc[i]);
		     list.add(criteria[i]);

		      	}
        closeset.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new FlipMenuAdapter(this, R.layout.listview_item_row, fav);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
      		public void onItemClick(AdapterView<?> parent, final View view, int position,
    				long id) {
      				Intent intent = new Intent(Settings.this,ChangeCoin.class);
      				startActivity(intent);
      		}
        });
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

}
