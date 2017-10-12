package app.cookie.app.viewmodel;


import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import app.cookie.app.model.Recipe;
import app.cookie.app.view.RecipeFragmentView;

public class RecipeFragmentViewModel {

    private final RecipeFragmentView view;

    public RecipeFragmentViewModel(RecipeFragmentView view) {
        this.view = view;
    }

    public void viewCreated() {
        new FetchRecipesTask(view).execute();
    }

    private static class FetchRecipesTask extends AsyncTask<Void, Void, List<Recipe>>{

        private RecipeFragmentView view;

        private FetchRecipesTask(RecipeFragmentView view) {
            this.view = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showProgressView();
        }

        @Override
        protected List<Recipe> doInBackground(Void... voids) {

            List<Recipe> recipeList = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                Recipe recipe = new Recipe();
                recipe.setName("Test");

                recipeList.add(recipe);
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
