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

	public static Bitmap generate(Context context, int widthDp, int heightDp) {
		Canvas canvas = new Canvas();
		Bitmap bitmap = Bitmap.createBitmap(dpInPixels(context, widthDp), dpInPixels(context, heightDp),
				Config.ARGB_4444);

		canvas.setBitmap(bitmap);

		int color = Color.rgb((int) (Math.random() * 254), (int) (Math.random() * 254), (int) (Math.random() * 254));
		Paint paint = new Paint();
		paint.setColor(color);

		ArrayList<Rect> rects = new ArrayList<Rect>();

		for (int i = 0; i < 3; i++) {
			boolean fits = true;
			Rect currentRect = null;
			do {
				fits = true;
				double rand = Math.random();
				if (rand < 0.5) {
					int left = dpInPixels(context, (int) (Math.random() * (widthDp / 2)));
					int top = dpInPixels(context, (int) (Math.random() * heightDp));
					int right = dpInPixels(context, widthDp / 6) + left;
					int bottom = dpInPixels(context, (int) (Math.random() * heightDp));
					currentRect = new Rect(left, top, right, bottom);
				} else {
					int left = dpInPixels(context, (int) (Math.random() * (widthDp / 2)));
					int top = dpInPixels(context, (int) (Math.random() * heightDp));
					int right = dpInPixels(context, (int) (Math.random() * (widthDp / 2)));
					int bottom = dpInPixels(context, heightDp / 6) + top;
					currentRect = new Rect(left, top, right, bottom);
				}

				for (Rect existingRect : rects) {
					if (existingRect.contains(currentRect)) {
						fits = false;
					}
				}
			} while (!fits);
			rects.add(currentRect);
			canvas.drawRect(currentRect, paint);
		}

		for (Rect r : rects) {
			r.left = dpInPixels(context, widthDp) - r.left;
			r.right = dpInPixels(context, widthDp) - r.right;
			canvas.drawRect(r, paint);
		}

		return bitmap;
	}

	private static int dpInPixels(Context context, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources()
				.getDisplayMetrics());
	}
}
