package app.cookie.app.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import app.cookie.app._new_architecture.dependency.App;
import app.cookie.app._new_architecture.model.Recipe;
import app.cookie.app._new_architecture.repository.RecipeRepository;

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

    public void previousStepButtonClicked() {
//        view.updateStepDetails(stepId - 1, recipeId);
    }

    public void nextStepButtonClicked() {
//        view.updateStepDetails(stepId + 1, recipeId);
    }

//    private final StepDetailsFragmentView view;
//    private final Bundle bundle;
//    private final Context context;
//    private int recipeId;
//    private int stepId;
//
//    public StepDetailsViewModel(Context context, StepDetailsFragmentView view, Bundle bundle) {
//        this.context = context;
//        this.view = view;
//        this.bundle = bundle;
//    }
//
//    public void onResume() {
//        recipeId = bundle.getInt(RECIPE_ID);
//        stepId = bundle.getInt(STEP_ID);
//        new FetchStepDetailsTask(context, view, recipeId, stepId).execute();
//    }



//    private class FetchStepDetailsTask extends AsyncTask<Void, Void, List<Step>> {
//
//        private final Context context;
//        private final StepDetailsFragmentView view;
//        private final int recipeId;
//        private final int stepId;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            view.showProgress();
//        }
//
//        public FetchStepDetailsTask(Context context, StepDetailsFragmentView view, int recipeId, int stepId) {
//            this.context = context;
//            this.view = view;
//            this.recipeId = recipeId;
//            this.stepId = stepId;
//        }
//
//        @Override
//        protected List<Step> doInBackground(Void... voids) {
//            List<Recipe> recipes = Util.getAllRecipes(context);
//            List<Step> steps = new ArrayList<>();
//            Recipe selectedRecipe = null;
//
//            for (Recipe recipe : recipes) {
//                if (recipeId == recipe.getId()) {
//                    selectedRecipe = recipe;
//                    break;
//                }
//            }
//
//            for (int i = 0; i < selectedRecipe.getSteps().size(); i++) {
//                Step step = selectedRecipe.getSteps().get(i);
//
//                if (stepId == step.getId()) {
//                    Step previousStep = i >= 1 ? selectedRecipe.getSteps().get(i - 1) : null;
//                    Step nextStep = i <= (selectedRecipe.getSteps().size() - 2) ? selectedRecipe.getSteps().get(i + 1) : null;
//
//                    steps.add(previousStep);
//                    steps.add(step);
//                    steps.add(nextStep);
//
//                    break;
//                }
//            }
//            return steps;
//        }
//
//        @Override
//        protected void onPostExecute(List<Step> steps) {
//            super.onPostExecute(steps);
//
//            if (steps.get(0) != null) {
//                view.displayPreviousStepButton();
//            } else {
//                view.hidePreviousStepButton();
//            }
//
//            if (steps.get(2) != null) {
//                view.displayNextStepButton();
//            } else {
//                view.hideNextStepButton();
//            }
//
//            view.displayStepDetails(steps.get(1));
//            view.hideProgress();
//        }
//    }
}
