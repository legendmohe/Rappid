package com.legendmohe.rappid.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    private static DialogProvider sDialogProvider = new DefaultDialogProvider();

    private BaseDialogFragment mLoadingDialog;

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
            mLoadingDialog = sDialogProvider.provideLoadingDialogFragment(msg).show(getSupportFragmentManager());
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
            return sDialogProvider.providePromptDialogFragment(title, msg, buttonTitle).show(getSupportFragmentManager());
        }
        return null;
    }

    public BaseDialogFragment showPromptDialog(String title, String msg) {
        if (sDialogProvider != null) {
            return sDialogProvider.providePromptDialogFragment(title, msg).show(getSupportFragmentManager());
        }
        return null;
    }

    public BaseDialogFragment showPromptDialog(String msg) {
        if (sDialogProvider != null) {
            return sDialogProvider.providePromptDialogFragment(msg).show(getSupportFragmentManager());
        }
        return null;
    }

    public void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void runAfterCreated(Runnable runnable) {
        getWindow().getDecorView().post(runnable);
    }
}
