package ru.terrakok.cicerone.android.container;

import android.support.v4.app.Fragment;

import android.support.annotation.Nullable;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 13.11.2017<br>
 * Time: 18:58<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 * Simple description of {@link Fragment} and it container id info<br>
 * ===================================================================================<br>
 */
public interface ISupportFragmentContainer {

    //======================================================
    //------------------------Methods-----------------------
    //======================================================
    int getContainerId();

    @Nullable
    Fragment getFragment();
}
