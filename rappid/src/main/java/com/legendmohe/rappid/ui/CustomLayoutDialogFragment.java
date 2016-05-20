package com.legendmohe.rappid.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by legendmohe on 16/4/6.
 */
public class CustomLayoutDialogFragment extends BaseDialogFragment {
    private static final String TAG = "CustomDialogFragment";

    private static final String BUNDLE_KEY_RESID = "BUNDLE_KEY_RESID";

    public static CustomLayoutDialogFragment newInstance(@LayoutRes int layoutId) {
        CustomLayoutDialogFragment fragment = new CustomLayoutDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY_RESID, layoutId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getArguments().getInt(BUNDLE_KEY_RESID);
        View view = inflater.inflate(layoutId, container, false);
        return view;
    }
}
