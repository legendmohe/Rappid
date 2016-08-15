package com.legendmohe.rappid.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * http://note.tyz.ren/blog/post/zerozhiqin/%E5%A6%82%E4%BD%95%E5%9C%A8%E5%9B%9E%E8%B0%83%E6%97%B6%E5%88%A4%E6%96%ADActivity%EF%BC%8CFragment%EF%BC%8CImageView%E7%AD%89%E7%AD%89%E6%98%AF%E5%90%A6%E5%B7%B2%E7%BB%8F%E8%A2%AB%E5%85%B3%E9%97%AD
 */
public class ContextHolder<T> extends WeakReference<T> {
    public ContextHolder(T r) {
        super(r);
    }

    public boolean isAlive() {
        T ref = get();
        if (ref == null) {
            return false;
        } else {
            if (ref instanceof Service)
                return isServiceAlive((Service) ref);
            if (ref instanceof Activity)
                return isActivityAlive((Activity) ref);
            if (ref instanceof Fragment)
                return isFragmentAlive((Fragment) ref);
            if (ref instanceof android.support.v4.app.Fragment)
                return isV4FragmentAlive((android.support.v4.app.Fragment) ref);
            if (ref instanceof ImageView)
                return isImageAlive((ImageView) ref);
        }
        return true;
    }

    boolean isServiceAlive(Service candidate) {
        if (candidate == null)
            return false;
        ActivityManager manager = (ActivityManager) candidate.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = manager.getRunningServices(Integer.MAX_VALUE);
        if (services == null)
            return false;
        for (ActivityManager.RunningServiceInfo service : services) {
            if (candidate.getClass().getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    boolean isActivityAlive(Activity a) {
        if (a == null)
            return false;
        if (a.isFinishing())
            return false;
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    boolean isFragmentAlive(Fragment fragment) {
        boolean ret = isActivityAlive(fragment.getActivity());
        if (!ret)
            return false;
        if (fragment.isDetached())
            return false;
        return true;
    }

    boolean isV4FragmentAlive(android.support.v4.app.Fragment fragment) {
        boolean ret = isActivityAlive(fragment.getActivity());
        if (!ret)
            return false;
        if (fragment.isDetached())
            return false;
        return true;
    }

    boolean isImageAlive(ImageView imageView) {
        Context context = imageView.getContext();
        if (context instanceof Service)
            return isServiceAlive((Service) context);
        if (context instanceof Activity)
            return isActivityAlive((Activity) context);
        return true;
    }
}
