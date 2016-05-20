package com.legendmohe.rappid.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by legendmohe on 16/4/24.
 */
public class SwipeRecyclerView extends RecyclerView {
    private static final String TAG = "SwipeRecyclerView";

    private float mInitX, mInitY;
    private boolean mIntercrpt;

    public SwipeRecyclerView(Context context) {
        super(context);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (MotionEventCompat.getActionMasked(event)) {
            case MotionEvent.ACTION_DOWN:
                mInitX = event.getX();
                mInitY = event.getY();
                mIntercrpt = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mIntercrpt) {
                    float dx = event.getX() - mInitX + 1;
                    float dy = event.getY() - mInitY;
                    float angle = Math.abs(dy / dx);
                    float degrees = (float) Math.toDegrees(Math.atan(angle));
                    if (degrees <= SwipeViewBehavior.SCROLL_SENSITVITY) {
                        mIntercrpt = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIntercrpt = false;
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mIntercrpt) {
            super.onInterceptTouchEvent(event);
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }
}
