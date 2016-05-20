package com.legendmohe.rappid.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    private BaseDialogFragment mLoadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        super.onStart();
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

    public void showLoadingDialog(String msg) {
        mLoadingDialog = LoadingDialogFragment.newInstance(msg).show(getSupportFragmentManager());
    }

    public void hideLoadingDialog() {
        mLoadingDialog.dismiss();
    }

    public void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void runAfterCreated(Runnable runnable) {
        getWindow().getDecorView().post(runnable);
    }
}
