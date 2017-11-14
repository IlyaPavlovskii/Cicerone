package ru.terrakok.cicerone.sample.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.terrakok.cicerone.android.container.ActivityContainerImpl;
import ru.terrakok.cicerone.android.container.IActivityContainer;
import ru.terrakok.cicerone.android.container.IFragmentContainer;
import ru.terrakok.cicerone.android.navigator.ActivityNavigatorImpl;
import ru.terrakok.cicerone.sample.Constants;
import ru.terrakok.cicerone.sample.container.SampleContainerActivity;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 14.11.2017<br>
 * Time: 17:29<br>
 * Project name: Cicerone<br><br>
 * ===================================================================================<br>
 */
public class ContainerNavigator extends ActivityNavigatorImpl {

    @Nullable
    @Override
    protected IFragmentContainer getFragmentContainer(String screenKey, Object... transitionData) {
        return null;
    }

    @Nullable
    @Override
    protected IActivityContainer getActivityContainer(String commandKey, Object... transitionData) {

        switch (commandKey){
            case Constants.ActivityKeys.SAMPLE_ACTIVITY:
                return buildSampleContainerWithParams(transitionData);
        }
        return null;
    }

    private IActivityContainer buildSampleContainerWithParams(@NonNull Object... transitionData) {
        Intent intent = new Intent( getActivity(), SampleContainerActivity.class);

        if( !isEmptyData(transitionData) ){
            if( transitionData[0] instanceof String){
                intent.putExtra(Constants.IntentKeys.SAMPLE_PARAM, (String)transitionData[0]);
            }
        }

        return ActivityContainerImpl.create(intent);
    }
}
