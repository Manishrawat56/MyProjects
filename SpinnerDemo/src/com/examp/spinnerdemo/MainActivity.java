package com.examp.spinnerdemo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

public class MainActivity extends ActionBarActivity {
	private Spinner spinner2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addItemsOnSpinner2();
		addListenerOnSpinnerItemSelection();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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

	public void addItemsOnSpinner2() {

		spinner2 = (Spinner) findViewById(R.id.spinner2);
		//i create arraylist as example
		List<String> list = new ArrayList<String>();
		list.add("list 1"); //this is values
		list.add("list 2");
		list.add("list 3");
		//list is which come from server
		spinner2.setAdapter(new DataAdapter(this, R.layout.spinner_item, list));
	}

	public void addListenerOnSpinnerItemSelection() {

		spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}
}
