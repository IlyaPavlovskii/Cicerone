package ru.terrakok.cicerone.commands;

/**
 * Created by Konstantin Tckhovrebov (aka @terrakok)
 * on 11.10.16
 */

/**
 * Replaces the current screen.
 */
public class Replace implements Command {
    private String screenKey;
    private Object[] transitionData;

    /**
     * Creates a {@link Replace} navigation command.
     *
     * @param screenKey      screen key
     * @param transitionArray initial array data
     */
    public Replace(String screenKey, Object... transitionArray) {
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
