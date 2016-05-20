package com.legendmohe.rappid.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Window;

import com.legendmohe.rappid.R;


/**
 * Created by legendmohe on 16/4/6.
 */
public class CustomViewDialogFragment extends BaseDialogFragment {
    private static final String TAG = "CustomDialogFragment";

    private static final String BUNDLE_KEY_RESID = "BUNDLE_KEY_RESID";
    private static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";

    public static CustomViewDialogFragment newInstance(@LayoutRes int layoutId, String title) {
        CustomViewDialogFragment fragment = new CustomViewDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY_RESID, layoutId);
        bundle.putString(BUNDLE_KEY_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int layoutId = getArguments().getInt(BUNDLE_KEY_RESID);
        String title = getArguments().getString(BUNDLE_KEY_TITLE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog alertDialog = builder
                .setTitle(title)
                .setView(layoutId)
                .setPositiveButton(getString(R.string.dialog_confirm), mDialogFragmentListener)
                .setNegativeButton(getString(R.string.dialog_cancel), mDialogFragmentListener)
                .create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return alertDialog;
    }
}
