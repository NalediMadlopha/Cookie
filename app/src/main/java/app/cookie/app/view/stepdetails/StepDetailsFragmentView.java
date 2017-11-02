package app.cookie.app.view.stepdetails;


import app.cookie.app._new_architecture.model.Step;

public interface StepDetailsFragmentView {

    void showProgress();

    void hideProgress();

    void displayStepDetails(Step step);

    void displayPreviousStepButton();

    void hidePreviousStepButton();

    void displayNextStepButton();

    void hideNextStepButton();

    void updateStepDetails(int stepId, int recipeId);
}
