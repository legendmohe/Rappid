package com.legendmohe.rappidDemo;

import com.legendmohe.rappid.mvp.MvpBaseActivityPresenter;

/**
 * Created by legendmohe on 16/8/15.
 */
public class MainPresenter extends MvpBaseActivityPresenter<MainView> {
    public MainPresenter(MainView view) {
        super(view);
    }

    public void doSomething() {
        getView().showToast();
    }

    @Override
    public void onActivityCreate() {
        getView().showActivityCreate();
    }
}
