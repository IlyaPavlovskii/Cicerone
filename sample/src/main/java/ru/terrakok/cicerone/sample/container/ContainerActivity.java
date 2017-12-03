package ru.terrakok.cicerone.sample.container;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.AppNavigator;
import ru.terrakok.cicerone.android.container.ActivityContainerImpl;
import ru.terrakok.cicerone.sample.Constants;
import ru.terrakok.cicerone.sample.R;
import ru.terrakok.cicerone.sample.SampleApplication;
import ru.terrakok.cicerone.sample.navigator.ContainerNavigator;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 14.11.2017<br>
 * Time: 17:12<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 * Sample activity to using containers<br>
 * ===================================================================================<br>
 */
public class ContainerActivity extends BaseActivity {

    private static final String TAG = ContainerActivity.class.getSimpleName();
    @Inject
    ContainerNavigator mContainerNavigator;

    private static int sIndex = 0;

    @Override
    protected void injectDependencies() {
        SampleApplication.INSTANCE
                .getAppComponent()
                .inject(this);
    }

    @Override
    protected Navigator initNavigator() {
        return mContainerNavigator;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_sample);
        ((TextView)findViewById(R.id.vTvIndex)).setText("Index: " + (++sIndex));
        Log.d(TAG, "onCreate: " + sIndex);
    }

    @Override
    public void finish() {
        super.finish();
        Log.d(TAG, "finish: " + sIndex);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: " + sIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onStop: " + sIndex);
        --sIndex;
    }

    public void onSampleClick(View view) {
        mRouter.navigateTo(Constants.ActivityKeys.SAMPLE);
    }

    public void onSampleParamsClick(View view) {
        mRouter.navigateTo(Constants.ActivityKeys.SAMPLE, "some params");
    }

    public void onForwardContainerClick(View view) {
        mRouter.navigateTo(Constants.ActivityKeys.CONTAINER);
    }

    public void onNewRootClick(View view) {
        mRouter.newRootScreen(Constants.ActivityKeys.CONTAINER);
    }
}
