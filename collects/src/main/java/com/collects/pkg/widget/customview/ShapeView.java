package com.collects.pkg.widget.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;


public class ShapeView extends View {

    private ShapedDelegate mDelegate;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDelegate = ShapedDelegate.obtain(this, attrs, defStyleAttr, 0);
    }

    public void setColor(@ColorInt int argb) {
        mDelegate.setColor(argb);
    }

    public void setStrokeColor(@ColorInt int argb) {
        mDelegate.setStrokeColor(argb);
    }

    public void setStroke(int strokeWidth, @ColorInt int argb) {
        mDelegate.setStroke(strokeWidth, argb);
    }
}
