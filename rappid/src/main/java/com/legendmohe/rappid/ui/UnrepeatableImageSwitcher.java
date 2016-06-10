package com.legendmohe.rappid.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

/**
 * Created by legendmohe on 16/6/2.
 */
public class UnrepeatableImageSwitcher extends ViewSwitcher {

    private int mLastResId = -1;
    private Uri mLastUri = null;
    private Drawable mLastDrawable = null;

    public UnrepeatableImageSwitcher(Context context) {
        super(context);
    }

    public UnrepeatableImageSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageResource(@DrawableRes int resid) {
        if (mLastResId != resid) {
            mLastResId = resid;
            ImageView image = (ImageView) this.getNextView();
            image.setImageResource(resid);
            showNext();
        }
    }

    public void setImageURI(Uri uri) {
        if (mLastUri != uri) {
            mLastUri = uri;
            ImageView image = (ImageView) this.getNextView();
            image.setImageURI(uri);
            showNext();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        if (mLastDrawable != drawable) {
            mLastDrawable = drawable;
            ImageView image = (ImageView) this.getNextView();
            image.setImageDrawable(drawable);
            showNext();
        }
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return UnrepeatableImageSwitcher.class.getName();
    }
}
