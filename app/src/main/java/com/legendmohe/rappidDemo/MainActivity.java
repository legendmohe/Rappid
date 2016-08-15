package com.legendmohe.rappidDemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.legendmohe.rappid.mvp.MvpBaseActivityPresenter;
import com.legendmohe.rappid.mvp.MvpBaseAppCompatActivity;
import com.legendmohe.rappid.mvp.MvpPresenter;
import com.legendmohe.rappid.util.ThreadUtil;

public class MainActivity extends MvpBaseAppCompatActivity implements MainView, OtherView {

    @MvpPresenter
    MainPresenter mMainPresenter;

    @MvpPresenter
    OtherPresenter mOtherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews(null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ThreadUtil.backgroundSleep(2000, new Runnable() {
            @Override
            public void run() {
                mMainPresenter.doSomething();
                mOtherPresenter.showToast();
            }
        });
    }

    @Override
    protected boolean shouldInjectPresenter(PresenterHolder holder, Class<? extends MvpBaseActivityPresenter> presenterClass) {
        if (presenterClass == OtherPresenter.class) {
            mOtherPresenter = new OtherPresenter(this);
            holder.registerPresenter(mOtherPresenter);
            return false;
        }
        return super.shouldInjectPresenter(holder, presenterClass);
    }

    @Override
    public void showToast() {
        Toast.makeText(getContext(), "showToast", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showActivityCreate() {
        Toast.makeText(getContext(), "showActivityCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupViews(View rootView) {
        findViewById(R.id.fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Main2Activity.class);
            }
        });
    }

    @Override
    public void showOtherToast() {
        Toast.makeText(getContext(), "showOtherToast", Toast.LENGTH_SHORT).show();
    }
}
