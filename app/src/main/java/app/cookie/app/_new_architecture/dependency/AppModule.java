package app.cookie.app._new_architecture.dependency;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import app.cookie.app._new_architecture.repository.RecipeRepository;
import app.cookie.app._new_architecture.service.WebService;
import app.cookie.app.viewmodel.RecipeViewModelFactory;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.cookie.app.stringdef.CookieConstants.URL.BASE_URL;

@Module
public class AppModule {

    AppModule() {
    }

    @Provides
    RecipeRepository provideRepository() {
        return new RecipeRepository();
    }

    @Provides
    @Inject
    ViewModelProvider.Factory provideRecipeViewModelFactory(RecipeViewModelFactory factory) {
        return factory;
    }

    @Provides
    WebService provideWebService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService.class);
    }
}
