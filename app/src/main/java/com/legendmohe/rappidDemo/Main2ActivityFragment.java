package com.legendmohe.rappidDemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.legendmohe.rappid.mvp.MvpBaseFragment;
import com.legendmohe.rappid.mvp.MvpPresenter;

/**
 * A placeholder fragment containing a simple view.
 */
public class Main2ActivityFragment extends MvpBaseFragment implements FragmentView {

    @MvpPresenter
    FragmentViewPresenter mFragmentViewPresenter;

    public Main2ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main2, container, false);
    }

    @Override
    public void showToast() {
        Toast.makeText(getContext(), "showToast!!!!!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupViews(View rootView) {

    }
}
