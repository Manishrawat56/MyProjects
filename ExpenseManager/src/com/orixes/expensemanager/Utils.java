package com.orixes.expensemanager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.orixes.expensemanager.bean.ItemInfo;
import com.orixes.expensemanager.bean.UserInfo;

public class Utils {
	
	private ItemInfo itemInfo;
	private UserInfo userInfo;
	
	String date,time;
	public static String dbNameOfAddItem="additemDatabase";
	public static String tableNameOfAddItem="addItem";
	public static String colsNameOfAddItem="item VARCHAR,category VARCHAR,quantity VARCHAR,totalprice VARCHAR,date VARCHAR,time VARCHAR";
	public static String dbNameOfProfileDetail="loginDatabase";
	public static String tableNameOfProfileDetail="loginDb";
	public static String colsNameOfProfileDetail="Name VARCHAR,Mobile VARCHAR,Email VARCHAR,Salary VARCHAR";
	
	public String additemValues(){
		
		//Log.d("itemInfo", ""+itemInfo.toString());
		String colsValuesOfAddItem="'"+itemInfo.getItemName()+"','"+itemInfo.getCategory()+"','"+itemInfo.getQuantity()+"','"+itemInfo.getToatlPrice()+"','"+date+"','"+time+"'";
		return colsValuesOfAddItem;
	}
	
	public void addValuesforAdditem(String itemName,String category,String quantity,String totalPrice,String date,String time){
		itemInfo=new ItemInfo(itemName,category,quantity,String.valueOf(Integer.parseInt(quantity)*Integer.parseInt(totalPrice)));
		//Log.d("itemInfo", ""+itemInfo.toString());
		this.date=date;
		this.time=time;
	}
	
	public String profileDetailValues(){
		String colsValuesOfAddItem="'"+userInfo.getName()+"','"+userInfo.getMobileNumber()+"','"+userInfo.getEmail()+"','"+userInfo.getSalary()+"'";
		return colsValuesOfAddItem;
	}
	public void addValuesforProfileDetail(String name,String mobile,String email,String salary){
		userInfo=new UserInfo(name, mobile, email, salary);
	}
}
