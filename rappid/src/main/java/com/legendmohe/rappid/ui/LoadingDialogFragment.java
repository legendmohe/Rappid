package com.legendmohe.rappid.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

/**
 * Created by legendmohe on 16/4/6.
 */
public class LoadingDialogFragment extends BaseDialogFragment {
    private static final String TAG = "LoadingDialogFragment";

    private static final String BUNDLE_KEY_MSG = "BUNDLE_KEY_MSG";
    private static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";

    public static LoadingDialogFragment newInstance() {
        LoadingDialogFragment fragment = new LoadingDialogFragment();
        return fragment;
    }

    public static LoadingDialogFragment newInstance(String msg) {
        LoadingDialogFragment fragment = new LoadingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_MSG, msg);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static LoadingDialogFragment newInstance(String title, String msg) {
        LoadingDialogFragment fragment = new LoadingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_MSG, msg);
        bundle.putString(BUNDLE_KEY_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateBaseDialog(Bundle savedInstanceState) {

        String msg = null;
        String title = null;
        if (getArguments() != null) {
            msg = getArguments().getString(BUNDLE_KEY_MSG);
            title = getArguments().getString(BUNDLE_KEY_TITLE);
        }

        ProgressDialog progressDialog = new ProgressDialog(getActivity(), getTheme());
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setOnCancelListener(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return progressDialog;
    }
}
