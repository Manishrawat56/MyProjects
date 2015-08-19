package com.examp.spinnerdemo;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.SpinnerAdapter;

public class DataAdapter extends ArrayAdapter<String> {
	Context context;
	List<String> values;
	LayoutInflater inflater;
	int id;
	
	public DataAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		this.context=context;
		this.values=objects;
		this.id=resource;
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
	
	}
	 @Override
	    public View getDropDownView(int position, View convertView,ViewGroup parent) {
	        return getCustomView(position, convertView, parent);
	    }
	 
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        return getCustomView(position, convertView, parent);
	    }
	 
	    // This funtion called for each row ( Called data.size() times )
	    public View getCustomView(int position, View convertView, ViewGroup parent) {
	 
	        View row = inflater.inflate(R.layout.spinner_item, parent, false);
	         
	        CheckBox checkBox = (CheckBox)row.findViewById(R.id.checkBox1);
	         
	        checkBox.setText(values.get(position));
	 
	        return row;
	      }
	}
