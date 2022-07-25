package com.collects.pkg.widget.customview;


import com.collect.R;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;


public final class ShapedDelegate {

    private int mSolidColor;

    private int mStrokeWidth;

    private int mStrokeColor;

    private float mCornerRadius;

    private float mCornerBottomStart;

    private float mCornerBottomEnd;

    private float mCornerTopStart;

    private float mCornerTopEnd;

    private int mGradientStartColor;

    private int mGradientCenterColor;

    private int mGradientEndColor;

    private int mGradientType;

    private int mGradientOrientation;

    private float mGradientCenterX;

    private float mGradientCenterY;

    private float mGradientRadius;

    private boolean mGradientUseLevel;

    private Drawable mAndroidDrawable;

    private View mTarget;

    public static ShapedDelegate obtain(View target, AttributeSet attrs, int defStyleAttr,
                                        int defStyleRes) {
        return new ShapedDelegate(target, attrs, defStyleAttr, defStyleRes);
    }

    private ShapedDelegate(View view, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = null;
        try {
            a = view.getContext().obtainStyledAttributes(attrs, R.styleable.ShapeView,
                    defStyleAttr, defStyleRes);
            mCornerRadius = a
                    .getDimension(R.styleable.ShapeView_corner_radius, 0f);
            mSolidColor = a.getColor(R.styleable.ShapeView_solid_color, Color.TRANSPARENT);
            mStrokeWidth = a.getDimensionPixelSize(R.styleable.ShapeView_stroke_width, 0);
            mStrokeColor = a.getColor(R.styleable.ShapeView_stroke_color, Color.TRANSPARENT);
            mCornerBottomStart = a.getDimension(R.styleable.ShapeView_corner_bottom_start,
                    mCornerRadius);
            mCornerBottomEnd = a
                    .getDimension(R.styleable.ShapeView_corner_bottom_end, mCornerRadius);
            mCornerTopStart = a
                    .getDimension(R.styleable.ShapeView_corner_top_start, mCornerRadius);
            mCornerTopEnd = a
                    .getDimension(R.styleable.ShapeView_corner_top_end, mCornerRadius);
            mGradientStartColor = a
                    .getColor(R.styleable.ShapeView_gradient_start_color, Color.TRANSPARENT);
            mGradientCenterColor = a
                    .getColor(R.styleable.ShapeView_gradient_center_color, Color.TRANSPARENT);
            mGradientEndColor = a
                    .getColor(R.styleable.ShapeView_gradient_end_color, Color.TRANSPARENT);
            mGradientType = a.getInteger(R.styleable.ShapeView_gradient_type, -1);
            mGradientOrientation = a.getInteger(R.styleable.ShapeView_gradient_orientation, -1);
            mGradientCenterX = a.getDimension(R.styleable.ShapeView_gradient_center_x, -1);
            mGradientCenterY = a.getDimension(R.styleable.ShapeView_gradient_center_y, -1);
            mGradientRadius = a.getDimension(R.styleable.ShapeView_gradient_radius, -1);
            mGradientUseLevel = a.getBoolean(R.styleable.ShapeView_gradient_use_level, false);
            mAndroidDrawable = a.getDrawable(R.styleable.ShapeView_android_background);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
        mTarget = view;
        if (mAndroidDrawable == null || mAndroidDrawable instanceof ColorDrawable) {
            setShapeBackground();
        }
    }

    private void setShapeBackground() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        if (mSolidColor != Color.TRANSPARENT) {
            gradientDrawable.setColor(mSolidColor);
        }
        if (mCornerRadius > 0) {
            gradientDrawable.setCornerRadius(mCornerRadius);
        } else if (mCornerTopStart != 0 || mCornerTopEnd != 0 || mCornerBottomEnd != 0
                || mCornerBottomStart != 0) {
            gradientDrawable.setCornerRadii(
                    new float[]{mCornerTopStart, mCornerTopStart, mCornerTopEnd, mCornerTopEnd,
                            mCornerBottomEnd, mCornerBottomEnd, mCornerBottomStart,
                            mCornerBottomStart});
        }
        if (mStrokeWidth > 0 && mStrokeColor != Color.TRANSPARENT) {
            gradientDrawable.setStroke(mStrokeWidth, mStrokeColor);
        }
        gradientDrawable.setUseLevel(mGradientUseLevel);
        if (mGradientRadius != -1) {
            gradientDrawable.setGradientRadius(mGradientRadius);
        }

        if (mGradientCenterX != -1 && mGradientCenterY != -1) {
            gradientDrawable.setGradientCenter(mGradientCenterX, mGradientCenterY);
        }
        if (mGradientOrientation != -1) {
            gradientDrawable.setOrientation(getGradientOrientation(mGradientOrientation));
        }
        if (mGradientType != -1) {
            gradientDrawable.setGradientType(mGradientType);
        }
        if (mGradientStartColor != Color.TRANSPARENT && mGradientEndColor != Color.TRANSPARENT) {
            if (mGradientCenterColor != Color.TRANSPARENT) {
                gradientDrawable.setColors(
                        new int[]{mGradientStartColor, mGradientCenterColor, mGradientEndColor});
            } else {
                gradientDrawable.setColors(
                        new int[]{mGradientStartColor, mGradientEndColor});
            }
        }

//        StateListDrawable drawable = new StateListDrawable();
//        drawable.addState(new int[]{}, gradientDrawable);  //normal state
//        drawable.addState(new int[]{-android.R.attr.state_enabled},
//                gradientDrawable);   //enable false state
        mTarget.setBackground(gradientDrawable);
    }

