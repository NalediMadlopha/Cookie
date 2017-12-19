package app.cookie.app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import app.cookie.app.dependency.App;
import app.cookie.app.model.Recipe;
import app.cookie.app.repository.RecipeRepository;

public class RecipeListViewModel extends ViewModel {

    private LiveData<List<Recipe>> recipes;
    RecipeRepository repository;

    @Inject
    public RecipeListViewModel(RecipeRepository repository) {
        App.appComponent().inject(this);
        this.repository = repository;
    }

    public void init() {
        if (recipes != null && !recipes.getValue().isEmpty()) {
            return;
        }
        recipes = repository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return this.recipes;
    }

//    public void offlineData(Context context) {
//        AsyncTask.execute(() -> {
//            repository.offlineData(context);
//        });
//    }
}
