package com.orixes.expensemanager;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orixes.expensemanager.bean.ItemInfo;

public class AddItem extends Activity implements OnClickListener {
	boolean isAppFirstTimeInstalled = false;
	private EditText item,category,quantity,price_per_item;
	 SQLiteDatabase db;
	static SharedPreferences pref;
	public ItemInfo itemInfo;
	TextView date,time;
	Button saveButton,skipButton;
	private String timeString,dateString;
	private Utils utils=new Utils();

	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_entry);
		 saveButton= (Button)findViewById(R.id.save);
		 skipButton= (Button)findViewById(R.id.skip);
		 date= (TextView)findViewById(R.id.date_value);
		 time=(TextView)findViewById(R.id.time_value);
		 
		saveButton.setOnClickListener(this);
		item=(EditText)findViewById(R.id.item_value);
		category=(EditText)findViewById(R.id.category_value);
		quantity=(EditText)findViewById(R.id.quantity_value);
		price_per_item=(EditText)findViewById(R.id.price_per_item_value);
		skipButton.setOnClickListener(this);
		timeString=getCurrentTime();
		dateString=getTodaysDate();
		date.setText(dateString);
		time.setText(timeString);
		pref = getApplicationContext().getSharedPreferences("MYprefAdditem", MODE_PRIVATE);
	       if(!pref.contains("firstTimeAdditem")){
	    	   setFirstTimeValue(0);
	       }
	       if(getFirstTimeValue()==0){
	    	   setFirstTimeValue(1);
	       }else{
	    	  // Log.d("second", "second");
				Intent i =new Intent(this,ExpenseView.class);
				startActivity(i);
				finish();
	       }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.save){
			

		if(!validate()){
			
		}else{
			
			utils.addValuesforAdditem(item.getText().toString(),category.getText().toString(),quantity.getText().toString(),price_per_item.getText().toString(),dateString,timeString);
			dbCreate(utils.dbNameOfAddItem, utils.tableNameOfAddItem, utils.colsNameOfAddItem);
			dbInsert(utils.tableNameOfAddItem, utils.additemValues());
			dbClose();
			
			/*db=openOrCreateDatabase("additemDatabase", MODE_PRIVATE, null);
			db.execSQL("CREATE TABLE IF NOT EXISTS addItem(item VARCHAR,category VARCHAR,quantity VARCHAR,totalprice VARCHAR,date VARCHAR,time VARCHAR);");
			
			db.execSQL("INSERT INTO addItem VALUES('"+itemInfo.getItemName()+"','"+itemInfo.getCategory()+"','"+itemInfo.getQuantity()+"','"+itemInfo.getToatlPrice()+"','"+dateString+"','"+timeString+"');");
			db.close();*/
			Intent i=new Intent(this,MainActivity.class);
			startActivity(i);
			finish();
	
		}
		}else if(v.getId()==R.id.skip){
			Intent i=new Intent(this,MainActivity.class);
			startActivity(i);
			finish();
		}
		
	}
	public static void setFirstTimeValue(int val){
    	Editor editor= pref.edit();
    	editor.putInt("firstTimeAdditem", val);
    	editor.commit();
    }
    public static int getFirstTimeValue(){
    	return pref.getInt("firstTimeAdditem", 0);
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	
	}
	
	private String getTodaysDate() {
		 
	    final Calendar c = Calendar.getInstance();
	 
	    return(new StringBuilder()
	            .append(c.get(Calendar.MONTH) + 1).append("/")
	            .append(c.get(Calendar.DAY_OF_MONTH)).append("/")
	            .append(c.get(Calendar.YEAR)).append(" ")).toString();
	}
	 
	//Time format HH:MM:SS
	private String getCurrentTime() {
	 
	    final Calendar c = Calendar.getInstance();
	 
	    return(new StringBuilder()
	            .append(c.get(Calendar.HOUR_OF_DAY)).append(":")
	            .append(c.get(Calendar.MINUTE)).append(":")
	            .append(c.get(Calendar.SECOND)).append(" ")).toString();
	}
	
	private boolean validate(){
        if(item.getText().toString().trim().equals("")){
        	Toast.makeText(getApplicationContext(), "Item name is missing", Toast.LENGTH_SHORT).show();
            return false;}
        else if(category.getText().toString().trim().equals("")){
        	Toast.makeText(getApplicationContext(), "Category is missing", Toast.LENGTH_SHORT).show();
            return false;}
        else if(quantity.getText().toString().trim().equals("")){
        	Toast.makeText(getApplicationContext(), "Quantity is missing", Toast.LENGTH_SHORT).show();
            return false;}
        else if(price_per_item.getText().toString().trim().equals("")){
        	Toast.makeText(getApplicationContext(), "Price per item is missing", Toast.LENGTH_SHORT).show();
            return false;}
        else
            return true;    
    }
	public void dbCreate(String dbName,String tableName,String colsName){
		db=openOrCreateDatabase(dbName, MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+"("+colsName+");");
		
	}
	
	public void dbInsert(String tableName,String colsValues){
		db.execSQL("INSERT INTO "+tableName+" VALUES("+colsValues+");");

	}
	
	public void dbClose(){
		db.close();
	}
	
	public String getTimeString() {
		return timeString;
	}

	public String getDateString() {
		return dateString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public Utils getUtils() {
		return utils;
	}

}
