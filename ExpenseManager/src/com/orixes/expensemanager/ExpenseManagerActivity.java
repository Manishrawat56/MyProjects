package com.orixes.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orixes.expensemanager.bean.UserInfo;

public class ExpenseManagerActivity extends Activity implements OnClickListener {
	boolean isAppFirstTimeInstalled = false;
	private EditText name,mobile,email,salary;
	private SQLiteDatabase db;
	public UserInfo userInfo;
	private static SharedPreferences pref;
	private Utils utils;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_manager);
		Button b=(Button)findViewById(R.id.sumbit);
		utils=new Utils();
		name=(EditText)findViewById(R.id.editText1);
		mobile=(EditText)findViewById(R.id.editText2);
		email=(EditText)findViewById(R.id.editText3);
		salary=(EditText)findViewById(R.id.editText4);
		b.setOnClickListener(this);
		
		pref = getApplicationContext().getSharedPreferences("MYpref", MODE_PRIVATE);
	       if(!pref.contains("firstTime")){
	    	   setFirstTimeDetailValue(0);
	       }
	       
	       if(getFirstTimeDetailValue()==1){
				Intent i =new Intent(this,AddItem.class);
				startActivity(i);
				finish();
	       }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_manager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if(!validate()){
			
		}else{
			utils.addValuesforProfileDetail(name.getText().toString(),mobile.getText().toString(),email.getText().toString(),salary.getText().toString());
			if(getFirstTimeDetailValue()==2){
				dbCreate(utils.dbNameOfProfileDetail, utils.tableNameOfProfileDetail, utils.colsNameOfProfileDetail);

				db.execSQL("UPDATE loginDb SET Name='"+userInfo.getName()+"',Mobile='"+userInfo.getMobileNumber()+"',Email='"+userInfo.getEmail()+"',Salary='"+userInfo.getSalary()+"'");
				db.close();
			}
			
			if(getFirstTimeDetailValue()==0){
			dbCreate(utils.dbNameOfProfileDetail, utils.tableNameOfProfileDetail, utils.colsNameOfProfileDetail);
			dbInsert(utils.tableNameOfProfileDetail, utils.profileDetailValues());
			db.close();
			}
		if(getFirstTimeDetailValue()==0 || getFirstTimeDetailValue()==2){
			setFirstTimeDetailValue(1);
	       	}

		Intent i =new Intent(this,AddItem.class);
		startActivity(i);
		finish();
		}
	}
	private boolean validate(){
        if(name.getText().toString().trim().equals("")){
        	Toast.makeText(getApplicationContext(), "Name is missing", Toast.LENGTH_SHORT).show();
            return false;}
        else if(mobile.getText().toString().trim().equals("")){
        	Toast.makeText(getApplicationContext(), "Moblie Number is missing", Toast.LENGTH_SHORT).show();
            return false;}
        else if(email.getText().toString().trim().equals("")){
        	Toast.makeText(getApplicationContext(), "Email address is missing", Toast.LENGTH_SHORT).show();
            return false;}
        else if(salary.getText().toString().trim().equals("")){
        	Toast.makeText(getApplicationContext(), "Salary amount is missing", Toast.LENGTH_SHORT).show();
            return false;}
        else
            return true;    
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(getFirstTimeDetailValue()==2){
			setFirstTimeDetailValue(1);
	       	}
  	 
	}
	public static void setFirstTimeDetailValue(int val){
    	Editor editor= pref.edit();
    	editor.putInt("firstTime", val);
    	editor.commit();
    }
    public static int getFirstTimeDetailValue(){
    	return pref.getInt("firstTime", 0);
    }
    public void dbCreate(String dbName,String tableName,String colsName){
		db=openOrCreateDatabase(dbName, MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+"("+colsName+");");
		
	}
    public void dbInsert(String tableName,String colsValues){
		db.execSQL("INSERT INTO "+tableName+" VALUES("+colsValues+");");

	}
    public Utils getUtils() {
		return utils;
	}

	public void setUtils(Utils utils) {
		this.utils = utils;
	}
}
