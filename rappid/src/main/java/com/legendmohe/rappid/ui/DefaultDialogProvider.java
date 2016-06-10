package com.legendmohe.rappid.ui;

/**
 * Created by legendmohe on 16/5/29.
 */
public class DefaultDialogProvider implements DialogProvider {
    @Override
    public BaseDialogFragment provideLoadingDialogFragment() {
        return LoadingDialogFragment.newInstance();
    }

    @Override
    public BaseDialogFragment provideLoadingDialogFragment(String msg) {
        return LoadingDialogFragment.newInstance(msg);
    }

    @Override
    public BaseDialogFragment provideLoadingDialogFragment(String title, String msg) {
        return LoadingDialogFragment.newInstance(title, msg);
    }

    @Override
    public BaseDialogFragment providePromptDialogFragment(String msg) {
        return PromptDialogFragment.newInstance(msg);
    }

    @Override
    public BaseDialogFragment providePromptDialogFragment(String title, String msg) {
        return PromptDialogFragment.newInstance(title, msg);
    }

    @Override
    public BaseDialogFragment providePromptDialogFragment(String title, String msg, String buttonTitle) {
        return PromptDialogFragment.newInstance(title, msg, buttonTitle);
    }

    @Override
    public BaseDialogFragment provideConfirmDialogFragment(String msg) {
        return null;
    }

    @Override
    public BaseDialogFragment provideConfirmDialogFragment(String title, String msg) {
        return null;
    }

    @Override
    public BaseDialogFragment provideConfirmDialogFragment(String title, String msg, String buttonTitle) {
        return null;
    }
}
