package injection;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import injection.component.ApplicationComponent;
import injection.component.DaggerApplicationComponent;

public class App extends dagger.android.support.DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();

        component.inject(this);

        return null;
    }
}
