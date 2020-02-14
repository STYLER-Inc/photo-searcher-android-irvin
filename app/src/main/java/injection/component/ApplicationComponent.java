package injection.component;


import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import injection.BaseApplication;
import injection.module.ActivityBindings;
import injection.module.ApplicationModule;
import injection.module.ContextModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidSupportInjectionModule.class,
        ContextModule.class,
        ActivityBindings.class})
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

    void inject(BaseApplication baseApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application app);

        ApplicationComponent build();
    }
}
