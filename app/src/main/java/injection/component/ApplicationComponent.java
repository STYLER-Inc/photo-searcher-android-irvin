package injection.component;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import injection.App;
import injection.module.ActivityBindings;
import injection.module.ApplicationModule;
import injection.module.ContextModule;

@Singleton
@Component(modules = {ApplicationModule.class, AndroidSupportInjectionModule.class, ContextModule.class, ActivityBindings.class})
public interface ApplicationComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(App app);

        ApplicationComponent build();
    }
}
