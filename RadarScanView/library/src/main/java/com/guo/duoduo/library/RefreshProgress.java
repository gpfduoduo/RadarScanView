package com.guo.duoduo.library;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import java.util.Map;


public class RefreshProgress extends View
{
    private static final String tag = "RefreshProgress";

    private RectF mRect = new RectF(0, 0, 200, 200);
    private float mStartAngle = 0.0f;
    private int mRadarRadius;
    private Paint mPaint;

    public RefreshProgress(Context context)
    {
        this(context, null);
    }

    public RefreshProgress(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public RefreshProgress(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE); //注释了就是雷达扫描图
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        mRadarRadius = Math.min(width, height);

        setMeasuredDimension(width, height);
        mRect.set(17, 17, width - 17, height - 17);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        drawAccProgressbar(mStartAngle, canvas);
    }

    private void drawAccProgressbar(float startAngle, Canvas canvas)
    {
        int[] f = {Color.parseColor("#00A8D7A7"), Color.parseColor("#ffA8D7A7")};
        float[] p = {0.0f, 1.0f};
        SweepGradient sweepGradient = new SweepGradient(mRect.centerX(), mRect.centerX(),
            f, p);
        Matrix matrix = new Matrix();
        matrix.postRotate(mStartAngle, mRect.centerX(), mRect.centerY());
        mPaint.setShader(sweepGradient);
        canvas.concat(matrix);
        canvas.drawArc(mRect, 0, 360, true, mPaint);
    }

    private void startRotate(long duration)
    {
        LinearAnimation animation = new LinearAnimation();
        animation.setDuration(duration);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setInterpolator(new LinearInterpolator());
        animation.setLinearAnimationListener(new LinearAnimation.LinearAnimationListener()
        {
            @Override
            public void applyTans(float interpolatedTime)
            {
                mStartAngle = 360 * interpolatedTime;
                invalidate();
            }
        });
        startAnimation(animation);
    }

    private void stopRotate()
    {
        clearAnimation();
    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        stopRotate();
        startRotate(1 * 1000);
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        stopRotate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        stopRotate();
        return super.onTouchEvent(event);
    }
}