package com.legendmohe.rappid.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.widget.FrameLayout;

import com.legendmohe.rappid.R;

/**
 * Created by legendmohe on 16/4/28.
 */
public class CustomBottomSheetFragment extends BaseDialogFragment {
    private static final String TAG = "CustomBottomSheet";

    protected static final String BUNDLE_KEY_RESID = "BUNDLE_KEY_RESID";

    public static CustomBottomSheetFragment newInstance(@LayoutRes int layoutId) {
        CustomBottomSheetFragment fragment = new CustomBottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY_RESID, layoutId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateBaseDialog(Bundle savedInstanceState) {
        int layoutId = getArguments().getInt(BUNDLE_KEY_RESID);
        BottomSheetDialog dialog = new BottomSheetDialog(getActivity(), getTheme());
        dialog.setContentView(layoutId);
        return dialog;
    }

    @Override
    protected void onDialogShow(DialogInterface dialog) {
        BottomSheetDialog d = (BottomSheetDialog) dialog;
        FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
