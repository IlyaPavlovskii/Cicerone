package ru.terrakok.cicerone.sample.container;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.navigator.IActivityNavigator;
import ru.terrakok.cicerone.android.navigator.ISystemMessageActions;
import ru.terrakok.cicerone.android.navigator.ISystemMessageNavigator;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 14.11.2017<br>
 * Time: 17:14<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 * Base container activity<br>
 * ===================================================================================<br>
 */
public abstract class BaseActivity extends Activity {
    @Inject
    protected Router mRouter;
    @Inject
    NavigatorHolder mNavigatorHolder;

    private Navigator mNavigator;

    protected abstract void injectDependencies();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
        mNavigator = initNavigator();
    }

    protected Navigator initNavigator() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if( mNavigatorHolder != null && mNavigator != null ){

            if(mNavigator instanceof IActivityNavigator) {
                ((IActivityNavigator)mNavigator).setActivity(this);
            }
            if(mNavigator instanceof ISystemMessageNavigator) {
                ((ISystemMessageNavigator)mNavigator).setSystemMessageActions(mSystemMessageActions);
            }

            mNavigatorHolder.setNavigator(mNavigator);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if( mNavigatorHolder != null ){

            if(mNavigator instanceof IActivityNavigator) {
                ((IActivityNavigator)mNavigator).setActivity(null);
            }
            if(mNavigator instanceof ISystemMessageNavigator) {
                ((ISystemMessageNavigator)mNavigator).setSystemMessageActions(null);
            }

            mNavigatorHolder.removeNavigator();
        }
    }

    private ISystemMessageActions mSystemMessageActions = new ISystemMessageActions() {
        @Override
        public void showMessage(@NonNull String message) {
            Toast.makeText( BaseActivity.this, message, Toast.LENGTH_SHORT)
                    .show();
        }
    };
}
