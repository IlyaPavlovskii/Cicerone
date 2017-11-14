package ru.terrakok.cicerone.android.container;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 14.11.2017<br>
 * Time: 12:32<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 * Simple {@link IActivityContainer} realization<br>
 * ===================================================================================<br>
 */
public class ActivityContainerImpl implements IActivityContainer {

    //======================================================
    //------------------------Fields------------------------
    //======================================================
    private int mRequestCode;
    private Intent mIntent;
    private Bundle mBundle;

    //======================================================
    //---------------------Constructors---------------------
    //======================================================
    private ActivityContainerImpl(int requestCode, Intent intent, Bundle bundle){
        this.mRequestCode = requestCode;
        this.mIntent = intent;
        this.mBundle = bundle;
    }

    //======================================================
    //-------------------Override methods-------------------
    //======================================================
    @Override
    public int getRequestCode() {
        return mRequestCode;
    }

    @NonNull
    @Override
    public Intent getIntent() {
        return mIntent;
    }

    @Override
    public Bundle getBundle() {
        return mBundle;
    }

    //======================================================
    //---------------------Public methods-------------------
    //======================================================
    public static ActivityContainerImpl create(int requestCode, Context context, Class<?> activity){
        return new ActivityContainerImpl(requestCode, new Intent( context, activity), null);
    }

    public static ActivityContainerImpl create(Context context, Class<?> activity){
        return create(new Intent(context, activity));
    }

    public static ActivityContainerImpl create(Intent intent){
        return new ActivityContainerImpl(0, intent, null);
    }

    public static ActivityContainerImpl create(int requestCode, Intent intent){
        return new ActivityContainerImpl(requestCode, intent, null);
    }

    public static ActivityContainerImpl create(int requestCode, Intent intent, Bundle bundle){
        return new ActivityContainerImpl(requestCode, intent, bundle);
    }
}
