package com.cookie.app.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.cookie.app.database.RecipeDao;
import com.cookie.app.dataservice.WebService;
import com.cookie.app.dependency.App;
import com.cookie.app.model.Recipe;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class RecipeRepository {

    @Inject
    WebService webService;
    @Inject
    RecipeDao recipeDao;

    public RecipeRepository() {
        App.appComponent().inject(this);
    }

    public LiveData<List<Recipe>> getRecipes() {
        updateRecipes();

        return recipeDao.getRecipesMinimal();
    }

    public LiveData<Recipe> getRecipe(int recipeId) {
        return recipeDao.getRecipe(recipeId);
    }

    private void updateRecipes() {
        final MutableLiveData<List<Recipe>> data = new MutableLiveData<>();
        webService.getRecipes().enqueue(new Callback<Recipe[]>() {
            @Override
            public void onResponse(Call<Recipe[]> call, Response<Recipe[]> response) {
                data.setValue(Arrays.asList(response.body()));
                AsyncTask.execute(() -> recipeDao.insertAll(data.getValue()));
            }

            @Override
            public void onFailure(Call<Recipe[]> call, Throwable t) {
                // I have left out the error case for brevity
            }
        });
    }
}
