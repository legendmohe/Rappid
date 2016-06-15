package com.legendmohe.rappid.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.legendmohe.rappid.util.CommonUtil;


public class BaseActivity extends AppCompatActivity {

    private static boolean sLightStatusBarText = false;

    private static DialogProvider sDialogProvider = new DefaultDialogProvider();

    private BaseDialogFragment mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (sLightStatusBarText) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }

            if (CommonUtil.isXiaomiRom()) {
                CommonUtil.setMiuiStatusBarDarkMode(this.getWindow(), true);
            } else if (CommonUtil.isMeizuRom()) {
                CommonUtil.setMeizuStatusBarDarkIcon(this.getWindow(), true);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            supportFinishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void startService(Class<?> paramClass) {
        Intent localIntent = new Intent(this, paramClass);
        startService(localIntent);
    }

    public void stopService(Class<?> paramClass) {
        Intent localIntent = new Intent(this, paramClass);
        stopService(localIntent);
    }

    public void startActivity(Class<?> activityClass) {
        Intent localIntent = new Intent(this, activityClass);
        startActivity(localIntent);
    }

    public void startActivity(Class<?> activityClass, Bundle data) {
        Intent localIntent = new Intent(this, activityClass);
        localIntent.putExtras(data);
        startActivity(localIntent);
    }

    public Context getContext() {
        return this;
    }

    public static void setDialogProvider(DialogProvider dialogProvider) {
        BaseActivity.sDialogProvider = dialogProvider;
    }

    public static DialogProvider getDialogProvider() {
        return sDialogProvider;
    }

    public BaseDialogFragment showLoadingDialog(String msg) {
        if (sDialogProvider != null) {
            mLoadingDialog = sDialogProvider.createLoadingDialogFragment(msg).show(getSupportFragmentManager());
            return mLoadingDialog;
        }
        return null;
    }

    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    public BaseDialogFragment showPromptDialog(String title, String msg, String buttonTitle) {
        if (sDialogProvider != null) {
            return sDialogProvider.createPromptDialogFragment(title, msg, buttonTitle).show(getSupportFragmentManager());
        }
        return null;
    }

    public BaseDialogFragment showPromptDialog(String title, String msg) {
        if (sDialogProvider != null) {
            return sDialogProvider.createPromptDialogFragment(title, msg).show(getSupportFragmentManager());
        }
        return null;
    }

    public BaseDialogFragment showPromptDialog(String msg) {
        if (sDialogProvider != null) {
            return sDialogProvider.createPromptDialogFragment(msg).show(getSupportFragmentManager());
        }
        return null;
    }

    public void showShortToast(String msg) {
        if (sDialogProvider != null) {
            View view = sDialogProvider.createToastView(this, msg);
            if (view == null) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            } else {
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(view);
                toast.show();
            }
        }
    }

    public void runAfterCreated(Runnable runnable) {
        getWindow().getDecorView().post(runnable);
    }

    public static boolean isLightStatusBarText() {
        return sLightStatusBarText;
    }

    public static void setLightStatusBarText(boolean sLightStatusBarText) {
        BaseActivity.sLightStatusBarText = sLightStatusBarText;
    }
}
