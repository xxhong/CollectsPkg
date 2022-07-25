package com.collects.pkg.widget.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;


public class ShapeRelativeLayout extends RelativeLayout {

    private ShapedDelegate mDelegate;

    public ShapeRelativeLayout(Context context) {
        this(context, null);
    }

    public ShapeRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ShapeRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr,
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
}
