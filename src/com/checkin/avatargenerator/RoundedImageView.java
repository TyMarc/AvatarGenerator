package com.checkin.avatargenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundedImageView extends ImageView {

	private static int stroke;

	public RoundedImageView(final Context context) {
		super(context);
		init();
	}

	public RoundedImageView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RoundedImageView(final Context context, final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		stroke = 2;
	}

	@Override
	protected void onDraw(final Canvas canvas) {

		final Drawable drawable = getDrawable();

		if (drawable == null) {
			return;
		}

		if (getWidth() == 0 || getHeight() == 0) {
			return;
		}
		final Bitmap b = ((BitmapDrawable) drawable).getBitmap();

		if (b != null) {
			final Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
			final Bitmap roundBitmap = getCroppedBitmap(bitmap);
			canvas.drawBitmap(roundBitmap, stroke, stroke, null);
		}
	}

	public Bitmap getCroppedBitmap(final Bitmap bitmap) {
		final int srcWidth = bitmap.getWidth();
		final int srcHeight = bitmap.getHeight();
		final int destWidth = getWidth();
		final int destHeight = getWidth();

		final float aspect = (float) srcWidth / (float) srcHeight;
		final float outAspect = (float) destWidth / (float) destHeight;

		final Rect srcRect = new Rect();
		if (aspect == outAspect) {
			srcRect.top = 0;
			srcRect.bottom = srcHeight;
			srcRect.left = 0;
			srcRect.right = srcWidth;
		} else if (aspect > outAspect) {
			// original is too large, need to crop two vertical bands
			final int maxWidth = destWidth * srcHeight / destHeight;
			final int cropWidth = (srcWidth - maxWidth) / 2;

			srcRect.top = 0;
			srcRect.bottom = srcHeight;
			srcRect.left = cropWidth;
			srcRect.right = srcWidth - cropWidth;
		} else {
			// original is too high, need to crop to horizontal bands

			final int maxHeight = destHeight * srcWidth / destWidth;
			final int cropHeight = (srcHeight - maxHeight) / 2;

			srcRect.top = cropHeight;
			srcRect.bottom = srcHeight - cropHeight;
			srcRect.left = 0;
			srcRect.right = srcWidth;
		}

		final Bitmap output = Bitmap.createBitmap(destWidth, destHeight, Config.ARGB_8888);
		final Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect destRect = new Rect(0, 0, destWidth, destHeight);
		// final Rect srcRect = new Rect(0, 0, srcWidth, srcHeight);

		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.WHITE);
		canvas.drawCircle(destWidth / 2 - (stroke), destHeight / 2 - (stroke), destWidth / 2 - (stroke * 2), paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, srcRect, destRect, paint);

		return output;
	}

}
