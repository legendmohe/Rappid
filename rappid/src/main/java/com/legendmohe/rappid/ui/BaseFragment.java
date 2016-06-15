package com.legendmohe.rappid.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * Created by legendmohe on 16/5/29.
 */
public class BaseFragment extends Fragment {

    WeakReference<BaseActivity> mBaseActivityWeakReference;

    public void attachBaseActivity(BaseActivity activity) {
        mBaseActivityWeakReference = new WeakReference<BaseActivity>(activity);
    }

    public BaseActivity getBaseActivity() {
        return mBaseActivityWeakReference.get();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof BaseActivity) {
            attachBaseActivity((BaseActivity) getActivity());
        } else {
            throw new IllegalArgumentException(getActivity().getClass().getName() + " is not subclass of BaseActivity");
        }
    }

    public void startActivity(Class<?> activityClass) {
        Intent localIntent = new Intent(getBaseActivity(), activityClass);
        startActivity(localIntent);
    }

    public void startActivity(Class<?> activityClass, Bundle data) {
        Intent localIntent = new Intent(getBaseActivity(), activityClass);
        localIntent.putExtras(data);
        startActivity(localIntent);
    }
}
