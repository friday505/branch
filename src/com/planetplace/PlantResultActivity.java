package com.planetplace;

import java.util.ArrayList;

import com.plant.abc.Plant;

import android.app.ListActivity;
import android.database.CursorJoiner.Result;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class PlantResultActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// retrivies any data that was passed in
		String searchTerm = getIntent().getStringExtra("SEARCH_PLANT_NAME");

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
		
		if(allPlants.size() == 0){
			Plant empty = new Plant();
			empty.setCommon("No plants match your Result. Please try again" );
			allPlants.add(empty);
		}

		// show this collection in our list

		ArrayAdapter<Plant> plantAdapter = new ArrayAdapter<Plant>(this,
				android.R.layout.simple_list_item_1, allPlants);

		setListAdapter(plantAdapter);

	}

}
