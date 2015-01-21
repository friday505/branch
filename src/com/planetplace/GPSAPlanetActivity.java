package com.planetplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.plant.abc.Plant;

public class GPSAPlanetActivity extends Activity {

	EditText description;
	TextView txtSelectedPlant;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps_plants);
		description = (EditText) findViewById(R.id.etDescription);
		txtSelectedPlant = (TextView) findViewById(R.id.tvSelectedPlant);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void searchClicked(View v) {
		Intent searchIntent = new Intent(this, AdvancedSearchActivity.class);

		startActivityForResult(searchIntent,
				AdvancedSearchActivity.PLANT_RESULTS);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == AdvancedSearchActivity.PLANT_RESULTS) {
			// change the label of the text view to be the plant that was passed
			// in

			// store the plant as an attribute
			Plant plant = (Plant) data
					.getSerializableExtra(PlantResultActivity.PLANT_RESULT);

			// set this plant in the TextView on the UI
			txtSelectedPlant.setText(plant.toString());
		}
	}

}
