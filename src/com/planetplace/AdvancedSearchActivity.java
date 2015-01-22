package com.planetplace;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.plant.abc.Plant;

public class AdvancedSearchActivity extends Activity {

	private static final int IMAGE_GALLERY = 10;
	public static final int PLANT_RESULTS = 1;
	public static final String SEARCH_PLANT_NAME = "SEARCH_PLANT_NAME";
	private AutoCompleteTextView actPlantName;
	private Bitmap selectedImage;
	private ImageView imagePlantsearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advancedsearch);

		actPlantName = (AutoCompleteTextView) findViewById(R.id.actPlantName);
		imagePlantsearch = (ImageView) findViewById(R.id.imgPlantSearch);

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

		if (resultCode == RESULT_OK) {
			// are we getting data returend from the plantResultIntent? If so,
			// this
			// if test will evaluate to true, because
			// we passed the PLANT_RESULTS constant in when invoked that intent
			if (requestCode == PLANT_RESULTS) {
				// fetch the selected data using the constant that we have using
				// as
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

			} else if (requestCode == IMAGE_GALLERY) {
				// If we are here we got response from the image gallery

				// find the path of the selected image
				Uri photoLocation = data.getData();

				// open this as a stream of data/byte
				try {
					// a stream of data from the file
					InputStream openInputStream = getContentResolver()
							.openInputStream(photoLocation);
					// take a stream of data and convert it to a Bitmap
					selectedImage = BitmapFactory.decodeStream(openInputStream);

					// assign this image to our ImageView
					imagePlantsearch.setImageBitmap(selectedImage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// alert the user that something went wrong
					Toast.makeText(this, "Unable to open image",
							Toast.LENGTH_LONG).show();
				}

			}
		}
	}

	// This method is invoked when the select a picture button is pressed

	public void onSelectAPictureClicked(View v) {

		// we want to pick an image from a gallery, so specify the action pick
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

		// specify where to find the image, using data.
		// give me the path (file system directory)where we store images
		String path = Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES).getPath();

		// Convert to URL, because that what our Gallery is expecting

		Uri picturesDirectory = Uri.parse(path);

		// set the data and type in this Intent, so we tell it where to look for
		// files, and
		// what file types we want

		photoPickerIntent.setDataAndType(picturesDirectory, "image/*");

		// start the activity and tell it show result
		startActivityForResult(photoPickerIntent, IMAGE_GALLERY);

	}
}
