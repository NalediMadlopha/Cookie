package app.cookie.app.view;


import app.cookie.app.model.Step;

public interface StepDetailsFragmentView {

    void showProgress();

    void hideProgress();

    void displayStepDetails(Step step);
}
