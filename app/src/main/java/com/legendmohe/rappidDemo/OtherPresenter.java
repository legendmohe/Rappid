package com.legendmohe.rappidDemo;

import com.legendmohe.rappid.mvp.MvpBaseActivityPresenter;

/**
 * Created by legendmohe on 16/8/15.
 */
public class OtherPresenter extends MvpBaseActivityPresenter<OtherView> {
    public OtherPresenter(OtherView view) {
        super(view);
    }

    public void showToast() {
        getView().showOtherToast();
    }
}
