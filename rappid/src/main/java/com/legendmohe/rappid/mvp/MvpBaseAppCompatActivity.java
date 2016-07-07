package com.legendmohe.rappid.mvp;

import android.os.Bundle;

import com.legendmohe.rappid.ui.BaseActivity;


/**
 * Created by legendmohe on 16/4/1.
 */
public abstract class MvpBaseAppCompatActivity<T extends MvpBaseActivityPresenter<? extends MvpBaseView>> extends BaseActivity {

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = this.createPresenterInstance();
        mPresenter.onActivityCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onActivityDestory();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mPresenter != null) {
            mPresenter.onActivityStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mPresenter != null) {
            mPresenter.onActivityStop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mPresenter != null) {
            mPresenter.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mPresenter != null) {
            mPresenter.onActivityPause();
        }
    }

    protected abstract T createPresenterInstance();
}
