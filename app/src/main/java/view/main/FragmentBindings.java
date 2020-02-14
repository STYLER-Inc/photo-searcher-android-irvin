package view.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import view.grid.ImageGridFragment;

@Module
public abstract class FragmentBindings {

    @ContributesAndroidInjector
    abstract ImageGridFragment provideGridFragment();

}
