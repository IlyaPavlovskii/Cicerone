package ru.terrakok.cicerone.android.container;

import android.app.Fragment;

import android.support.annotation.Nullable;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 14.11.2017<br>
 * Time: 12:34<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 * Simple {@link IFragmentContainer} realization<br>
 * ===================================================================================<br>
 */
public class FragmentContainerImpl implements IFragmentContainer {
    //======================================================
    //------------------------Fields------------------------
    //======================================================
    private int mContainerId;
    @Nullable
    private Fragment mFragment;

    //======================================================
    //---------------------Constructors---------------------
    //======================================================
    private FragmentContainerImpl(int containerId, @Nullable Fragment fragment) {
        mContainerId = containerId;
        mFragment = fragment;
    }

    //======================================================
    //-------------------Override methods-------------------
    //======================================================
    @Override
    public int getContainerId() {
        return mContainerId;
    }

    @Nullable
    @Override
    public Fragment getFragment() {
        return mFragment;
    }

    //======================================================
    //---------------------Public methods-------------------
    //======================================================
    public static FragmentContainerImpl create(int containerId, @Nullable Fragment fragment) {
        return new FragmentContainerImpl( containerId, fragment);
    }

}
