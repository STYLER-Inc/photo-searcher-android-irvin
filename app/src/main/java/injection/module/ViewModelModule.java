package injection.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import data.factories.ViewModelFactory;
import view.detail.ImageDetailViewModel;
import view.grid.GridViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GridViewModel.class)
    abstract ViewModel bindGridViewModel(GridViewModel gridViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ImageDetailViewModel.class)
    abstract ViewModel bindImageDetailViewModel(ImageDetailViewModel gridViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
