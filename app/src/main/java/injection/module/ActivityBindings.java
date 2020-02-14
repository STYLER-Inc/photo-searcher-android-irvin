package injection.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import view.main.FragmentBindings;
import view.main.MainActivity;

@Module
public abstract class ActivityBindings {

    @ContributesAndroidInjector(modules = {FragmentBindings.class})
    abstract MainActivity bindMainActivity();
}
