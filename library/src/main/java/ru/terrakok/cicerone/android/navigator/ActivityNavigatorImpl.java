package ru.terrakok.cicerone.android.navigator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.terrakok.cicerone.Constants;
import ru.terrakok.cicerone.IFragmentTag;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.container.IActivityContainer;
import ru.terrakok.cicerone.android.container.IFragmentContainer;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;
import ru.terrakok.cicerone.commands.SystemMessage;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 13.11.2017<br>
 * Time: 20:46<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 * Simple {@link Activity} navigator implementation. All that you need to override abstract method<br>
 * with <br>
 * ===================================================================================<br>
 */
public abstract class ActivityNavigatorImpl
        extends BaseNavigator
        implements IActivityNavigator, ISystemMessageNavigator {

    //======================================================
    //------------------------Fields------------------------
    //======================================================
    @Nullable
    protected Activity mActivity;
    @Nullable
    protected ISystemMessageActions mSystemMessageActions;

    //======================================================
    //---------------------Constructors---------------------
    //======================================================
    public ActivityNavigatorImpl() {
    }

    public ActivityNavigatorImpl(@Nullable Navigator parentNavigator) {
        super(parentNavigator);
    }

    public ActivityNavigatorImpl(@Nullable Activity activity, @Nullable ISystemMessageActions systemMessageActions) {
        mActivity = activity;
        mSystemMessageActions = systemMessageActions;
    }


    //======================================================
    //-------------------Protected methods------------------
    //======================================================
    @Nullable
    protected abstract IFragmentContainer getFragmentContainer(String screenKey, Object... transitionData);

    @Nullable
    protected abstract IActivityContainer getActivityContainer(String commandKey, Object... transitionData);
    //======================================================
    //-------------------Override methods-------------------
    //======================================================
    @Nullable
    @Override
    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public void setSystemMessageActions(@Nullable ISystemMessageActions systemMessageActions) {
        mSystemMessageActions = systemMessageActions;
    }

    @Override
    public void setActivity(@Nullable Activity activity) {
        mActivity = activity;
    }

    @Override
    protected void systemMessage(@NonNull SystemMessage command) {
        if( mSystemMessageActions != null ){
            mSystemMessageActions.showMessage( command.getMessage() );
        }
    }

    @Override
    protected void back() {
        if( mActivity != null ){
            mActivity.onBackPressed();
        }
    }
    @Override
    protected void replace(@NonNull Replace command) {
        if( isEmpty(command.getScreenKey()) ){
            if( command.getScreenKey().contains(Constants.FragmentKeys.PREFIX)){
                replaceFragment( command );
            } else if( command.getScreenKey().contains(Constants.ActivityKeys.PREFIX)){
                replaceActivity(command);
            }
        }
    }

    @Override
    protected void backTo(BackTo command) {

        if( mActivity != null ){
            FragmentManager fragmentManager = mActivity.getFragmentManager();
            String key = command.getScreenKey();

            if (key == null) {
                backToRoot();
            } else if( key.contains( Constants.FragmentKeys.PREFIX ) ){
                boolean hasScreen = false;
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                    if (key.equals(fragmentManager.getBackStackEntryAt(i).getName())) {
                        fragmentManager.popBackStackImmediate(key, 0);
                        hasScreen = true;
                        break;
                    }
                }
                if (!hasScreen) {
                    backToRoot();
                }
            } else if( key.contains( Constants.ActivityKeys.PREFIX ) ){
                backToActivity(command);
            }
        }
    }

    @Override
    protected void forward(@NonNull Forward command) {
        if( !isEmpty(command.getScreenKey()) ){
            if( command.getScreenKey().contains(Constants.FragmentKeys.PREFIX)){
                forwardFragment( command );
            } else if( command.getScreenKey().contains(Constants.ActivityKeys.PREFIX)){
                forwardActivity(command);
            }
        }
    }

    //======================================================
    //-------------------Protected methods------------------
    //======================================================
    protected boolean isEmpty(@Nullable String string){
        return string == null || string.equals("");
    }

    protected boolean isEmptyData(Object... data){
        return data == null || data.length < 1;
    }

    protected void replaceActivity(Replace command) {
        if( mActivity != null ){
            forwardActivity( new Forward(command.getScreenKey(), command.getTransitionData()) );
            mActivity.finish();
        }
    }

    protected void replaceFragment(Replace command) {
        if( mActivity != null ){
            FragmentManager fragmentManager = mActivity.getFragmentManager();
            IFragmentContainer fragmentContainer = getFragmentContainer( command.getScreenKey(), command.getTransitionData() );

            if( fragmentContainer != null ){
                String tag = null;
                if( fragmentContainer.getFragment() != null ){
                    if( fragmentContainer.getFragment() instanceof IFragmentTag){
                        tag = ((IFragmentTag)fragmentContainer.getFragment()).getFragmentTag();
                    } else {
                        tag = fragmentContainer.getFragment().getTag();
                    }
                }
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStackImmediate();

                    fragmentManager
                            .beginTransaction()
                            .replace( fragmentContainer.getContainerId(), fragmentContainer.getFragment(), tag )
                            .addToBackStack( command.getScreenKey() )
                            .commit();
                } else {
                    fragmentManager
                            .beginTransaction()
                            .replace( fragmentContainer.getContainerId(), fragmentContainer.getFragment(), tag )
                            .commit();
                }
            }
        }

    }

    protected void forwardActivity(Forward command) {
        if( mActivity != null ){
            IActivityContainer container = getActivityContainer( command.getScreenKey(), command.getTransitionData());
            if( container != null ){
                if( container.getRequestCode() > 0 ){
                    if( container.getBundle() != null ){

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            mActivity.startActivityForResult(
                                    container.getIntent(),
                                    container.getRequestCode(),
                                    container.getBundle());
                        } else {
                            mActivity.startActivityForResult(
                                    container.getIntent(),
                                    container.getRequestCode());
                        }
                    } else {
                        mActivity.startActivityForResult(
                                container.getIntent(),
                                container.getRequestCode());
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                        mActivity.startActivity( container.getIntent(), container.getBundle());
                    } else {
                        mActivity.startActivity( container.getIntent());
                    }
                }
            }
        }

    }

    protected void forwardFragment(Forward command){
        if( mActivity != null ){
            FragmentManager fragmentManager = mActivity.getFragmentManager();
            IFragmentContainer fragmentContainer = getFragmentContainer(command.getScreenKey(), command.getTransitionData());
            if(  fragmentContainer != null ){
                Fragment fragment = fragmentContainer.getFragment();
                String tag = null;
                if( fragment != null ){
                    tag = fragment.getClass().getSimpleName();
                }
                fragmentManager
                        .beginTransaction()
                        .replace( fragmentContainer.getContainerId(), fragment, tag)
                        .addToBackStack( command.getScreenKey())
                        .commit();
            }
        }
    }

    protected void backToRoot() {
        if( mActivity != null ){
            FragmentManager fragmentManager = mActivity.getFragmentManager();
            final int count = fragmentManager.getBackStackEntryCount();
            for (int i = 0; i < count; i++) {
                fragmentManager.popBackStack();
            }
            fragmentManager.executePendingTransactions();
        }
    }



    private void backToActivity(BackTo command) {
        if(getActivity()!= null) {
            IActivityContainer container = getActivityContainer( command.getScreenKey(), command.getTransitionData());
            if( container != null  ){
                Intent intent = container.getIntent();
                intent.setAction("backToFrom:"+getActivity().getLocalClassName());
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        }
    }

}

