package com.checkin.avatargenerator;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;

public class Generator {

	public static Bitmap generate(Context context) {
		Canvas canvas = new Canvas();
		Bitmap bitmap = Bitmap.createBitmap(dpInPixels(context, 120), dpInPixels(context, 120), Config.ARGB_4444);

		canvas.setBitmap(bitmap);

		int color = Color.rgb((int) (Math.random() * 254), (int) (Math.random() * 254), (int) (Math.random() * 254));
		Paint paint = new Paint();
		paint.setColor(color);

		ArrayList<Rect> rects = new ArrayList<Rect>();

		for (int i = 0; i < 3; i++) {
			boolean fits = true;
			Rect r = null;
			do {
				fits = true;
				double rand = Math.random();
				if (rand < 0.5) {
					int left = dpInPixels(context, (int) (Math.random() * 60));
					int top = dpInPixels(context, (int) (Math.random() * 120));
					int right = dpInPixels(context, (int) (Math.random() * 30)) + left;
					int bottom = dpInPixels(context, (int) (Math.random() * 120));
					r = new Rect(left, top, right, bottom);
				} else {
					int left = dpInPixels(context, (int) (Math.random() * 60));
					int top = dpInPixels(context, (int) (Math.random() * 120));
					int right = dpInPixels(context, (int) (Math.random() * 60));
					int bottom = dpInPixels(context, (int) (Math.random() * 60)) + top;
					r = new Rect(left, top, right, bottom);
				}

				for (Rect rr : rects) {
					if (rr.contains(r)) {
						fits = false;
					}
				}
			} while (!fits);
			rects.add(r);
			canvas.drawRect(r, paint);
		}

		for (int i = 0; i < 3; i++) {
			Rect r = rects.get(i);
			r.left = dpInPixels(context, 120) - r.left;
			r.right = dpInPixels(context, 120) - r.right;
			canvas.drawRect(r, paint);
		}

		return bitmap;
	}

	public static int dpInPixels(Context context, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources()
				.getDisplayMetrics());
	}
}
