package app.cookie.app.viewmodel;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.cookie.app.Utils.Util;
import app.cookie.app._new_architecture.model.Recipe;
import app.cookie.app._new_architecture.model.Step;
import app.cookie.app.view.stepdetails.StepDetailsFragmentView;

import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.STEP_ID;

public class StepDetailsFragmentViewModel {

    private final StepDetailsFragmentView view;
    private final Bundle bundle;
    private final Context context;
    private int recipeId;
    private int stepId;

    public StepDetailsFragmentViewModel(Context context, StepDetailsFragmentView view, Bundle bundle) {
        this.context = context;
        this.view = view;
        this.bundle = bundle;
    }

    public void onResume() {
        recipeId = bundle.getInt(RECIPE_ID);
        stepId = bundle.getInt(STEP_ID);
        new FetchStepDetailsTask(context, view, recipeId, stepId).execute();
    }

    public void previousStepButtonClicked() {
        view.updateStepDetails(stepId - 1, recipeId);
    }

    public void nextStepButtonClicked() {
        view.updateStepDetails(stepId + 1, recipeId);
    }

    private class FetchStepDetailsTask extends AsyncTask<Void, Void, List<Step>> {

        private final Context context;
        private final StepDetailsFragmentView view;
        private final int recipeId;
        private final int stepId;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showProgress();
        }

        public FetchStepDetailsTask(Context context, StepDetailsFragmentView view, int recipeId, int stepId) {
            this.context = context;
            this.view = view;
            this.recipeId = recipeId;
            this.stepId = stepId;
        }

        @Override
        protected List<Step> doInBackground(Void... voids) {
            List<Recipe> recipes = Util.getAllRecipes(context);
            List<Step> steps = new ArrayList<>();
            Recipe selectedRecipe = null;

            for (Recipe recipe : recipes) {
                if (recipeId == recipe.getId()) {
                    selectedRecipe = recipe;
                    break;
                }
            }

            for (int i = 0; i < selectedRecipe.getSteps().size(); i++) {
                Step step = selectedRecipe.getSteps().get(i);

                if (stepId == step.getId()) {
                    Step previousStep = i >= 1 ? selectedRecipe.getSteps().get(i - 1) : null;
                    Step nextStep = i <= (selectedRecipe.getSteps().size() - 2) ? selectedRecipe.getSteps().get(i + 1) : null;

                    steps.add(previousStep);
                    steps.add(step);
                    steps.add(nextStep);

                    break;
                }
            }
            return steps;
        }

        @Override
        protected void onPostExecute(List<Step> steps) {
            super.onPostExecute(steps);

            if (steps.get(0) != null) {
                view.displayPreviousStepButton();
            } else {
                view.hidePreviousStepButton();
            }

            if (steps.get(2) != null) {
                view.displayNextStepButton();
            } else {
                view.hideNextStepButton();
            }

            view.displayStepDetails(steps.get(1));
            view.hideProgress();
        }
    }
}
