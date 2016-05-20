package com.legendmohe.rappid.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Window;

import com.legendmohe.rappid.R;


/**
 * Created by legendmohe on 16/4/6.
 */
public class PromptDialogFragment extends BaseDialogFragment {
    private static final String TAG = "PromptDialogFragment";

    private static final String BUNDLE_KEY_MSG = "BUNDLE_KEY_MSG";
    private static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";

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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String msg = getArguments().getString(BUNDLE_KEY_MSG);
        String title = getArguments().getString(BUNDLE_KEY_TITLE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog alertDialog = builder
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(getString(R.string.dialog_confirm), mDialogFragmentListener)
                .setCancelable(false)
                .create();

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return alertDialog;
    }
}
