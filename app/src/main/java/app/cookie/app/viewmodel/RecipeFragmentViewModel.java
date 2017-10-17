package app.cookie.app.viewmodel;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import app.cookie.app.data.ApiService;
import app.cookie.app.model.Recipe;
import app.cookie.app.view.RecipeFragmentView;

public class RecipeFragmentViewModel {

    private final Context context;
    private final RecipeFragmentView view;
    private final ApiService apiService;

    public RecipeFragmentViewModel(Context context, RecipeFragmentView view, ApiService apiService) {
        this.context = context;
        this.view = view;
        this.apiService = apiService;
    }

    public void onResume() {
        new FetchRecipesTask(context, view).execute();
    }

    private static class FetchRecipesTask extends AsyncTask<Void, Void, List<Recipe>>{

        // TODO: 10/17/17 Remove the context from the view model
        private final Context context;
        private RecipeFragmentView view;

        private FetchRecipesTask(Context context, RecipeFragmentView view) {
            this.context = context;
            this.view = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showProgressView();
        }

        @Override
        protected List<Recipe> doInBackground(Void... voids) {
            return getRecipesLocally();
        }

        private List<Recipe> getRecipesLocally() {
            List<Recipe> recipeList = new ArrayList<>();

            try {
                InputStream inputStream = context.getAssets().open("recipes_data.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                // TODO: 10/12/17 Check the value returned fromt the input stream
                inputStream.read(buffer);
                inputStream.close();
                String json = new String(buffer, "UTF-8");

                Type typeRecipes = new TypeToken<ArrayList<Recipe>>(){}.getType();
                recipeList = new Gson().fromJson(json, typeRecipes);
            } catch (IOException e) {
                Log.e(this.getClass().getSimpleName(), e.getMessage(), e);
            }

            return recipeList;
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            super.onPostExecute(recipes);

            view.displayRecipes(recipes);
            view.hideProgressView();
        }
    }
}
