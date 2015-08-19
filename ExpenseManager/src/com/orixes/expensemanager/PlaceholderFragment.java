package com.orixes.expensemanager;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceholderFragment extends Fragment implements OnClickListener{
AddItem addItem=new AddItem();
	
	ExpenseManagerActivity profiledetail=new ExpenseManagerActivity();
	TextView totalExpenses,salary,totlaBal,personName,total_expenses_amount;
	TableLayout tableLayout;
	SQLiteDatabase db;
	int totalSalary,expenses=0,width;
	ArrayList<ArrayList<String>> listItem;
	ListView list;
	ArrayList<String> dateAndTime;
	int sliderPageId;
	MainActivity mainActivity=new MainActivity();
	
	ExpenseView exView=new ExpenseView();
	private static final String ARG_SECTION_NUMBER = "section_number";

	
	public static PlaceholderFragment newInstance(int sectionNumber) {
		PlaceholderFragment fragment = new PlaceholderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public PlaceholderFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sliderPageId=getArguments().getInt(ARG_SECTION_NUMBER);
		
			
	}
	private void history(View v) {
		// TODO Auto-generated method stub
		
	}

	private void todayView(View v) {
		dateAndTime =new ArrayList<String>();
		listItem=new ArrayList<ArrayList<String>>();
		ImageButton b=(ImageButton)v.findViewById(R.id.add_item);
		//b.setOnClickListener(getActivity());
		salary=(TextView)v.findViewById(R.id.salary_value);
		totlaBal=(TextView)v.findViewById(R.id.total_amount_value);
		totalExpenses=(TextView)v.findViewById(R.id.expense_value);
		
		//tableLayout=(TableLayout)findViewById(R.id.expenses_list);
		personName=(TextView)v.findViewById(R.id.person_name);
		//total_expenses_amount=(TextView)findViewById(R.id.total_expenses_amount);
		
		mainActivity.dbCreate(profiledetail.getUtils().dbNameOfProfileDetail, profiledetail.getUtils().tableNameOfProfileDetail, profiledetail.getUtils().colsNameOfProfileDetail);
		Cursor c=db.rawQuery("SELECT * from "+profiledetail.getUtils().tableNameOfProfileDetail, null);
	    c.moveToFirst();
	    totalExpenses.setText("0");
	    personName.setText(c.getString(c.getColumnIndex("Name")));
	   totalSalary=Integer.parseInt(c.getString(c.getColumnIndex("Salary")));
	    salary.setText(totalSalary+"$");
	    
	    db.close();
/*	    addItem.dbCreate(addItem.getUtils().dbNameOfAddItem, addItem.getUtils().tableNameOfAddItem, addItem.getUtils().colsNameOfAddItem);
		Cursor c1=db.rawQuery("SELECT * from "+addItem.getUtils().tableNameOfAddItem, null);
	     int count= c1.getCount();
	     
	    
	     width=exView.getWindowWidth();
	     ((TextView)v.findViewById(R.id.item1)).setWidth(width);
	     ((TextView)v.findViewById(R.id.category1)).setWidth(width);
	     ((TextView)v.findViewById(R.id.price1)).setWidth(width);
	     //Log.d("screenWidth", ""+screenWidth);
	     //Log.d("count",""+count);
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
	         
	         
	         
	         
	    	listItem.add(itemOfList);
	         expenses=expenses+(Integer.parseInt(c1.getString(c1.getColumnIndex("totalprice"))));
	         c1.moveToNext();
	     }
	db.close();
	list=(ListView)v.findViewById(R.id.list);
	ItemList adapter=new ItemList(getActivity(), listItem,width);
	list.setAdapter(adapter);
	totalExpenses.setText(expenses+"$");
	totlaBal.setText(getTotalBal()+"$");
	Log.d("dateAndTime", ""+dateAndTime);
	list.setOnItemClickListener(new OnItemClickListener() {
		  @Override
		  public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id) {
		    Toast.makeText(getActivity(),
		      " Item = "+listItem.get(position).get(0)+"\n Category = " +listItem.get(position).get(1)+"\n Price = "+listItem.get(position).get(2), Toast.LENGTH_SHORT)
		      .show();
		  }
		}); 
	list.setOnItemLongClickListener(new OnItemLongClickListener() {

	      @Override
	      public boolean onItemLongClick(AdapterView<?> parent, View view,
	          int position, long id) {
	        Toast.makeText(getActivity(),
	            "Item long  click at" + position+" position" ,
	            Toast.LENGTH_SHORT).show();
	        // Return true to consume the click event. In this case the
	        // onListItemClick listener is not called anymore.
	        return true;
	      }
	    });
	setActionBar(c.getString(c.getColumnIndex("Name")));
*/
		
	}

	private void yesterdayView(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View stateView = null;
		if(sliderPageId==1){
		 stateView = inflater.inflate(R.layout.fragment_main, container,false);
		}else if(sliderPageId==2){
			stateView = inflater.inflate(R.layout.expense_view, container,false);
		}else if(sliderPageId==3){
			stateView = inflater.inflate(R.layout.fragment_3, container,false);
		}
		if(sliderPageId==1){
			yesterdayView(stateView);
			}else if(sliderPageId==2){
				todayView(stateView);
			}else if(sliderPageId==3){
				history(stateView);
			}
		return stateView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	public void setActionBar(String heading) {
	    // TODO Auto-generated method stub

	   /*ActionBar actionBar = getSupportActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setDisplayHomeAsUpEnabled(false);
	    actionBar.setDisplayShowHomeEnabled(false);
	    actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_color)));
	    actionBar.setTitle(heading);
	    actionBar.show();
*/
	}
	private int getTotalBal() {
		// TODO Auto-generated method stub
		return (totalSalary-expenses);
	}
	/*public void dbCreate(String dbName,String tableName,String colsName){
		db=openOrCreateDatabase(dbName,MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+"("+colsName+");");
		
	}*/
}


