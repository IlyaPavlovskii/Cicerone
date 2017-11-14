package ru.terrakok.cicerone.sample.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

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

    @Inject
    ContainerNavigator mContainerNavigator;

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
    }

    public void onSampleClick(View view) {
        mRouter.navigateTo(Constants.ActivityKeys.SAMPLE_ACTIVITY);
    }

    public void onSampleParamsClick(View view) {
        mRouter.navigateTo(Constants.ActivityKeys.SAMPLE_ACTIVITY, "some params");
    }
}
