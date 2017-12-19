package app.cookie.app.dependency;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import javax.inject.Inject;

import app.cookie.app.database.AppDatabase;
import app.cookie.app.database.RecipeDao;
import app.cookie.app.dataservice.WebService;
import app.cookie.app.repository.RecipeRepository;
import app.cookie.app.viewmodel.factory.RecipeDetailsViewModelFactory;
import app.cookie.app.viewmodel.factory.RecipeListViewModelFactory;
import app.cookie.app.viewmodel.factory.StepDetailsViewModelFactory;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.cookie.app.stringdef.CookieConstants.URL.BASE_URL;

@Module
public class AppModule {

    private final Context context;

    AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context() {
        return context;
    }

    @Provides
    RecipeRepository provideRepository() {
        return new RecipeRepository();
    }

    @Provides
    @Inject
    ViewModelProvider.Factory provideRecipeListViewModelFactory(RecipeListViewModelFactory factory) {
        return factory;
    }

    @Provides
    @Inject
    ViewModelProvider.Factory provideRecipeDetailsViewModelFactory(RecipeDetailsViewModelFactory factory) {
        return factory;
    }

    @Provides
    @Inject
    ViewModelProvider.Factory provideStepDetailsViewModelFactory(StepDetailsViewModelFactory factory) {
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

    @Provides
    RecipeDao provideRecipeDao() {
        return AppDatabase.getInstance(context).recipeDao();
    }
}
