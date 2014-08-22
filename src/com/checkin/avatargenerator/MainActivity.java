package com.checkin.avatargenerator;

import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {
	private Handler handler;
	private Generate generate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		handler = new Handler();
		generate = new Generate();
		handler.post(generate);
	}
	
	private class Generate extends TimerTask{

		@Override
		public void run() {
			((RoundedImageView) findViewById(R.id.roundedImage)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage2)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage3)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage4)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage5)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage6)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage7)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage8)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage9)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage10)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage11)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			((RoundedImageView) findViewById(R.id.roundedImage12)).setImageBitmap(AvatarGenerator.generate(MainActivity.this, 120, 120));
			//handler.postDelayed(generate, 50);
		}
		
	}
}
