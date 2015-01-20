package com.planetplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;

public class AdvancedSearchActivity extends Activity {
	
	private AutoCompleteTextView actPlantName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advancedsearch);
		
		actPlantName = (AutoCompleteTextView) findViewById(R.id.actPlantName);

	}

	public void searchForPlant(View v) {
		Intent plantResultIntent = new Intent(this, PlantResultActivity.class);
		
		String searchPlantName = actPlantName.getText().toString();
		
		//pass that data to the next activity
		plantResultIntent.putExtra("SEARCH_PLANT_NAME", searchPlantName);
		
		startActivity(plantResultIntent);
	}

}
