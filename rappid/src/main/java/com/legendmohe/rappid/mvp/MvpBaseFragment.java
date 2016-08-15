package com.legendmohe.rappid.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.legendmohe.rappid.ui.BaseFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by legendmohe on 16/4/5.
 */
public abstract class MvpBaseFragment extends BaseFragment {

    protected PresenterHolder mPresenterHolder = new PresenterHolder();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenterHolder.onFragmentCreateView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initPresenters();
        mPresenterHolder.onFragmentAttached(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenterHolder.onFragmentDetached();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenterHolder.onFragmentDestoryView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenterHolder.onActivityCreate();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterHolder.onFragmentCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenterHolder.onFragmentStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenterHolder.onFragmentStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenterHolder.onFragmentResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenterHolder.onFragmentPause();
    }

    private void initPresenters() {
        Class<?> clazz = this.getClass();
        Set<Field> presenterFields = MvpInjector.getPresenterFields(clazz, MvpPresenter.class, MvpBaseFragmentPresenter.class);
        Collection<Field> injectTarget = new ArrayList<>();
        for (Field field :
                presenterFields) {
            if (shouldInjectPresenter(mPresenterHolder, (Class<? extends MvpBaseFragmentPresenter>) field.getType())) {
                injectTarget.add(field);
            }
        }
        mPresenterHolder.registerPresenters(MvpInjector.<MvpBaseFragmentPresenter>injectPresenter(this, injectTarget));
    }

    protected boolean shouldInjectPresenter(PresenterHolder holder, Class<? extends MvpBaseFragmentPresenter> presenterClass) {
        return true;
    }

    public PresenterHolder getPresenterHolder() {
        return mPresenterHolder;
    }

    public static class PresenterHolder {
        List<MvpBaseFragmentPresenter> mPresenters;

        public PresenterHolder() {
            mPresenters = new ArrayList<>();
        }

        public void registerPresenter(MvpBaseFragmentPresenter presenter) {
            mPresenters.add(presenter);
        }

        public void registerPresenters(Collection<MvpBaseFragmentPresenter> presenters) {
            mPresenters.addAll(presenters);
        }

        public void unregisterPresenter(MvpBaseFragmentPresenter presenter) {
            mPresenters.remove(presenter);
        }

        public void onActivityCreate() {
            for (MvpBaseFragmentPresenter presenter :
                    mPresenters) {
                presenter.onActivityCreate();
            }
        }

        public void onFragmentCreateView() {
            for (MvpBaseFragmentPresenter presenter :
                    mPresenters) {
                presenter.onFragmentCreateView();
            }
        }

        public void onFragmentAttached(Context context) {
            for (MvpBaseFragmentPresenter presenter :
                    mPresenters) {
                presenter.onFragmentAttached(context);
            }
        }

        public void onFragmentDetached() {
            for (MvpBaseFragmentPresenter presenter :
                    mPresenters) {
                presenter.onFragmentDetached();
            }
        }

        public void onFragmentDestoryView() {
            for (MvpBaseFragmentPresenter presenter :
                    mPresenters) {
                presenter.onFragmentDestoryView();
            }
        }

        public void onFragmentCreate() {
            for (MvpBaseFragmentPresenter presenter :
                    mPresenters) {
                presenter.onFragmentCreate();
            }
        }

        public void onFragmentStart() {
            for (MvpBaseFragmentPresenter presenter :
                    mPresenters) {
                presenter.onFragmentStart();
            }
        }

        public void onFragmentStop() {
            for (MvpBaseFragmentPresenter presenter :
                    mPresenters) {
                presenter.onFragmentStop();
            }
        }

        public void onFragmentResume() {
            for (MvpBaseFragmentPresenter presenter :
                    mPresenters) {
                presenter.onFragmentResume();
            }
        }

        public void onFragmentPause() {
            for (MvpBaseFragmentPresenter presenter :
                    mPresenters) {
                presenter.onFragmentPause();
            }
        }
    }
}
