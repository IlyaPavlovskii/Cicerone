package ru.terrakok.cicerone.android.navigator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 13.11.2017<br>
 * Time: 13:30<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 * System message actions interface. Describe actions which called on {@link android.widget.Toast}, <br>
 * {@link java.awt.Dialog} or custom solution<br>
 * ===================================================================================<br>
 */
public interface ISystemMessageActions {
    void showMessage(@NonNull String message);
}
