package app.cookie.app.viewmodel;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import app.cookie.app.Utils.Util;
import app.cookie.app._new_architecture.model.Recipe;
import app.cookie.app.view.recipedetails.RecipeDetailsActivity;
import app.cookie.app.view.recipedetails.RecipeDetailsActivityView;

import static android.content.Context.MODE_PRIVATE;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_NAME;

public class RecipeDetailsActivityViewModel {

    private final RecipeDetailsActivityView view;
    private final Bundle bundle;
    private String recipeName;
    private RecipeDetailsActivity recipeDetailsActivity;

    public RecipeDetailsActivityViewModel(RecipeDetailsActivityView view, Bundle bundle) {
        this.view = view;
        this.recipeDetailsActivity = (RecipeDetailsActivity) view;
        this.bundle = bundle;
    }

    public void onResume(Context context, boolean isTablet) {
        SharedPreferences sharedPreferences;
        int recipeId;

        if (bundle != null) {
            recipeId = bundle.getInt(RECIPE_ID, 0);
            recipeName = bundle.getString(RECIPE_NAME);
            savePreferences(recipeId, recipeName);
        } else {
            sharedPreferences = recipeDetailsActivity.getPreferences(MODE_PRIVATE);
            recipeId = sharedPreferences.getInt(RECIPE_ID, 0);
            recipeName = sharedPreferences.getString(RECIPE_NAME, "");
        }

        new FetchRecipeDetailsTask(context, view, isTablet, recipeId, recipeName).execute();
    }

    public void onCreate() {
        view.displayScreenTitle(recipeName);
    }

    private void savePreferences(int recipeId, String recipeName) {
        SharedPreferences sharedPreferences;
        sharedPreferences = recipeDetailsActivity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(RECIPE_ID, recipeId);
        editor.putString(RECIPE_NAME, recipeName);
        editor.apply();
    }

    private static class FetchRecipeDetailsTask extends AsyncTask<Void, Void, Recipe>{

        private Context context;
        private final RecipeDetailsActivityView view;
        private final boolean isTablet;
        private final int recipeId;
        private String recipeName;

        FetchRecipeDetailsTask(Context context, RecipeDetailsActivityView view, boolean isTablet, int recipeId, String recipeName) {
            this.context = context;
            this.view = view;
            this.isTablet = isTablet;
            this.recipeId = recipeId;
            this.recipeName = recipeName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showProgress();
        }

        @Override
        protected Recipe doInBackground(Void... voids) {
            return Util.getRecipe(context, recipeId);
        }

        @Override
        protected void onPostExecute(Recipe recipe) {
            super.onPostExecute(recipe);
            view.displayIngredients(recipe.getIngredients());
            view.displaySteps(recipeId, recipeName, recipe.getSteps());

            if (isTablet) {
                view.displayStepDetails(recipeId, recipeName, 0);
            }

            view.hideProgress();
        }
    }
}
