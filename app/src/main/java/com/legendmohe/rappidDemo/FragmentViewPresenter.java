package com.legendmohe.rappidDemo;

import com.legendmohe.rappid.mvp.MvpBaseFragmentPresenter;

/**
 * Created by legendmohe on 16/8/15.
 */
public class FragmentViewPresenter extends MvpBaseFragmentPresenter<FragmentView> {
    public FragmentViewPresenter(FragmentView view) {
        super(view);
    }

    public void showToast() {
        getView().showToast();
    }

    @Override
    public void onFragmentResume() {
        showToast();
    }
}
