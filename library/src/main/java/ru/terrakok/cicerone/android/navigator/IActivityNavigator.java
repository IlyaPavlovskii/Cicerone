package ru.terrakok.cicerone.android.navigator;

import android.app.Activity;
import android.content.Intent;

import android.support.annotation.Nullable;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 12.11.2017<br>
 * Time: 21:32<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 * {@link Activity} navigator. Uses for call methods {@link Activity#startActivity(Intent)},<br>
 * {@link Activity#startActivityForResult(Intent, int, android.os.Bundle)},pass params and others<br>
 * ===================================================================================<br>
 */
public interface IActivityNavigator {
    Activity getActivity();
    void setActivity(@Nullable Activity activity);
}
