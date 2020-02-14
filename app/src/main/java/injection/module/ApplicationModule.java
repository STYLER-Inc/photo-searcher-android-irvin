package injection.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import data.endpoint.Service;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static data.endpoint.Constants.BASE_URL;

@Module(includes = ViewModelModule.class)
public class ApplicationModule {


    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    Service provideService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }
}
