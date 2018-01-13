package com.cookie.app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cookie.app.dependency.App;
import com.cookie.app.model.Recipe;
import com.cookie.app.repository.RecipeRepository;

import java.util.List;

import javax.inject.Inject;

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
}
