package com.orixes.expensemanager;

import java.util.ArrayList;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemList extends BaseAdapter {
	private ArrayList<ArrayList<String>> itemValues; 
	private Context context;
	private LayoutInflater inflater;
	private boolean isTitle;
	int width;
	public ItemList(Context context,ArrayList<ArrayList<String>> itemValues,int width) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.itemValues=itemValues;
		this.width=width;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemValues.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemValues.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            
		}
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_item, null);
            isTitle=true;
        }
        /*if(isTitle){
        	 ((TextView)convertView.findViewById(R.id.item)).setText("Item");
             ((TextView)convertView.findViewById(R.id.category)).setText("Category");
             ((TextView)convertView.findViewById(R.id.price)).setText("Price");
             ((TextView)convertView.findViewById(R.id.item)).setTextSize(25);
             ((TextView)convertView.findViewById(R.id.category)).setTextSize(25);
             ((TextView)convertView.findViewById(R.id.price)).setTextSize(25);
             isTitle=false;
        }else{*/
        ((TextView)convertView.findViewById(R.id.item)).setWidth(width);
        ((TextView)convertView.findViewById(R.id.category)).setWidth(width);
        ((TextView)convertView.findViewById(R.id.price)).setWidth(width);
        ((TextView)convertView.findViewById(R.id.item)).setText(itemValues.get(position).get(0));
        ((TextView)convertView.findViewById(R.id.category)).setText(itemValues.get(position).get(1));
        ((TextView)convertView.findViewById(R.id.price)).setText(itemValues.get(position).get(2)+"$");
     
		return convertView;
	}

}
