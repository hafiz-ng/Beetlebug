package app.beetlebug.utils;

import android.view.View;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import app.beetlebug.R;

public class CustomProgressBar extends View {

    private int foregroundProgressThickness = 10;
    private int backgroundProgressThickness = 5;

    private Paint foregroundCircle = new Paint();
    private Paint backgroundCircle = new Paint();

    private float progress = 0;

    private final int mBarColorStandard = 0xff009688; //stylish blue

    private int foregroundProgressColor = Color.GREEN;
    private int backgroundProgressColor = Color.GRAY;

    private int[] foregroundBarGradientColors = new int[]{mBarColorStandard};

    private int[] backgroundBarGradientColors = new int[]{mBarColorStandard};

    private int min = 0;
    private int max = 100;

    private int startAngle = -90;

    private int height, width;

    private int centerPoint;

    private int subtractingValue;

    private RectF rectF = new RectF();

    private boolean roundedCorner;

    private int drawRadius, drawOuterRadius;

    private int DEFAULT_ANIMATION_DURATION = 2100;

    public CustomProgressBar(Context context) {
        super(context);
        init();
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, 0, 0);

        TypedArray colorsArray = context.getResources().obtainTypedArray(R.array.gradient_colors);

        backgroundProgressThickness = typedArray.getInteger(R.styleable.CustomProgressBar_background_progress_thickness, backgroundProgressThickness);
        foregroundProgressThickness = typedArray.getInteger(R.styleable.CustomProgressBar_foreground_progress_thickness, foregroundProgressThickness);
        progress = typedArray.getFloat(R.styleable.CustomProgressBar_progress, progress);
        foregroundProgressColor = typedArray.getInt(R.styleable.CustomProgressBar_foreground_progress_color, foregroundProgressColor);
        backgroundProgressColor = typedArray.getColor(R.styleable.CustomProgressBar_background_progress_color, backgroundProgressColor);
        roundedCorner = typedArray.getBoolean(R.styleable.CustomProgressBar_rounded_corner, roundedCorner);
        min = typedArray.getInt(R.styleable.CustomProgressBar_min, min);
        max = typedArray.getInt(R.styleable.CustomProgressBar_max, max);

        foregroundBarGradientColors = new int[colorsArray.length()];

        for (int i = 0; i < colorsArray.length(); i++) {

            foregroundBarGradientColors[i] = colorsArray.getColor(i, 0);
        }

        typedArray.recycle();

