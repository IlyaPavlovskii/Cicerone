package ru.terrakok.cicerone.sample.container;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
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
        setContentView(R.layout.activity_container);
        ((TextView)findViewById(R.id.vTvIndex)).setText("Index: " + (++sIndex));
        Log.d(TAG, "onCreate: " + sIndex);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: " + sIndex);
        --sIndex;
        super.onDestroy();
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
