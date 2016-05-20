package com.legendmohe.rappid.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by legendmohe on 16/4/6.
 */
public abstract class BaseDialogFragment extends DialogFragment {
    DialogFragmentListener mDialogFragmentListener;

    public BaseDialogFragment show(FragmentManager manager) {
        return show(manager, true);
    }

    public BaseDialogFragment show(FragmentManager manager, boolean dismissPrev) {
        return show(manager, BaseDialogFragment.class.getSimpleName(), dismissPrev);
    }

    public BaseDialogFragment show(FragmentManager manager, String tag, boolean dismissPrev) {

        if (dismissPrev && manager != null && manager.getFragments() != null && manager.getFragments().size() != 0) {
            Fragment prev = null;
            for (Fragment current : manager.getFragments()) {
                if (current == null)
                    continue;
                if (current == this) {
                    break;
                } else if (current instanceof DialogFragment) {
                    prev = current;
                }
            }
            if (prev != null && prev != this) {
                DialogFragment df = (DialogFragment) prev;
                df.dismiss();
            }
        }

        super.show(manager, tag);
        return this;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

        if (mDialogFragmentListener != null) {
            mDialogFragmentListener.onDialogCancel(dialog);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = onCreateBaseView(inflater, container, savedInstanceState);
        if (view != null) {
            getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    if (mDialogFragmentListener != null) {
                        mDialogFragmentListener.onDialogShow(dialog);
                    }
                }
            });
        }
        return view;
    }

    public View onCreateBaseView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = onCreateBaseDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                if (mDialogFragmentListener != null) {
                    mDialogFragmentListener.onDialogShow(dialog);
                }
            }
        });
        return dialog;
    }

    public Dialog onCreateBaseDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (mDialogFragmentListener != null) {
            mDialogFragmentListener.onDialogDismiss(dialog);
        }
    }

    public BaseDialogFragment setDialogFragmentListener(DialogFragmentListener dialogFragmentListener) {
        mDialogFragmentListener = dialogFragmentListener;
        return this;
    }

    public static class DialogFragmentListener implements DialogInterface.OnClickListener {
        public void onDialogCancel(DialogInterface dialog) {

        }

        public void onDialogDismiss(DialogInterface dialog) {

        }

        public void onDialogConfirm(DialogInterface dialog) {

        }

        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                onDialogConfirm(dialog);
            }
        }

        public void onDialogShow(DialogInterface dialog) {

        }
    }
}
