package com.flip.flip;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class FlipMenuAdapter extends ArrayAdapter<FlipMenuClass>{

    Context context;
    int layoutResourceId;   
    FlipMenuClass data[] = null;
   
    public FlipMenuAdapter(Context context, int layoutResourceId, FlipMenuClass[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HelpPointHolder holder = null;
       
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
           
            holder = new  HelpPointHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.txtDesc = (TextView)row.findViewById(R.id.txtDesc);
            row.setTag(holder);
        }
        else
        {
            holder = (HelpPointHolder)row.getTag();
        }
       
        FlipMenuClass hp = data[position];
        holder.txtTitle.setText(hp.title);
        holder.txtDesc.setText(hp.desc);
        holder.imgIcon.setImageResource(hp.icon);
       
        return row;
    }
   
    static class HelpPointHolder
    {
        public TextView txtDesc;
		ImageView imgIcon;
        TextView txtTitle;
    }
}