    private GradientDrawable.Orientation getGradientOrientation(int orientation) {
        GradientDrawable.Orientation mOrientation;
        switch (orientation) {
            case 0:
                mOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
            case 1:
                mOrientation = GradientDrawable.Orientation.TR_BL;
                break;
            case 2:
                mOrientation = GradientDrawable.Orientation.RIGHT_LEFT;
                break;
            case 3:
                mOrientation = GradientDrawable.Orientation.BR_TL;
                break;
            case 4:
                mOrientation = GradientDrawable.Orientation.BOTTOM_TOP;
                break;
            case 5:
                mOrientation = GradientDrawable.Orientation.BL_TR;
                break;
            case 6:
                mOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 7:
                mOrientation = GradientDrawable.Orientation.TL_BR;
                break;
            default:
                mOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
        }
        return mOrientation;
    }

    public void setColors(@ColorInt int[] colors) {
        if (colors.length < 2) {
            throw new RuntimeException("colors length < 2");
        } else {
            mGradientStartColor = colors[0];
            if (colors.length == 2) {
                mGradientEndColor = colors[1];
            } else {
                mGradientCenterColor = colors[1];
                mGradientEndColor = colors[2];
            }
        }
        mSolidColor = 0;
        if (mTarget.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) mTarget.getBackground()).setColor(Color.TRANSPARENT);
            ((GradientDrawable) mTarget.getBackground()).setColors(colors);
        } else {
            setShapeBackground();
        }
    }

    public void setColor(@ColorInt int argb) {
        mSolidColor = argb;
        mGradientStartColor = 0;
        mGradientCenterColor = 0;
        mGradientEndColor = 0;
        if (mTarget.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) mTarget.getBackground()).setColor(mSolidColor);
        } else {
            setShapeBackground();
        }
    }

    public void setStrokeColor(@ColorInt int argb) {
        mStrokeColor = argb;
        if (mTarget.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) mTarget.getBackground()).setStroke(mStrokeWidth, argb);
        } else {
            setShapeBackground();
        }
    }

    public void setStroke(int strokeWidth, @ColorInt int argb) {
        mStrokeColor = argb;
        mStrokeWidth = strokeWidth;
        if (mTarget.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) mTarget.getBackground()).setStroke(mStrokeWidth, mStrokeColor);
        } else {
            setShapeBackground();
        }
    }

    public void setCornerRadii(int... cornerRadii) {
        mCornerTopStart = cornerRadii.length > 0 ? cornerRadii[0] : mCornerTopStart;
        mCornerTopEnd = cornerRadii.length > 1 ? cornerRadii[1] : mCornerTopEnd;
        mCornerBottomEnd = cornerRadii.length > 2 ? cornerRadii[2] : mCornerBottomEnd;
        mCornerBottomStart = cornerRadii.length > 3 ? cornerRadii[3] : mCornerBottomStart;
        if (mTarget.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) mTarget.getBackground()).setCornerRadii(
                    new float[]{mCornerTopStart, mCornerTopStart, mCornerTopEnd, mCornerTopEnd,
                            mCornerBottomEnd, mCornerBottomEnd, mCornerBottomStart,
                            mCornerBottomStart});
        } else {
            setShapeBackground();
        }
    }

    public void setCornerRadiis(int cornerRadii) {
        setCornerRadii(cornerRadii, cornerRadii, cornerRadii, cornerRadii);
    }

    public void setGradientOrientation(GradientDrawable.Orientation orientation) {
        switch (orientation) {
            case BL_TR:
                mGradientOrientation = 5;
                break;
            case BR_TL:
                mGradientOrientation = 3;
                break;
            case TL_BR:
                mGradientOrientation = 7;
                break;
            case TR_BL:
                mGradientOrientation = 1;
                break;
            case BOTTOM_TOP:
                mGradientOrientation = 4;
                break;
            case LEFT_RIGHT:
                mGradientOrientation = 6;
                break;
            case RIGHT_LEFT:
                mGradientOrientation = 2;
                break;
            case TOP_BOTTOM:
                mGradientOrientation = 0;
                break;
            default:
                mGradientOrientation = 0;
                break;
        }
        if (mTarget.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) mTarget.getBackground()).setOrientation(orientation);
        } else {
            setShapeBackground();
        }
    }
}
