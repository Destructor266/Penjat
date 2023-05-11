package com.penjat.jarrega_ezquerro_practica_android_m08uf2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
public class CustomCanvas extends View {

    private AnimationDrawable animation;

    public CustomCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        animation = (AnimationDrawable) context.getResources().getDrawable(R.drawable.sprite);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        invalidate();
    }
}
