package com.legendmohe.rappid.ui;

/**
 * Created by legendmohe on 16/5/29.
 */
public interface DialogProvider {
    BaseDialogFragment provideLoadingDialogFragment();

    BaseDialogFragment provideLoadingDialogFragment(String msg);

    BaseDialogFragment provideLoadingDialogFragment(String title, String msg);

    BaseDialogFragment providePromptDialogFragment(String msg);

    BaseDialogFragment providePromptDialogFragment(String title, String msg);

    BaseDialogFragment providePromptDialogFragment(String title, String msg, String buttonTitle);

    BaseDialogFragment provideConfirmDialogFragment(String msg);

    BaseDialogFragment provideConfirmDialogFragment(String title, String msg);

    BaseDialogFragment provideConfirmDialogFragment(String title, String msg, String buttonTitle);
}
