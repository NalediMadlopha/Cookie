package app.cookie.app.dependency;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import app.cookie.app.repository.RecipeRepository;
import app.cookie.app.viewmodel.RecipeViewModelFactory;
import dagger.Module;
import dagger.Provides;

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
}
