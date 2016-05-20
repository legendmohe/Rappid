package com.legendmohe.rappid.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.legendmohe.rappid.util.UIUtil;


/**
 * Created by legendmohe on 16/5/9.
 */
public class BottomSheetView extends LinearLayout {

    private OnBottomSheetItemClickListener mListener;

    public BottomSheetView(Context context, @MenuRes @NonNull int resId, OnBottomSheetItemClickListener listener) {
        super(context);

        setOnBottomSheetItemClickListener(listener);
        initParams();
        initMenus(resId);
    }
//
//    public BottomSheetView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public BottomSheetView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public BottomSheetView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//
//    }

    public void setOnBottomSheetItemClickListener(OnBottomSheetItemClickListener listener) {
        mListener = listener;
    }

    private void initParams() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        setLayoutParams(layoutParams);
        setOrientation(VERTICAL);
    }

    private void initMenus(@MenuRes int resId) {
        MenuInflater menuInflater = new MenuInflater(getContext());
        Menu menu = new MenuBuilder(getContext());
        menuInflater.inflate(resId, menu);
        inflaterItems(0, menu);
    }

    private int inflaterItems(int i, Menu menu) {
        for (; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.getGroupId() != 0) {
                if (i != 0) {
                    addView(createSepView());
                }
                addView(createItemViewFromMenuItem(item));
                i = inflaterItems(i + 1, menu);
                if (i < menu.size()) {
                    addView(createSepView());
                }
            } else {
                if (i != 0) {
                    addView(createLineView());
                }
                addView(createItemViewFromMenuItem(item));
            }
        }
        return i;
    }

    private View createSepView() {
        View view = new View(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                UIUtil.dpToPx(getContext(), 8)
        );

        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(Color.parseColor("#c1c1c1"));
        return view;
    }

    private View createLineView() {
        View view = new View(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                UIUtil.dpToPx(getContext(), 1)
        );

        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(Color.parseColor("#d4d4d4"));
        return view;
    }

    private View createItemViewFromMenuItem(MenuItem item) {
        final int finalId = item.getItemId();
        TextView textView = new TextView(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        textView.setLayoutParams(layoutParams);
        textView.setText(item.getTitle());
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textView.setPadding(0, 40, 0, 40);
        if (mListener != null) {
            textView.setTextColor(mListener.colorIntForItem(finalId));
        } else {
            textView.setTextColor(Color.BLACK);
        }
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OnItemButtonClicked(finalId);
            }
        });
        return textView;
    }

    private void OnItemButtonClicked(int id) {
        if (mListener != null) {
            mListener.onBottomSheetItemClicked(id);
        }
    }

    public interface OnBottomSheetItemClickListener {
        void onBottomSheetItemClicked(int which);

        @ColorInt
        int colorIntForItem(int which);
    }
}
