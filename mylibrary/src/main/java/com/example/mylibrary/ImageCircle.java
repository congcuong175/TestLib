package com.example.mylibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class ImageCircle extends AppCompatImageView {

    private Paint paint;
    private BitmapShader shader;

    public ImageCircle(@NonNull Context context) {
        super(context);
        init();
    }

    public ImageCircle(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageCircle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        int minEdge = Math.min(getWidth(), getHeight());
        if (shader == null && minEdge > 0) {
            Bitmap bitmap = getBitmapFromDrawable();
            if (bitmap != null) {
                shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
        }

        if (shader != null) {
            int radius = minEdge / 2;
            paint.setShader(shader);
            canvas.drawCircle(minEdge / 2, minEdge / 2, radius, paint);
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getBitmapFromDrawable() {
        if (getDrawable() == null) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        getDrawable().setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        getDrawable().draw(canvas);
        return bitmap;
    }
}
