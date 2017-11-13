package app.cookie.app._new_architecture.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import app.cookie.app._new_architecture.dependency.App;
import app.cookie.app._new_architecture.model.Recipe;
import app.cookie.app._new_architecture.repository.RecipeRepository;

public class RecipeDetailsViewModel extends ViewModel {

    private LiveData<Recipe> recipe;
    private RecipeRepository repository;

    @Inject
    public RecipeDetailsViewModel(RecipeRepository repository) {
        App.appComponent().inject(this);
        this.repository = repository;
    }

    public void init(int recipeId) {
        if (recipe != null) {
            return;
        }
        recipe = repository.getRecipe(recipeId);
    }

    public LiveData<Recipe> getRecipe() {
        return this.recipe;
    }
}
