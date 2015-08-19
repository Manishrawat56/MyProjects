package com.orixes.expensemanager;

import java.util.ArrayList;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseView extends ActionBarActivity  implements OnClickListener{
	AddItem addItem=new AddItem();
	
	ExpenseManagerActivity profiledetail=new ExpenseManagerActivity();
	TextView totalExpenses,salary,totlaBal,personName,total_expenses_amount;
	TableLayout tableLayout;
	SQLiteDatabase db;
	int totalSalary,expenses=0,width;
	ArrayList<ArrayList<String>> listItem;
	ListView list;
	ArrayList<String> dateAndTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 dateAndTime =new ArrayList<String>();
		listItem=new ArrayList<ArrayList<String>>();
		setContentView(R.layout.expense_view);
		ImageButton b=(ImageButton)findViewById(R.id.add_item);
		b.setOnClickListener(this);
		salary=(TextView)findViewById(R.id.salary_value);
		totlaBal=(TextView)findViewById(R.id.total_amount_value);
		totalExpenses=(TextView)findViewById(R.id.expense_value);
		
		//tableLayout=(TableLayout)findViewById(R.id.expenses_list);
		personName=(TextView)findViewById(R.id.person_name);
		//total_expenses_amount=(TextView)findViewById(R.id.total_expenses_amount);
		
		dbCreate(profiledetail.getUtils().dbNameOfProfileDetail, profiledetail.getUtils().tableNameOfProfileDetail, profiledetail.getUtils().colsNameOfProfileDetail);
		Cursor c=db.rawQuery("SELECT * from "+profiledetail.getUtils().tableNameOfProfileDetail, null);
	    c.moveToFirst();
	    totalExpenses.setText("0");
	    personName.setText(c.getString(c.getColumnIndex("Name")));
	   totalSalary=Integer.parseInt(c.getString(c.getColumnIndex("Salary")));
	    salary.setText(totalSalary+"$");
	    
	    db.close();
	    dbCreate(addItem.getUtils().dbNameOfAddItem, addItem.getUtils().tableNameOfAddItem, addItem.getUtils().colsNameOfAddItem);
		Cursor c1=db.rawQuery("SELECT * from "+addItem.getUtils().tableNameOfAddItem, null);
	     int count= c1.getCount();
	     Display window=getWindowManager().getDefaultDisplay();
	     int screenWidth=window.getWidth();
	    
	     width=(screenWidth/3);
	     ((TextView)findViewById(R.id.item1)).setWidth(width);
	     ((TextView)findViewById(R.id.category1)).setWidth(width);
	     ((TextView)findViewById(R.id.price1)).setWidth(width);
	     Log.d("screenWidth", ""+screenWidth);
	     Log.d("count",""+count);
	     //setTitleName();
	    c1.moveToFirst();
	    TableRow tableRow;
		   TextView item,category,totalprice;
	    for (Integer j = 0; j < count; j++)
	     {
	    	ArrayList<String> itemOfList =new ArrayList<String>();
	    	itemOfList.add(c1.getString(c1.getColumnIndex("item")));
	    	itemOfList.add(c1.getString(c1.getColumnIndex("category")));
	    	itemOfList.add(c1.getString(c1.getColumnIndex("totalprice")));
	    	dateAndTime.add(c1.getString(c1.getColumnIndex("date")));
	    	dateAndTime.add(c1.getString(c1.getColumnIndex("time")));
	         /* tableRow = new TableRow(getApplicationContext());
	          item = new TextView(getApplicationContext());
	          TableRow.LayoutParams tl= new TableRow.LayoutParams(width,LayoutParams.WRAP_CONTENT);
	          item.setLayoutParams(tl);
	          item.setTextColor(Color.BLACK);
	         item.setText(c1.getString(c1.getColumnIndex("item")));
	         item.setGravity(Gravity.CENTER);	        
	          category = new TextView(getApplicationContext());
	          TableRow.LayoutParams t2= new TableRow.LayoutParams(width,LayoutParams.WRAP_CONTENT);
	          category.setLayoutParams(t2);
	          category.setTextColor(Color.BLACK);
	         category.setText(c1.getString(c1.getColumnIndex("category")));
	         category.setGravity(Gravity.CENTER);
	          
	          totalprice = new TextView(getApplicationContext());
	          TableRow.LayoutParams t3= new TableRow.LayoutParams(width,LayoutParams.WRAP_CONTENT);
	          totalprice.setLayoutParams(t3);
	          totalprice.setTextColor(Color.BLACK);
	         totalprice.setText(c1.getString(c1.getColumnIndex("totalprice"))+"$");
	         totalprice.setGravity(Gravity.CENTER);
	         tableRow.addView(item);
	         tableRow.addView(category);
	        
	         tableRow.addView(totalprice);
	         tableLayout.addView(tableRow);*/
	    	listItem.add(itemOfList);
	         expenses=expenses+(Integer.parseInt(c1.getString(c1.getColumnIndex("totalprice"))));
	         c1.moveToNext();
	     }
	db.close();
	list=(ListView)findViewById(R.id.list);
	ItemList adapter=new ItemList(this, listItem,width);
	list.setAdapter(adapter);
	totalExpenses.setText(expenses+"$");
	totlaBal.setText(getTotalBal()+"$");
	Log.d("dateAndTime", ""+dateAndTime);
	list.setOnItemClickListener(new OnItemClickListener() {
		  @Override
		  public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id) {
		    Toast.makeText(getApplicationContext(),
		      " Item = "+listItem.get(position).get(0)+"\n Category = " +listItem.get(position).get(1)+"\n Price = "+listItem.get(position).get(2), Toast.LENGTH_SHORT)
		      .show();
		  }
		}); 
	list.setOnItemLongClickListener(new OnItemLongClickListener() {

	      @Override
	      public boolean onItemLongClick(AdapterView<?> parent, View view,
	          int position, long id) {
	        Toast.makeText(getApplicationContext(),
	            "Item long  click at" + position+" position" ,
	            Toast.LENGTH_SHORT).show();
	        // Return true to consume the click event. In this case the
	        // onListItemClick listener is not called anymore.
	        return true;
	      }
	    });
	setActionBar(c.getString(c.getColumnIndex("Name")));
	}
	public void setActionBar(String heading) {
	    // TODO Auto-generated method stub

	   ActionBar actionBar = getSupportActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setDisplayHomeAsUpEnabled(false);
	    actionBar.setDisplayShowHomeEnabled(false);
	    actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_color)));
	    actionBar.setTitle(heading);
	    actionBar.show();

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_manager, menu);
		// Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
         SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		SQLiteDatabase db;
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			profiledetail.setFirstTimeDetailValue(2);
			Intent i=new Intent(this,ExpenseManagerActivity.class);
			startActivity(i);
			finish();
			return true;
		}
		if (id == R.id.login) {
			
			return true;
		}
		if (id == R.id.search) {
			
			return true;
		}
		if (id == R.id.addItem) {
			AddItem.setFirstTimeValue(0);
			Intent i=new Intent(this,AddItem.class);
			startActivity(i);
			finish();
			return true;
		}
		if (id == R.id.additem) {
			
			return true;
		}
		
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	private int getTotalBal() {
		// TODO Auto-generated method stub
		return (totalSalary-expenses);
	}
	/*private void setTitleName(){
		TableRow tableRow;
		   TextView itemTitle,categoryTitle,price;
		   Typeface font =Typeface.create("mono", Typeface.BOLD);
		tableRow = new TableRow(getApplicationContext());
        itemTitle = new TextView(getApplicationContext());
        TableRow.LayoutParams tl= new TableRow.LayoutParams(width,LayoutParams.WRAP_CONTENT);
        itemTitle.setLayoutParams(tl);
        itemTitle.setTypeface(font, Typeface.BOLD );
       itemTitle.setText("Item");
       itemTitle.setGravity(Gravity.CENTER);
       itemTitle.setTextSize(16);

        categoryTitle = new TextView(getApplicationContext());
        TableRow.LayoutParams t2= new TableRow.LayoutParams(width,LayoutParams.WRAP_CONTENT);
        categoryTitle.setLayoutParams(t2);
        categoryTitle.setTypeface(font, Typeface.BOLD );
       categoryTitle.setText("Category");
       categoryTitle.setGravity(Gravity.CENTER);
       categoryTitle.setTextSize(16);
        
        price = new TextView(getApplicationContext());
        TableRow.LayoutParams t3= new TableRow.LayoutParams(width,LayoutParams.WRAP_CONTENT);
        price.setLayoutParams(t3);
        price.setTypeface(font, Typeface.BOLD );
       price.setText("Price");
       price.setGravity(Gravity.CENTER);
       price.setTextSize(16);
       tableRow.addView(itemTitle);
       tableRow.addView(categoryTitle);
      
       tableRow.addView(price);
       tableLayout.addView(tableRow);
	}
*/	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}
	 public void dbCreate(String dbName,String tableName,String colsName){
			db=openOrCreateDatabase(dbName, MODE_PRIVATE, null);
			db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+"("+colsName+");");
			
		}
	   
	 public int getWindowWidth(){
		 Display window=getWindowManager().getDefaultDisplay();
	     int screenWidth=window.getWidth();
		 return   (screenWidth/3);
	 }
}
