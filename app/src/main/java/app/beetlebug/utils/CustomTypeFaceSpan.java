package app.beetlebug.utils;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

import androidx.annotation.ColorInt;

public class CustomTypeFaceSpan extends TypefaceSpan {

    private final Typeface newType;
    private final int mColor;

    public CustomTypeFaceSpan(String family, Typeface type, @ColorInt int color) {

        super(family);
        newType = type;
        mColor = color;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(mColor);
        applyCustomTypeFace(ds, newType);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeFace(paint, newType);
    }

    @Override
    public int getSpanTypeId() {
        return super.getSpanTypeId();
    }

    @ColorInt
    public int getForegroundColor() {
        return mColor;
    }

    private static void applyCustomTypeFace(Paint paint, Typeface tf) {
        int oldStyle;
        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(tf);
    }

}

