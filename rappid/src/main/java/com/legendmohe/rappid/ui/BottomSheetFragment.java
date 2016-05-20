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

    public void setOnBottomSheetItemClickListener(BottomSheetView.OnBottomSheetItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int menuResId = getArguments().getInt(BUNDLE_KEY_MENU_RESID);
        BottomSheetDialog dialog = new BottomSheetDialog(getActivity(), getTheme());
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;

                FrameLayout bottomSheet = (FrameLayout) d.findViewById(android.support.design.R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        dialog.setContentView(getMenuView(menuResId));
        return dialog;
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
                });
        return bottomSheetView;
    }
}
