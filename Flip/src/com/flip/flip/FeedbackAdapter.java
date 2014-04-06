package com.flip.flip;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class FeedbackAdapter extends ArrayAdapter<FeedbackClass>{

    Context context;
    int layoutResourceId;   
    FeedbackClass data[] = null;
   
    public FeedbackAdapter(Context context, int layoutResourceId, FeedbackClass[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FeedbackHolder holder = null;
       
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
           
            holder = new  FeedbackHolder();
            
            holder.txtName = (TextView)row.findViewById(R.id.txtName);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.txtDesc = (TextView)row.findViewById(R.id.txtDesc);
            holder.txtDate = (TextView)row.findViewById(R.id.txtDate);
            row.setTag(holder);
        }
        else
        {
            holder = (FeedbackHolder)row.getTag();
        }
       
        FeedbackClass fb = data[position];
        holder.txtTitle.setText(fb.title);
        holder.txtDesc.setText(fb.desc);
        holder.txtDate.setText(fb.dateposted);
        holder.txtName.setText(fb.uname);
        
       
        return row;
    }
   
    static class FeedbackHolder
    {
        public TextView txtDesc;

        TextView txtTitle,txtName,txtEmail,txtDate;
    }
}
