package com.planetplace;

import java.io.Serializable;

import com.plant.abc.Plant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class AdvancedSearchActivity extends Activity {

	public static final int PLANT_RESULTS = 1;
	public static final String SEARCH_PLANT_NAME = "SEARCH_PLANT_NAME";
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

		// pass that data to the next activity
		plantResultIntent.putExtra(SEARCH_PLANT_NAME, searchPlantName);

		startActivityForResult(plantResultIntent, PLANT_RESULTS);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		// are we getting data returend from the plantResultIntent? If so, this
		// if test will evaluate to true, because
		// we passed the PLANT_RESULTS constant in when invoked that intent
		if (requestCode == PLANT_RESULTS) {
			// fetch the selected data using the constant that we have using as
			// a key
			Plant plant = (Plant) data
					.getSerializableExtra(PlantResultActivity.PLANT_RESULT);

			// This Toast will be invoked if we recieved a result from
			// plantResultIntent
			// Toast.makeText(this, "Recieved Result " + plant,
			// Toast.LENGTH_LONG).show();

			// put the plant in the intent which we are about to return
			getIntent().putExtra(PlantResultActivity.PLANT_RESULT, plant);

			// EveryThing went fine
			setResult(RESULT_OK, getIntent());

			// Finish this Intent
			finish();

		}
	}
}
