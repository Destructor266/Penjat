package com.penjat.jarrega_ezquerro_practica_android_m08uf2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
public class CustomCanvas extends View {
    private Paint paint;
    private String text;

    public CustomCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        text = "HangedMan";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text, 200f, 70f, paint);
    }
}
