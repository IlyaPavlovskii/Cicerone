package ru.terrakok.cicerone.android.navigator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;
import ru.terrakok.cicerone.commands.SystemMessage;

/**
 * Create with Android Studio<br>
 * Created by Pavlovskii Ilya<br>
 * E-mail: pavlovskii_ilya@mail.ru, trane91666@gmail.com<br>
 * Skype: trane9119<br>
 * Date: 12.11.2017<br>
 * Time: 21:36<br>
 * Project name: Cicerone<br>
 * ===================================================================================<br>
 */
public abstract class BaseNavigator implements Navigator {

    //======================================================
    //----------------------Constants-----------------------
    //======================================================
    private final String TAG = this.getClass().getSimpleName();

    //======================================================
    //------------------------Fields------------------------
    //======================================================
    @Nullable
    protected Navigator mParentNavigator;

    //======================================================
    //---------------------Constructors---------------------
    //======================================================
    public BaseNavigator() {
    }

    public BaseNavigator(@Nullable Navigator parentNavigator) {
        mParentNavigator = parentNavigator;
    }

    //======================================================
    //-------------------Override methods-------------------
    //======================================================
    @Override
    public void applyCommand(Command command) {
        if (command instanceof Forward) {
            forward((Forward) command);
        } else if (command instanceof Replace) {
            replace((Replace) command);
        } else if (command instanceof Back) {
            back();
        } else if( command instanceof BackTo){
            backTo((BackTo)command);
        } else if (command instanceof SystemMessage) {
            systemMessage((SystemMessage)command);
        } else if( mParentNavigator != null ){
            // TODO: 13.11.2017 Check this feature
            mParentNavigator.applyCommand( command );
        }
    }

    //======================================================
    //-------------------Protected methods------------------
    //======================================================
    protected boolean isEmpty(@Nullable String string){
        return string == null || string.equals("");
    }

    //======================================================
    //-------------------Abstract methods-------------------
    //======================================================
    protected abstract void systemMessage(@NonNull SystemMessage command);

    protected abstract void back();

    protected abstract void backTo(BackTo command);

    protected abstract void replace(@NonNull Replace command);

    protected abstract void forward(@NonNull Forward command);

}