        init();
    }

    private void init(){

        rectF = new RectF();

        foregroundCircle.setStrokeWidth(foregroundProgressThickness);
        foregroundCircle.setAntiAlias(true);
        foregroundCircle.setStyle(Paint.Style.STROKE);

        backgroundCircle.setAntiAlias(true);
        backgroundCircle.setStyle(Paint.Style.STROKE);
        backgroundCircle.setStrokeWidth(backgroundProgressThickness);

        setRoundedCorner(roundedCorner);

        setupBarPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        centerPoint = Math.min(width, height);

        min = Math.min(width, height);

        setMeasuredDimension(min, min);

        setRadiusRect();
    }

    private void setRadiusRect() {

        centerPoint = Math.min(width, height) / 2;
        subtractingValue = (backgroundProgressThickness > foregroundProgressThickness) ? backgroundProgressThickness : foregroundProgressThickness;
        int newSeekWidth = subtractingValue / 2;

        drawRadius = Math.min((width - subtractingValue) / 2, (height - subtractingValue) / 2);
        drawOuterRadius = Math.min((width - newSeekWidth), (height - newSeekWidth));

        rectF.set(subtractingValue / 2, subtractingValue / 2, drawOuterRadius, drawOuterRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(centerPoint, centerPoint, drawRadius, backgroundCircle);
        float sweepAngle = 360 * progress / max;

        canvas.drawArc(rectF, startAngle, sweepAngle, false, foregroundCircle);
    }

    public void setProgress(float progress) {
        this.progress = progress;
        this.invalidate();
    }

    public void setForegroundBarGradientColors(@ColorInt int... foregroundBarGradientColors) {

        this.foregroundBarGradientColors = foregroundBarGradientColors;
        setupBarPaint();
    }

    public void setBackgroundBarGradientColors(@ColorInt int... backgroundBarGradientColors) {

        this.backgroundBarGradientColors = backgroundBarGradientColors;
        setupBarPaint();
    }

    public void setBackgroundProgressThickness(int thickness) {

        this.backgroundProgressThickness = thickness;
        backgroundCircle.setStrokeWidth(backgroundProgressThickness);
        requestLayout();
        invalidate();
    }

    public int getBackgroundProgressThickness() {
        return backgroundProgressThickness;
    }

    public int getForegroundProgressThickness() {
        return foregroundProgressThickness;
    }

    public void setForegroundProgressThickness(int foregroundProgressThickness) {

        this.foregroundProgressThickness = foregroundProgressThickness;
        foregroundCircle.setStrokeWidth(foregroundProgressThickness);
        requestLayout();
        invalidate();
    }

    private void setupBarPaint() {

        if (foregroundBarGradientColors.length > 1) {

            Matrix matrix = new Matrix();

            foregroundCircle.setShader(new SweepGradient(centerPoint, centerPoint, foregroundBarGradientColors, null));
            foregroundCircle.getShader().getLocalMatrix(matrix);

            matrix.postTranslate(-centerPoint, -centerPoint);
            matrix.postRotate(startAngle);
            matrix.postTranslate(centerPoint, centerPoint);

            foregroundCircle.getShader().setLocalMatrix(matrix);
            foregroundCircle.setColor(foregroundBarGradientColors[0]);

        } else if (foregroundBarGradientColors.length == 1) {

            foregroundCircle.setColor(foregroundProgressColor);
            foregroundCircle.setShader(null);

        } else {

            foregroundCircle.setColor(foregroundProgressColor);
            foregroundCircle.setShader(null);
        }

        if (backgroundBarGradientColors.length > 1) {

            Matrix matrix = new Matrix();

            backgroundCircle.setShader(new SweepGradient(centerPoint, centerPoint, backgroundBarGradientColors, null));
            backgroundCircle.getShader().getLocalMatrix(matrix);

            matrix.postTranslate(-centerPoint, -centerPoint);
            matrix.postRotate(startAngle);
            matrix.postTranslate(centerPoint, centerPoint);

            backgroundCircle.getShader().setLocalMatrix(matrix);
            backgroundCircle.setColor(backgroundBarGradientColors[0]);

        } else if (backgroundBarGradientColors.length == 1) {

            backgroundCircle.setColor(backgroundProgressColor);
            backgroundCircle.setShader(null);

        } else {

            backgroundCircle.setColor(backgroundProgressColor);
            backgroundCircle.setShader(null);
        }
    }

    public void setRoundedCorner(boolean roundedCorner) {

        if (roundedCorner) {

            foregroundCircle.setStrokeCap(Paint.Cap.ROUND);
            backgroundCircle.setStrokeCap(Paint.Cap.ROUND);

        } else {

            foregroundCircle.setStrokeCap(Paint.Cap.SQUARE);
            backgroundCircle.setStrokeCap(Paint.Cap.SQUARE);
        }
    }

    public void setProgressWithAnimation(float progress) {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", progress);
        objectAnimator.setDuration(DEFAULT_ANIMATION_DURATION);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();

    }

    public void setProgressWithAnimation(float progress, int duration) {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", progress);
        objectAnimator.setDuration(duration);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();

    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getForegroundProgressColor() {
        return foregroundProgressColor;
    }

    public void setForegroundProgressColor(int foregroundProgressColor) {

        this.foregroundProgressColor = foregroundProgressColor;
        foregroundCircle.setColor(foregroundProgressColor);
        invalidate();
    }

    public int getBackgroundProgressColor() {
        return backgroundProgressColor;
    }

    public void setBackgroundProgressColor(int backgroundProgressColor) {

        this.backgroundProgressColor = backgroundProgressColor;
        backgroundCircle.setColor(backgroundProgressColor);
        invalidate();
    }

}
