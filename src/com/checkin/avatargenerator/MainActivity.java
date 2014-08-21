package com.checkin.avatargenerator;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((RoundedImageView) findViewById(R.id.roundedImage)).setImageBitmap(Generator.generate(this, 120, 120));
		((RoundedImageView) findViewById(R.id.roundedImage2)).setImageBitmap(Generator.generate(this, 120, 120));
		((RoundedImageView) findViewById(R.id.roundedImage3)).setImageBitmap(Generator.generate(this, 120, 120));
		((RoundedImageView) findViewById(R.id.roundedImage4)).setImageBitmap(Generator.generate(this, 120, 120));
	}
}
