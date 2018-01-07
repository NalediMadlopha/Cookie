package com.cookie.app.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cookie.app.dependency.App;
import com.cookie.app.model.Recipe;
import com.cookie.app.repository.RecipeRepository;

import javax.inject.Inject;

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
        return recipe;
    }
}
