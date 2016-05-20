package com.legendmohe.rappid.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.legendmohe.rappid.R;
import com.legendmohe.rappid.util.UIUtil;

/**
 * Created by legendmohe on 16/4/6.
 */
public class InputDialogFragment extends BaseDialogFragment {
    private static final String TAG = "InputDialogFragment";

    private static final String BUNDLE_KEY_HINT = "BUNDLE_KEY_HINT";
    private static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";
    private static final String BUNDLE_KEY_CONTENT = "BUNDLE_KEY_CONTENT";
    private EditText mEditText;

    public static InputDialogFragment newInstance(String title, String hint) {
        InputDialogFragment fragment = new InputDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_HINT, hint);
        bundle.putString(BUNDLE_KEY_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static InputDialogFragment newInstance(String title, String hint, String content) {
        InputDialogFragment fragment = new InputDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_HINT, hint);
        bundle.putString(BUNDLE_KEY_TITLE, title);
        bundle.putString(BUNDLE_KEY_CONTENT, content);
        fragment.setArguments(bundle);
        return fragment;
    }

    public String getInputString() {
        if (mEditText == null)
            throw new IllegalStateException("edittext is not initialized");
        return mEditText.getText().toString();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String hint = getArguments().getString(BUNDLE_KEY_HINT);
        String title = getArguments().getString(BUNDLE_KEY_TITLE);
        String content = getArguments().getString(BUNDLE_KEY_CONTENT);

        LinearLayout linearLayout = getContentLayout(hint, content);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog alertDialog = builder
                .setTitle(title)
                .setView(linearLayout)
                .setPositiveButton(getString(R.string.dialog_confirm), mDialogFragmentListener)
                .setNegativeButton(getString(R.string.dialog_cancel), mDialogFragmentListener)
                .create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return alertDialog;
    }

    @NonNull
    private LinearLayout getContentLayout(String hint, String content) {
        LinearLayout linearLayout = new LinearLayout(getActivity());

        mEditText = new EditText(this.getActivity());
        mEditText.setHint(hint);
        mEditText.setText(content);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin = UIUtil.dpToPx(getActivity(), 16);
        layoutParams.setMargins(margin, margin, margin, margin);

        linearLayout.addView(mEditText, layoutParams);
        return linearLayout;
    }
}
