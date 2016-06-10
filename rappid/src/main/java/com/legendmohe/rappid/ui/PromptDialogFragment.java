package com.legendmohe.rappid.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Window;

import com.legendmohe.rappid.R;


/**
 * Created by legendmohe on 16/4/6.
 */
public class PromptDialogFragment extends BaseDialogFragment {
    private static final String TAG = "PromptDialogFragment";

    private static final String BUNDLE_KEY_MSG = "BUNDLE_KEY_MSG";
    private static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";
    private static final String BUNDLE_KEY_BUTTON_TITLE = "BUNDLE_KEY_BUTTON_TITLE";

    public static PromptDialogFragment newInstance(String msg) {
        PromptDialogFragment fragment = new PromptDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_MSG, msg);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static PromptDialogFragment newInstance(String title, String msg) {
        PromptDialogFragment fragment = new PromptDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_MSG, msg);
        bundle.putString(BUNDLE_KEY_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static PromptDialogFragment newInstance(String title, String msg, String buttonTitle) {
        PromptDialogFragment fragment = new PromptDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_MSG, msg);
        bundle.putString(BUNDLE_KEY_TITLE, title);
        bundle.putString(BUNDLE_KEY_BUTTON_TITLE, buttonTitle);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateBaseDialog(Bundle savedInstanceState) {

        String msg = getArguments().getString(BUNDLE_KEY_MSG);
        String title = getArguments().getString(BUNDLE_KEY_TITLE);
        String buttonTitle = getArguments().getString(BUNDLE_KEY_BUTTON_TITLE);
        if (TextUtils.isEmpty(buttonTitle)) {
            buttonTitle = getString(R.string.dialog_confirm);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog alertDialog = builder
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(buttonTitle, mDialogFragmentListener)
                .setCancelable(false)
                .create();

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return alertDialog;
    }
}
