package com.planetplace;

import java.util.ArrayList;

import com.plant.abc.Plant;

import android.app.ListActivity;
import android.database.CursorJoiner.Result;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlantResultActivity extends ListActivity {

	public static final String PLANT_RESULT = "PLANT_RESULT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// retrivies any data that was passed in AutoCompleteTextView
		String searchTerm = getIntent().getStringExtra(
				AdvancedSearchActivity.SEARCH_PLANT_NAME);

		// create a collection to hold the plants
		ArrayList<Plant> allPlants = new ArrayList<Plant>();

		if (searchTerm.equalsIgnoreCase("redbud")) {

			// create a new object from class plant.
			Plant redbud = new Plant();
			redbud.setCommon("Redbud");
			redbud.setGenus("Cercis");
			redbud.setSpecies("canadensis");
			allPlants.add(redbud);
		}
		if (searchTerm.contains("pawpaw")) {

			Plant pawpaw = new Plant();
			pawpaw.setCommon("PawPaw");
			pawpaw.setGenus("Asimina");
			pawpaw.setSpecies("trilloba");
			allPlants.add(pawpaw);
		}

		if (allPlants.size() == 0) {
			Plant empty = new Plant();
			empty.setCommon("No plants match your Result. Please try again");
			allPlants.add(empty);
		}

		// show this collection in our list

		ArrayAdapter<Plant> plantAdapter = new ArrayAdapter<Plant>(this,
				android.R.layout.simple_list_item_1, allPlants);

		setListAdapter(plantAdapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		// get the item that the user clicked
		Plant plant = (Plant) getListAdapter().getItem(position);

		//put the plant in the intent which we are about to return
		getIntent().putExtra(PLANT_RESULT, plant);
		
		// EveryThing went fine
		setResult(RESULT_OK, getIntent());

		// Finish this Intent
		finish();
	}

}
