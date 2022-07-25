package com.collects.pkg.widget.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;

public class ShapedFrameLayout extends FrameLayout {

    private ShapedDelegate mDelegate;

    public ShapedFrameLayout(Context context) {
        this(context, null);
    }

    public ShapedFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapedFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ShapedFrameLayout(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mDelegate = ShapedDelegate.obtain(this, attrs, defStyleAttr, defStyleRes);
    }

    public void setColor(@ColorInt int argb) {
        mDelegate.setColor(argb);
    }

    public void setColors(@ColorInt int[] colors) {
        mDelegate.setColors(colors);
    }

    public void setStroke(int strokeWidth, @ColorInt int argb) {
        mDelegate.setStroke(strokeWidth, argb);
    }
}
