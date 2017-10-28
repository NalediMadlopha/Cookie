package app.cookie.app._new_architecture.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.cookie.app._new_architecture.dependency.App;
import app.cookie.app._new_architecture.model.Recipe;
import app.cookie.app._new_architecture.service.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class RecipeRepository {

    @Inject
    WebService webService;

    public RecipeRepository() {
        App.appComponent().inject(this);
    }

    public LiveData<List<Recipe>> getRecipes() {

        final MutableLiveData<List<Recipe>> data = new MutableLiveData<>();
        webService.getRecipes().enqueue(new Callback<Recipe[]>() {
            @Override
            public void onResponse(Call<Recipe[]> call, Response<Recipe[]> response) {
                Log.i(this.getClass().getSimpleName(), response.body()[0].getName());
                data.setValue(Arrays.asList(response.body()));
                Log.i(this.getClass().getSimpleName(), data.getValue().get(0).getName());
            }

            @Override
            public void onFailure(Call<Recipe[]> call, Throwable t) {
                // I have left out the error case for brevity
            }
        });
        return data;
    }
}
