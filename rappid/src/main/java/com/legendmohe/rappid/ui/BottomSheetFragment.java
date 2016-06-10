package com.legendmohe.rappid.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.widget.FrameLayout;

import com.legendmohe.rappid.R;

/**
 * Created by legendmohe on 16/4/28.
 */
public class BottomSheetFragment extends BaseDialogFragment {
    private static final String TAG = "CustomBottomSheet";

    private static final String BUNDLE_KEY_MENU_RESID = "BUNDLE_KEY_MENU_RESID";

    BottomSheetView.OnBottomSheetItemClickListener mListener;

    public static BottomSheetFragment newMenuInstance(@MenuRes int menuResId) {
        BottomSheetFragment fragment = new BottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY_MENU_RESID, menuResId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public BottomSheetFragment setOnBottomSheetItemClickListener(BottomSheetView.OnBottomSheetItemClickListener listener) {
        mListener = listener;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateBaseDialog(Bundle savedInstanceState) {
        int menuResId = getArguments().getInt(BUNDLE_KEY_MENU_RESID);
        BottomSheetDialog dialog = new BottomSheetDialog(getActivity(), getTheme());
        dialog.setContentView(getMenuView(menuResId));
        return dialog;
    }

    @Override
    protected void onDialogShow(DialogInterface dialog) {
        BottomSheetDialog d = (BottomSheetDialog) dialog;
        FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @NonNull
    private BottomSheetView getMenuView(int menuResId) {
        BottomSheetView bottomSheetView = new BottomSheetView(
                getContext(),
                menuResId,
                new BottomSheetView.OnBottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClicked(int which) {
                        if (mListener != null) {
                            mListener.onBottomSheetItemClicked(which);
                        }
                        dismiss();
                    }

                    @Override
                    public int colorIntForItem(int which) {
                        if (mListener != null) {
                            return mListener.colorIntForItem(which);
                        } else {
                            return Color.BLACK;
                        }
                    }

                    @Override
                    public String titleForItem(int which) {
                        if (mListener != null) {
                            return mListener.titleForItem(which);
                        } else {
                            return null;
                        }
                    }
                });
        return bottomSheetView;
    }
}
