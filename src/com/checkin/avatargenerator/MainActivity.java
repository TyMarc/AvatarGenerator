package com.checkin.avatargenerator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((RoundedImageView) findViewById(R.id.roundedImage)).setImageBitmap(Generator.generate(this));
	}
}
