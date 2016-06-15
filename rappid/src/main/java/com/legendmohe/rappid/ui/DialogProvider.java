package com.legendmohe.rappid.ui;

import android.view.View;

/**
 * Created by legendmohe on 16/5/29.
 */
public interface DialogProvider {
    BaseDialogFragment createLoadingDialogFragment();

    BaseDialogFragment createLoadingDialogFragment(String msg);

    BaseDialogFragment createLoadingDialogFragment(String title, String msg);

    BaseDialogFragment createPromptDialogFragment(String msg);

    BaseDialogFragment createPromptDialogFragment(String title, String msg);

    BaseDialogFragment createPromptDialogFragment(String title, String msg, String buttonTitle);

    BaseDialogFragment createConfirmDialogFragment(String msg);

    BaseDialogFragment createConfirmDialogFragment(String title, String msg);

    BaseDialogFragment createConfirmDialogFragment(String title, String msg, String buttonTitle);

    View createToastView(BaseActivity activity, String msg);
}
