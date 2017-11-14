package ru.terrakok.cicerone.sample.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.terrakok.cicerone.sample.container.ContainerActivity;
import ru.terrakok.cicerone.sample.container.SampleContainerActivity;
import ru.terrakok.cicerone.sample.dagger.module.LocalNavigationModule;
import ru.terrakok.cicerone.sample.dagger.module.NavigationModule;
import ru.terrakok.cicerone.sample.ui.bottom.BottomNavigationActivity;
import ru.terrakok.cicerone.sample.ui.bottom.TabContainerFragment;
import ru.terrakok.cicerone.sample.ui.main.MainActivity;
import ru.terrakok.cicerone.sample.ui.main.SampleFragment;
import ru.terrakok.cicerone.sample.ui.start.StartActivity;

/**
 * Created by terrakok 24.11.16
 */

@Singleton
@Component(modules = {
        NavigationModule.class,
        LocalNavigationModule.class
})
public interface AppComponent {

    void inject(StartActivity activity);

    void inject(MainActivity activity);

    void inject(SampleFragment fragment);

    void inject(BottomNavigationActivity activity);

    void inject(TabContainerFragment fragment);

    void inject(ContainerActivity containerActivity);

    void inject(SampleContainerActivity sampleContainerActivity);
}
