package app.cookie.app.viewmodel;


import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import app.cookie.app.Utils.Util;
import app.cookie.app.service.WebService;
import app.cookie.app.model.Recipe;
import app.cookie.app.view.recipes.RecipeFragmentView;

public class RecipeFragmentViewModel {

    private final Context context;
    private final RecipeFragmentView view;
    private final WebService webService;

    public RecipeFragmentViewModel(Context context, RecipeFragmentView view, WebService webService) {
        this.context = context;
        this.view = view;
        this.webService = webService;
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
            return Util.getAllRecipes(context);
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            super.onPostExecute(recipes);

            view.displayRecipes(recipes);
            view.hideProgressView();
        }
    }
}
