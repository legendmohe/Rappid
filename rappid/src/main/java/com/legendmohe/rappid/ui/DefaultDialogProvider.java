package com.legendmohe.rappid.ui;

import android.view.View;

/**
 * Created by legendmohe on 16/5/29.
 */
public class DefaultDialogProvider implements DialogProvider {
    @Override
    public BaseDialogFragment createLoadingDialogFragment() {
        return LoadingDialogFragment.newInstance();
    }

    @Override
    public BaseDialogFragment createLoadingDialogFragment(String msg) {
        return LoadingDialogFragment.newInstance(msg);
    }

    @Override
    public BaseDialogFragment createLoadingDialogFragment(String title, String msg) {
        return LoadingDialogFragment.newInstance(title, msg);
    }

    @Override
    public BaseDialogFragment createPromptDialogFragment(String msg) {
        return PromptDialogFragment.newInstance(msg);
    }

    @Override
    public BaseDialogFragment createPromptDialogFragment(String title, String msg) {
        return PromptDialogFragment.newInstance(title, msg);
    }

    @Override
    public BaseDialogFragment createPromptDialogFragment(String title, String msg, String buttonTitle) {
        return PromptDialogFragment.newInstance(title, msg, buttonTitle);
    }

    @Override
    public BaseDialogFragment createConfirmDialogFragment(String msg) {
        return null;
    }

    @Override
    public BaseDialogFragment createConfirmDialogFragment(String title, String msg) {
        return null;
    }

    @Override
    public BaseDialogFragment createConfirmDialogFragment(String title, String msg, String buttonTitle) {
        return null;
    }

    @Override
    public View createToastView(BaseActivity activity, String msg) {
        return null;
    }
}
