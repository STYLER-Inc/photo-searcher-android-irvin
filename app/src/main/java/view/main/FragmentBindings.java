package view.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import view.detail.ImageDetailFragment;
import view.grid.ImageGridFragment;

@Module
public abstract class FragmentBindings {

    @ContributesAndroidInjector
    abstract ImageGridFragment provideGridFragment();

    @ContributesAndroidInjector
    abstract ImageDetailFragment provideImageDetailFragment();

}
