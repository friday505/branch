package com.planetplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdvancedSearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advancedsearch);

	}

	public void searchForPlant(View v) {
		Intent plantResultIntent = new Intent(this, PlantResultActivity.class);
		startActivity(plantResultIntent);
	}

}
