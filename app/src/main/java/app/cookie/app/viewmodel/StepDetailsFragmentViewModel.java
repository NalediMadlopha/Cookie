package app.cookie.app.viewmodel;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import app.cookie.app.Utils.Util;
import app.cookie.app.model.Step;
import app.cookie.app.view.StepDetailsFragmentView;

import static app.cookie.app.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app.stringdef.CookieConstants.KEY.STEP_ID;

public class StepDetailsFragmentViewModel {

    private final StepDetailsFragmentView view;
    private final Bundle bundle;
    private final Context context;

    public StepDetailsFragmentViewModel(Context context, StepDetailsFragmentView view, Bundle bundle) {
        this.context = context;
        this.view = view;
        this.bundle = bundle;
    }

    public void onResume() {
        int recipeId = bundle.getInt(RECIPE_ID);
        int stepId = bundle.getInt(STEP_ID);
        new FetchStepDetailsTask(context, view, recipeId, stepId).execute();
    }

    private class FetchStepDetailsTask extends AsyncTask<Void, Void, Step>{

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
        protected Step doInBackground(Void... voids) {
            return Util.getRecipeStep(context, recipeId, stepId);
        }

        @Override
        protected void onPostExecute(Step step) {
            super.onPostExecute(step);
            view.displayStepDetails(step);
            view.hideProgress();
        }
    }
}
