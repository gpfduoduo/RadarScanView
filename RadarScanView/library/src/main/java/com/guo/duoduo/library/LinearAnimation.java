package com.guo.duoduo.library;


import android.view.animation.Animation;
import android.view.animation.Transformation;


/**
 * Created by 郭攀峰 on 2015/11/22.
 */
public class LinearAnimation extends Animation
{
    private LinearAnimationListener mListener = null;

    public interface LinearAnimationListener
    {
        void applyTans(float interpolatedTime);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t)
    {
        super.applyTransformation(interpolatedTime, t);
        if (mListener != null)
            mListener.applyTans(interpolatedTime);
    }

    public void setLinearAnimationListener(LinearAnimationListener listener)
    {
        mListener = listener;
    }
}
