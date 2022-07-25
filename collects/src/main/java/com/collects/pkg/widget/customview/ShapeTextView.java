package com.collects.pkg.widget.customview;

import com.collect.R;
import com.collects.pkg.widget.span.CenteredImageSpan;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatTextView;





public class ShapeTextView extends AppCompatTextView {

    private ShapedDelegate mDelegate;

    private int mIconMargin;

    private int mIconResId;

    public ShapeTextView(Context context) {
        this(context, null);
    }

    public ShapeTextView(Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDelegate = ShapedDelegate.obtain(this, attrs, defStyleAttr, 0);
        TypedArray a = null;
        try {
            a = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);
            mIconMargin = a
                    .getDimensionPixelSize(R.styleable.ShapeTextView_icon_margin, 0);
            mIconResId = a.getResourceId(R.styleable.ShapeTextView_button_icon, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (a != null) {
                a.recycle();
            }
        }

        if (mIconMargin < 0) {
            mIconMargin = 0;
        }

        if (mIconResId > 0) {
            setButtonIcon(getText());
        }
    }

    private void setButtonIcon(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            CenteredImageSpan imageSpan = new CenteredImageSpan(getContext(), mIconResId);
            SpannableStringBuilder builder = new SpannableStringBuilder(" ");
            builder.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (mIconMargin > 0) {
                Bitmap placeholder = Bitmap.createBitmap(mIconMargin, 1, Bitmap.Config.ARGB_8888);
                ImageSpan span = new ImageSpan(getContext(), placeholder,
                        DynamicDrawableSpan.ALIGN_BASELINE);
                int start = builder.length();
                builder.append(" ");
                builder.setSpan(span, start, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            builder.append(text);
            setText(builder);
        }
    }

    public void setButtonText(String text) {
        setButtonIcon(text);
    }

    public void setButtonText(@StringRes int stringRes) {
        setButtonText(getResources().getString(stringRes));
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

    public void setColors(@ColorInt int[] colors) {
        mDelegate.setColors(colors);
    }

    public void setGradientOrientation(GradientDrawable.Orientation orientation) {
        mDelegate.setGradientOrientation(orientation);
    }

    public void setCornerRadii(int... cornerRadii) {
        mDelegate.setCornerRadii(cornerRadii);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }
}
