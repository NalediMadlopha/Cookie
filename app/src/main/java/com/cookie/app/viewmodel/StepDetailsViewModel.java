package com.cookie.app.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cookie.app.dependency.App;
import com.cookie.app.model.Recipe;
import com.cookie.app.repository.RecipeRepository;

import javax.inject.Inject;

public class StepDetailsViewModel extends ViewModel {

    private LiveData<Recipe> recipe;
    private RecipeRepository repository;
    private int recipeId;
    private int stepId;

    @Inject
    public StepDetailsViewModel(RecipeRepository repository) {
        App.appComponent().inject(this);
        this.repository = repository;
    }

    public void init(int recipeId, int stepId) {
        this.recipeId = recipeId;
        this.stepId = stepId;
        if (recipe != null) {
            return;
        }
        recipe = repository.getRecipe(recipeId);
    }

    public LiveData<Recipe> getRecipe() {
        return recipe;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getStepId() {
        return stepId;
    }
}
