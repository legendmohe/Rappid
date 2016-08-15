package com.legendmohe.rappid.mvp;

import android.os.Bundle;

import com.legendmohe.rappid.ui.BaseActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * Created by legendmohe on 16/4/1.
 */
public abstract class MvpBaseAppCompatActivity extends BaseActivity {

    protected PresenterHolder mPresenterHolder = new PresenterHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenters();
        mPresenterHolder.onActivityCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenterHolder.onActivityDestory();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenterHolder.onActivityStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenterHolder.onActivityStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenterHolder.onActivityResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenterHolder.onActivityPause();
    }

    private void initPresenters() {
        Class<?> clazz = this.getClass();
        Set<Field> presenterFields = MvpInjector.getPresenterFields(clazz, MvpPresenter.class, MvpBaseActivityPresenter.class);
        Collection<Field> injectTarget = new ArrayList<>();
        for (Field field :
                presenterFields) {
            if (shouldInjectPresenter(mPresenterHolder, (Class<? extends MvpBaseActivityPresenter>) field.getType())) {
                injectTarget.add(field);
            }
        }
        mPresenterHolder.registerPresenters(MvpInjector.<MvpBaseActivityPresenter>injectPresenter(this, injectTarget));
    }

    protected boolean shouldInjectPresenter(PresenterHolder holder, Class<? extends MvpBaseActivityPresenter> presenterClass) {
        return true;
    }

    public PresenterHolder getPresenterHolder() {
        return mPresenterHolder;
    }

    public static class PresenterHolder {
        List<MvpBaseActivityPresenter> mPresenters;

        public PresenterHolder() {
            mPresenters = new ArrayList<>();
        }

        public void registerPresenter(MvpBaseActivityPresenter presenter) {
            mPresenters.add(presenter);
        }

        public void registerPresenters(Collection<MvpBaseActivityPresenter> presenters) {
            mPresenters.addAll(presenters);
        }

        public void unregisterPresenter(MvpBaseActivityPresenter presenter) {
            mPresenters.remove(presenter);
        }

        public void onActivityCreate() {
            for (MvpBaseActivityPresenter presenter :
                    mPresenters) {
                presenter.onActivityCreate();
            }
        }

        public void onActivityDestory() {
            for (MvpBaseActivityPresenter presenter :
                    mPresenters) {
                presenter.onActivityDestory();
            }
        }

        public void onActivityStart() {
            for (MvpBaseActivityPresenter presenter :
                    mPresenters) {
                presenter.onActivityStart();
            }
        }

        public void onActivityStop() {
            for (MvpBaseActivityPresenter presenter :
                    mPresenters) {
                presenter.onActivityStop();
            }
        }

        public void onActivityResume() {
            for (MvpBaseActivityPresenter presenter :
                    mPresenters) {
                presenter.onActivityResume();
            }
        }

        public void onActivityPause() {
            for (MvpBaseActivityPresenter presenter :
                    mPresenters) {
                presenter.onActivityPause();
            }
        }
    }
}
