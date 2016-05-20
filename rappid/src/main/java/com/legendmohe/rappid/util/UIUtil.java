package com.legendmohe.rappid.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by legendmohe on 16/3/30.
 */
public class UIUtil {
    public static ProgressDialog showLoadingIndicator(Context context, String msg, boolean cancelOnTouchOutside, DialogInterface.OnCancelListener cancelListener, DialogInterface.OnShowListener onShowListener, DialogInterface.OnDismissListener dismissListener) {
        if (!CommonUtil.currentThreadIsMainThread())
            throw new IllegalStateException("should show in UI Thread");

        ProgressDialog dialog = new ProgressDialog(context); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(msg);
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(cancelOnTouchOutside);
        dialog.setOnCancelListener(cancelListener);
        dialog.setOnDismissListener(dismissListener);
        dialog.setOnShowListener(onShowListener);
        dialog.show();

        return dialog;
    }

    public static ProgressDialog showLoadingCancelableIndicator(Context context, String msg, DialogInterface.OnCancelListener cancelListener) {
        return showLoadingIndicator(context, msg, true, cancelListener, null, null);
    }

    public static ProgressDialog showLoadingFixedIndicator(Context context, String msg) {
        return showLoadingIndicator(context, msg, false, null, null, null);
    }

    public static void hideSoftKeyboard(Window window) {
        if (window == null)
            throw new IllegalArgumentException("window is null");

        View view = window.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) window.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Determines if given points are inside view
     *
     * @param x    - x coordinate of point
     * @param y    - y coordinate of point
     * @param view - view object to compare
     * @return true if the points are within view bounds, false otherwise
     */
    public static boolean isPointInsideView(float x, float y, View view) {
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];

        //point is inside view bounds
        if ((x > viewX && x < (viewX + view.getWidth())) &&
                (y > viewY && y < (viewY + view.getHeight()))) {
            return true;
        } else {
            return false;
        }
    }

    public static void showToastShort(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static Bitmap bitmapForView(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap srcBitmap = view.getDrawingCache();
        Bitmap resultBitmap = srcBitmap.copy(Bitmap.Config.ARGB_8888, false);
        view.destroyDrawingCache();
        return resultBitmap;
    }

    //http://stackoverflow.com/questions/2801116/converting-a-view-to-bitmap-without-displaying-it-in-android
    public static Bitmap bitmapForViewWithoutDisplay(View view, boolean fillBackground) {
        Bitmap bitmap = Bitmap.createBitmap(view.getLayoutParams().width, view.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null && fillBackground)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);

        view.draw(canvas);
        return bitmap;
    }

    public static Bitmap bitmapFromImageView(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            return bitmap;
        }
        return null;
    }
}