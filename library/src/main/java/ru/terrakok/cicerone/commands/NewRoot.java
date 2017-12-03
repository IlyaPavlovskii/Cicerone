package ru.terrakok.cicerone.commands;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 03.12.2017<br>
 * Time: 17:10<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 * Navigate to new root activity<br>
 * ===================================================================================<br>
 */
public class NewRoot extends Replace {

    /**
     * Creates a {@link Forward} navigation command.
     *
     * @param screenKey      screen key
     * @param transitionData initial array data
     */
    public NewRoot(String screenKey, Object... transitionData) {
        super(screenKey, transitionData);
    }
}
