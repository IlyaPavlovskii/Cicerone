package ru.terrakok.cicerone.commands;

import ru.terrakok.cicerone.Navigator;

/**
 * Created by Konstantin Tckhovrebov (aka @terrakok)
 * on 11.10.16
 */

/**
 * Rolls back to the needed screen from the screens chain.
 * Behavior in the case when no needed screens found depends on an implementation of the {@link Navigator}.
 * But the recommended behavior is to return to the root.
 */
public class BackTo implements Command {
    private String screenKey;
    private Object[] transitionData;

    /**
     * Creates a {@link BackTo} navigation command.
     *
     * @param screenKey screen key
     */
    public BackTo(String screenKey) {
        this.screenKey = screenKey;
    }

    /**
     * Creates a {@link BackTo} navigation command.
     *
     * @param screenKey      screen key
     * @param transitionArray initial array data
     */
    public BackTo(String screenKey, Object... transitionArray) {
        this.screenKey = screenKey;
        this.transitionData = transitionArray;
    }

    public String getScreenKey() {
        return screenKey;
    }

    public Object[] getTransitionData() {
        return transitionData;
    }
}
