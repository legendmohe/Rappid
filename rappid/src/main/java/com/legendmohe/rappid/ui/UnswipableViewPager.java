package com.legendmohe.rappid.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by legendmohe on 16/6/7.
 */
public class UnswipableViewPager extends ViewPager {

    private boolean mSwipable = true;

    public UnswipableViewPager(Context context) {
        super(context);
    }

    public UnswipableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mSwipable) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.mSwipable) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.mSwipable = enabled;
    }
}
