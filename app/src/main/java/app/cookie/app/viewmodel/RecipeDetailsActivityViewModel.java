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

import app.cookie.app.model.Recipe;
import app.cookie.app.view.RecipeDetailsActivityView;

public class RecipeDetailsActivityViewModel {

    private final RecipeDetailsActivityView view;
    private final int recipeId;

    public RecipeDetailsActivityViewModel(RecipeDetailsActivityView view, int recipeId) {
        this.view = view;
        this.recipeId = recipeId;
    }

    public void onResume(Context context) {
        new FetchRecipeDetailsTask(context, view, recipeId).execute();
    }

    public void onCreate(String title) {
        view.displayScreenTitle(title);
    }

    private static class FetchRecipeDetailsTask extends AsyncTask<Void, Void, Recipe>{

        private final RecipeDetailsActivityView view;
        private final int recipeId;
        private Context context;

        FetchRecipeDetailsTask(Context context, RecipeDetailsActivityView view, int recipeId) {
            this.context = context;
            this.view = view;
            this.recipeId = recipeId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showProgress();
        }

        @Override
        protected Recipe doInBackground(Void... voids) {
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

            Recipe selectedRecipe = new Recipe();
            for (Recipe recipe : recipeList) {
                if (recipeId == recipe.getId()) {
                    selectedRecipe = recipe;
                }
            }

            return selectedRecipe;
        }

        @Override
        protected void onPostExecute(Recipe recipe) {
            super.onPostExecute(recipe);
            view.displayIngredients(recipe.getIngredients());
            view.displaySteps(recipe.getSteps());
            view.hideProgress();
        }
    }
}
