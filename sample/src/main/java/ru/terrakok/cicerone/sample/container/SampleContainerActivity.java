package ru.terrakok.cicerone.sample.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
 * Time: 17:28<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 */
public class SampleContainerActivity extends BaseActivity {

    @Inject
    ContainerNavigator mContainerNavigator;

    @Override
    protected void injectDependencies() {
        SampleApplication.INSTANCE
                .getAppComponent()
                .inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        String param = getIntent().getStringExtra(Constants.IntentKeys.SAMPLE_PARAM);
        TextView vTvParams = ((TextView)findViewById(R.id.vTvParams));

        if( !TextUtils.isEmpty(param)){
            vTvParams.setText(param);
        } else {
            vTvParams.setText("PARAMS IS EMPTY");
        }
    }

    @Override
    protected Navigator initNavigator() {
        return mContainerNavigator;
    }

    public void onForwardClick(View view) {
        mRouter.navigateTo(Constants.ActivityKeys.SAMPLE);
    }

    public void onNewChainClick(View view) {
        mRouter.newScreenChain(Constants.ActivityKeys.CONTAINER);
    }
}
