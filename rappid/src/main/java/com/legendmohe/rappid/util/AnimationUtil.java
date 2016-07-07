package com.legendmohe.rappid.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;

/**
 * Created by legendmohe on 16/5/24.
 */
public class AnimationUtil {

    public static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
    public static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();

    public static void applyGoneWithAlphaAnimation(final View view) {
        view.animate().cancel();
        view.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
    }

    public static void applyVisibleWithAlphaAnimation(final View view) {
        view.animate().cancel();
        view.animate().alpha(1.0f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    public static int lerp(int startValue, int endValue, float fraction) {
        return startValue + Math.round(fraction * (float) (endValue - startValue));
    }

    public static long lerp(long startValue, long endValue, float fraction) {
        return startValue + Math.round(fraction * (float) (endValue - startValue));
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void enableAnimationInImageView(ImageView imageView, boolean enable) {
        if (imageView.getDrawable() instanceof AnimationDrawable) {
            if (enable)
                ((AnimationDrawable) imageView.getDrawable()).start();
            else
                ((AnimationDrawable) imageView.getDrawable()).stop();
        }
    }

    public static ValueAnimator animateColorChange(
            int fromColor,
            int toColor,
            int duration,
            final AnimateColorChangeListener listener) {
        final float[] from = new float[3];
        final float[] to = new float[3];

        Color.colorToHSV(fromColor, from);   // from color
        Color.colorToHSV(toColor, to);     // to color

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);   // animate from 0 to 1
        anim.setDuration(duration);

        final float[] hsv = new float[3];                  // transition color
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (listener != null) {
                    // Transition along each axis of HSV (hue, saturation, value)
                    hsv[0] = from[0] + (to[0] - from[0]) * animation.getAnimatedFraction();
                    hsv[1] = from[1] + (to[1] - from[1]) * animation.getAnimatedFraction();
                    hsv[2] = from[2] + (to[2] - from[2]) * animation.getAnimatedFraction();
                    listener.onColorChange(Color.HSVToColor(hsv));
                }
            }
        });

        return anim;
    }

    public interface AnimateColorChangeListener {
        void onColorChange(int color);
    }
}
